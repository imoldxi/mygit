package com.leaves.smalltiger.comsumptiontype.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.ConsumptionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConsumptionTypeMapper extends BaseMapper<ConsumptionType> {

    @Select("SELECT * FROM ConsumptionType WHERE contName=#{contName} AND contStatus=#{contStatus}")
    public List<ConsumptionType> findByName(@Param("contName")String contName,
                                            @Param("contStatus")int contStatus);
}
