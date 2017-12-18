package com.peramdy.service.fall.impl;

import com.peramdy.entity.User;
import com.peramdy.mapper.fall.UserMapper;
import com.peramdy.model.UserDto;
import com.peramdy.service.fall.FallService;
import com.peramdy.utils.TransUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author peramdy
 * @date 2017/12/15.
 */
@Service
public class FallServiceImpl implements FallService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String sayHelloToFall() {
        return "hello fall";
    }

    @Override
    public UserDto queryById(Integer id) {
        User user = userMapper.queryUserInfoById(id);
        UserDto dto = TransUtils.smipleConvertor(user, UserDto.class);
        return dto;
    }

}
