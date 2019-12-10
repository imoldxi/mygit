package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumerInsert {

    private Integer conId;

    private String conName;

    private String password;

    private String conAvatar;

    private int conSex;

    private String conTel;

    private String conMail;
}
