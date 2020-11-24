<%--
  Created by IntelliJ IDEA.
  User: 清Great
  Date: 2020/11/4
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>挂号预约</title>
    <link rel="stylesheet" href="../css/Appointment.css">
    <link rel="stylesheet" href="../css/fullcalendar.css">

</head>
<body>
<!--排班-->
<div class="bigDiv">
    <div class="myDiv">
        <span>请输入卡号：</span>
        <input type="text" id="cardNum"/>
        <input type="button" id="readCard" value="读卡" class="Btn">
        <input type="button" id="returnHomePage" value="返回首页" class="Btn">
    </div>
    <div class="myDiv">
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
    <div class="myDiv">
        <span>证件号码：</span>
        <span id="idNum"></span>
        <span>现住址：</span>
        <span id="addr"></span>
    </div>

    <div class="myDiv">
        <span>卡余额：</span>
        <span id="balance"></span>
        <span>卡押金：</span>
        <span id="deposit"></span>
    </div>


    <div class="myDiv">门诊排班表</div>
    <%--    <div id='calendar' style="margin-top: 20px"></div>--%>
    <div class="myDiv">

        <table border="1" cellspacing="0" class="scheduleTable">
            <thead>
            <tr>
                <col class="tableClass-td1"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
                <col class="tableClass-td2"/>
            </tr>
            </thead>
            <tbody id="schedule">
            <tr>
                <td colspan="8">
                    查无排班
                </td>
            </tr>

            </tbody>
        </table>

    </div>

    <div class="weekDiv">
        <input type="button" id="lastWeek" value="上一周" class="weekBtn">
        <input type="button" id="nextWeek" value="下一周" class="weekBtn">
    </div>


</div>

<!--预约面板-->
<div id="appointPanel" class="panel">
    <div id="appointPanelTitle">
        XX医生门诊时间
    </div>
    <div class="appointPanelContent">
        <table border="1" cellspacing="0" class="appointTable">
            <thead>
            <tr>
                <col class="appoint-td1"/>
                <col class="appoint-td2"/>
            </tr>
            <tr>
                <th>
                    门诊时间
                </th>
                <th>
                    预约人
                </th>
            </tr>
            </thead>
            <tbody id="appoint">
            <tr>
                <td>
                    9:00 -- 10:00
                </td>
                <td>
                    <span id="appoint9"></span>
                </td>
            </tr>
            <tr>
                <td>
                    10:00 -- 11:00
                </td>
                <td>
                    <span id="appoint10"></span>
                </td>
            </tr>
            <tr>
                <td>
                    11:00 -- 12:00
                </td>
                <td>
                    <span id="appoint11"></span>
                </td>
            </tr>
            <tr>
                <td>
                    14:00 -- 15:00
                </td>
                <td>
                    <span id="appoint14"></span>
                </td>
            </tr>
            <tr>
                <td>
                    15:00 -- 16:00
                </td>
                <td>
                    <span id="appoint15"></span>
                </td>
            </tr>
            <tr>
                <td>
                    16:00 -- 17:00
                </td>
                <td>
                    <span id="appoint16"></span>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="closeAppointDiv">
            <input type="button" value="关闭" id="closeAppointBtn">
        </div>
    </div>
</div>

<script src="../js/jquery-2.1.4.js"></script>
<script src="../js/moment.js"></script>
<script src="../js/Appointment.js"></script>


</body>
</html>
