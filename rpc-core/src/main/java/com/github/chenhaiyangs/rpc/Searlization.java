package com.github.chenhaiyangs.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 序列化与反序列化抽象
 * @author chenhaiyang
 */
public interface Searlization {

    /**
     * 反序列化
     * @param input input输入流
     * @return 对象
     * @throws Exception exception
     */
    Object read(InputStream input) throws Exception;

    /**
     * 序列化
     * @param stream 输出流
     * @param t 参数
     * @throws Exception exception
     */
    void searlize(OutputStream stream,Object t) throws Exception;

}
