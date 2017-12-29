package com.peramdy.controller.summer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author peramdy
 * @date 2017/12/14.
 */
@Controller
@RequestMapping("/summer")
public class SummerController {

    @Value("${huahua}")
    private String name;

    @GetMapping("/apolloTest.action")
    @ResponseBody
    public String apolloTest() {
        System.out.println(name);
        return name;
    }

}
