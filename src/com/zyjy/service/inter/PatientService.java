package com.zyjy.service.inter;

import com.zyjy.dto.ResponseBean;

public interface PatientService {

    /**
     * 读卡
     * @param readCard 卡号
     * @return
     */
    ResponseBean findForReadCard(String readCard);

}
