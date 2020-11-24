package com.zyjy.controller;

import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyParam;
import com.zyjy.pojo.ViewPatient;
import com.zyjy.service.inter.CardService;
import com.zyjy.util.RegexUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName CardCtrl
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 16:41
 */
public class CardCtrl {

    @AutoSet
    private CardService cardService;

    /**
     * 充值
     *
     * @param cardNum 卡号
     */
    public ResponseBean recharge(@MyParam("request")HttpServletRequest request,
                                 @MyParam("cardNum") String cardNum,
                                 @MyParam("money") String money) {

        if (!RegexUtils.isInt(money)) {
            return new ResponseBean(1, null, "金额请输入整数");
        }

        Object cardInfo = request.getSession().getAttribute("cardInfo");
        if (cardInfo == null) {
            return new ResponseBean(1, null, "请先读卡");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        ViewPatient viewPatient = null;
        try {
            viewPatient = objectMapper.readValue(String.valueOf(cardInfo), ViewPatient.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseBean(1, null, "系统异常");
        }
//        String cardNum1 = viewPatient.getCardNum();
        if (cardNum == null || !cardNum.equals(viewPatient.getCardNum())) {
            return new ResponseBean(1, null, "充值卡号与读卡信息不一致，请重新读卡");
        }
        int i = Integer.parseInt(money);
        if (i > 0) {
            return cardService.recharge(i, cardNum);
        } else {
            return new ResponseBean(1, null, "充值金额必须为大于0的整数");
        }

    }

}
