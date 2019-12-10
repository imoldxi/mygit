package com.leaves.smalltiger.detail.service;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.vo.DetailHome;
import com.leaves.smalltiger.detail.vo.DetailParam;
import org.springframework.web.bind.annotation.RequestParam;

public interface DetailService {
//    查询明细页面的当月总收入和总支出
    public MsgResult querydetails(DetailParam detailParam);
//    查询明细页面当月的流水账
    public MsgResult queryHome(DetailParam detailParam);


    //######################后端方法######################################

    //模糊查询
    public MsgResult fuzzySearch(String markName , int pageNum , int pageSize);


}
