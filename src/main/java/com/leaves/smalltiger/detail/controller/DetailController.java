package com.leaves.smalltiger.detail.controller;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.DetailParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/api")
public class DetailController {
    @Autowired
    private DetailService detailService;

    /**
     *根据年份、月份和yonghuId查询该用户当月的总收入和总支出
     * @param
     * @return
     */
    @RequestMapping(value = "/queryDetails",method = RequestMethod.GET)
    public MsgResult query(@RequestBody DetailParam detailParam){
        log.info("前台传来的参数: 用户ID"+detailParam.getConId()+"  年份： "+detailParam.getYear()+"  月份："+detailParam.getMonth());
        MsgResult msgResult = detailService.querydetails(detailParam);
        return msgResult;
    }



    /**
     * 查询并返回明细
     * @param
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public MsgResult queryHome(  DetailParam detailParam){
        log.info("Controller前台传来的参数: 用户ID:   "+detailParam.getConId()+"  年份： "+detailParam.getYear()+"  月份："+detailParam.getMonth());
        MsgResult msgResult = detailService.queryHome(detailParam);
        return msgResult;
    }

  /*  @RequestMapping(value = "/home",method = RequestMethod.GET)
    public MsgResult queryHome(@RequestParam(value = "conId")int conId,
                               @RequestParam(value = "year")int year,
                               @RequestParam(value = "month")int month){
        MsgResult msgResult = detailService.queryHome(conId, year, month);
        return msgResult;
    }*/


    /**
     * 模糊查询
     * @param markName
     * @return
     */
    @RequestMapping(value = "/fuzzySearch",method = RequestMethod.GET)
    public MsgResult fuzzySearch(@Param("markName") String markName,
                                 @Param("pageNum") int pageNum,
                                 @Param("pageSize") int pageSize){
        MsgResult msgResult = detailService.fuzzySearch(markName,pageNum,pageSize);
        return msgResult;
    }





    /*  /**
     *根据年份、月份和yonghuId查询该用户当月的总收入和总支出
     * @param detailParam
     * @return
     */
    /*@RequestMapping(value = "/queryDetails",method = RequestMethod.GET)
    public MsgResult query(@RequestParam(value = "conId") int conId,
                           @RequestParam(value = "year") int year,
                           @RequestParam(value = "month") int month, DetailParam detailParam){
        log.info("前台传来的参数: 用户ID"+detailParam.getConId()+"  年份： "+detailParam.getYear()+"  月份："+detailParam.getMonth());
        MsgResult msgResult = detailService.querydetails(detailParam);
        return msgResult;
    }*/

}
