import com.alibaba.fastjson.JSONObject;
import design.mode.*;
import org.junit.Test;

public class DesignModeTest {
    @Test
    public void proxyByJava(){
        JSONObject user = new JSONObject();
        user.put("name", "sherwin");
        user.put("age",22);
        UserDao userDao = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy();
        userDaoProxy.setUserDao(userDao);
        userDaoProxy.save(user);//static proxy for userDao
        ProxyFactory proxyFactory = new ProxyFactory(userDao);
        ((IUserDao)proxyFactory.getProxyInstance()).save(user);//Auto proxy based on java reflect
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(userDao);
        ((UserDao)cglibProxyFactory.getProxyInstance()).save(user);
    }
}
