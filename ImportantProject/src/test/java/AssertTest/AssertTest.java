package AssertTest;

import com.Important.WebApplication;
import com.Important.dto.LoginUserDto;
import com.Important.entity.User;
import com.Important.mapper.SupplierEntityMapper;
import com.Important.service.AdminService;
import com.Important.service.SupplierService;
import com.Important.service.UserService;
import com.Important.service.serviceImpl.SupplierServiceImpl;
import com.Important.utils.JwtUserLogin;
import com.Important.utils.SendMessageUtil;
import com.Important.utils.TimeFormatUtil;
import com.Important.utils.testVserionLock;
import com.Important.vo.UserVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class AssertTest   {

     static Integer i=1;
    public static String getRandomCode(Integer code ){
        Random random = new Random();
        String result="";
        for (int i=0;i<code;i++){
            result+=random.nextInt(10);
        }
        return result;
    }

    @Autowired
    private UserService userService;
    @Resource
    private JwtUserLogin jwtUserLogin;

    @Autowired
    private SupplierService supplierService;

    @Resource
    private AdminService adminService;

    @Resource
    private testVserionLock testVserionLocak;

    @Autowired
    private RedisTemplate   redisTemplate;

    @Test
    public void testss(){
        UserVo login = userService.login("string", "string");
        System.out.println(login);
        String token = login.getToken();
        System.out.println("++++++++++");
        System.out.println(jwtUserLogin.getUserName(token));
        System.out.println("++++++++++");
    }


    /**
     * 测试短信验证码发送
     */
    @Test
    public void smsTest(){
        /**
         * @author：lvfang
         * @mathName： testSendMessage
         * @parameter： 无
         * @return value：
         * @throws null
         * @date 2018/8/11
         * @desc SMS短信测试
         */

//        SendMessageUtil.send("SMS账户","接口秘钥","目标号码","发送内容");
        Integer lvfaxxxxxx = SendMessageUtil.send("你就是下一个大佬", "d41d8cd98f00b204e980", "18173425291", "验证码:" + getRandomCode(6));
        System.out.println(SendMessageUtil.getMessage(lvfaxxxxxx));
    }

    @Test
    public void tetDateTime(){
        LocalDateTime localDateTime = TimeFormatUtil.nowDateTime();
        System.out.println(localDateTime);
    }

    @Test
    public void adminService(){
        adminService.querySupplierData("","",1,10,0);
    }


    /**
    * 测试jedis连接
    */
    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
        /**
         * 常用api
         */

    }

    @Test
    public void testredis(){
        User chen = new User().builder()
                .username("chen")
                .build();
        try{
            String string = new ObjectMapper().writeValueAsString(chen);
        }catch (Exception e ){
            System.out.println(e);
        }
        redisTemplate.opsForValue().set("user",chen);
    System.out.println(redisTemplate.opsForValue().get("user"));
    }

    /**
     *统计字符出现次数
     */
    @Test
    public void charCount(){
        String str = new String("abcdefsaaaaa");
        System.out.println(str.length());
        String[] split = str.split("");
        System.out.println(Arrays.toString(split));
        for (int i=0;i<str.length();i++){
            int count=0;
            for (int t=0;t<str.length();t++){
                if (split[i].equals(split[t])){
                    count+=1;
                }
            }
            System.out.println("字符"+split[i]+"出现的次数为："+count);
        }
    }
    @Test
    public   void    maxList(){
        String s = new String();
        s="123456,123467,999999,888888";
        String[] split = s.split(",");
        if (split.length>25){
            System.out.println("小组最多二十五人");
        }
        List<String> lists = Arrays.asList(split);
        List<Integer> integerArrays = new ArrayList<Integer>();
        for (int i=0;i<split.length;i++){
            integerArrays.add(Integer.valueOf(split[i]));
        }
        integerArrays.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.valueOf(o2.toString())-Integer.valueOf(o1.toString());
            }
        });
        String string = new String();
        for (Integer integer:integerArrays){
            string=string+integer+"";
        }
        System.out.println(string);
    }

    @Test
    public void testabc(){
        String adx = new String("Adx");
        String ber = new String("Der");
        String der = new String("Ber");
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list.add(adx);list.add(der);list.add(ber);
        for (String s   :list){
            String s1 = s.toLowerCase();
            list1.add(s1);
        }
        String[] arrays=new String[3];
        arrays[0]="a";
        arrays[1]="b";
        arrays[2]="d";
        List<Integer>   integers=new ArrayList<Integer>();
        String[] array2=new String[3];
        for (String s:list1){
            String substring = s.substring(0, 1);
            for ( int i=0;i<arrays.length;i++){
                if (substring.equals(arrays[i])){
                    integers.add(i);
                }
            }
        }
        for (int i=0;i<arrays.length;i++){
            array2[i]=list1.get(integers.get(i));
        }
        integers.forEach(System.out::println);
        System.out.println(Arrays.toString(array2));
    }



@Test
    public void oneToOnehunder(){

    Thread thread =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                  for (;i<100;){
                      if (i%2!=0){
                          System.out.println("线程111输出："+i);
                          i++;
                      }
                  }
              }
            });
    Thread thread1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                  for (;i<=100;){
                      if (i%2==0){
                          System.out.println("线程222输出："+i);
                          i++;
                      }

                  }
              }
            });
        thread.start();
        thread1.start();
}







}
