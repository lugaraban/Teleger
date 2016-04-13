/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweb;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;




/**
 *
 * @author Rapnika
 */
public class PruebaWeb extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
  }
 
    @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("Teleger");
     
      WebView myBrowser = new WebView();
      WebEngine myWebEngine = myBrowser.getEngine();
      myWebEngine.load("file:///C:/Users/Rapnika/Documents/3_curso/Distribuida/TerceraEntrega/usuarios.html");
     
      myWebEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State t, State t1) -> {
          if (t1 == Worker.State.SUCCEEDED) {
              JSObject window;
              window = (JSObject) myWebEngine.executeScript("window");
              window.setMember("app", new JavaApplication());
          }
      });
      
      StackPane root = new StackPane();
      root.getChildren().add(myBrowser);
      
      primaryStage.setScene(new Scene(root, 900, 500));
      primaryStage.show();
      
      
  }
}
