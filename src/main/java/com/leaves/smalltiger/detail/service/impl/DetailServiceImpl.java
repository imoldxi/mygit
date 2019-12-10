package com.leaves.smalltiger.detail.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.mapper.DetailMapper;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.DataResult;
import com.leaves.smalltiger.detail.vo.DetailHome;
import com.leaves.smalltiger.detail.vo.DetailParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailMapper detailMapper;

    /**
     * 计算用户当月总收入和总支出
     * @param detailParam
     * @return
     */
    @Override
    public MsgResult querydetails(DetailParam detailParam) {
        MsgResult result = new MsgResult();
        log.info("传来的年份是:"+detailParam.getYear()+" 月份:"+detailParam.getMonth()+" 用户ID是："+detailParam.getConId());
        List<DataResult> details=detailMapper.queryDetails(detailParam.getYear(),detailParam.getMonth(),detailParam.getConId());
        if (!details.isEmpty()){
//            将查询到总和的数据保留小数位，整数保留一位，非整数保留两位小数
            for (int i=0;i<details.size();i++){
//                设置保留小数位格式
                DecimalFormat df = new DecimalFormat("0.00");
//                将当月总收入保留小数
                double income = Double.parseDouble(df.format(details.get(i).getMonthTotalIncome()));
//                将当月总支出保留两位小数
                double Expenditure = Double.parseDouble(df.format(details.get(i).getMonthTotalExpenditure()));
//                将当月总支出和总收入放入包装类中
                details.get(i).setMonthTotalIncome(income);
                details.get(i).setMonthTotalExpenditure(Expenditure);
//                打印值
                log.info("当月总收入"+income);
                log.info("当月总支出"+Expenditure);
//                将结果封装到返回前端类中
                result.setStatusCode(200);
                result.setData(details);
                result.setMsg("查询成功");
                return result;
            }
        }
//        查询失败
        result.setStatusCode(201);
        result.setMsg("查询失败");
        return result;
    }

    /**
     * 首页显示明细
     * @param detailParam
     * @return
     */
    @Override
    public MsgResult queryHome(DetailParam detailParam) {
//        打印传来的参数值
        log.info("传来的年份是:"+detailParam.getYear()+" 月份:"+detailParam.getMonth()+" 用户ID是："+detailParam.getConId());
        MsgResult result = new MsgResult();
//        把查询到的数据放到list里
        List<DetailHome> detailHomes = detailMapper.queryHome(detailParam.getYear(), detailParam.getMonth(), detailParam.getConId());
        for (DetailHome dataResult:detailHomes){
                log.info(dataResult+"打印查询结果");
        }
//        判断是否为空值
        if (!detailHomes.isEmpty()){
//            返回查询状态吗
            result.setStatusCode(200);
//            返回查询信息
            result.setMsg("查询成功");
//            将结果封装到包装类里返回到前端
            result.setData(detailHomes);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("查询失败");
        return result;
    }
    //################################后端方法##########################################

    @Override
    public MsgResult fuzzySearch(String markName, int pageNum , int pageSize) {
        log.info(markName);
        MsgResult msgResult = new MsgResult();
        PageHelper.startPage(pageNum,pageSize);
        List<Detail> details = detailMapper.fuzzySearch(markName);
        PageInfo<Detail> pageInfo = new PageInfo<>(details);
        if (details.size()>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("查询成功");
            msgResult.setData(pageInfo);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        return msgResult;
    }
}
