package com.github.chenhaiyangs.rpc;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * IO模型抽象接口
 * @author chenhaiyang
 */
public interface IoExecustor {
    /**
     * 使用特定的IO模型处理socket请求
     * @param serverSocket socketService
     * @throws IOException IOException
     */
    void excuse(ServerSocket serverSocket) throws IOException;
}
