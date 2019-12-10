package com.leaves.smalltiger.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgResult {
    private int statusCode;
    private String msg;
    private Object data;
}
