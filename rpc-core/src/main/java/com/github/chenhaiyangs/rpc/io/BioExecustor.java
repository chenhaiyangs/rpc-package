package com.github.chenhaiyangs.rpc.io;

import com.github.chenhaiyangs.rpc.IoExecustor;
import com.github.chenhaiyangs.rpc.RegistCenter;
import com.github.chenhaiyangs.rpc.Searlization;
import com.github.chenhaiyangs.rpc.searlizer.bean.ProtocolBean;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 采用BIO模型处理请求。
 * 即一个线程处理一个请求
 * @author chenhaiyang
 */
public class BioExecustor implements IoExecustor {

    private ExecutorService executor;
    private Searlization searlization;
    private RegistCenter registCenter;


    public BioExecustor(int threadNum,Searlization searlization,RegistCenter registCenter){
        super();
        executor= new ThreadPoolExecutor(threadNum, threadNum, 0L,
                        TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                        (ThreadFactory) Thread::new);
        this.searlization=searlization;
        this.registCenter=registCenter;
    }

    /**
     * 使用BIO模型处理请求
     * @param serverSocket socket服务
     * @throws IOException IOException
     */
    @Override
    public void excuse(ServerSocket serverSocket) throws IOException {
        while(true) {
            // 1.监听客户端的TCP连接，接到TCP连接后将其封装成task，由线程池执行
            executor.execute(new ServiceTask(serverSocket.accept()));
        }
    }

    private class ServiceTask implements Runnable{
        private Socket socket;
        private ServiceTask(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run() {
            try (InputStream input=socket.getInputStream();
                OutputStream output = socket.getOutputStream()){

                ProtocolBean protocolBean = (ProtocolBean) searlization.read(input);
                Object result = registCenter.invoke(protocolBean.getServiceName(),
                        protocolBean.getMethodName(),
                        protocolBean.getParameterTypes(),
                        protocolBean.getParameters());
                searlization.searlize(output,result);

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
