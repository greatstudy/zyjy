<%--
  Created by IntelliJ IDEA.
  User: 清Great
  Date: 2020/11/4
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>取号面板</title>
    <link rel="stylesheet" href="../css/TakeNumber.css">
</head>
<body>
<!--取号-->
<div>
    <div>
        <span>请输入卡号：</span>
        <input type="text" id="cardNum"/>
        <input type="button" id="readCard" value="读卡" class="Btn">
        <input type="button" id="returnHomePage" value="返回首页" class="Btn">
    </div>
    <div>
        <span>姓名：</span>
        <span id="patientName"></span>
        <span>性别：</span>
        <span id="sex"></span>
        <span>年龄：</span>
        <span id="age"></span>
        <span>籍贯：</span>
        <span id="native"></span>
        <span>联系电话：</span>
        <span id="tel"></span>
    </div>
    <div>
        <span>证件号码：</span>
        <span id="idNum"></span>
        <span>现住址：</span>
        <span id="addr"></span>
    </div>

    <div>
        <span>卡余额：</span>
        <span id="balance"></span>
        <span>卡押金：</span>
        <span id="deposit"></span>
    </div>

    <div>预约挂号记录</div>
    <div>
        <table border="1" cellspacing="0" class="scheduleTable">
            <thead>
            <tr>
                <col class="tableClass-td1"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td3"/>
                <col class="tableClass-td3"/>
            </tr>
            <tr>
                <th>
                    选择
                </th>
                <th>
                    预约日期
                </th>
                <th>
                    预约就诊日期
                </th>
                <th>
                    预约医生
                </th>
                <th>
                    预约科室
                </th>
            </tr>
            </thead>
            <tbody id="appoint">
            <%--            <tr>--%>
            <%--                <td>--%>
            <%--                    <input type="checkbox" >--%>
            <%--                </td>--%>
            <%--                <td>--%>
            <%--                    2007年10月10日--%>
            <%--                </td>--%>
            <%--                <td>--%>
            <%--                    2007年10月10日--%>
            <%--                </td>--%>
            <%--                <td>--%>
            <%--                    张三--%>

            <%--                </td>--%>
            <%--                <td>--%>
            <%--                    内科--%>
            <%--                </td>--%>
            <%--            </tr>--%>
            </tbody>
        </table>
    </div>

    <div class="TakeNumberDiv">
        <input type="button" id="TakeNumberBtn" value="取号">
    </div>
</div>

<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/moment.js"></script>
<script src="../js/TakeNumber.js"></script>
</body>
</html>
