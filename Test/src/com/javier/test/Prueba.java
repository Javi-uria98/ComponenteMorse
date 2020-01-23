package com.javier.test;

import com.javier.componente.BotonCodigoMorse;
import com.javier.componente.EnLetraDetectada;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Prueba extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox=new VBox();
        BotonCodigoMorse bcm=new BotonCodigoMorse();
        bcm.setDuracionCorta(200);
        bcm.setDuracionLarga(2000);
        bcm.addEnLetraDetectada(new EnLetraDetectada() {
            @Override
            public void ejecuta(String letra) {
                System.out.println("Se ha generado la letra "+letra);
            }
        });

        bcm.addEnLetraDetectada(new EnLetraDetectada() {
            @Override
            public void ejecuta(String letra) {
                System.out.println("Repito, se ha generado la letra "+letra);
            }
        });

        vBox.getChildren().add(bcm);
        Scene scene=new Scene(vBox);
        stage.setScene(scene);
        stage.show();
        bcm.iniciar();
    }

    public static void main (String[] args){
        launch(args);
    }
}
