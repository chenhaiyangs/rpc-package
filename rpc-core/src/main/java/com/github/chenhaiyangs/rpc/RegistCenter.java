package com.github.chenhaiyangs.rpc;

/**
 * 注册中心:存储服务元信息
 * @author chenhaiyang
 */
public interface RegistCenter {

    /**
     * 执行方法调用
     * @param serviceName 类型
     * @param method 方法
     * @param parameterTypes 参数调用
     * @param arguments 参数
     * @return 返回执行结果
     * @throws Exception exception
     */
    Object invoke(String serviceName, String method,Class<?>[] parameterTypes,Object[] arguments) throws Exception;

    /**
     * 注册结果实现
     * @param serviceName 类元信息
     * @param impl 实现类
     */
    void regist(String serviceName,Object impl);
}
