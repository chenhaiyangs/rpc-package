package com.github.chenhaiyangs.rpc.searlizer;

import com.github.chenhaiyangs.rpc.Searlization;
import java.io.*;

/**
 * 使用默认的jdk序列化方式
 * @author chenhaiyang
 */
public class JdkSearlizer implements Searlization {

    @Override
    public Object read(InputStream input) throws Exception {
        ObjectInputStream objInput = new ObjectInputStream(input);
       return objInput.readObject();

    }

    @Override
    public void searlize(OutputStream output,Object result) throws Exception {
        ObjectOutputStream objOutput = new ObjectOutputStream(output);
        objOutput.writeObject(result);
    }
}
