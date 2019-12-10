package com.leaves.smalltiger.smalltiger;





import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.po.ConsumptionType;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.utils.HelloSender;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.comsumptiontype.mapper.ConsumptionTypeMapper;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import com.leaves.smalltiger.consumer.service.ConsumerService;
import com.leaves.smalltiger.consumer.service.impl.ConsumerServiceImpl;
import com.leaves.smalltiger.consumer.vo.ConsumerInsert;
import com.leaves.smalltiger.consumer.vo.ConsumerParam;
import com.leaves.smalltiger.detail.mapper.DetailMapper;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.DataResult;
import com.leaves.smalltiger.detail.vo.DetailHome;
import com.leaves.smalltiger.detail.vo.DetailParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmalltigerApplicationTests {

    @Resource
    private ConsumerMapper consumerMapper;
    @Resource
    private ConsumptionTypeMapper consumptionTypeMapper;
    @Resource
    private DetailMapper detailMapper;
   @Resource
   private ConsumerService consumers;
   @Resource
   private DetailService detailService;

    /**
     * 用户表全查询用户测试
     */
    @Test
    public void consumerSelectTest() {
        List<Consumer> consumers = consumerMapper.selectAll();
        for (Consumer consumer : consumers) {
            log.info("用户表数据全查测试===" + consumer.toString());
        }
    }

    /**
     * 用户表增加用户测试
     */
    @Test
    public void consumerInsertTest() {
        Consumer consumer = new Consumer();
        consumer.setConId(5678);
        consumer.setConName("ddd");
        consumerMapper.insert(consumer);
    }

    /**
     * 消费类型表全查测试
     */
    @Test
    public void consumptionTypeSelectTest() {
        List<ConsumptionType> consumptionTypes = consumptionTypeMapper.selectAll();
        for (ConsumptionType consumptionType : consumptionTypes) {
            log.info("消费类型表数据全查测试===" + consumptionType.toString());
        }
    }





    /**
     * 明细表
     */
    @Test
    public void  queryHome(){
        DetailParam detailParam = new DetailParam();
        detailParam.setConId(520131417);
        detailParam.setYear(2019);
        detailParam.setMonth(12);
        MsgResult msgResult = detailService.queryHome(detailParam);

//        for (DetailHome detailHome:detailHomes){
//            log.info(detailHome+"    打印结果");
//        }
        log.info(msgResult+"打印测试数据");
    }

    /**
     * 查询所有用户详细信息
     */
    @Test
    public void queryConsumer(){
        List<Consumer> consumers = consumerMapper.queryConsumers();
        for (Consumer consumer:consumers){
            log.info(consumer+"打印结果");
        }
        log.info("查询结果的长度"+consumers.size());
    }

    /**
     * 根据用户Id删除用户信息(修改状态)
     */
    @Test
    public void deleteConsumer(){
        consumerMapper.deleteConsumer(1);

    }
    @Test
    public void  changeStatus(){
        consumerMapper.changeStatus(1);
    }

    /**
     * 用户表分页查询
     */
    @Test
    public void  queryConsumersByMsgwords(){
       // List<Consumer> consumers = consumerMapper.queryConsumersByWords("");
        MsgResult msgResult = consumers.queryConsumersByWords("", 1, 10);

        log.info("数据的大小"+msgResult.toString());
    }
    /*conId;

    private String conName;

    private String conAvatar;

    private Integer conSex;

    private String conTel;

    private String conMail;

    private Integer conStatus;*/
    @Test
    public void  updateConsumerById(){
        ConsumerParam consumerParam = new ConsumerParam(5678,"辣鸡","askdnas",1,"56456","asd@163.com",1);
        MsgResult msgResult = consumers.updateConsumerById(consumerParam);
        log.info(msgResult.toString());
    }

    @Test
    public void fuzzySearch(){
        MsgResult result = detailService.fuzzySearch("工资", 1, 10);
            log.info(result.toString());
    }

    @Test
    public  void addConsumer(){
        /*588, '小强', '654', 'jhb', 1, '56465', 'asd@163.com'*/
        ConsumerInsert consumerInsert = new ConsumerInsert(77, "小强", "54", "jhb", 1, "56465", "asd@163.com");
        consumers.addConsumer(consumerInsert);
        log.info("测试数据是#############################"+consumers.toString());
    }


}
    /**
     * 明细表全查测试
     */
//    @Test
//    public void DetailSelectTest() {
//        List<DetailParam> details = detailMapper.selectAll();
//        for (DetailParam detail : details) {
//            log.info("消费类型表数据全查测试===" + detail.toString());
//        }
//    }



