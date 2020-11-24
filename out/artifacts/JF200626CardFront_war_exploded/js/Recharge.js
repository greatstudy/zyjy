//读卡
$('#readCard').click(
    function () {

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
                } else {
                    cancelDisplay();
                    alert(data.msgInfo);

                }
            },
            error: function (err) {
                console.log(err);
            }
        });
    }
);


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

//充值
$('#rechargeBtn').click(
    function () {

        if (!confirm("确认要充值嘛")) {
            return;
        }

        let cardNum = $('#cardNum').val();
        let money = $('#recharge').val();
        // if (cardNum == null || cardNum == '') {
        //     alert('请输入卡号');
        //     return;
        // }
        if (cardNum == null || cardNum == '' || cardNum.length != 10) {
            alert('请输入完整十位卡号,并读卡');
            return;
        }

        if (money == null || money == '' || money <= 0) {
            alert('请输入大于零的充值金额');
            return;
        }

        $.ajax({
            url: "CardCtrl.do?method=recharge",
            type: "post",
            dataType: "json",
            data: {
                cardNum: cardNum,
                money: money
            },
            success: function (data) {
                alert(data.msgInfo);
                // if (data.state == 0) {
                //     cancelDisplay();
                // }
                $('#recharge').val('');

            },
            error: function (err) {
                console.log(err);
            }
        });
    }
);

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


$('#returnHomePage').click(
    function () {
        location.href = '/JF200626CardFront';
    }
);