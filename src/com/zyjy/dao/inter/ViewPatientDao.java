package com.zyjy.dao.inter;

import com.zyjy.pojo.ViewPatient;

import java.sql.Connection;
import java.util.List;

public interface ViewPatientDao {

    List<ViewPatient> readCard(Connection connection, String readCard) throws Exception;

}
