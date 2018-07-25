package com.github.chenhaiyangs.client;

import com.github.chenhaiyangs.dto.DemoRequest;
import com.github.chenhaiyangs.dto.DemoResponse;
import com.github.chenhaiyangs.rpc.RemoteClient;
import com.github.chenhaiyangs.rpc.searlizer.JdkSearlizer;
import com.github.chenhaiyangs.service.DemoService;

/**
 * 调用rpc远程服务的
 * @author chenhaiyang
 */
public class RpcClient {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        RemoteClient<DemoService> remoteClient = new RemoteClient<DemoService>("127.0.0.1",22020)
                .bindSearLizer(new JdkSearlizer());

        DemoService demoService = remoteClient.getRemoteProxyObj(DemoService.class);

        for(int i=0;i<100;i++){
            new Thread(()->{
                DemoRequest request = new DemoRequest();
                request.setUserName("javademoRequest程序员");
                request.setAge(18);
                DemoResponse demoResponse = demoService.sayHelloWorld(request);
                System.out.println("远程调用结果："+demoResponse);
            }).start();
        }
    }
}
