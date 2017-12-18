package com.peramdy.controller.autumn;

import com.peramdy.service.fall.FallService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author peramdy
 * @date 2017/12/14
 */
@Controller
@RequestMapping("/fall")
public class AutumnController {

    @Resource
    FallService fallService;

    @GetMapping("/helloFall.action")
    @ResponseBody
    public String sayHelloToFall() {
        return fallService.sayHelloToFall();
    }


    @PostMapping("/helloFall.action")
    @ResponseBody
    public String sayHello() {
        return fallService.sayHelloToFall();
    }

    @RequestMapping(value = "/query.action", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String query(@RequestParam("id") Integer id) {
        return fallService.queryById(id).toString();
    }

}
