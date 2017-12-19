package com.peramdy.service.fall.impl;

import com.peramdy.entity.User;
import com.peramdy.mapper.fall.UserMapper;
import com.peramdy.model.UserDto;
import com.peramdy.service.fall.FallService;
import com.peramdy.utils.TransUtils;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("student")
    @Override
    public UserDto queryById(Integer id) {
        User user = userMapper.queryUserInfoById(id);
        UserDto dto = TransUtils.simpleConverter(user, UserDto.class);
        return dto;
    }


    @Cacheable(value = "student", key = "T(String).valueOf(#id).concat('-').concat(#classId)")
    @Override
    public UserDto queryById(Integer id, Integer classId) {
        User user = userMapper.queryUserInfoByIdAndClassId(id, classId);
        UserDto dto = TransUtils.simpleConverter(user, UserDto.class);
        return dto;
    }

}
