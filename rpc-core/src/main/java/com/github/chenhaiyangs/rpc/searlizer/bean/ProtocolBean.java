package com.github.chenhaiyangs.rpc.searlizer.bean;

import com.github.chenhaiyangs.rpc.Searlization;

import java.io.Serializable;

/**
 * 协议的具体内容。有先后顺序
 * @author chenhaiyang
 */
public class ProtocolBean implements Serializable {

    /**
     * 服务名
     */
    private String serviceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 参数列表
     */
    private Object[] parameters;

    public ProtocolBean withServiceName(String serviceName){
        this.serviceName=serviceName;
        return this;
    }
    public ProtocolBean withMethodName(String methodName){
        this.methodName=methodName;
        return this;
    }
    public ProtocolBean withTypes(Class<?>[] parameterTypes){
        this.parameterTypes=parameterTypes;
        return this;
    }
    public ProtocolBean withParameters(Object[] parameters){
        this.parameters=parameters;
        return this;
    }


    public String getServiceName() {
        return serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
