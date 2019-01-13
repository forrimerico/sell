<!DOCTYPE html>
<html lang="en">
<#include "../Common/title.ftl">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label><input type="checkbox" />Remember me</label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div id="login-btn" class="btn btn-default">Sign in</div>
                    </div>
                </div>
        </div>
    </div>
</div>
</body>
<script>
$("#login-btn").on('click', function() {
    var username = $("#inputEmail3").val();
    var password = $("#inputPassword3").val();
    $.ajax({
        url:"/sell/admin/login/verify",
        type: 'POST',
        dataType : "json",
        data: {
            username: username,
            password: password
        },
        success:function(result){
            if (result.code === 0) {
                alert(result.message);
                setTimeout(function () {
                    location.href = "/sell/seller/order/list";
                }, 300);
            } else {
                alert(result.message);
            }
        },
        error: function (result) {
            alert('error')
        }
    });
})
</script>
</html>