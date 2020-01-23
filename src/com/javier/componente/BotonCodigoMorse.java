package com.javier.componente;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class BotonCodigoMorse extends Button {

    private ArrayList<EnLetraDetectada> enLetraDetectada;
    private IntegerProperty duracionCorta = new SimpleIntegerProperty();
    private IntegerProperty duracionLarga = new SimpleIntegerProperty();
    private String letraDetectada;
    private long pulsar;
    private long soltar;
    private char duracion = ' ';
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

    public String getLetraDetectada() {
        return letraDetectada;
    }

    public void setLetraDetectada(String letraDetectada) {
        this.letraDetectada = letraDetectada;
    }

    public char getDuracion() {
        return duracion;
    }

    public void setDuracion(char duracion) {
        this.duracion = duracion;
    }

    public void iniciar() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pulsar = System.currentTimeMillis();
                contadorClick++;
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                soltar = System.currentTimeMillis();

                long tiempoPulsado = soltar - pulsar;

                System.out.println(tiempoPulsado);

                if (tiempoPulsado <= duracionCorta.get()) {
                    if (duracion == ' ') {
                        duracion = 'c';
                    } else {
                        if (duracion == 'c') {
                            setLetraDetectada("I");
                            duracion = ' ';
                        } else {
                            if (duracion == 'l') {
                                setLetraDetectada("N");
                                duracion = ' ';
                            }
                        }

                    }
                } else {
                    if (tiempoPulsado > duracionCorta.get() && tiempoPulsado <= duracionLarga.get()) {
                        if (duracion == ' ') {
                            duracion = 'l';
                        } else {
                            if (duracion == 'l') {
                                setLetraDetectada("M");
                                duracion = ' ';
                            } else {
                                if (duracion == 'c') {
                                    setLetraDetectada("A");
                                    duracion = ' ';
                                }
                            }
                        }

                    }


                }

                if (contadorClick==2) {
                    contadorClick=0;
                    for (EnLetraDetectada e : enLetraDetectada) {
                        e.ejecuta(letraDetectada);
                    }
                }
            }
        });
    }
}
