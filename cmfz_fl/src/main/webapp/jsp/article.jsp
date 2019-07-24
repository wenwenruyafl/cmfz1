<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>

    <script type="text/javascript">
    $(function () {

            KindEditor.create('#editor_id', {
                width : '700px',
                //图片上传路径
                uploadJson:'${pageContext.request.contextPath}/article/upload',
                //    图片空间路径
                fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
                //显示浏览远程服务器按钮
                allowFileManager:true,
                //指定上传文件form名称。
                filePostName:'file',
                afterBlur:function () {
                    this.sync();
                }
        });

        KindEditor.create('#editor1_id', {
            width : '700px',
            //图片上传路径
            uploadJson:'${pageContext.request.contextPath}/article/upload',
            //    图片空间路径
            fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
            //显示浏览远程服务器按钮
            allowFileManager:true,
            //指定上传文件form名称。
            filePostName:'file',
            afterBlur:function () {
                this.sync();
            }
        });

        $("#article").jqGrid({
            url:"${pageContext.request.contextPath}/article/queryByPage",
            styleUI:"Bootstrap",
            datatype:"json",
            rownumbers:true,
            multiselect:true,

            autowidth: true,
            colNames:["编号","上师编号","文章标题","内容","发布时间"],
            colModel:[{
                name:"id",
            }, {
                name:"guruId",
            },{
                name : "title",editable:true,
            },{
                name:"id",formatter:function (celVal, row, jsonRow) {
                    return "<button class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" data-target=\"#myModal2\" onclick='preview(\""+jsonRow.id+"\")'>预览</button> ";


                <%--formatter:function (cellvalue, options, rowObject) {--%>
                <%--    return  "<a type=\"button\" class='btn btn-primary btn-lg' data-toggle=\"modal\" data-target=\"#myModal2\" id=\"preview\" " +--%>
                <%--        "href=\"${pageContext.request.contextPath}/article/preview?id="+cellvalue+"\">预览</a> ";--%>
                }},{
                name:"publishTime",edittype:"date",editable:true
            }],
            // 分页相关
            pager:"#articlePager",
            viewrecords:true,
            rowNum:4,// 每页显示的行数
            rowList:[4,8,12], // 行数的数组
            page:1,  // 默认显示的页码
            height:"100%",
        //    增删改
            editurl:"${pageContext.request.contextPath}/article/edit"

        }).jqGrid("navGrid","#articlePager",{edit : true,
            add : false,
            del : true});
    });
function addArtcle() {
    $.ajax({
        url:"${pageContext.request.contextPath}/article/addArticle",
        type:"post",
        closeAfterAdd:true,
        datatype:"json",
        data:$("#acc").serialize(),
        success:function () {
            $("#article").trigger("reloadGrid");
        }

    })
}
    function allGuru(){
        $.get("${pageContext.request.contextPath}/guru/queryAllGuru",function (data) {
            $("#guruId").empty();
            for(var i=0; i<data.length; i++){
                $("#guruId").append($("<option value='"+data[i].id+"'></option>").text(data[i].name).val(data[i].id));
            }
        },"json")
    }

    function preview(ids){
        $.get("${pageContext.request.contextPath}/article/preview","id="+ids,function (data) {
                 $("#aaa").empty();
                 var title=$("<text></text></br>").text(data.title);
                var guruname=$("<text></text></br>").text(data.guruId);
                var content=$("<div>"+data.content+"</div></br>")
                $("#aaa").append(title).append(guruname).append(content);

        },"json")
    }

</script>

<div class="page-header">
    <h1>文章管理</h1>
</div>

<%--文章上传模态框--%>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" id="uploading" onclick="allGuru()">
    文章上传
</button>

<!-- Modal -->
<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>

            <form action="javascript:void(0)" id="acc">
            <div class="modal-body">

                <input type="text" class="form-control" name="title">
<%--                下拉菜单大师--%>
                <div>
                    选择上师：
                    <select class="form-control" name="guruName" id="guruId" >

                    </select>
                </div>
                <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="addArtcle()">上传</button>
            </div>
            </form>
        </div>
    </div>
</div>


<table id="article"></table>

<div id="articlePager">
</div>



<!-- Modal -->
<div class="modal fade" id="myModal2"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">Modal title</h4>
            </div>

            <div class="modal-body" id="aaa">
<%--                文章标题：--%>
<%--                <input id="title"  name="title">--%>
                <%--                下拉菜单大师--%>
<%--                <div>--%>
<%--                    作者上师：--%>
<%--                    <input type="text" class="form-control" name="guru" value="${article.id}">--%>
<%--                </div>--%>
<%--                <textarea id="editor1_id" name="content" style="width:700px;height:300px;">--%>
<%--                &lt;strong&gt;${article.content}&lt;/strong&gt;--%>
<%--                </textarea>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

