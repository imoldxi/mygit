package com.leaves.smalltiger.detail.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.detail.vo.DataResult;
import com.leaves.smalltiger.detail.vo.DetailHome;
import com.leaves.smalltiger.detail.vo.DetailParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Repository
public interface DetailMapper extends BaseMapper<Detail> {

    /**
     * 通过用户id conId，年份year，月份month计算该用户当前月份的总支出和总收入
     * @param year
     * @param month
     * @param conId
     * @return
     */
    @Select("SELECT MONTH (detTime) AS modernMonth, SUM(CASE WHEN detSort=2 THEN detAmount ELSE null END) AS monthTotalExpenditure," +
            "SUM( CASE WHEN detSort=1 THEN  detAmount ELSE null END ) AS monthTotalIncome FROM detail " +
            "WHERE YEAR (detTime) = #{year} and conId=#{conId} and MONTH(detTime) = #{month} GROUP BY MONTH (detTime) DESC")
    public List<DataResult> queryDetails(@Param("year") int year,
                                         @Param("month") int month,
                                         @Param("conId") int conId);

    /**
     * 通过用户id conId，年份year，月份month查询当月流水账
     * @return
     * @param conId
     * @param year
     * @param month
     */
    @Select("select c.conName, d.detId, cpt.contIcon, d.detRemark, d.detSort, d.detAmount, d.detTime, cpt.contName  from consumer c, consumptiontype cpt, detail d " +
            "where c.conId=d.conId and d.contId=cpt.contId and MONTH(detTime)=#{month} AND YEAR(detTime)=#{year} AND c.conId=#{conId} AND d.detStatus=1")
    public List<DetailHome> queryHome(@Param("conId") int conId,
                                      @Param("year")int year,
                                      @Param("month")int month);

    @Select("SELECT * FROM detail WHERE detRemark LIKE '%${markName}%' ORDER BY contId ")
    public List<Detail> fuzzySearch(@Param("markName") String markName);
}
