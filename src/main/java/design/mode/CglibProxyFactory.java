package design.mode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/* @description: Dynamic Proxy by Cglib
 * @author: Sherwin Liang
 * @timestamp: 2020/12/6 16:42
*/
public class CglibProxyFactory implements MethodInterceptor {

    private static final Logger logger = LogManager.getLogger(CglibProxyFactory.class);
    //The object be applied
    private Object target;

    public CglibProxyFactory(Object target){
        this.target = target;
    }

    public Object getProxyInstance(){
        Enhancer en = new Enhancer();
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        return en.create();

    }
    //The method of target is executed by this proxy.
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.error("Proxy by cglib start");
        Object returnValue = method.invoke(target, objects);
        logger.error("Proxy by cglib end");
        return returnValue;
    }
}
