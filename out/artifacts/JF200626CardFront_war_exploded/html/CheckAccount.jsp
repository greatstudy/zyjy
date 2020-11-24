<%--
  Created by IntelliJ IDEA.
  User: 清Great
  Date: 2020/11/4
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>卡对账</title>
    <link rel="stylesheet" href="../css/CheckAccount.css">
    <link rel="stylesheet" href="../css/Recharge.css">
</head>
<body>
<div>
    <div>
        <span>请输入卡号：</span>
        <input type="text" id="cardNum"/>
        <input type="button" id="readCard" value="读卡" class="Btn">
        <input type="button" id="returnHomePage" value="返回首页" class="Btn" style="margin-left: 20px">
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

    <div>卡账务明细</div>
    <div>
        <table border="1" cellspacing="0">
            <thead>
            <tr>
                <col class="tableClass-td1"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td3"/>
                <col class="tableClass-td4"/>
            </tr>

            <tr>
                <th>发生时间</th>
                <th>发生事项</th>
                <th>发生余额</th>
                <th>执行人</th>
            </tr>
            </thead>
            <tbody id="mainTable">


            </tbody>
        </table>
        <div class="divForTable">
            <input type="button" id="lastPage" value="上一页" class="pageBtn" onclick="goPage(-1);">
            <span id="pageSpan">1/1</span>
            <input type="button" id="nextPage" value="下一页" class="pageBtn" onclick="goPage(1);">
        </div>
    </div>


</div>

<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/moment.js"></script>
<script src="../js/CheckAccount.js"></script>

</body>
</html>
