<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script type="text/javascript">
    $(function () {
        $("#album").jqGrid({
            url:"${pageContext.request.contextPath}/album/queryByPage",
            styleUI:"Bootstrap",
            datatype:"json",
            rownumbers:true,
            multiselect:true,
            autowidth: true,
            colNames:["编号","专辑名称","专辑封面","章节数目","专辑得分","专辑作者","播音员","专辑简介","出版时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"cover",edittype:"file",editable:true,formatter:function(cellvalue, options, rowObject) {
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/image/"+cellvalue+ " '/>";
                    }},
                {name:"count",editable:true,},
                {name:"score",editable:true,},
                {name:"author",editable:true,},
                {name:"broadcast",editable:true,},
                {name:"brief",editable:true,},
                {name:"publishTime",edittype:"date",editable:true,}],
            // 分页相关
            pager:"#albumPager",
            viewrecords:true,
            rowNum:4,// 每页显示的行数
            rowList:[4,8,12], // 行数的数组
            page:1,  // 默认显示的页码
            height:"100%",
            //    增删改
            editurl:"${pageContext.request.contextPath}/album/edit",
            //多级表格
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                // we pass two parameters
                // subgrid_id is a id of the div tag created whitin a table data
                // the id of this elemenet is a combination of the "sg_" + id of the row
                // the row_id is the id of the row
                // If we wan to pass additinal parameters to the url we can use
                // a method getRowData(row_id) - which returns associative array in type name-value
                // here we can easy construct the flowing
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${pageContext.request.contextPath}/chapter/queryByPage?id=" + row_id,
                        styleUI:"Bootstrap",
                        rownumbers:true,
                        multiselect:true,
                        autowidth: true,
                        datatype : "json",
                        colNames : [ '编号', '专辑编号', '音频名称', '音频大小','音频路径',"上传时间" ,"操作"],
                        colModel : [
                            {name : "id"},
                            {name : "albumId"},
                            {name : "title",editable:true},
                            {name : "size"},
                            {name : "downPath",editable:true,
                                edittype:"file"},
                            {name:"uploadTime",editable:true,edittype:"date"},
                            {name:"downPath",formatter:function (cellvalue, options, rowObject) {
                                    return  "<a onclick=\"\" class=\"btn btn-primary\"href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"                                }}
                        ],
                        pager : pager_id,
                        rowNum:2,// 每页显示的行数
                        rowList:[4,8,12], // 行数的数组
                        page:1,  // 默认显示的页码
                        editurl:"${pageContext.request.contextPath}/chapter/edit?idd="+ row_id,
                        height : '100%'
                    });
                jQuery("#" + subgrid_table_id).jqGrid("navGrid",
                    "#" + pager_id, {
                    },{
                        //修改
                        closeAfterEdit:true,
                        afterSubmit:function (response) {
                            $.ajaxFileUpload
                            (
                                {
                                    url: "${pageContext.request.contextPath}/chapter/upload",
                                    fileElementId: "downPath",
                                    dataType: "json",
                                    data: { "id": response.responseText},
                                    type:"post",
                                    success: function () {
                                        $("#" + pager_id).trigger("reloadGrid");
                                    }
                                }
                            )
                            return "[true]";
                        }

                    },{
                        //添加
                        closeAfterAdd:true,
                        afterSubmit:function (response) {
                            $.ajaxFileUpload
                            (
                                {
                                    url: "${pageContext.request.contextPath}/chapter/upload",
                                    fileElementId: "downPath",
                                    dataType: "json",
                                    data: { "id": response.responseText},
                                    type:"post",
                                    success: function () {
                                        $("#" + pager_id).trigger("reloadGrid");
                                    }
                                }
                            )
                            return "[true]";
                        },
                    });
            },

        }).jqGrid("navGrid","#albumPager",{},{
            //修改
            closeAfterEdit:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload
                (
                    {
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        dataType: 'json',
                        data: { "id": response.responseText},
                        type:"post",
                        success: function () {
                            $("#album").trigger("reloadGrid");
                        }
                    }
                )
                return "[true]";
            }
        },{
            //添加
            closeAfterAdd:true,
            afterSubmit:function (response) {
                $.ajaxFileUpload
                (
                    {
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        dataType: 'json',
                        data: { "id": response.responseText},
                        type:"post",
                        success: function () {
                            $("#album").trigger("reloadGrid");
                        }
                    }
                )
                return "[true]";
            },

        });
    });


</script>

<div class="page-header">
    <h1>专辑管理</h1>
</div>

<table id="album"></table>

<div id="albumPager">

</div>