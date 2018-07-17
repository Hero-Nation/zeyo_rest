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
		
		var f_connnet = function(){
			window.location ="https://${shop_eng_id}.cafe24api.com/api/v2/oauth/authorize?response_type=code&client_id=lTXWDxObBNEtZNE7sF9QkB&state=${state}&redirect_uri=https://www.zeyo.co.kr/oauth/cafe24/callback&scope=${scope}"; 
		};
		
		var f_not_avaiable = function(){
			alert("준비중입니다");
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
				<h3>회원님의 쇼핑몰과 ZEYO를 연결합니다.</h3>
			</p>

			<div class="loginBtnArea">
				<button type="button" class="kakao" onclick='f_connnet();'><span>연결하기</span></button>
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