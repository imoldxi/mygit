package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumerParam {
    private Integer conId;

    private String conName;

    private String conAvatar;

    private Integer conSex;

    private String conTel;

    private String conMail;

    private Integer conStatus;

}
