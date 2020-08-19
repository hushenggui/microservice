package com.shenzhou.hu.springboot.controller;


import com.shenzhou.hu.springboot.moudles.redis.RedisUtil;
import com.shenzhou.hu.springboot.service.UserService;
import com.shenzhou.hu.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/*
   比实现 CommandLineRunner  执行的快  setEnvironment
 */
@Controller
public class HelloController  implements EnvironmentAware {
    //注入application.properties的属性到指定变量中.
    @Value("${cas.url}")
    private String myUrl;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/a")
    @ResponseBody
    public List<User>  index(Model model) {
        List<User> list = userService.findAll();
        for (User user:list) {
            System.out.println(user.getUname());
        }
        return list;
    }


    /**
     * 返回html模板.
     */
    @RequestMapping("/hello")
    public String helloHtml(Map<String,Object> map){
        // redisUtil.set("hu123","hu123");
        //  String hu =  (String)redisUtil.get("hu123");
        // System.out.println(hu);

  /*      User user = new User();
        user.setUname("hu");
        user.setId("1");

        User user1 = new User();
        user1.setUname("hu1");
        user1.setId("2");

        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user1);

        redisUtil.lSet("list",list);
        List<User> userList =   userService.findAll();*/
       /* List<Object> userList1 = redisUtil.lGet("userList",0,-1);
        if(userList1 != null){
            System.out.println(userList1.size()+"  个");
        }*/


        //    ArrayList<Object> userList = (ArrayList)redisUtil.lGet("list",0,-1);


        //  redisUtil.set("user",user1);
        //  User user2 = (User)redisUtil.get("user");

        //  System.out.println(user2.getUname());

        // User user3 = userService.findOne("1");
        String hu = userService.findtt();
         String ss = (String)redisUtil.get("hu456");
         System.out.println(ss+"  胡");
        // User user2 = (User)redisUtil.get("users::user_1");

        return"/hello";
    }

    @RequestMapping(value = "/user/{id}",method = RequestMethod.POST)
    @ResponseBody
    public User findUserById(@PathVariable("id") String id){

        return null;
    }


    @Override
    public void setEnvironment(Environment environment) {
        //打印注入的属性信息.
        System.out.println("myUrl="+myUrl);

        //通过 environment 获取到系统属性.
        System.out.println(environment.getProperty("JAVA_HOME"));

        //通过 environment 同样能获取到application.properties配置的属性.
        System.out.println(environment.getProperty("cas.url"));

        //获取到前缀是"spring.datasource." 的属性列表值.
       //  RelaxedPropertyResolver relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
     //   System.out.println("spring.datasource.url="+relaxedPropertyResolver.getProperty("url"));



    }
}
