<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<jsp:include page="../../module/1doctype_head.jsp"></jsp:include>
	
<body>
 	
 	<jsp:include page="../../module/2body_first.jsp"></jsp:include>
 		
 		<style>
 		.heart-switch {
  --duration: .45s;
  --stroke: #D1D6EE;
  --stroke-active: #ec4472;
  --fill: #fff;
  --fill-active: #ec638e;
  --shadow: rgba(0, 9, 61, 0.25);
  cursor: pointer;
  position: relative;
  -webkit-transform: scale(var(--s, 1)) translateZ(0);
          transform: scale(var(--s, 1)) translateZ(0);
  transition: -webkit-transform .2s;
  transition: transform .2s;
  transition: transform .2s, -webkit-transform .2s;
  -webkit-tap-highlight-color: transparent;
}
.heart-switch:active {
  --s: .95;
}
.heart-switch input:focus{
	outline-color : #fff;
	outline-style: solid;
	outline-width : 0px;
}
.heart-switch input {
  -webkit-appearance: none;
  -moz-appearance: none;
  position: absolute;
  outline: none;
  border: none;
  pointer-events: none;
  z-index: 1;
  margin: 0;
  padding: 0;
  left: 1px;
  top: 1px;
  width: 18px;
  height: 18px;
  border-radius: 50%;
  background: #fff;
  box-shadow: 0 1px 3px 0 var(--shadow);
}
.heart-switch input + svg {
  width: 36px;
  height: 25px;
  fill: var(--fill);
  stroke: var(--stroke);
  stroke-width: 1px;
  stroke-linejoin: round;
  display: block;
  transition: stroke var(--duration), fill var(--duration);
}
.heart-switch input:not(:checked) {
  -webkit-animation: uncheck var(--duration) linear forwards;
          animation: uncheck var(--duration) linear forwards;
}
.heart-switch input:checked {
  -webkit-animation: check var(--duration) linear forwards;
          animation: check var(--duration) linear forwards;
}
.heart-switch input:checked + svg {
  --fill: var(--fill-active);
  --stroke: var(--stroke-active);
}

@-webkit-keyframes uncheck {
  0% {
    -webkit-transform: rotate(-30deg) translateX(13.5px) translateY(8px);
            transform: rotate(-30deg) translateX(13.5px) translateY(8px);
  }
  50% {
    -webkit-transform: rotate(30deg) translateX(9px);
            transform: rotate(30deg) translateX(9px);
  }
  75% {
    -webkit-transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
            transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
  }
  100% {
    -webkit-transform: rotate(30deg);
            transform: rotate(30deg);
  }
}

@keyframes uncheck {
  0% {
    -webkit-transform: rotate(-30deg) translateX(13.5px) translateY(8px);
            transform: rotate(-30deg) translateX(13.5px) translateY(8px);
  }
  50% {
    -webkit-transform: rotate(30deg) translateX(9px);
            transform: rotate(30deg) translateX(9px);
  }
  75% {
    -webkit-transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
            transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
  }
  100% {
    -webkit-transform: rotate(30deg);
            transform: rotate(30deg);
  }
}
@-webkit-keyframes check {
  0% {
    -webkit-transform: rotate(30deg);
            transform: rotate(30deg);
  }
  25% {
    -webkit-transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
            transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
  }
  50% {
    -webkit-transform: rotate(30deg) translateX(9px);
            transform: rotate(30deg) translateX(9px);
  }
  100% {
    -webkit-transform: rotate(-30deg) translateX(13.5px) translateY(8px);
            transform: rotate(-30deg) translateX(13.5px) translateY(8px);
  }
}
@keyframes check {
  0% {
    -webkit-transform: rotate(30deg);
            transform: rotate(30deg);
  }
  25% {
    -webkit-transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
            transform: rotate(30deg) translateX(4.5px) scaleX(1.1);
  }
  50% {
    -webkit-transform: rotate(30deg) translateX(9px);
            transform: rotate(30deg) translateX(9px);
  }
  100% {
    -webkit-transform: rotate(-30deg) translateX(13.5px) translateY(8px);
            transform: rotate(-30deg) translateX(13.5px) translateY(8px);
  }
}
html {
  box-sizing: border-box;
  -webkit-font-smoothing: antialiased;
}

