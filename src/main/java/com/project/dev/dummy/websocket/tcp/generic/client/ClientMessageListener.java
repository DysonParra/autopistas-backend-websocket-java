/*
 * @fileoverview    {ClientMessageListener}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev.dummy.websocket.tcp.generic.client;

import java.net.Socket;

/**
 * TODO: Description of {@code ClientMessageListener}.
 *
 * @author Dyson Parra
 * @since 11
 */
public interface ClientMessageListener {

    /**
     * TODO: Description of {@code onMessage}.
     *
     * @param client
     * @param sentMessage
     * @param server
     */
    public abstract void onMessage(GenericClient client, Socket server, String sentMessage);

    /**
     * TODO: Description of {@code onResponse}.
     *
     * @param client
     * @param response
     * @param server
     */
    public abstract void onResponse(GenericClient client, Socket server, String response);
}
