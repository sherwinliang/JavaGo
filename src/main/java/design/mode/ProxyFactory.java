package design.mode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyFactory {
    
    private static final Logger logger = LogManager.getLogger(ProxyFactory.class);

    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        Class clazz = target.getClass();
        return java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        logger.error("Proxy by java start");
                        Object returnValue = method.invoke(target, args);
                        logger.error("Proxy by java end");
                        return returnValue;
                    }
                });
    }
}
