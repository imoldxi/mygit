package com.leaves.smalltiger.consumer.controller;

import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import com.leaves.smalltiger.consumer.service.ConsumerService;
import com.leaves.smalltiger.consumer.vo.ConsumerInsert;
import com.leaves.smalltiger.consumer.vo.ConsumerParam;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
@Slf4j
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/api")
public class ConsumerController {
    @Resource
    private ConsumerService consumerService;

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping(value = "/queryConsumers",method = RequestMethod.GET)
    public MsgResult queryConsumers(){
        MsgResult msgResult = consumerService.queryConsumers();
        return msgResult;
    }

    /**
     * 根据id删除用户信息(修改状态)
     * @param conId
     * @return
     */
    @RequestMapping(value = "/deletecon",method = RequestMethod.PUT)
    public MsgResult deleteConsumer(int conId){
        log.info("传来的id："+conId);
        MsgResult msgResult = consumerService.deleteConsumer(conId);
        return msgResult;
    }

    /**
     * 批量删除用户信息(修改状态)
     * @param conIds
     * @return
     */
    @RequestMapping(value = "/deletecons",method = RequestMethod.PUT)
    public MsgResult deleteConsumers(int[] conIds){
        log.info("Id数组:"+conIds);
        MsgResult msgResult = consumerService.deleteConsumers(conIds);
        return msgResult;
    }

    /**
     * 修改用户状态
     * @param conId
     * @return
     */
    @RequestMapping(value = "/statuschange",method = RequestMethod.PUT)
    public MsgResult statuschange(int conId){
        log.info(conId+"传来的参数");
        MsgResult msgResult = consumerService.changeStatus(conId);
        return msgResult;
    }

    /**
     * 模糊搜索+分页
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryConsumersByWords",method = RequestMethod.GET)
    public MsgResult queryConsumersByWords(@RequestParam(value = "msgWords",defaultValue = "")String msgWords,
                                         @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                         @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        log.info("ConsumerController => queryConsumersByWords要查询的是:"+msgWords);
        //consumerService.queryConsumersByWords(String msgWords);
        MsgResult msgResult = consumerService.queryConsumersByWords(msgWords,pageNum,pageSize);
        return msgResult;

    }

    /**
     * 修改用户信息
     * @param consumerParam
     * @return
     */
    @RequestMapping(value = "/updateConsumer",method = RequestMethod.PUT)
    public MsgResult updateConsumerById(@RequestBody ConsumerParam consumerParam){
        //consumerService.updateConsumerById(conId);
        MsgResult msgResult = consumerService.updateConsumerById(consumerParam);
        return msgResult;

    }
/**
 * @author James
 * @date 2019/12/10 17:33
 */
    /**
     * 新增
     * @param consumerInsert
     * @return
     */
    @RequestMapping(value = "/addConsumer",method = RequestMethod.POST)
    public  MsgResult insertConsumer(ConsumerInsert consumerInsert){
        MsgResult msgResult = consumerService.addConsumer(consumerInsert);
        return msgResult;
    }

}
