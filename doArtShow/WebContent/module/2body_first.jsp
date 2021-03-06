<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="isLogin" value="${sessionScope.member}"/>

    <!-- 메인로고나 top 버튼 클릭시 최상단 좌표 역할 #page-top -->
    <!-- <span id="page-top">&nbsp;</span> -->
    <div id="page-top-div"></div>
    <!--최상단 Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top my-nav-top">
        <div id="top-container">
            <!-- 모바일에서 출력 -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <%-- top-mainlogo-img --%>
                <a class="navbar-brand page-scroll" id="top-mainlogo-a" href="/doArtShow"><div class="navbar" style="width:100px;height:30px;"></div>
                <!-- <img src="/doArtShow/resourcesImages/mainlogo2.png"> -->
                </a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">



                <ul class="nav navbar-nav navbar-right">
                	
                	<li style="margin-top: 10px;">
                		<form name="searchForm" action="<%=request.getContextPath()%>/search.do" onsubmit="return chkSearch(this.form)" method="get">
						<div class="cntr" style="display:contents;">
							<div class="cntr-innr" style="display: inline-block;">
								<label class="search navbar" for="inpt_search">
                    				<input type="text" id="inpt_search" name="search" style="font-family: 'Noto Sans KR', sans-serif;" />
                    				<input type="submit" class="hidden" value="검색">
                    			</label>
                    		</div>
                    	</div>
                    	</form>
                	</li>
                    
                    <li>
                        <a class="page-scroll acss" href="<%=request.getContextPath()%>/client/exhibition/ExListView.do" id="top-list">
                    	  	 전시목록
                        </a>
                    </li>
                    <li>
                        <a class="page-scroll acss" href="<%=request.getContextPath()%>/searchMap.do" id="top-list">
                        	지도
                        </a>
                    </li>
                    <li>
                        <a class="page-scroll acss" href="<%=request.getContextPath() %>/client/exhibition/addForm.do" id="top-list">
                        	전시등록
                        </a>
                    </li>
                    <li>
                        <a class="page-scroll acss" href="" onclick="return false" id="myBtn">
                        	로그인
                        </a>
                    </li>
                    <li>
                        <a class="page-scroll acss" href="" onclick="return false" id="myBtn">
                        	문의하기
                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
    <!--/ 최상단 Navigation -->


    <!-- 로그인 Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content" id="login-content">
                <div class="modal-header">
                    <h4 class="modal-title"> </h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">

                    <div id="login-wrap">
                    
                         <form method="post" name="loginForm" action="<%=request.getContextPath()%>/client/auth/memberLogIn.do">
                            <div class="login-input">
                                <input class="form-control" type="email" placeholder="이메일" name="email" id="login_email">
                                <div style="margin-top:5px" id="chkLoginEmail"></div>
                            </div>
                            
                            <div class="login-input">
                            	<input class="form-control" type="password" placeholder="비밀번호" name="pw" id="login_pw">
                            	<div style="margin-top:5px" id="chkLoginPw"></div>
                            </div>
                                <input type="submit" class="btn btn-info" value="로그인" onclick="javascript:alert(document.getElementById('login_email').value);" style="margin-bottom: 10px;">
   						 </form>
                         
                            <div class="login-input">
                                <a href="<%=request.getContextPath()%>/client/auth/findEmail.do">이메일</a> 
                                / <a href="<%=request.getContextPath()%>/client/auth/findPw.do">비밀번호 찾기</a><br>
                            </div>
                            <hr>
                            	<h5 style="display: inline;font-family: 'Noto Sans KR', sans-serif;font-size: 17px;font-weight: bold;">SNS로 간편 회원가입/로그인</h5>
                            <div id="login-btngroup">
                                
                                 <a id="kakao-login-btn"></a>
                                 <div id="naverIdLogin"></div>
                             <a class="btn btn-danger" href="<%=request.getContextPath()%>/client/auth/memberAdd.do">이메일로 회원가입</a>
                            </div>


                    </div>


                </div>
            </div>
        </div>
    </div>





