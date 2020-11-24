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
                getCardSer();
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
    $('#mainTable').html('');

}

$('#returnHomePage').click(
    function () {
        location.href = '/JF200626CardFront';
    }
);


//获取卡账单
let currentPage = 1;
let pageSize = 5;
let totalPage = 1;

function getCardSer() {

    $.ajax({
        url: "CardSerCtrl.do?method=findCardSer",
        type: "post",
        dataType: "json",
        data: {
            cardNum: $('#cardNum').val(),
            currentPage: currentPage,
            pageSize: pageSize
        },
        success: function (data) {
            if (data.state == 0) {
                console.log(data.data);
                freshTable(data.data);
                updatePage(data.pageBean);
            } else {
                alert(data.msgInfo);
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

//动态加载到主界面表格
//刷新表格
function freshTable(arr) {
    let tableBody = $('#mainTable');
    tableBody.html('');

    for (let i = 0; i < arr.length; i++) {
        let o = arr[i];
        tableBody.append(addTr(o));
    }
}


//添加行
function addTr(viewCardSer) {

    let tr = $('<tr>');

    let td1 = $("<td>");
    td1.html(moment(viewCardSer.serTime).format('YYYY-MM-DD HH:mm:ss'));

    let td2 = $("<td>");
    td2.html(viewCardSer.serType);

    let td3 = $("<td>");
    td3.html(viewCardSer.serCost);

    let td4 = $("<td>");
    td4.html(viewCardSer.userName);

    tr.append(td1);
    tr.append(td2);
    tr.append(td3);
    tr.append(td4);
    return tr;
}

//换页
function goPage(val) {
    currentPage += val;
    if (currentPage > 0 && currentPage <= totalPage) {
        getCardSer();
    } else {
        currentPage -= val;
    }
}

function updatePage(pageBean) {

    let pageSpan = document.getElementById('pageSpan');
    totalPage = pageBean.totalPage;
    pageSpan.innerHTML = pageBean.currentPage + '/' + totalPage;

}










