<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id', {
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
    });
</script>

<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>