<div class="float-button-container">
  <div class="open-button" id="backtop">
  </div>
  <a href="#" alt="Settings" title="Settings"><div class="item01"></div></a>
  <a href="#" alt="Notifications" title="Notifications"><div class="item02"></div></a>
  <a href="<%=request.getContextPath() %>/client/exhibition/addForm.do" alt="전시등록" title="전시등록"><div class="item03"></div></a>
  <a href="#" id="myBtn" alt="My profile" title="마이 페이지"><div class="item04"></div></a>
</div>


<style>

/* jungmi : myPage의 프로필 부분 */
@media (min-width: 1340px){
	#profileDiv{
		width : 20vw;
	}
}


/* 상단 네비게이션 목록들  */
.acss {
/*   position: relative;
  margin: 3rem;
  text-decoration: none;
  color: black;
  font-family: sans-serif;
  font-size: 2rem; */
}
.acss::after {
  content: "";
  display: block;
  position: absolute;
}
.acss::before {
  content: "";
  display: block;
  position: absolute;
}

.acss:first-of-type::after {
  left: 50%;
  bottom: -0.5rem;
  width: 0%;
  height: 0.2rem;
  background-color: black;
  transform: translateX(-50%);
  transition: all 0.3s;
}
.acss:first-of-type:hover::after {
  width: 100%;
}







