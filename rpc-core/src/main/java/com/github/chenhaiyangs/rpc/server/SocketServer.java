package com.github.chenhaiyangs.rpc.server;

import com.github.chenhaiyangs.rpc.IoExecustor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 配置socket连接服务
 * @author chenhaiyang
 */
public class SocketServer {

    private ServerSocket server;
    private IoExecustor excustor;

    public SocketServer(int port,IoExecustor excustor) throws IOException {

        this.server = new ServerSocket();
        this.excustor=excustor;
        server.bind(new InetSocketAddress(port));
        System.out.println("server is starting......");
    }

    /**
     * 启动服务
     */
    public void  start(){
        try {
            excustor.excuse(server);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
