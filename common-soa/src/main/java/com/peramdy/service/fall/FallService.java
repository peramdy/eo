package com.peramdy.service.fall;

import com.peramdy.model.UserDto;

/**
 * @author peramdy
 * @date 2017/12/14.
 */
public interface FallService {

    /**
     * test
     *
     * @return
     */
    String sayHelloToFall();

    /**
     * query
     *
     * @param id
     * @return
     */
    UserDto queryById(Integer id);


    /**
     * query
     *
     * @param id
     * @param classId
     * @return
     */
    UserDto queryById(Integer id, Integer classId);

}
