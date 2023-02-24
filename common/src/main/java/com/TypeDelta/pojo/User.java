package com.TypeDelta.pojo;


import lombok.Data;

import java.util.Date;

/**
 * @author TypeDelta
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private int valid;
    private Date created;
    private String error;
    private Integer result;
}
