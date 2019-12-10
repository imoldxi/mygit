package com.leaves.smalltiger.comsumptiontype.service.impl.impl;

import com.leaves.smalltiger.common.po.ConsumptionType;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.comsumptiontype.mapper.ConsumptionTypeMapper;
import com.leaves.smalltiger.comsumptiontype.service.impl.ContypeServie;
import com.leaves.smalltiger.comsumptiontype.vo.ContypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ContypeInfoServiceImpl implements ContypeServie {
    @Resource
    private ConsumptionTypeMapper ctp;

    @Override
    public MsgResult insertData(ContypeInfo contypeInfo) {
        MsgResult msgResult = new MsgResult();
        if (contypeInfo != null){
            ConsumptionType type = new ConsumptionType();

            type.setContName(contypeInfo.getContName());
            type.setContIcon(contypeInfo.getContIcon());
            type.setContStatus(1);

            int i = ctp.insertSelective(type);
            if (i > 0 ){
                msgResult.setStatusCode(200);
                msgResult.setMsg("添加成功");
                return msgResult;
            }
        }

        msgResult.setStatusCode(201);
        msgResult.setMsg("添加失败");
        return msgResult;
    }

    @Override
    public MsgResult find(String contName, int contStatus) {
        MsgResult result = new MsgResult();
        List<ConsumptionType> list = ctp.findByName(contName, contStatus);
        if (!list.isEmpty()){
            result.setStatusCode(200);
            result.setData(list);
            result.setMsg("查询成功");
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("查询失败");
        return result;
    }
}
