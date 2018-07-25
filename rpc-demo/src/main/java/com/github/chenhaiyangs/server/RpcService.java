package com.github.chenhaiyangs.server;


import com.github.chenhaiyangs.Impl.DemoServiceImpl;
import com.github.chenhaiyangs.rpc.RemoteServer;
import com.github.chenhaiyangs.rpc.regist.LocalRegistCenter;
import com.github.chenhaiyangs.rpc.searlizer.JdkSearlizer;
import com.github.chenhaiyangs.service.DemoService;

import java.io.IOException;

/**
 * rpc 服务的发布
 * @author chenhaiyang
 */
public class RpcService {

    public static void main(String[] args) throws IOException {

        RemoteServer remoteServer = new RemoteServer(22020)
                .bindSearlization(new JdkSearlizer())
                .bindRegistCenter(new LocalRegistCenter())
                .setThreadNum(200)
                .build();

        DemoService service = new DemoServiceImpl();
        remoteServer.registService(DemoService.class.getName(),service);

        remoteServer.start();
    }

}
