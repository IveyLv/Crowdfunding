<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2020/2/12
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
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
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form id="roleForm" role="form" class="form-inline">
                        <input type="hidden" name="userId" value="${user.id}">
                        <div class="form-group">
                            <label for="leftList">未分配角色列表</label><br>
                            <select id="leftList" name="unAssignRoleIds" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${unAssignedRoles}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="left2RightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="right2LeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="rightList">已分配角色列表</label><br>
                            <select id="rightList" name="assignRoleIds" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                                <c:forEach items="${assignedRoles}" var="role">
                                    <option value="${role.id}">${role.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
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

        $("#left2RightBtn").click(function () {
            var opts = $("#leftList :selected");
            if (opts.length === 0) {
                layer.msg("请选择需要分配的角色数据！", {time:1000, icon:5, shift:6}, function () {

                });
            } else {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/user/doAssign",
                    data: $("#roleForm").serialize(),
                    success: function (result) {
                        if (result.success) {
                            $("#rightList").append(opts);
                            layer.msg("角色数据分配成功！", {time:1000, icon:6}, function () {

                            });
                        } else {
                            layer.msg("角色数据分配失败！", {time:1000, icon:6, shift:6}, function () {

                            });
                        }
                    }
                });
            }
        });

        $("#right2LeftBtn").click(function () {
            var opts = $("#rightList :selected");
            if (opts.length === 0) {
                layer.msg("请选择需要取消分配的角色数据！", {time:1000, icon:5, shift:6}, function () {

                });
            } else {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/user/undoAssign",
                    data: $("#roleForm").serialize(),
                    success: function (result) {
                        if (result.success) {
                            $("#leftList").append(opts);
                            layer.msg("取消角色数据分配成功！", {time:1000, icon:6}, function () {

                            });
                        } else {
                            layer.msg("取消角色数据分配失败！", {time:1000, icon:6, shift:6}, function () {

                            });
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>