.float-button-container {
  position: fixed;
  right: 20px;
  bottom: 20px;
  height: 70px;
  width: 70px;
  cursor: pointer;
  z-index: 9999;
}
.open-button {
  position: absolute;
  background: #ffaf66;
  height: 70px;
  width: 70px;
  bottom: 0;
  transform: scale(0.8, 0.8);
  border-radius: 100px;
  z-index: 999;
  transition: all 0.3s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
.open-button:before, .item01:before, .item02:before, .item03:before, .item04:before {
  position: absolute;
  font-family: "FontAwesome";
  font-size: 3.5rem;
  top: 50%;
  left: 50%;
  color: black;
  transform: translate(-50%, -50%);
}
.open-button:before {
  content: "\f0aa";
  color: white;
}
.item01:before {
  content: "\f013";
}
.item02:before {
  content: "\f0f3";
}
.item03:before {
  content: "\f040";
}
.item04:before {
  content: "\f007";
}
.item01, .item02, .item03, .item04 {
  position: absolute;
  background: #fff;
  height: 70px;
  width: 70px;
  border-radius: 100px;
  bottom: 0;
  transform: scale(0.4, 0.4);
  transition: all 0.3s cubic-bezier(0.68, -0.15, 0.265, 1.15);
}
.item01:hover, .item02:hover, .item03:hover, .item04:hover {
  background: #ff7a00;
}
.float-button-container:hover {
  height: 100%;
}
.float-button-container:hover .open-button {
  transform: scale(1, 1);
}
.float-button-container:hover .item01,
.float-button-container:hover .item02,
.float-button-container:hover .item03,
.float-button-container:hover .item04 {
  transform: scale(0.6, 0.6);
}
.float-button-container:hover .item01 {
  bottom: 90px;
}
.float-button-container:hover .item02 {
  bottom: 160px;
}
.float-button-container:hover .item03 {
  bottom: 230px;
}
.float-button-container:hover .item04 {
  bottom: 300px;
}

</style>



<%-- 
    <div id="back-top">
    
    	
    
        <c:choose>
    	<c:when test="${empty isLogin}">
        
        <a data-toggle="modal" href="myBtn" id="myBtn" style="line-height:750%;">
        <span class="item" style="color: #F17F42; align-items: center; font-family: 'Noto Sans KR', sans-serif;">로그인</span>
        </a>
    	</c:when>
    	
    	
    	
    	<c:when test="${!empty isLogin}">
    	
    	<a href="<%=request.getContextPath()%>/client/auth/memberLogOut.do" id="myBtn" style="line-height:750%;">
        <span class="item" style="color: #F17F42; align-items: center; font-family: 'Noto Sans KR', sans-serif;">로그아웃</span>
        </a>
    	
    	</c:when>
        
    </c:choose>
        
        <a href="
        <c:choose>
        <c:when test="${!empty isLogin}">
        <%=request.getContextPath() %>/client/exhibition/addForm.do 		
        </c:when>
        
        <c:when test="${empty isLogin}">
        javascript:addFormLogin();
        </c:when>
        
    	</c:choose>
        ">
        <span class="item" style="color: #CE6D39; font-family: 'Noto Sans KR', sans-serif; "><br><br>전시<br><br>등록</span>
        </a>
        
        
        <a href="#" id="backtop"><i class="fa fa-angle-up"></i></a>
<!--         <a href="#page-top"><i class="fa fa-angle-up"></i></a> -->
    </div>
    --%>
   
   
   
   
   <style>


.cntr {
  display: table;
  width: 100%;
  height: 100%;
}
.cntr .cntr-innr {
  display: table-cell;
  text-align: center;
  vertical-align: middle;
}
/*** STYLES ***/
.search {
  display: inline-block;
  position: relative;
  height: 25px;
  width: 25px;
  box-sizing: border-box;
  margin: 0px 8px 7px 0px;
  padding: 0px 9px 0px 9px;
  border: 3px solid #fff;
  border-radius: 25px;
  transition: all 200ms ease;
  cursor: text;
}
.search:after {
  content: "";
  position: absolute;
  width: 3px;
  height: 20px;
  right: -5px;
  top: 14px;/* 21px; */
  background: #fff;
  border-radius: 3px;
  -webkit-transform: rotate(-45deg);
          transform: rotate(-45deg);
  transition: all 200ms ease;
}
.search.active,
.search:hover {
  width: 200px;
}
.search.active:after,
.search:hover:after {
  height: 0px;
  margin-right: 5px;
}
.search input {
  width: 100%;
  border: none;
  box-sizing: border-box;
  font-family: Helvetica;
  font-size: 15px;
  color: #fff;
  background: transparent;
  outline-width: 0px;
}


</style>
   
   
   
   
   
    <style>


/*     


.sm-link{
	--uismLinkDisplay: var(--smLinkDisplay, inline-flex);	
	--uismLinkTextColor: var(--smLinkTextColor);
	--uismLinkTextColorHover: var(--smLinkTextColorHover);	
	
	display: var(--uismLinkDisplay);
	color: var(--uismLinkTextColor);
	position: relative;
	overflow: hidden;
}

a.sm-link{
	text-decoration: none;
}

.sm-link__label{
  display: block;
}

/* sm-link_padding-all */ 

.sm-link_padding-all{
	--uismLinkLineWeight: var(--smLinkLineWeight, 2px);
	--uismLinkLineColor: var(--smLinkLineColor, #000);
	--uismLinkPadding: var(--smLinkPadding, 5px);
	
	padding: var(--uismLinkPadding);
}

.sm-link_padding-all::before, 
.sm-link_padding-all::after{
  width: 100%;
  height: var(--uismLinkLineWeight);
  left: 0;
}

.sm-link_padding-all::before{
  top: 0;
}

.sm-link_padding-all::after{
  bottom: 0;
}

.sm-link_padding-all .sm-link__label::before,
.sm-link_padding-all .sm-link__label::after{
  width: var(--uismLinkLineWeight);
  height: 100%;
  top: 0;
}

.sm-link_padding-all .sm-link__label::before{
  left: 0;
}

.sm-link_padding-all .sm-link__label::after{
  right: 0;
}

.sm-link_padding-all::before,
.sm-link_padding-all::after,
.sm-link_padding-all .sm-link__label::before,
.sm-link_padding-all .sm-link__label::after{
  content: "";     
	background-color: var(--uismLinkLineColor);
  position: absolute; 
	opacity: 0;
	
	will-change: transform, opacity;
	transition-property: transform, opacity;
}

.sm-link_padding-all:hover::before,
.sm-link_padding-all:hover::after,
.sm-link_padding-all:hover .sm-link__label::before,
.sm-link_padding-all:hover .sm-link__label::after{
	opacity: 1;
}

/* sm-link_padding-bottom */ 

.sm-link_padding-bottom{
	--uismLinkLineWeight: var(--smLinkLineWeight, 2px);
	--uismLinkLineColor: var(--smLinkLineColor, #000);	
	
	padding-bottom: var(--uismLinkLineWeight);	
	position: relative;
}

.sm-link_padding-bottom::after{
  content: "";
  width: 100%;
  height: var(--uismLinkLineWeight);
	background-color: var(--uismLinkLineColor);
	
  position: absolute;
  left: 0;
  bottom: 0;
}

/* sm-link_bg */ 

.sm-link_bg {
	--uismLinkLineColor: var(--smLinkLineColor, #000);	
	--uismLinkTextColorHover: var(--smLinkTextColorHover, #fff);	
	--uismLinkPadding: var(--smLinkPadding, 5px);
	
	padding: var(--uismLinkPadding);
	transition: color .3s ease-out;
}

.sm-link_bg::before, 
.sm-link_bg::after{
  content: "";
	background-color: var(--uismLinkLineColor);	
  opacity: 0;
  position: absolute;
	
	transition: transform .2s ease-out, opacity .2s ease-out .03s;
}

.sm-link_bg .sm-link__label{
  position: relative;
  z-index: 2;
}

.sm-link_bg:hover::before, 
.sm-link_bg:hover::after{
  opacity: 1;
	transition-duration: .35s, .35s;
	transition-delay: 0s, 0s;
}

.sm-link_bg:hover{
	color: var(--uismLinkTextColorHover);
}

/* sm-link_text */ 

.sm-link_text::before{
  content: attr(data-sm-link-text);
	color: var(--uismLinkTextColorHover);
  position: absolute;
}

.sm-link_text::before, 
.sm-link_text .sm-link__label{
  transition-property: transform;
	transition-timing-function: cubic-bezier(.86, .6, .08, 1.01); 
	transition-duration: .3s;
}

.sm-link_text:hover::before,
.sm-link_text:hover .sm-link__label{
	transition-duration: .4s;
}

/* effect 1 */

.sm-link1::before{
  transform: translate3d(-105%, 0, 0);
}

.sm-link1::after{
  transform: translate3d(105%, 0, 0);
}

.sm-link1 .sm-link__label::before{
  transform: translate3d(0%, -100%, 0);
}

.sm-link1 .sm-link__label::after{
  transform: translate3d(0%, 100%, 0);
}

.sm-link1::before,
.sm-link1::after,
.sm-link1 .sm-link__label::before,
.sm-link1 .sm-link__label::after{
	transition-timing-function: ease-out;
	transition-duration: .2s, .15s;
	transition-delay: 0s, .15s;
}

.sm-link1:hover::before,
.sm-link1:hover::after,
.sm-link1:hover .sm-link__label::before,
.sm-link1:hover .sm-link__label::after{
  transform: translate3d(0, 0, 0);
	opacity: 1;
	
	transition-duration: .25s;
	transition-delay: 0s;
}


/*
SETTINGS
*/

.sm-link{
	--smLinkPadding: 10px 15px;
	--smLinkLineWeight: 5px;
	--smLinkLineColor: #fff373;
	--smLinkTextColor: #fff373;
	--smLinkTextColorHover: #1b255a;
}

.sm-link_bg{
	--smLinkTextColorHover: #fff;
}
     */
    
    </style>
    
    
    
    
    
    
    
    
    
    