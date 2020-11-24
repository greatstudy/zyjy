$('#readCard').click(readCard);

//读卡
function readCard() {
    let cardNum = $('#cardNum').val();
    // if (cardNum == null || cardNum == '') {
    //     alert('请输入卡号');
    //     return;
    // }
    if (cardNum == null || cardNum == '' || cardNum.length != 10) {
        alert('请输入完整十位卡号,并读卡');
        return;
    }
    $.ajax({
        url: "PatientCtrl.do?method=readCard",
        type: "post",
        dataType: "json",
        data: {
            cardNum: cardNum
        },
        success: function (data) {
            // console.log(data.data);
            if (data.state == 0) {
                let card = data.data[0];
                infoDisplay(card);
                getAppointment();
            } else {
                alert(data.msgInfo);
                cancelDisplay();
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

//显示
function infoDisplay(bean) {

    $('#patientName').html(bean.patientName);
    $('#sex').html(bean.sex);
    $('#age').html(bean.age);
    $('#native').html(bean.nativeArea);
    $('#idNum').html(bean.idNum);
    $('#tel').html(bean.tel);
    $('#addr').html(bean.addr);
    $('#balance').html(bean.balance);
    $('#deposit').html(bean.deposit);

}

//清空显示
function cancelDisplay() {
    $('#cardNum').val('');
    $('#patientName').html('');
    $('#sex').html('');
    $('#age').html('');
    $('#native').html('');
    $('#idNum').html('');
    $('#tel').html('');
    $('#addr').html('');
    $('#balance').html('');
    $('#deposit').html('');
    $('#appoint').html('');


}

$('#returnHomePage').click(
    function () {
        location.href = '/JF200626CardFront';
    }
);


function getAppointment() {
    let cardNum = $('#cardNum').val();
    $.ajax({
        url: "ScheduleCtrl.do?method=getAppointment",
        type: "post",
        dataType: "json",
        data: {
            cardNum: cardNum
        },
        success: function (data) {
            if (data.state == 0) {
                fillAppointment(data.data);
            } else {
                alert(data.msgInfo);
                cancelDisplay();
                $('#appoint').empty();
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function fillAppointment(beanList) {
    $('#appoint').empty();
    for (let i = 0; i < beanList.length; i++) {
        // console.log(beanList[i]);

        $('#appoint').append(addTr(beanList[i]));
    }
}

//添加行
function addTr(bean) {

    let tr = $('<tr>');

    let td1 = $("<td>");
    let checkBox = $("<input>");
    checkBox.attr('type', 'checkBox');
    checkBox.attr('bean', JSON.stringify(bean));
    // checkBox.attr('name', 'checkBox');
    checkBox.bind('click', checkDate);
    td1.html(checkBox);

    let td2 = $("<td>");
    td2.html(moment(bean.appointTime).format('YYYY-MM-DD'));

    let td3 = $("<td>");
    td3.html(bean.schDate);

    let td4 = $("<td>");
    td4.html(bean.userName);

    let td5 = $("<td>");
    td5.html(bean.depName);

    tr.append(td1);
    tr.append(td2);
    tr.append(td3);
    tr.append(td4);
    tr.append(td5);
    return tr;
}

function checkDate() {
    let bean = JSON.parse($(this).attr('bean'));
    //获取日期和时间
    if (new Date(bean.schDate + ' ' + (bean.startTime + 1) + ':00').getTime() <= new Date().getTime()) {
        alert('已过期不能取号，请联系管理员');
        $(this).attr('checked', false);
    }
}

$('#TakeNumberBtn').click(takeNum);

//发送取号请求
function takeNum() {

    let cardNum = $('#cardNum').val();
    if (cardNum == null || cardNum == '' || cardNum.length != 10) {
        alert('请输入完整十位卡号,并读卡');
        return;
    }

    let checkBoxArr = $("input[type='checkbox']");
    let schIdArr = new Array();

    for (let i = 0; i < checkBoxArr.length; i++) {
        let checkBox = checkBoxArr[i];
        if (checkBox.checked) {
            let bean = JSON.parse(checkBox.getAttribute('bean'));
            schIdArr.push(bean.scheduleId);
        }
    }

    if (schIdArr.length == 0) {
        alert('请先选择要取号预约');
        return;
    }

    if (!confirm("确认要取号嘛")) {
        return;
    }


    $.ajax({
        url: "ScheduleCtrl.do?method=takeNumber",
        type: "post",
        dataType: "json",
        data: {
            cardNum: cardNum,
            schIdArr: JSON.stringify(schIdArr)
        },
        success: function (data) {
            if (data.state == 0) {
                getAppointment();
                let info = '';
                for (let i = 0; i < data.data.length; i++) {
                    info += data.data[i].msgInfo;
                }

                alert("取号成功：\n" + info);

            } else {
                alert(data.msgInfo);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}



