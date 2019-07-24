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
    <title>持明法洲后台管理系统</title>
</head>
<body>
    <%--标题导航栏--%>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="#" class="navbar-brand">持明法洲后台管理系统v1.0</a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span >退出登陆</span>
                        <span class="glyphicon glyphicon-log-in"></span>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span>欢迎：${username}</span>
                        <span class="glyphicon glyphicon-user"></span>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
    <%--页面主体--%>
    <div class="row">
        <div class="col-md-2">
            <%--             collapse--%>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                 轮播图
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                   <li role="presentation"><a href="javascript:$('#center').load('carousel.jsp')" >轮播图管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                专辑
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li role="presentation"><a href="javascript:$('#center').load('album.jsp')" >专辑和文章管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li role="presentation"><a href="javascript:$('#center').load('article.jsp')" >文章管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                用户
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li role="presentation"><a href="javascript:$('#center').load('user.jsp')" >用户管理</a></li>
                                <li role="presentation"><a href="javascript:$('#center').load('histogramEcharts.jsp')" >用户注册柱状图</a></li>
                                <li role="presentation"><a href="javascript:$('#center').load('mapEcharts.jsp')" >用户在全国地区的注册分布图</a></li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-10" id="center">
            <div class="jumbotron">
                <div class="container">
                   <strong> <h3>欢迎来到持明法洲后台管理系统</h3> </strong>
                </div>
            </div>

            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/1.png" class="img-responsive center-block">
                        <div class="carousel-caption">
                        ...
                       </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/2.png" class="img-responsive center-block">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/3.png" class="img-responsive center-block">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div><div class="item">
                    <img src="${pageContext.request.contextPath}/img/4.png" class="img-responsive center-block">
                    <div class="carousel-caption">
                        ...
                    </div>
                </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>


        </div>


    </div>
    <%--页尾--%>
    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="text-center" style="height: 50px;line-height: 50px">
            <span>@百置教育 baizhi@zparkhr.com.cn</span>
        </div>
    </nav>
</body>
</html>