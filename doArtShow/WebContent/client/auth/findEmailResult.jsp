<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과</title>
</head>
<body>
<c:if test="${empty sessionScope.email}">
가입되어 있는 이메일이 없습니다
</c:if>
<c:if test="${!empty sessionScope.email}">
이메일 : ${email}
</c:if>
</body>
</html>