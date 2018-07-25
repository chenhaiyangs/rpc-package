package com.github.chenhaiyangs.rpc;

import com.github.chenhaiyangs.rpc.io.BioExecustor;
import com.github.chenhaiyangs.rpc.regist.LocalRegistCenter;
import com.github.chenhaiyangs.rpc.searlizer.JdkSearlizer;
import com.github.chenhaiyangs.rpc.server.SocketServer;

import java.io.IOException;

/**
 * 远程服务的Server
 * @author chenhaiyang
 */
public class RemoteServer {

    private Searlization searlization = new JdkSearlizer();
    private RegistCenter registCenter= new LocalRegistCenter();
    private IoExecustor execustor;
    private SocketServer socketServer;
    private int port;
    private int threadNum;

    public RemoteServer(int port){
        this.port=port;
    }

    public RemoteServer bindSearlization(Searlization searlization){
        this.searlization=searlization;
        return this;
    }

    public RemoteServer bindRegistCenter(RegistCenter registCenter){
        this.registCenter=registCenter;
        return this;
    }
    public RemoteServer setThreadNum(int threadNum){
        this.threadNum=threadNum;
        return this;
    }
    public RemoteServer bindIoExecustor(IoExecustor execustor){
        this.execustor=execustor;
        return this;
    }
    public RemoteServer build() throws IOException {
        if(execustor==null){
            execustor = new BioExecustor(threadNum,searlization,registCenter);
        }
        socketServer = new SocketServer(port,execustor);
        return this;
    }
    public void start() throws IOException {
        socketServer.start();
    }

    public void registService(String serviceName,Object impl){
        registCenter.regist(serviceName,impl);
    }

}
