<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- module/top.jsp 회원가입, 회원로긴  -->
<% 
try {
	if(session.getAttribute("id")==null) {
%>
<nav class="navbar navbar-inverse navbar-fixed-top"> <!-- 메뉴 상단 고정 -->
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
				<a class="navbar-brand" href="#">도서 쇼핑몰</a>
		</div>
		<div >
			<div class="collapse navbar-collapse" id="myNavbar"> <!-- 회원가입 페이지로 넘어가려면 폼이 필요한다.  -->
				<form class="navbar-form navbar-right" action="../shopping/loginPro.jsp" >
					<div class="form-group">
						<input type="text" class="form-control" name="id" size="12"
							maxlength="12" placeholder="아이디">
						<input type="password" class="form-control" name="passwd" size="12"
							maxlength="12" placeholder="비밀번호">
					</div>
					<button type="submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-log-in" > 로그인</span></button>
					<a href="../shopping/logon/memberInsertForm.jsp" class="btn btn-danger"
						aria-pressed="true"><span class="glyphicon glyphicon-user"> 회원가입</span></a>		
				</form>	
			</div>
		</div>
	
	</div>
</nav>
		
	
	
		
<%	}else{ %>
<nav class="navbar navbar-inverse navbar-fixed-top"> <!-- 메뉴 상단 고정 -->
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
				<a class="navbar-brand" href="#">도서 쇼핑몰</a>
		</div>
		<div class = "form-group navbar-right">
			<p class = "navbar-text"><b><%=session.getAttribute("id") %>님, 즐거운 쇼핑시간 되십시오.</b></p>
			<a href = "../shopping/shopMain.jsp" class="btn btn-success" aria-pressed="true">
				<span class="glyphicon glyphicon-eye-open"> 메인으로</span></a>
				
			<a href = "../shopping/cartList.jsp?book_kind=all" class="btn btn-primary" aria-pressed="true">
				<span class="glyphicon glyphicon-shopping-cart"></span> 장바구니보기</a>	
				
			<!-- 구매목록, 회원정보, 로그아웃 -->
			<a href = "../shopping/buyList.jsp" class="btn btn-warning" aria-pressed="true">
				<span class="glyphicon glyphicon-list-alt"></span> 구매목록보기</a>
				
			<a href = "../shopping/logon/memberUpDelForm.jsp" class="btn btn-info" aria-pressed="true">
				<span class="glyphicon glyphicon-user"></span> 회원정보수정탈퇴</a>	
				
			<a href = "../shopping/logout.jsp" class="btn btn-danger" aria-pressed="true">
				<span class="glyphicon glyphicon-log-out"></span> 로그아웃</a>		
		</div>







	</div>
</nav>		
		
		
<%	}
}catch(NullPointerException e) {
	e.printStackTrace();
}catch(Exception e) {
	e.printStackTrace();
}
%>