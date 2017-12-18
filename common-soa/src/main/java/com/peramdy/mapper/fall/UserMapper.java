package com.peramdy.mapper.fall;

import com.peramdy.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author peramdy
 * @date 2017/12/15
 */
public interface UserMapper {

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    User queryUserInfoById(@Param("id") Integer id);

}
