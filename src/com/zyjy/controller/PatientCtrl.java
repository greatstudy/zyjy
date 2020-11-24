package com.zyjy.controller;

import com.zyjy.dto.ResponseBean;
import com.zyjy.myAnnotation.AutoSet;
import com.zyjy.myAnnotation.MyParam;
import com.zyjy.pojo.ViewPatient;
import com.zyjy.service.inter.PatientService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName PatientCtrl
 * @Description
 * @Author 清Great
 * @Date 2020/11/11 16:23
 */
public class PatientCtrl {

    @AutoSet
    private PatientService patientService;

    /**
     * 读卡
     * @param cardNum 可能是手机号，卡号，证件号
     */
    public ResponseBean readCard(@MyParam("request") HttpServletRequest request,
                                 @MyParam("cardNum") String cardNum) {

        if (cardNum == null || cardNum.equals("")) {
            return new ResponseBean(1, null, "请先输入卡号");
        }
        ResponseBean responseBean = patientService.findForReadCard(cardNum);

        if (responseBean.getState() == 0) {
            List<ViewPatient> cardInfo = (List<ViewPatient>) responseBean.getData();
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String s = objectMapper.writeValueAsString(cardInfo.get(0));
                request.getSession().setAttribute("cardInfo", s);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseBean(1, null, "JSON转换异常");
            }
        }
        return responseBean;
    }
}
