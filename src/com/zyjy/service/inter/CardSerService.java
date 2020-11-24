package com.zyjy.service.inter;

import com.zyjy.dto.PageBean;
import com.zyjy.dto.ResponseBean;

public interface CardSerService {

    /**
     * 获取卡账单
     */
    ResponseBean findCardSerList(String cardNum, PageBean pageBean);


}