* {
  box-sizing: inherit;
}
*:before, *:after {
  box-sizing: inherit;
}
 		</style>
 		
 		
	<div class="container" id="mainContainer">
		<div>
			<h2><b>${listOne.exhName}</b></h2>
			<h4>${listOne.artistName}</h4>
		</div>
		<div>
			<table class="table nanum">
				<tr>
					<td rowspan="7">
						<img src="/doArtShow/exhibitionImages/${listOne.imageFile1}" id="exhImage" style="height: 450px; width: 330px;"/>
					</td>
					<td>&nbsp;기간 : ${listOne.exhStartDate}&nbsp;~&nbsp;${listOne.exhEndDate}</td>
				</tr>
				<tr>
					<td style="width: 80%">&nbsp;전시관 : ${listOne.exhPlace}</td>
				</tr>
				<tr>
					<td>&nbsp;주소 : ${listOne.exhPlaceAddr1}</td>
				</tr>
				<tr>
					<td>&nbsp;시간 : ${listOne.opTime}</td>
				</tr>
				<tr>
					<td>&nbsp;입장료 : ${listOne.admFee}</td>
				</tr>
				<tr>
					<td>&nbsp;문의 : ${listOne.tel}</td>
				</tr>
				<tr>
					<td>&nbsp;사이트 : <a href="${listOne.exhUrl}">${listOne.exhUrl}</a></td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="myHist">
							<c:choose>
								<c:when test="${!empty sessionScope.member}">
									<a href="javascript:" id="wishBtn" style="color: #3d3d3d;"> <!-- wishArt_func(); -->
										<label class="heart-switch">
											<input type="checkbox"> 
											<svg viewBox="0 0 33 23" fill="white">
												<path	d="M23.5,0.5 C28.4705627,0.5 32.5,4.52943725 32.5,9.5 C32.5,16.9484448 21.46672,22.5 16.5,22.5 C11.53328,22.5 0.5,16.9484448 0.5,9.5 C0.5,4.52952206 4.52943725,0.5 9.5,0.5 C12.3277083,0.5 14.8508336,1.80407476 16.5007741,3.84362242 C18.1491664,1.80407476 20.6722917,0.5 23.5,0.5 Z"></path>
											</svg>
										   가고싶어요
										</label>
									</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:login_need();" id="wishBtn" style="color: #3d3d3d;"> <!-- wishArt_func(); -->
										<i class="fa fa-heart-o fa-2x"></i><br>
										<label>가고싶어요</label>
									</a>
								</c:otherwise>
							</c:choose>
								<!-- 
								가고싶어요 클릭하면 채워진 하트 이모티콘으로 변경
								<i class="fa fa-heart fa-2x"></i>
								-->
							<c:choose>
								<c:when test="${!empty sessionScope.member}">
									<a href="javascript:visitBtn_func();" id="visitBtn" style="color: #3d3d3d;"> <!-- visitArt_func(); -->
										<i class="fa fa-check fa-2x"></i><br>
										<label>다녀왔어요</label>
									</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:login_need(this);" id="visitBtn" style="color: #3d3d3d;"> <!-- visitArt_func(); -->
										<i class="fa fa-check fa-2x"></i><br>
										<label>다녀왔어요</label>
									</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:;" id="reviewBtn" style="color: #3d3d3d; visibility: hidden;"> <!-- 평소에 숨겨져있다가 다녀왔어요 클릭하면 리뷰작성버튼 show로 변경 -->
								<i class="fa fa-pencil fa-2x"></i><br> 
								<label>리뷰작성</label>
							</a>
						</div>
						<div class="dropdown dropright" style="display:inline-flex;">
							<a href="#shareModal" id="shareBtn" class="dropdown-toggle" data-toggle="dropdown" style="color: #3d3d3d;">
								<i class="fa fa-share-alt fa-2x"></i><br>
								<label>공유</label>
							</a>
							<div class="dropdown-menu">
	                    		<a class="dropdown-item" href="javascript:sendLinkFaceBook()" title="페이스북으로 공유하기"><img src="/doArtShow/resourcesImages/sns_face.png" width="36" alt="FaceBook"></a>
	                    		<a class="dropdown-item" href="javascript:sendLinkTwitter()" title="트위터로 공유하기"><img src="/doArtShow/resourcesImages/sns_tw.png" width="36" alt="Twitter"></a>
	                    		<a class="dropdown-item" href="javascript:sendLinkNaver()" title="네이버로 공유하기"><img src="/doArtShow/resourcesImages/sns_naver.png" width="36" alt="Naver"></a>
	                    		<a class="dropdown-item" href="javascript:sendLinkKakao()" id="kakao-link-btn" title="카카오톡으로 공유하기"><img src="/doArtShow/resourcesImages/sns_ka.png" width="36" alt="KakaoTalk"></a>  	 
	                    	</div>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="height: 1500px;">${listOne.exhContent}</td>
				</tr>
				<tr>
					<td><!-- image2,3,4 보여지게 --></td>
				</tr>
			</table>
		</div>
		<br>
		<!-- 리뷰 보여지는 부분 (Bootstrap 4 Carousel 기능으로!) -->
	</div>
		   
	
	<!-- 리뷰창 모달 -->
	<div class="modal fade" id="revWriteModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content" id="revWriteModal-content">
                <div class="modal-header">
                	<button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal body -->
                <div class="modal-body" style="padding: 30px; padding-bottom: 40px; padding-top: 40px;">
                	<h4 class="modal-title"><b>다녀온 전시관의 리뷰를 작성해 주세요</b></h4><br><br>
                	<form action="review.do" method="post" id="revForm">
                		<table class="table nanum">
                			<tr>
	                			<td width="20%">전시 내용</td>
	                			<td width="80%"><input type="text" readonly="readonly" value="${listOne.exhName}"></td>
                			</tr>
                			<tr>
                				<td>리뷰 내용</td>
                				<td><textarea cols="60" rows="15" name="revContent" id="revContent" placeholder="리뷰는 50자 이내로 작성 가능합니다."></textarea></td>
                			</tr>
                		</table>
                	</form>
                </div>
                <!-- Modal footer -->
                <div id="shareModal-footer">
                	<button type="button" class="btn btn-light" onclick="chkreviewForm(this.form)">등록하기</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                </div>
            </div>
        </div>
    </div>
	    
	
	<script>
		function login_need(){
			alert('로그인이 필요합니다');
		}
		
		/* 갔다왔어요 버튼 눌렀을때 적용하는 함수 */
		function visitBtn_func(visitBtn_con){
			document.getElementById('reviewBtn').style.visibility = "visible";
			
			visitBtn_con
		}
			
		function sendLinkFaceBook(){
		    var facebook_share_url = "https://www.facebook.com/sharer/sharer.php?u=" + encodeURI(document.location.href) + "&t=" + encodeURI('${listOne.exhName} 보러갈래?');
		    window.open(facebook_share_url,
		                'Share on Facebook',
		                'scrollbars=no, width=500, height=500');
		}
		function sendLinkTwitter(){
		    var twitter_share_text = encodeURI('${listOne.exhName} 보러갈래?');
		    var twitter_share_url = encodeURI(document.location.href);
		    window.open("https://twitter.com/share?text=" + twitter_share_text + "&url=" + twitter_share_url,
		                'Share on Twitter',
		                'scrollbars=no, width=500, height=500');
		}
		function sendLinkNaver(){
		    var raw_url = document.location.href;
		    var raw_title = '${listOne.exhName} 보러갈래?';
		    var naver_root_url = "http://share.naver.com/web/shareView.nhn?url="
		    var naver_share_url = naver_root_url + encodeURIComponent(raw_url) + "&title=" + encodeURIComponent(raw_title);
		    window.open(naver_share_url,
		                'Share on Naver',
		                'scrollbars=no, width=500, height=500');    
		}
		/* Kakao.init('3f954d79af6a536ec76db999e7f2ba5b'); */ 
		function sendLinkKakao(){
			Kakao.Link.sendDefault({
				objectType: 'feed',
				content: {
					title: '${listOne.exhName} 보러갈래?',
					description: '나만의 인생전시 찾아서, 전시:해',
					imageUrl: 'https://blogfiles.pstatic.net/MjAyMDAxMDJfMTgg/MDAxNTc3OTUzMDM0NTg0.mUQhLuECjSx5lvE4HxOz8je68sHTSsz5NgDtmw47cfkg.sDN5cQYDWuQVLKI9IU_s7qXwlDgwNS_dxCdXbfaMrrwg.PNG.rladudrl286/%EC%A0%84%EC%8B%9C%ED%95%B43.png',
					link: {
						mobileWebUrl: document.location.href,
						webUrl: document.location.href
					}
				},
				buttons: [
					{
						title: '링크 열기',
						link: {
							mobileWebUrl: document.location.href,
							webUrl: document.location.href
						}
					}
				]
			});
		}
		
		function chkreviewForm(revForm){
			if(!revForm.revContent.value){
				alert("내용을 작성해주세요");
				revForm.revConter.focus();
				return false;
			}
			
			
		}
	</script>

	<jsp:include page="../../module/3body_last.html"></jsp:include>
	
	<script>
		/* 리뷰작성 버튼 눌렀을때 리뷰작성모달 불러오는 함수 */
		$(document).ready(function(){
			$("#reviewBtn").click(function(){
				$("#revWriteModal").modal({
					backdrop: true
				});
			});
		});	
	</script>
	
</body>
</html>
