<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.js"></script>

    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <script>
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            var goEasy = new GoEasy({
                appkey: 'BC-155e769e1ce54593a386d1e2308ca77b'
            });
            goEasy.subscribe({
                channel:'demo_channe2',
                onMessage: function(message){
                    var data = JSON.parse(message.content);
                    // 指定图表的配置项和数据
                    var option = {
                        title : {
                            text: '注册人数',
                            subtext: '纯属虚构',
                            left: 'center'
                        },
                        tooltip : {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data:['男','女']
                        },
                        visualMap: {
                            min: 0,
                            max: 100,
                            left: 'left',
                            top: 'bottom',
                            text:['高','低'],           // 文本，默认为数值文本
                            calculable : true
                        },
                        toolbox: {
                            show: true,
                            orient : 'vertical',
                            left: 'right',
                            top: 'center',
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        series : [
                            {
                                name: '男',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.men
                            },
                            {
                                name: '女',
                                type: 'map',
                                mapType: 'china',
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.mens
                            }

                        ]
                    };;

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });

            $.ajax({
                url:"${pageContext.request.contextPath}/user/mapEcharts",
                type:"post",
                datatype:"json",
                success:function (data) {


                    // 指定图表的配置项和数据
                    var option = {
                        title : {
                            text: '注册人数',
                            subtext: '纯属虚构',
                            left: 'center'
                        },
                        tooltip : {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left',
                            data:['男','女']
                        },
                        visualMap: {
                            min: 0,
                            max: 100,
                            left: 'left',
                            top: 'bottom',
                            text:['高','低'],           // 文本，默认为数值文本
                            calculable : true
                        },
                        toolbox: {
                            show: true,
                            orient : 'vertical',
                            left: 'right',
                            top: 'center',
                            feature : {
                                mark : {show: true},
                                dataView : {show: true, readOnly: false},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },
                        series : [
                            {
                                name: '男',
                                type: 'map',
                                mapType: 'china',
                                roam: false,
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.men
                            },
                            {
                                name: '女',
                                type: 'map',
                                mapType: 'china',
                                label: {
                                    normal: {
                                        show: false
                                    },
                                    emphasis: {
                                        show: true
                                    }
                                },
                                data:data.mens
                            }

                        ]
                    };;

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

