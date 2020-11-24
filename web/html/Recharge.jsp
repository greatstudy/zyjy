<%--
  Created by IntelliJ IDEA.
  User: 清Great
  Date: 2020/11/4
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>卡充值</title>
    <link rel="stylesheet" href="../css/Recharge.css">
</head>
<body>
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
    </div>
    <div>
        <span>证件号码：</span>
        <span id="idNum"></span>
        <span>联系电话：</span>
        <span id="tel"></span>
    </div>
    <div>
        <span>现住址：</span>
        <span id="addr"></span>
    </div>
    <div>
        <span>卡余额：</span>
        <span id="balance"></span>
        <span>卡押金：</span>
        <span id="deposit"></span>
    </div>
    <div>
        <span>请输入充值金额：</span>
        <input type="number" id="recharge"/> 元
        <input type="button" value="充值" id="rechargeBtn" class="Btn">
    </div>


</div>
<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/Recharge.js"></script>

</body>
</html>
