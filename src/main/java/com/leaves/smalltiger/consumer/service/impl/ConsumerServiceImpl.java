package com.leaves.smalltiger.consumer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import com.leaves.smalltiger.consumer.service.ConsumerService;
import com.leaves.smalltiger.consumer.vo.ConsumerParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.rmi.runtime.Log;

import java.util.List;
@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 查询所有用户的详细信息
     * @return
     */
    @Override
    public MsgResult queryConsumers() {
        MsgResult msgResult = new MsgResult();
        List<Consumer> consumers = consumerMapper.queryConsumers();
        if (!consumers.isEmpty()){
            msgResult.setStatusCode(200);
            msgResult.setMsg("查询成功");
            msgResult.setData(consumers);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("查询失败");
        msgResult.setData(consumers);
        return msgResult;
    }

    /**
     * 根据用户id删除该用户信息(修改该用户状态)
     * @param conId
     * @return
     */
    @Override
    public MsgResult deleteConsumer(int conId) {
        MsgResult msgResult = new MsgResult();

        consumerMapper.deleteConsumer(conId);
        if (conId>0){
            List<Consumer> consumers = consumerMapper.queryConsumers();
            msgResult.setStatusCode(200);
            msgResult.setMsg("删除成功");
            msgResult.setData(consumers);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("删除失败");
        return msgResult;
    }

    /**
     * 根据用户id修改用户状态
     * @param conId
     * @return
     */
    @Override
    public MsgResult changeStatus(int conId) {
        MsgResult msgResult = new MsgResult();
        log.info(conId+"传来的ID");
        if(conId>0){
            consumerMapper.changeStatus(conId);
            List<Consumer> consumers = consumerMapper.queryConsumers();
            msgResult.setStatusCode(200);
            msgResult.setMsg("修改成功");
            msgResult.setData(consumers);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("修改失败");
        return msgResult;
    }
    /**
     * 批量删除用户信息(修改状态)
     * @param conIds
     * @return
     */
    @Override
    public MsgResult deleteConsumers(int[] conIds) {
        MsgResult msgResult = new MsgResult();
        for (int id:conIds){
            if (id>0){
                consumerMapper.deleteConsumer(id);

            }else {
                msgResult.setStatusCode(201);
                msgResult.setMsg("批量删除失败");
            }
        }
        List<Consumer> consumers =consumerMapper.queryConsumers();
        msgResult.setStatusCode(200);
        msgResult.setMsg("批量删除成功");
        msgResult.setData(consumers);
        return msgResult;
    }

    /**
     * 分页查询
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public MsgResult queryConsumersByWords(String msgWords,int pageNum,int pageSize) {
        log.info("ConsumerServiceImpl => queryConsumersByWords方法要查询的关键字是:"+msgWords);
        MsgResult msgResult = new MsgResult();
        PageHelper.startPage(pageNum,pageSize);
        List<Consumer> consumers = consumerMapper.queryConsumersByWords(msgWords);
        PageInfo<Consumer> pageInfo = new PageInfo<>(consumers);
        log.info(pageInfo.toString());
        if (pageInfo.getSize()>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("查询成功");
            msgResult.setData(pageInfo);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("查询失败");
        return msgResult;
    }

    /**
     * 修改用户信息
     * @param consumerParam
     * @return
     */
    @Override
    public MsgResult updateConsumerById(ConsumerParam consumerParam) {
        Consumer consumer = new Consumer();

        consumer.setConId(consumerParam.getConId());

        consumer.setConName(consumerParam.getConName());

        consumer.setConAvatar(consumerParam.getConAvatar());

        consumer.setConSex(consumerParam.getConSex());

        consumer.setConMail(consumerParam.getConMail());

        consumer.setConTel(consumerParam.getConTel());

        consumer.setConStatus(consumerParam.getConStatus());

        MsgResult msgResult = new MsgResult();
        int i = consumerMapper.updateByPrimaryKeySelective(consumer);
        if (i>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("修改成功");
            return msgResult;
        }

        msgResult.setStatusCode(201);
        msgResult.setMsg("修改失败");
        return msgResult;
    }
}
