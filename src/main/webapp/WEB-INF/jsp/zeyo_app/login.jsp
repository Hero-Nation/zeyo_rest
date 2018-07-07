<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<title> ZEYO </title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="stylesheet" type="text/css" href="https://www.zeyo.co.kr/static/zeyo_app/common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="https://www.zeyo.co.kr/static/zeyo_app/common/css/jquery.ui.lastest.css" />

	<script type="text/javascript" src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.lastest.js"></script>
	<script type="text/javascript" src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.ui.lastest.js"></script>
	<script type="text/javascript" src="https://www.zeyo.co.kr/static/zeyo_app/common/js/publisher.js"></script>

	<script type="text/javascript">
		//<![CDATA[
		$(window).load(function(){
			openLay('layerArea');
		});
		
		var f_login_naver = function(){
			window.location ="https://nid.naver.com/oauth2.0/authorize?client_id=FErhZOHFFXjHRTFwqb11&response_type=code&redirect_uri=https%3A%2F%2Fzeyo.co.kr%2Foauth%2Fnaver%2Fcallback&state=${state}"; 
		};
		
		var f_login_kakao = function(){
			window.location ="https://kauth.kakao.com/oauth/authorize?client_id=e6f2ee99262f3e782e4756845d7f36fe&redirect_uri=https://www.zeyo.co.kr/oauth/kakao/callback&response_type=code&state=${state}";
		};
		
		//]]>
	</script>
</head>
<body>

<!--[s] Layer -->
<div class="layerArea type03">
	<div class="layerWrap">
		<h1 class="popuplogo"><img src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-logo.png" alt="" /></h1>
		

		<div class="contLy">
		<!--
			<a href="javascript:void(0);" class="closeLy"><img src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-close.png" alt="팝업 닫기"></a>
			-->
			<p class="text_type02">
				ZEYO는 구매하려는 의류의 사이즈를 분석하여<br />
				나에게 알맞은 사이즈를 찾아줍니다.<br /><br />

				ZERO 서비스를 이용하기 위해 로그인이 필요합니다.
			</p>

			<div class="loginBtnArea">
				<button type="button" class="kakao" onclick='f_login_kakao();'><span>카카오계정 로그인</span></button>
				<button type="button" class="naver" onclick='f_login_naver();'><span>네이버계정 로그인</span></button>
				<button type="button" class="facebook"><span>페이스북계정 로그인</span></button>
				<button type="button" class="google"><span>구글계정 로그인</span></button>
			</div>
<!-- 
			<div class="join"><a href="#none" >ZEYO 회원가입</a></div>
	 -->		
		</div>

	</div>
</div>
<!--[e] Layer -->
</body>
</html>