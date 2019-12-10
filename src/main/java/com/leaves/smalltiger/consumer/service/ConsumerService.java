package com.leaves.smalltiger.consumer.service;

import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.vo.ConsumerParam;

public interface ConsumerService {
    //查询所有用户的信息
    public MsgResult queryConsumers();
    //删除用户信息(修改用户状态)
    public MsgResult deleteConsumer(int conId);
    //修改用户状态
    public MsgResult changeStatus(int conId);
    //批量删除用户
    public MsgResult deleteConsumers(int[] conIds);
    //##############################后台方法##################################
    //模糊查询
    public MsgResult queryConsumersByWords(String msgWords,int pageNum,int pageSize );
    //修改用户信息
    public MsgResult updateConsumerById(ConsumerParam consumerParam);
}
