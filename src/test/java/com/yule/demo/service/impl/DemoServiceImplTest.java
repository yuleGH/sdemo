package com.yule.demo.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yule.demo.service.DemoService;
import com.yule.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Type;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-mvc.xml"})
//@ContextConfiguration(locations={"classpath:applicationContext.xml"})  //路径在resources下面
public class DemoServiceImplTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testAssertTrue() {
        this.demoService.test(3);
    }

    @Test
    public void testGson(){
        Gson gson = new Gson();
        User user = gson.fromJson("{'id' : '123'}", User.class);
        System.out.println(user);

        user = gson.fromJson("{'id' : '123'}", new TypeToken<User>(){}.getType());
        System.out.println(user);

        List<User> userList = gson.fromJson("[{'id': '123'}, {'id':'234'}]", List.class);
        System.out.println(userList);

        List<User> userList1 = gson.fromJson("[{'id': '123'}, {'id':'234'}]",
                new TypeToken<List<User>>(){}.getType());
        System.out.println(new TypeToken<List<User>>(){}.getType());
        System.out.println(userList1);

    }

    @Test
    public void testEqualsObj(){
        User user1 = new User("1", "xiaohua", "14");
        User user2 = new User("2", "xiaohua", "14");
        System.out.println((user1.equals(user2)));//打印为 true
    }

    @Test
    public void testHashCodeObj(){
        User user1 = new User("1", "xiaohua", "14");
        User user2 = new User("2", "xiaohua", "14");
        Set<User> userSet = new HashSet<>();
        userSet.add(user1);
        userSet.add(user2);
        System.out.println(user1.equals(user2));
        System.out.println(user1.hashCode() == user2.hashCode());
        System.out.println(userSet);
    }

    @Test
    public void test(){
        System.out.println(10 << 1); //20
        System.out.println(10 >> 1); //5
        System.out.println(10 >>> 1); //5

        System.out.println(-10 << 1); //-20
        System.out.println(-10 >> 1); //-5
        System.out.println(-10 >>> 1); //2147483643

        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(-10));
        System.out.println(Integer.toBinaryString(2147483643));
    }
}