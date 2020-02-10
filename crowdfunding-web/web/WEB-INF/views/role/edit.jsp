<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2020/2/8
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
        </div>
        <jsp:include page="../head.jsp"/>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../left.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form role="form" id="roleForm">
                        <div class="form-group">
                            <label for="roleName">角色名称</label>
                            <input type="text" class="form-control" id="roleName" value="${role.roleName}" placeholder="请输入登陆账号">
                        </div>
                        <button type="button" id="updateBtn" class="btn btn-primary"><i class="glyphicon glyphicon-pencil"></i> 修改</button>
                        <button type="button" id="resetBtn" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/script/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });

        // 修改用户
        $("#updateBtn").click(function () {
            var roleName = $("#roleName").val();
            if (roleName === "") {
                layer.msg("角色名称不能为空，请输入...", {time:1000, icon:5, shift:6}, function () {

                });
                return;
            }

            var loadingIndex = null;
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/role/update",
                data: {
                    "id": ${role.id},
                    "roleName": roleName
                },
                beforeSend: function () {
                    loadingIndex = layer.msg('处理中', {icon: 16});
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if (result.success) {
                        layer.msg("用户信息修改成功！", {time:1000, icon:6}, function () {
                            window.location.href = "${pageContext.request.contextPath}/role/index"
                        });
                    } else {
                        layer.msg("用户信息修改失败！", {time:1000, icon:5, shift:6}, function () {

                        });
                    }
                }
            });
        });

        // 重置用户信息
        $("#resetBtn").click(function () {
            // $(JQuery)[0] ==> DOM
            // $(DOM) ==> JQuery

            $("#roleForm")[0].reset();
        });
    });
</script>
</body>
</html>
