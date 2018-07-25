package com.github.chenhaiyangs.rpc.regist;

import com.github.chenhaiyangs.rpc.RegistCenter;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心的一个实现：本地map注册中心
 * @author chenhaiyang
 */
public class LocalRegistCenter implements RegistCenter{

    private Map<String,Object> register = new ConcurrentHashMap<>();

    @Override
    public Object invoke(String serviceName, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Exception {
        Object target = register.get(serviceName);
        Class<?> clazz = target.getClass();
        Method method = clazz.getMethod(methodName, parameterTypes);
        return method.invoke(target, arguments);
    }

    @Override
    public void regist(String serviceName, Object impl) {
        register.put(serviceName,impl);
    }
}
