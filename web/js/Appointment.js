let date = moment(new Date()).format('YYYY-MM-DD');
getSchedule();
let tbody = $('#schedule');

$('#returnHomePage').click(
    function () {
        location.href = '/JF200626CardFront';
    }
);

//获取排班
function getSchedule() {

    $.ajax({
        url: "ScheduleCtrl.do?method=findSchedule",
        type: "post",
        dataType: "json",
        data: {
            date: date
        },
        success: function (data) {
            if (data.state == 0) {
                // console.log(data.data);
                addTitle();
                fillSchedule(data.data);

            } else {
                alert(data.msgInfo);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

addTitle();

// 添加表头
function addTitle() {
    //清空表数据
    tbody.html('');

    let moment1 = moment(moment(date).startOf('week'));

    let tr = $('<tr>');

    let dep = $("<td>");
    dep.html('科室/日期');

    let monday = $("<td>");
    // moment(new Date(moment(moment(date).startOf('week'))._d)).format('yyyy-MM-dd')
    monday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期一');

    let Tuesday = $("<td>");
    Tuesday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期二');

    let Wednesday = $("<td>");
    Wednesday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期三');

    let Thursday = $("<td>");
    Thursday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期四');

    let Friday = $("<td>");
    Friday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期五');

    let Saturday = $("<td>");
    Saturday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期六');

    let Sunday = $("<td>");
    Sunday.html(moment(new Date(moment1.add(1, 'd'))).format('YYYY-MM-DD') + '<br/>' + '星期天');

    tr.append(dep);
    tr.append(monday);
    tr.append(Tuesday);
    tr.append(Wednesday);
    tr.append(Thursday);
    tr.append(Friday);
    tr.append(Saturday);
    tr.append(Sunday);

    tbody.append(tr);

}

//将排班显示到表格
function fillSchedule(beanList) {
    for (let i = 0; i < beanList.length; i++) {

        let bean = beanList[i];
        // console.log(new Date().getTime());
        // console.log(new Date(bean.schDate).getTime());

        //今天和之前的都不显示
        if (new Date() > new Date(bean.schDate)) {
            continue;
        }

        let depName = bean.depName;

        let tr = $('<tr>');

        let dep = $("<td>");
        dep.html(depName);

        let monday = $("<td>");
        let Tuesday = $("<td>");
        let Wednesday = $("<td>");
        let Thursday = $("<td>");
        let Friday = $("<td>");
        let Saturday = $("<td>");
        let Sunday = $("<td>");

        for (let j = 0; j < beanList.length; j++) {

            let bean = beanList[j];
            //今天和之前的都不显示
            if (new Date() > new Date(bean.schDate)) {
                continue;
            }

            if (depName != bean.depName) {
                continue;
            }

            //获取周几
            let s = moment(bean.schDate).format('d');
            let td;
            if (s == 1) {
                td = monday;
            }

            if (s == 2) {
                td = Tuesday;
            }

            if (s == 3) {
                td = Wednesday;
            }

            if (s == 4) {
                td = Thursday;
            }

            if (s == 5) {
                td = Friday;
            }

            if (s == 6) {
                td = Saturday;
            }

            if (s == 0) {
                td = Sunday;
            }


            addBtn(td, bean);

            beanList.splice(j, 1);
            j--;

        }
        tr.append(dep);
        tr.append(monday);
        tr.append(Tuesday);
        tr.append(Wednesday);
        tr.append(Thursday);
        tr.append(Friday);
        tr.append(Saturday);
        tr.append(Sunday);

        tbody.append(tr);
    }
}

function addBtn(td, bean) {
    let input = $('<input>');
    input.val(bean.userName);
    input.attr('type', 'button');
    input.attr("bean", JSON.stringify(bean));
    input.css('margin-top', '5px');
    input.css('margin-bottom', '5px');
    input.click(appointPanelDisplay);

    if (td.html() != '') {
        td.append($('<br/>'));
    }
    td.append(input);

}

$('#lastWeek').click(function () {

    let tempDate = moment(moment(date).add('days', -7)).format('YYYY-MM-DD');
    //获取今天零点的时间戳
    let time = new Date(new Date().toDateString()).getTime();
    if (time > new Date(tempDate).getTime()) {
        alert('过往时间不能预约');
    } else {
        date = tempDate;
        getSchedule();
    }
});

$('#nextWeek').click(function () {
    date = moment(moment(date).add('days', 7)).format('YYYY-MM-DD');
    getSchedule();
});

//获取预约数据
function appointPanelDisplay() {

    let bean = JSON.parse($(this).attr('bean'));
    if (new Date().getTime() > new Date(bean.schDate)) {
        alert("请提前一天预约");
        return;
    }
    // console.log(bean);
    $.ajax({
        url: "ScheduleCtrl.do?method=findAppointment",
        type: "post",
        dataType: "json",
        data: {
            doctorId: bean.userId,
            schDate: bean.schDate
        },
        success: function (data) {

            if (data.state == 0) {

                fillAppointPanel(data.data);
                $('#appointPanel').show();
            } else {
                alert(data.msgInfo);

            }

            // console.log(data);
        },
        error: function (err) {
            console.log(err);
        }
    });

}


//显示预约面板
function fillAppointPanel(beanList) {


    let appoint9 = $('#appoint9');
    let appoint10 = $('#appoint10');
    let appoint11 = $('#appoint11');
    let appoint14 = $('#appoint14');
    let appoint15 = $('#appoint15');
    let appoint16 = $('#appoint16');

    appoint9.empty();
    appoint10.empty();
    appoint11.empty();
    appoint14.empty();
    appoint15.empty();
    appoint16.empty();

    if (beanList.length > 0) {
        $('#appointPanelTitle').html(beanList[0].userName + '医生门诊时间');
    }

    for (let i = 0; i < beanList.length; i++) {

        let bean = beanList[i];

        let startTime = bean.startTime;
        if (startTime == 9) {
            fillAppoint(appoint9, bean);
        } else if (startTime == 10) {
            fillAppoint(appoint10, bean);
        } else if (startTime == 11) {
            fillAppoint(appoint11, bean);
        } else if (startTime == 14) {
            fillAppoint(appoint14, bean);
        } else if (startTime == 15) {
            fillAppoint(appoint15, bean);
        } else if (startTime == 16) {
            fillAppoint(appoint16, bean);
        }
    }
}

function fillAppoint(appointTd, bean) {
    if (bean.stateName == '待预约') {
        appointTd.html('预约');
        appointTd.attr('class', 'appoint');
        appointTd.attr('bean', JSON.stringify(bean));
        appointTd.unbind("click"); //移除click
        appointTd.bind('click', sendAppoint);
    } else if (bean.stateName == '待取号') {
        appointTd.html(bean.patientName);
        appointTd.attr('class', 'noAppoint');
        appointTd.unbind("click"); //移除click
        appointTd.attr('bean', JSON.stringify(bean));
        appointTd.bind('click', cancelAppoint);
    } else {
        appointTd.html(bean.patientName);
        appointTd.attr('class', 'noAppoint');
        appointTd.unbind("click"); //移除click
        // appointTd.attr('bean', JSON.stringify(bean));
        appointTd.bind('click', haveTakeNum);
    }
}

//发送预约申请
function sendAppoint() {

    let cardNum = $('#cardNum').val();
    if (cardNum == null || cardNum == '') {
        alert('请先读卡');
        return;
    }

    let bean = JSON.parse($(this).attr('bean'));
    if (new Date().getTime() > new Date(bean.schDate)) {
        alert("请提前一天预约");
        return;
    }

    if (!confirm('确定要预约嘛')) {
        return;
    }


    $.ajax({
        url: "ScheduleCtrl.do?method=appoint",
        type: "post",
        dataType: "json",
        data: {
            scheduleId: bean.scheduleId,
            cardNum: cardNum
        },
        success: function (data) {
            if (data.state == 0) {
                readCard();
                hiddenAppointPanel();
            }
            alert(data.msgInfo);
        },
        error: function (err) {
            console.log(err);
        }
    });


}

//隐藏预约面板
$('#closeAppointBtn').click(hiddenAppointPanel);

function hiddenAppointPanel() {
    $('#appointPanel').hide();
}

$('#readCard').click(readCard);

//读卡
function readCard() {
    let cardNum = $('#cardNum').val();
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
    $('#recharge').val('');
}


//取消预约
function cancelAppoint() {


    let cardNum = $('#cardNum').val();
    if (cardNum == null || cardNum == '') {
        alert('请先读卡');
        return;
    }


    if (!confirm('确定要取消预约吗')) {
        return;
    }

    let bean = JSON.parse($(this).attr('bean'));

    $.ajax({
        url: "ScheduleCtrl.do?method=cancelAppoint",
        type: "post",
        dataType: "json",
        data: {
            scheduleId: bean.scheduleId,
            cardNum: cardNum
        },
        success: function (data) {
            if (data.state == 0) {
                readCard();
                hiddenAppointPanel();
            }
            alert(data.msgInfo);
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function haveTakeNum() {
    alert('已取号，不可修改');
}







