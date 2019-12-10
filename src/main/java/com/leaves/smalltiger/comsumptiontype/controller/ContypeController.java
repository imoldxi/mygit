package com.leaves.smalltiger.comsumptiontype.controller;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.comsumptiontype.service.impl.ContypeServie;
import com.leaves.smalltiger.comsumptiontype.vo.ContypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@ResponseBody
public class ContypeController {
    @Autowired
    private ContypeServie contypeServie;

    /**
     * 新增
     * @param contypeInfo
     * @return
     */
    @RequestMapping(value = "/contype",method = RequestMethod.GET)

    public MsgResult insertData(@RequestBody ContypeInfo contypeInfo){
        MsgResult result = contypeServie.insertData(contypeInfo);
        return result;
    }

    /**
     * 查询
     * @param contName
     * @param contStatus
     * @return
     */
    @RequestMapping(value = "/contypes",method = RequestMethod.GET)
    public MsgResult find(String contName, int contStatus){
        MsgResult result = contypeServie.find(contName,contStatus);
        return result;
    }
}
