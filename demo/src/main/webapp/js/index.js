$(document).ready(function () {

    bindEvent();

    function bindEvent() {
        $("#js_login").on("click", function () {
            var username = $("#js_username").val();
            var password = $("#js_password").val();
            requestLogin(username, password);
        });
    }

    function requestLogin(username, password) {
        if (!username) {
            alert("请输入用户名");
            return;
        }
        if (!password) {
            alert("请输入密码");
            return;
        }

        var params = JSON.stringify({
            account: username,
            password: password
        });

        $.ajax({
            url: "http://localhost:8080/main/api/login",
            type: "POST",
            data: params,
            contentType: "application/json",
            success: function (data) {
                if (!data || data.code) {
                    alert(data.msg);
                    return;
                }
                alert("登录成功");
                window.location.href = "success";
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
    }
});