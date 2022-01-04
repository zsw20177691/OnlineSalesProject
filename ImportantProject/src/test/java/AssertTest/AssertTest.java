package AssertTest;

import com.Important.WebApplication;
import com.Important.dto.LoginUserDto;
import com.Important.service.UserService;
import com.Important.utils.JwtUserLogin;
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


@Slf4j
@SpringBootTest(classes = WebApplication.class)
@RunWith(SpringRunner.class)
public class AssertTest  {


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




}
