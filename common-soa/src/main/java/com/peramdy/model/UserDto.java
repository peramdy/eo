package com.peramdy.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author peramdy
 * @date 2017/12/16.
 */
@Data
public class UserDto implements Serializable {

    private Integer id;
    private String name;
    private Integer classId;
    private String remark;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
