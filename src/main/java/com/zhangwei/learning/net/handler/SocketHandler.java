package com.zhangwei.learning.net.handler;

import java.net.Socket;

/**
 * Created by design on 15-10-15.
 */
public interface SocketHandler {

    void handle(Socket socket);
}
