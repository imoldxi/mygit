package com.leaves.smalltiger.consumer.mapper;
import java.util.List;
import	java.util.Map;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConsumerMapper extends BaseMapper<Consumer> {
    /**
     * 查询所有用户的详细信息
     * @return
     */
    @Select("SELECT conId,\n" +
            "conName,\n" +
            "password,\n" +
            "conAvatar,\n" +
            "conSex,\n" +
            "conTel,\n" +
            "conMail,\n" +
            "conStatus,\n" +
            "conCreateTime FROM consumer")
    public List<Consumer> queryConsumers();

    /**
     *删除用户信息(假删除，真修改用户状态)
     * UPDATE consumer SET conStatus = 1 WHERE conId=5
     */
    @Update("UPDATE consumer SET conStatus=0 WHERE conId = #{conId}")
    public void deleteConsumer(int conId);

    /**
     * 修改用户状态为1
     * @param conId
     */
    @Update("UPDATE consumer SET conStatus=1 WHERE conId = #{conId}")
    public void changeStatus(int conId);

    /**
     * 模糊查询+分页
     * @param msgWords
     * @return
     */
    @Select("SELECT * FROM consumer WHERE conName LIKE '%${msgWords}%' ORDER BY conId")
    public List<Consumer> queryConsumersByWords(@Param(value = "msgWords") String msgWords);


    //public List<Consumer> updateConsumerById(@Param(value = "conId") int conId);
}
