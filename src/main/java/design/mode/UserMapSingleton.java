package design.mode;

import common.User;

import java.util.HashMap;
import java.util.Map;
/* @description: Singleton mode
 * @author: Sherwin Liang
 * @timestamp: 2020/12/6 16:47
*/
public class UserMapSingleton {

    private static Map<String, User> userMap = null;

    public static void init(){
        if(userMap == null){
            userMap = new HashMap<>();
        }else{
            userMap.clear();
        }
    }

    public static User getUserById(String id){
        return userMap.get(id);
    }

    public static boolean putUser(User user){
        userMap.put(user.getId(), user);
        return true;
    }

    public static boolean removeUserById(String id){
        userMap.remove(id);
        return true;
    }

    public static User getUserByName(String name){
        User user = null;
        for(Map.Entry<String, User> entry : userMap.entrySet()){
            if(entry.getValue().getName().equals(name)){
                user = entry.getValue();
                break;
            }
        }
        return user;
    }
}
