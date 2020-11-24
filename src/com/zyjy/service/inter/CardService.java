package com.zyjy.service.inter;

import com.zyjy.dto.ResponseBean;

public interface CardService {
    /**
     * 卡充值
     *
     * @param money   充值金额
     * @param cardNum 卡号
     * @return
     */
    ResponseBean recharge(int money, String cardNum);

}
