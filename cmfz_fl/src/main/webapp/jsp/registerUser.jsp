<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
</head>

<body>
<div class="jumbotron text-center">
    <div>
        <h1>注册</h1>
    </div>
<form  action="${pageContext.request.contextPath}/user/register" method="post">
    用户名(手机号)：<input type="text" name="phone"></br>
    密码：<input type="password" name="password"></br>
    法名：<input type="text" name="dharmaName"></br>
    省份：<input type="text" name="province"></br>
    城市：<input type="text" name="city"></br>
    性别：<input type="text" name="gender"></br>
    个性签名(选填)：<input type="text" name="personalSign"></br>
    头像(选填)：<input type="file"  name="profile"></br>

    <button type="submit" class="btn btn-default">注册</button>
</form>
</div>
${map}
</body>
</html>