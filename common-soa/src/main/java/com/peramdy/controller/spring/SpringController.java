package com.peramdy.controller.spring;

import com.peramdy.model.UserDto;
import com.peramdy.redis.CustomRedisUtils;
import com.peramdy.redis.RedisManager;
import com.peramdy.redis.RedisTemplateService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author peramdy
 * @date 2017/12/14.
 */
@Controller
@RequestMapping(value = "/spring")
public class SpringController {

    @Autowired
    private RedisTemplateService redisTemplateService;

    @Autowired
    @Qualifier("redisManagerService")
    private RedisManager redisManager;

    @Autowired
    private CustomRedisUtils customRedisUtils;


    @GetMapping(value = "/january.action")
    @ResponseBody
    public String january() {
        System.out.println("hello january!");
        redisTemplateService.putValue("redis_temple", "1111");
        System.out.println(redisTemplateService.getValue("redis_temple"));
        customRedisUtils.set("custom", "custom_111", 5000L);
        System.out.println(customRedisUtils.get("custom"));
        return "hello january!";
    }

    @GetMapping("/helloJanuary.action")
    public ModelAndView helloJanuary() {
        ModelAndView mv = new ModelAndView();
        redisManager.putValue("redis_temple", "222");
        System.out.println(redisManager.getValue("redis_temple"));
        mv.setViewName("spring/january");
        return mv;
    }


    @PostMapping("/serializeList.action")
    @ResponseBody
    public String serializeList() {

        UserDto dto1 = new UserDto();
        dto1.setId(1);
        dto1.setClassId(1);
        dto1.setName("花花");
        dto1.setRemark("你好");

        UserDto dto2 = new UserDto();
        dto2.setId(2);
        dto2.setClassId(2);
        dto2.setName("小明");
        dto2.setRemark("世界");

        List<UserDto> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);

        customRedisUtils.set("huahua", list, null);
        List<UserDto> dtos = customRedisUtils.getList("huahua", UserDto.class);
        System.out.println(dtos.size());
        return "success";
    }

    @GetMapping("/sayHelloSpring.action")
    @ResponseBody
    public String sayHelloSpring() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> str = restTemplate.getForEntity("http://www.baidu.com", String.class);
        return str.getBody();
    }


    @GetMapping("/sayMorning.action")
    @ResponseBody
    public String sayMorning() {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://www.baidu.com";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }


    /**
     * error
     *
     * @return
     */
    @GetMapping("/testHttpClient.action")
    @ResponseBody
    public String testHttpClient() {
        HttpPost post = new HttpPost("http://www.baidu.com");
        String str = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = client.execute(post);
            System.out.println(response.getStatusLine().getStatusCode());
            str = response.getEntity().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
