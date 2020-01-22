package com.javier.componente;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class BotonCodigoMorse extends Button {

    private ArrayList<EnLetraDetectada> enLetraDetectada;
    private IntegerProperty duracionCorta = new SimpleIntegerProperty();
    private IntegerProperty duracionLarga = new SimpleIntegerProperty();
    private long pulsar;
    private long soltar;
    private int contadorClick = 0;

    public BotonCodigoMorse() {
        enLetraDetectada = new ArrayList<EnLetraDetectada>();
    }

    public void addEnLetraDetectada(EnLetraDetectada enLetraDetectada) {
        this.enLetraDetectada.add(enLetraDetectada);
    }

    public int getDuracionCorta() {
        return duracionCorta.get();
    }

    public IntegerProperty duracionCortaProperty() {
        return duracionCorta;
    }

    public void setDuracionCorta(int duracionCorta) {
        this.duracionCorta.set(duracionCorta);
    }

    public int getDuracionLarga() {
        return duracionLarga.get();
    }

    public IntegerProperty duracionLargaProperty() {
        return duracionLarga;
    }

    public void setDuracionLarga(int duracionLarga) {
        this.duracionLarga.set(duracionLarga);
    }

    public long getPulsar() {
        return pulsar;
    }

    public void setPulsar(long pulsar) {
        this.pulsar = pulsar;
    }

    public long getSoltar() {
        return soltar;
    }

    public void setSoltar(long soltar) {
        this.soltar = soltar;
    }

    public int getContadorClick() {
        return contadorClick;
    }

    public void setContadorClick(int contadorClick) {
        this.contadorClick = contadorClick;
    }

    public void iniciar() {
        Label label = new Label();
        onMousePressedProperty().addListener(new ChangeListener<EventHandler<? super MouseEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue, EventHandler<? super MouseEvent> eventHandler, EventHandler<? super MouseEvent> t1) {
                pulsar = System.currentTimeMillis();
            }
        });
        onMouseReleasedProperty().addListener(new ChangeListener<EventHandler<? super MouseEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<? super MouseEvent>> observableValue, EventHandler<? super MouseEvent> eventHandler, EventHandler<? super MouseEvent> t1) {
                soltar = System.currentTimeMillis();
            }
        });

        contadorClick++;

        long tiempoPulsado = soltar - pulsar;

        if (contadorClick==2){
            contadorClick=0;
            if (duracionCorta.get() >= tiempoPulsado){
                label.setText("A");
            }
        }
        /*if (duracionCorta.get() >= tiempoPulsado) {
            corto++;
        }

        if (tiempoPulsado > duracionCorta.get() && tiempoPulsado <= duracionLarga.get()) {
            largo++;
        }*/

    }
}
