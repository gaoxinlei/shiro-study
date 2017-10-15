<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>list.jsp</h1>

<!-- 登出按钮 -->
<a href="${pageContext.request.contextPath }/shiro/logout">logout</a>
<br><br>
<shiro:guest>欢迎游客,请<a href="login.jsp">登陆</a></shiro:guest>
<shiro:user>欢迎有标识用户<a href="logout">登出</a></shiro:user>
<shiro:authenticated>欢迎已验证用户<a href="logout">登出</a></shiro:authenticated>
<shiro:hasAnyRoles name="user,admin">
有user或admin角色的用户
<br>
<!-- user page -->
<a href="${pageContext.request.contextPath }/user.jsp">user page</a>
</shiro:hasAnyRoles>
<shiro:hasRole name="admin">
有admin角色的用户
<br>
<!-- admin page -->
<a href="${pageContext.request.contextPath }/admin.jsp">admin page</a>
</shiro:hasRole>
<shiro:lacksRole name="admin">没有admin角色的用户</shiro:lacksRole>
<shiro:notAuthenticated>未验证用户</shiro:notAuthenticated>
您的用户,非对象不能用property:<shiro:principal></shiro:principal>
<br><br>
<!-- shiro权限注解 -->
<a href="${pageContext.request.contextPath }/qualifiedMethod">授权方法</a>
</body>
</html>