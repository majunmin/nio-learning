package com.mjm.niolearning.socket.one;

import lombok.Data;

import java.io.Serializable;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2019-06-20 17:40
 * @since
 */
@Data
public class UserInfo implements Serializable {

    private String name;

    private int age;

    private String sex;
}
