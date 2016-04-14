/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebaweb;

import javafx.scene.control.Button;
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;




/**
 *
 * @author Rapnika
 */
public class PruebaWeb extends Application{
    private WebEngine webengine;
    private static WebView webview;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Application.launch(args);
  }
 
    @Override
  public void start(Stage primaryStage) {
      //primaryStage.setTitle("Teleger");
     
      //WebView myBrowser = new WebView();
      //WebEngine myWebEngine = myBrowser.getEngine();
      //myWebEngine.setJavaScriptEnabled(true);
      
      webview = new WebView();
        webview.setVisible(true);
        webengine = webview.getEngine();
        webengine.setJavaScriptEnabled(true);
      
     
      webengine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> ov, State t, State t1) -> {
          if (t1 == Worker.State.SUCCEEDED) {
              JSObject window;
              window = (JSObject) webengine.executeScript("window");
              window.setMember("app", new JavaApplication());
          }
      });
      
//      JSObject window;
//              window = (JSObject) myWebEngine.executeScript("window");
//              window.setMember("app", new JavaApplication());
      
        try{
            


            File file = new File("C:\\Users\\Rapnika\\Documents\\3_curso\\Distribuida\\TerceraEntrega\\Teleger\\ClientCode\\Web\\usuarios.html");
            System.out.println(file.exists() + " file exitence");
            webengine.load(file.toURI().toURL().toString());
        
        }catch(Exception ex){
            System.err.print("error " + ex.getMessage());
            ex.printStackTrace();
        }
        
        
        StackPane root = new StackPane();

        Scene scene = new Scene(root, 900, 550);

        primaryStage.setTitle("Teleger");
        primaryStage.setScene(scene);
        primaryStage.show();
      
      
      
      //PRUEBA DE STACKOVERFLOW
//      Button btn = new Button();
//        btn.setText("fire JS");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                if (webengine != null) 
//                {
//                    webengine.executeScript("test()");
//                }
//            }
//        });
//
//        publishServices();
//        StackPane root = new StackPane();
////        root.getChildren().add(btn);
//        HBox hh = new HBox();
//        hh.getChildren().add(btn);
//        hh.getChildren().add(webview);
//
//
//        root.getChildren().add(hh);
//
//        Scene scene = new Scene(root, 300, 250);
//
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//  }
//  
//    
//    
//    private void publishServices() {
//
//
//
//        try {
//            webview = new WebView();
//            webview.setVisible(true);
//            webengine = webview.getEngine();
//            webengine.setJavaScriptEnabled(true);
//            File file = new File("C:\\Users\\Rapnika\\Documents\\3_curso\\Distribuida\\TerceraEntrega\\Teleger\\ClientCode\\Web\\hello.html");
//            System.out.println(file.exists() + " file exitence");
//            webengine.load(file.toURI().toURL().toString());
//        } catch (Exception ex) {
//            System.err.print("error " + ex.getMessage());
//            ex.printStackTrace();
//        }
//
//
//
//
//    }
  }
}
