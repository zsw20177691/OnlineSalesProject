package AssertTest;

import com.Important.WebApplication;
import com.Important.dto.LoginUserDto;
import com.Important.service.UserService;
import com.Important.utils.JwtUserLogin;
import com.Important.utils.SendMessageUtil;
import com.Important.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;


@Slf4j
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class AssertTest  {

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








}
