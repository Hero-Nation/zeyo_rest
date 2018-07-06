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
	<script type="text/javascript" src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.customSelect.js"></script>

	<script type="text/javascript">
		//<![CDATA[
		$(window).load(function(){
			openLay('layerArea');
		});
		
		var f_by_buy_product = function(){
			alert("현재 준비중입니다.");
		};
		
		var f_by_user_data = function(){
			window.location ="https://www.zeyo.co.kr/zeyo_app/compare?zeyo_pid=${zeyo_pid}";
		};
		
		//]]>
	</script>
</head>
<body>

<!--[s] Layer -->
<div class="layerArea type02">
	<div class="layerWrap">
		<h1 class="popuplogo"><img src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-logo.png" alt="" /></h1>
		

		<div class="contLy">
		<!-- 
			<a href="javascript:void(0);" class="closeLy"><img src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-close.png" alt="팝업 닫기"></a>
		-->
			
			<p class="text_type01 mt40">
				알려드립니다.<br /><br />

				기존에 구매하신 의류 정보 또는 기입하신 신체 정보를 토대로,<br />
				구매하시고자 하는 의류의 사이즈 정보와 비교하여<br />
				알맞은 사이즈를 알려드립니다.<br /><br />

				이미지 및 텍스트로 비교한 결과값을 제공하여,<br />
				구매하시려는 의류사이즈를 비교할 수 있습니다.
			</p>

			<div class="msg_btn01 mt30">
			<!-- 
				<button type="button" onclick="f_by_buy_product();">구매상품으로 비교</button>
			 -->
				<button type="button" onclick="f_by_user_data();">신체정보로 비교</button>
			</div>
			<div class="msg_btn02 mt20">
				<!-- <button type="button">사이즈 비교하기</button>  -->
			</div>

			<p class="text_type02 mt20">
				※ 신체 정보를 통해 비교 시 정확한 정보를 기입하지 않으시면,<br />
				비교 시 오차가 발생할 수 있습니다.
			</p>
		</div>

	</div>
</div>
<!--[e] Layer -->
</body>
</html>