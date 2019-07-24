<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>


    <script type="text/javascript">
    $(function () {
        $("#carousel").jqGrid({
            url:"${pageContext.request.contextPath}/carousel/queryByPage",
            styleUI:"Bootstrap",
            datatype:"json",
            rownumbers:true,
            multiselect:true,
            autowidth: true,
            colNames:["编号","轮播图名称","轮播图图片","状态","创建时间"],
            colModel:[{
                name:"id",
            }, {
                name:"title",editable:true,
            },{
                name : "imgPath", edittype : "file",editable:true,formatter:function(cellvalue, options, rowObject) {
                    return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/image/"+cellvalue+ " '/>";
                }
            },{
                name:"status",editable:true,
            },{
                name:"createTime",edittype:"date",editable:true
            }],
            // 分页相关
            pager:"#pag",
            viewrecords:true,
            rowNum:4,// 每页显示的行数
            rowList:[4,8,12], // 行数的数组
            page:1,  // 默认显示的页码
            height:"100%",
        //    增删改
            editurl:"${pageContext.request.contextPath}/carousel/edit"

        }).jqGrid("navGrid","#pag",{},{
            //修改
            closeAfterEdit:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload
                (
                    {
                        url: "${pageContext.request.contextPath}/carousel/upload",
                        secureuri: false,
                        fileElementId: "imgPath",
                        dataType: 'json',
                        data: { "id": response.responseText},
                        typr:"post",
                        success: function () {
                            $("#carousel").trigger("reloadGrid")
                        }
                    }
                )
                return "[true]";
            }
        },{
            //添加
            claseAfterAdd:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload
                (
                    {
                        url: "${pageContext.request.contextPath}/carousel/upload",
                        fileElementId: "imgPath",
                        dataType: 'json',
                        data: { "id": response.responseText},
                        type:"post",
                        success: function () {
                            $("#carousel").trigger("reloadGrid");
                        }
                    }
                )
                return "[true]";
            }
        });
    });


</script>

<div class="page-header">
    <h1>轮播图管理</h1>
</div>

<table id="carousel"></table>

<div id="pag">

</div>
