<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2020/1/23
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <div class="col-sm-3 col-md-2 sidebar">
        <div class="tree">
            <ul style="padding-left:0px;" class="list-group">
                <c:forEach items="${rootPermission.children}" var="permission">
                    <c:if test="${empty permission.children}">
                        <li class="list-group-item tree-closed" >
                            <a href="${pageContext.request.contextPath}${permission.url}"><i class="${permission.icon}"></i> ${permission.name}</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty permission.children}">
                        <li class="list-group-item tree-closed">
                            <span><i class="${permission.icon}"></i> ${permission.name} <span class="badge" style="float:right">${permission.children.size()}</span></span>
                            <ul style="margin-top:10px;">
                                <c:forEach items="${permission.children}" var="child">
                                    <li style="height:30px;">
                                        <a href="${pageContext.request.contextPath}${child.url}"><i class="${child.icon}"></i> ${child.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
