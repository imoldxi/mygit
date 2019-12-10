package com.leaves.smalltiger.comsumptiontype.service.impl;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.comsumptiontype.vo.ContypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.annotations.Param;

public interface ContypeServie {

    public MsgResult insertData(ContypeInfo contypeInfo);

    public MsgResult find(String contName,int contStatus);
}
