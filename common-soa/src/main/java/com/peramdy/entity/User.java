package com.peramdy.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author peramdy
 * @date 2017/12/15.
 */
@Data
public class User {

    private Integer id;
    private String name;
    private Integer classId;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
