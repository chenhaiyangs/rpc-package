package com.github.chenhaiyangs.rpc;

import com.github.chenhaiyangs.rpc.searlizer.JdkSearlizer;
import com.github.chenhaiyangs.rpc.searlizer.bean.ProtocolBean;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 远程服务调用代理客户端
 * @author chenhaiyang
 */
public class RemoteClient<T> {

    private InetSocketAddress address;
    private Searlization searlization = new JdkSearlizer();

    public RemoteClient(String ip,int port){
        address= new InetSocketAddress(ip,port);
    }
    public RemoteClient bindSearLizer(Searlization searlization){
        this.searlization=searlization;
        return this;
    }

    @SuppressWarnings("unchecked")
    public T getRemoteProxyObj(final Class<?> serviceInterface){
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                (proxy,method,args)->{
                    Socket socket=null;
                    OutputStream out=null;
                    InputStream in=null;
                    try{
                        socket = new Socket();
                        socket.connect(address);

                        out = socket.getOutputStream();
                        in = socket.getInputStream();

                        ProtocolBean protocolBean = new ProtocolBean()
                                .withServiceName(serviceInterface.getName())
                                .withMethodName(method.getName())
                                .withTypes(method.getParameterTypes())
                                .withParameters(args);

                        searlization.searlize(out,protocolBean);
                        return searlization.read(in);
                    }catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }finally {
                        if(out!=null){
                            out.close();
                        }
                        if(in!=null){
                            in.close();
                        }
                        if(socket!=null){
                            socket.close();
                        }
                    }
                }
        );
    }
}
