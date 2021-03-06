<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2020/2/8
  Time: 17:37
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
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
        table tbody tr:nth-child(odd){background:#F4F4F4;}
        table tbody td:nth-child(even){color:#C00;}
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
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${pageContext.request.contextPath}/role/add'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form id="roleForm">
                            <table class="table  table-bordered">
                                <thead>
                                <tr >
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox" id="allSelBox"></th>
                                    <th>名称</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody id="roleData">

                                </tbody>
                                <tfoot>
                                <tr >
                                    <td colspan="6" align="center">
                                        <ul id="pageContent" class="pagination">

                                        </ul>
                                    </td>
                                </tr>

                                </tfoot>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/jquery/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/script/docs.min.js"></script>
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<script type="text/javascript">
    var likeFlag = false;
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
        pageQuery(1);

        $("#queryBtn").click(function () {
            var queryText = $("#queryText").val();
            if (queryText === "") {
                likeFlag = false;
            } else {
                likeFlag = true;
            }
            pageQuery(1);
        });

        $("#allSelBox").click(function () {
            var flag = this.checked;

            $("#roleData :checkbox").each(function () {
                this.checked = flag;
            })
        });
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });

    // 分页查询
    function pageQuery(pageNo) {
        var loadingIndex = null;
        var jsonData = {"pageNo":pageNo, "pageSize":5};
        if (likeFlag === true) {
            jsonData.queryText = $("#queryText").val();
        }

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/role/pageQuery",
            data: jsonData,
            beforeSend: function () {
                loadingIndex = layer.msg('处理中', {icon: 16});
            },
            success: function (result) {
                layer.close(loadingIndex);
                if (result.success) {
                    // 局部刷新页面数据
                    var tableContent = "";
                    var pageContent = "";

                    var rolePage = result.data;
                    var roles = rolePage.datas;

                    $.each(roles, function (i, role) {
                        tableContent += '<tr>';
                        tableContent += '    <td>' + (i + 1) + '</td>';
                        tableContent += '    <td><input type="checkbox" name="roleId" value="' + role.id + '"></td>';
                        tableContent += '    <td>' + role.roleName + '</td>';
                        tableContent += '    <td>';
                        tableContent += '    <button type="button" onclick="goAssignPage(' + role.id + ')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                        tableContent += '    <button type="button" onclick="goUpdatePage(' + role.id + ')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                        tableContent += '    <button type="button" onclick="deleteUser(' + role.id +  ', \'' + role.roleName + '\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                        tableContent += '    </td>';
                        tableContent += '</tr>';
                    });

                    if (pageNo > 1) {
                        pageContent += '<li><a href="#" onclick="pageQuery(' + (pageNo - 1) + ')">上一页</a></li>';
                    }

                    for (var i = 1; i <= rolePage.totalNo; i++) {
                        if (i === pageNo) {
                            pageContent += '<li class="active"><a href="#" onclick="pageQuery('+ i +')">' + i + '</a></li>';
                        } else {
                            pageContent += '<li><a href="#" onclick="pageQuery('+ i +')">' + i + '</a></li>';
                        }
                    }

                    if (pageNo < rolePage.totalNo) {
                        pageContent += '<li><a href="#" onclick="pageQuery(' + (pageNo + 1) + ')">下一页</a></li>';
                    }

                    $("#roleData").html(tableContent);
                    $("#pageContent").html(pageContent);
                } else {
                    layer.msg("角色信息查询失败！", {time:1000, icon:5, shift:6}, function () {

                    });
                }
            }
        });
    }

    function goUpdatePage(id) {
        window.location.href = "${pageContext.request.contextPath}/role/edit?id=" + id;
    }

    function deleteUser(id, roleName) {
        layer.confirm("删除角色信息【"+ roleName +"】, 是否继续？",  {icon: 3, title:'提示'}, function(cindex){

            // 删除用户信息
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/role/delete",
                data: {id : id},
                success: function (result) {
                    if (result.success) {
                        pageQuery(1);
                    } else {
                        layer.msg("用户信息删除失败！", {time:1000, icon:5, shift:6}, function () {

                        });
                    }
                }
            });

            layer.close(cindex);
        }, function(cindex){
            layer.close(cindex);
        });
    }

    function deleteUsers() {
        var boxes = $("#roleData :checked");

        if (boxes.length === 0) {
            layer.msg("请选择要删除的用户信息", {time:1000, icon:5, shift:6}, function () {

            });
        } else {
            layer.confirm("删除选择的用户信息, 是否继续？",  {icon: 3, title:'提示'}, function(cindex){

                // 删除选择的用户信息
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/role/deletes",
                    data: $("#roleForm").serialize(),
                    success: function (result) {
                        if (result.success) {
                            pageQuery(1);
                        } else {
                            layer.msg("用户信息删除失败！", {time:1000, icon:5, shift:6}, function () {

                            });
                        }
                    }
                });

                layer.close(cindex);
            }, function(cindex){
                layer.close(cindex);
            });
        }
    }

    function goAssignPage(id) {
        window.location.href = "${pageContext.request.contextPath}/role/assign?id=" + id;
    }
</script>
</body>
</html>
