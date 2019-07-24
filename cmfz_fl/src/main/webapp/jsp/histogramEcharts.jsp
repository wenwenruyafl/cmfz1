<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.js"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script>
        $(function () {
            var myChart = echarts.init(document.getElementById('main'));
            var goEasy = new GoEasy({
                appkey: 'BC-155e769e1ce54593a386d1e2308ca77b'
            });
            goEasy.subscribe({
                channel:'demo_channel',
                onMessage: function(message){
                    var data = JSON.parse(message.content);
                    var option = {
                        title: {
                            text: '持明法洲注册人数一览表'
                        },
                        tooltip: {},

                        legend: {
                            data:['人数']
                        },
                        xAxis: {
                            data: data.month
                        },
                        yAxis: {},
                        series: [{
                            name: '人数',
                            type: 'bar',
                            data: data.count
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });
            $.ajax({
                url:"${pageContext.request.contextPath}/user/histogram",
                type:"post",
                datatype:"json",
                success:function (data) {
                    // 基于准备好的dom，初始化echarts实例


                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '持明法洲注册人数一览表'
                        },
                        tooltip: {},

                        legend: {
                            data:['人数']
                        },
                        xAxis: {
                            data: data.month
                        },
                        yAxis: {},
                        series: [{
                            name: '人数',
                            type: 'bar',
                            data: data.count
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            })
        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

</body>

</html>