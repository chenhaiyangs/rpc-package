package com.github.chenhaiyangs.Impl;

import com.github.chenhaiyangs.dto.DemoRequest;
import com.github.chenhaiyangs.dto.DemoResponse;
import com.github.chenhaiyangs.service.DemoService;

/**
 * DemoService的实现类，用来模拟远程调用
 * @author chenhaiyang
 */
public class DemoServiceImpl implements DemoService{
    @Override
    public DemoResponse sayHelloWorld(DemoRequest demoRequest) {

        String msg = String.format("hello:[%s],your age:[%d]",demoRequest.getUserName(),demoRequest.getAge());
        DemoResponse response = new DemoResponse();
        response.setResult(msg);
        System.out.println("我是服务端，我得到了调用"+demoRequest.toString());
        return response;
    }
}
