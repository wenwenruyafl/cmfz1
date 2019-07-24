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
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath}/css/style.css" />
	<script src="../boot/js/jquery-3.3.1.min.js"></script>
	<script src="../boot/js/bootstrap.3.3.7.min.js"></script>
	<script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
	<script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
	<script src="../boot/js/ajaxfileupload.js"></script>
	<title>持明法洲后台管理系统</title>
</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								${date}
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								<a href="#">欢迎來到持明法洲后台登陸</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						登陆
					</h1>
					<form action="${pageContext.request.contextPath}/cmfz/admin" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									账号:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" />
								</td>
								
							</tr>
							<tr>
								<td valign="middle" align="right">
									验证码:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="code" />
									<img src="/Users/verify"><a href="#">看不清？换一个</a><br/>
								</td>
								
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="确认登陆" />
							&nbsp;&nbsp;

						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					亮哥最帅集团
				</div>
			</div>
		</div>
	</body>
</html>
