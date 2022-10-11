/*
 * @fileoverview {GenericSerialClient} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {GenericSerialClient} fue realizada el 31/07/2022.
 * @Dev - La primera version de {GenericSerialClient} fue escrita por Dyson A. Parra T.
 */
package com.rtc.dummy.serial.generic.client;

import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: Definición de {@code GenericSerialClient}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
//@AllArgsConstructor
//@Builder
@Data
//@NoArgsConstructor
public class GenericSerialClient implements Runnable {

    protected String name;
    @Setter(AccessLevel.NONE)
    protected SerialPort serialPort;
    protected String portName;
    protected int baudRate;
    protected int dataBits;
    protected int stopBits;
    protected int parity;
    @Setter(AccessLevel.NONE)
    protected boolean start = false;
    @Setter(AccessLevel.NONE)
    protected boolean killed = false;
    @Setter(AccessLevel.NONE)
    protected String lastMessage = "";
    protected GenericSerialMessageListener onMessageListener;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected final Object synchronizer = new Object();

    /**
     * TODO: Definición de {@code GenericSerialClient}.
     *
     * @param portName
     * @param baudRate
     * @param dataBits
     * @param stopBits
     * @param parity
     */
    public GenericSerialClient(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        this.serialPort = new SerialPort(portName) {
        };
    }

    /**
     * TODO: Definición de {@code setSerialPort}.
     *
     * @param serialPort
     */
    public void setSerialPort(SerialPort serialPort) {
        if (start)
            stop();
        this.serialPort = serialPort;
    }

    /**
     * TODO: Definición de {@code initialize}.
     *
     * @return
     */
    public boolean initialize() {
        boolean result = false;
        if (serialPort != null && !start) {
            try {
                serialPort.openPort();
                serialPort.setParams(baudRate, dataBits, stopBits, parity);
                start = true;
                result = true;
                synchronized (synchronizer) {
                    synchronizer.notifyAll();
                }
                System.out.println("Started Listener of '" + name + "'.");
            } catch (SerialPortException e) {
                e.printStackTrace(System.out);
            }
        }
        return result;
    }

    /**
     * TODO: Definición de {@code init}.
     *
     */
    public void init() {
        new Thread(this::initialize).start();
    }

    /**
     * TODO: Definición de {@code stop}.
     *
     * @return
     */
    public boolean stop() {
        boolean result = false;
        if (start) {
            try {
                serialPort.closePort();
                start = false;
                result = true;
                System.out.println("Stopped Listener of '" + name + "'.");
            } catch (SerialPortException e) {
                e.printStackTrace(System.out);
            }
        }
        return result;
    }

    /**
     * TODO: Definición de {@code run}.
     *
     */
    @Override
    public void run() {
        initialize();
        while (!killed) {
            String aux;
            while (start) {
                //System.out.println("Reading...");
                aux = "";
                lastMessage = "";
                while (!"\n".equals(aux)) {
                    try {
                        aux = serialPort.readString(1);
                        lastMessage = lastMessage.concat(aux);
                        //System.out.print("<" + aux + ">");
                    } catch (Exception e) {
                        System.out.println("Error reading character from serial port.");
                    }
                }
                //System.out.println("");
                //System.out.println("'" + lastMessage + "'");
                if (lastMessage.length() > 2 && onMessageListener != null) {
                    onMessageListener.onResponse(lastMessage);
                }
            }
            try {
                synchronized (synchronizer) {
                    synchronizer.wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

}
