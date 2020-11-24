package com.zyjy.controller;

import com.zyjy.dto.PageBean;
import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyParam;
import com.zyjy.service.inter.CardSerService;
import com.zyjy.util.RegexUtils;

/**
 * @ClassName CardSerCtrl
 * @Description
 * @Author 清Great
 * @Date 2020/11/14 12:01
 */
public class CardSerCtrl {

    @AutoSet
    private CardSerService cardSerService;

    /**
     * 获取卡业务信息
     * @param cardNum 卡号
     * @param currentPageStr 当前页
     * @param pageSizeStr 每页显示数量
     * @return
     */
    public ResponseBean findCardSer(@MyParam("cardNum") String cardNum,
                                 @MyParam("currentPage") String currentPageStr,
                                 @MyParam("pageSize") String pageSizeStr) {

        if (!RegexUtils.isInt(currentPageStr) || !RegexUtils.isInt(pageSizeStr)) {
            return new ResponseBean(1, null, "数据错误");
        }

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        PageBean pageBean = new PageBean(currentPage, pageSize);

        return cardSerService.findCardSerList(cardNum, pageBean);
    }

}
