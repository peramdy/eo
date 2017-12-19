package com.peramdy.controller.autumn;

import com.peramdy.model.UserDto;
import com.peramdy.service.fall.FallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PostMapping(value = "/query.action")
    public ResponseEntity<?> query(@RequestParam("id") Integer id) {
        UserDto dto = fallService.queryById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/queryInfo.action")
    public ResponseEntity<?> query(@RequestParam("id") Integer id,
                                   @RequestParam("classId") Integer classId) {
        UserDto dto = fallService.queryById(id, classId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
