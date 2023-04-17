
package ferreteria.Vista;

import static ferreteria.Util.calcularMD5;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login extends Application {
  
  private final String[][] users = {{"JW","856fc81623da2150ba2210ba1b51d241","true"},{"user","ee11cbb19052e40b07aac0ca060c23ee","false"}};
  private Stage stage;

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    this.stage.getIcons().add(new Image("file:resources/images/JWv2.png",120,120,true,true));
    this.stage.setResizable(false);
    this.stage.setScene(login());
    this.stage.getScene().getStylesheets().add("DarkTheme.css");
    this.stage.centerOnScreen();
    this.stage.show();
  }
  

  public Scene login() {    
    GridPane grid = new GridPane();
    grid.getStyleClass().add("background");
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    
    stage.setTitle("Ferreteria JW - Login - Renovación salón de asambleas Ajalvir");
    Label usuario = new Label("Usuario:");
    grid.add(usuario, 0, 0);
    
    

    TextField userTF = new TextField();
    grid.add(userTF, 1, 0);

    Label pw = new Label("Contrase\u00F1a:");
    grid.add(pw, 0, 1);

    PasswordField pwPF = new PasswordField();
    grid.add(pwPF, 1, 1);
    
    Button btn = new Button("Iniciar sesi\u00F3n");
    grid.add(btn, 1, 2);
    
    Text txt = new Text("Que tengais un buen d\u00EDa de trabajo (Proverbios 14:23)");
    txt.setFill(Color.DARKTURQUOISE);
    grid.add(txt, 1, 3);
    
    btn.setOnAction((ActionEvent) -> {
      if(!"".equals(userTF.getText())) {
        if(!"".equals(pwPF.getText())) {
          for (String[] user : users) {
            if(user[0].equals(userTF.getText())) {
              if(user[1].equals(calcularMD5(pwPF.getText()))) {
                txt.setText("Cargando...");
                stage.setUserData(user[2]);
                Platform.runLater(() -> {
                  new MenuPrincipal().start(stage);
                });
                stage.close();
                return;
              }
            }
          }
          userTF.clear();
          pwPF.clear();
          txt.setText("Usuaio/Contrase\u00F1a invalido");
        } else {
          txt.setText("Introduce una contrase\u00F1a");
        }
      } else {
        txt.setText("Introduce un usuario");
      }
      txt.setFill(Color.FIREBRICK);
    });
    
    return new Scene(grid, 900, 525);
  }
}
