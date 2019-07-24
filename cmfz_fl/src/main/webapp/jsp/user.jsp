<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>


    <script type="text/javascript">
    $(function () {
        $("#user").jqGrid({
            url:"${pageContext.request.contextPath}/user/queryByPage",
            styleUI:"Bootstrap",
            datatype:"json",
            rownumbers:true,
            multiselect:true,
            autowidth: true,
            colNames:["编号","账号","密码","盐","法名","省","市","性别","个性签名","头像","状态","注册时间"],
            colModel:[{
                name:"id",
            }, {
                name:"phone",
            },{
                name : "password",
            },{
                name:"salt",
            },{
                name:"dharmaName",
            },{name:"province"},
                {name:"city"},
                {name:"gender"},
                {name:"personalSign"},
                {name:"profile",edittype:"file",formatter:function(cellvalue, options, rowObject) {
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/image/"+cellvalue+ " '/>";
                    }},
                {name:"status",editable:true},
                {name:"registTime",edittype:"date"}],
            // 分页相关
            pager:"#userPager",
            viewrecords:true,
            rowNum:4,// 每页显示的行数
            rowList:[4,8,12], // 行数的数组
            page:1,  // 默认显示的页码
            height:"100%",
        //    增删改
            editurl:"${pageContext.request.contextPath}/user/edit"

        }).jqGrid("navGrid","#userPager",{ edit : true,
            add : false,
            del : true},{
            //修改
            closeAfterEdit:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload
                (
                    {
                        url: "${pageContext.request.contextPath}/user/upload",
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
        }
        );
    });


</script>

<div class="page-header">
    <h1>用户管理</h1>
</div>

<table id="user"></table>

<div id="userPager">

</div>
<div class="page-header">
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/exportUser" role="button">一键导出</a>
    <form action="${pageContext.request.contextPath}/user/importUser" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" class="btn btn-primary" value="一键导入">
    </form>
</div>
