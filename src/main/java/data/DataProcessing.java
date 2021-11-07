package data;

import common.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProcessing {

    /*
     * 使用stream类创建集合
    */
    private static List<User> userList = Stream.of(new User("1","Sherwin","10.13.113.0"),
            new User("2","Allen","10.13.110.1"),
            new User("3","Sky","10.12.113.3"),
            new User("4","Tracy","10.13.113.1"),
            new User("5","Alice","10.13.103.204"),
            new User("6","William","10.13.112.10")).collect(Collectors.toList());
    /* @description: 过滤出集合中想要的数据，例如过滤出ipAddress以condition开头的数据，然后转为map存储
     * @author: Sherwin Liang
     * @timestamp: 2021/11/5 23:10
     * @param: condition 过滤条件
     * @return: Map<String, User> 过滤后的user Map
    */
    public static Map<String, User> filterUser(final String condition){
        return userList.stream().filter(u->u.getIpAddress().startsWith(condition))
                .collect(Collectors.toMap(User::getIpAddress,u->u));
    }
    /* @description: 查询文件中某个字符出现的次数
     * @author: Sherwin Liang
     * @timestamp: 2021/11/6 11:46
     * @param: filePath 文件路径
     * @param: character 查找的字符
     * @return: long 出现次数
    */
    public static long countCharacterInFile(final String filePath, final Character character){
        final AtomicInteger atomicInteger = new AtomicInteger();
        try(Stream<String> lines = Files.lines(Paths.get(filePath))){
            lines.map(l->l.toCharArray()).forEach(chars -> {
                for(char c:chars){
                    if(c==character)
                        atomicInteger.getAndIncrement();
                }
            });

        }catch(IOException e){
            e.printStackTrace();
            atomicInteger.set(0);
        }
        return atomicInteger.intValue();
    }
    /* @description: 排序
     * @author: Sherwin Liang
     * @timestamp: 2021/11/6 12:09
     * @param: originalList 原列表
     * @param: comparator 比较器
     * @return: List<User> 排序后的列表
    */
    public static List<User> sortUserList(List<User> originalList,Comparator comparator){
        List<User> list = (List<User>) originalList.stream().sorted(comparator)
                .collect(Collectors.toList());
        return list;
    }

    public static void main(String... args){
        filterUser("10.13.113").forEach((k,v)->System.out.println(k));
        System.out.println(countCharacterInFile("D:\\IdeaProjects\\JavaGo\\src\\main\\resources\\test.txt",'a'));
        sortUserList(userList, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return u1.getName().length()>u2.getName().length()?1:-1;
            }
        }).forEach(u->System.out.println(u.getName()));
    }
}
