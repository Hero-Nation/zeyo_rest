<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ tagliburi ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<title>ZEYO</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" type="text/css"
	href="https://www.zeyo.co.kr/static/zeyo_app/common/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="https://www.zeyo.co.kr/static/zeyo_app/common/css/jquery.ui.lastest.css" />

<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.lastest.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.ui.lastest.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/common/js/publisher.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/common/js/jquery.customSelect.js"></script>

<script type="text/javascript">
	//<![CDATA[

	var item = ${item};
	var seller = '${seller}';
	var ItemScmmSoValue = ${ItemScmmSoValue};
	var sub_category = ${sub_category};
	


	
	var so = {};
	var mi = {};
	var iv = {};
	var iv_inch = {};
	var so_index = Object.keys(so);
	var mi_index = Object.keys(mi);
	
	var in_unit_display = false;
	var input_mode = "SIMPLE";
	var centi_to_inch = 0.393701;	
	var body_type = "NOT";
	var selected_so_id= "";
	var chest_mi_id = 0;
	
	$(window).load(
			function() {
				openLay('layerArea');

				$("body").on("click", ".bodyCheck button", function() {
					$(this).addClass("check");
					$(".bodyCheck button").not($(this)).removeClass("check");
				});

				$.each(ItemScmmSoValue.size, function(index, item) {
					so[item.so_id] = item;
					mi[item.mi_id] = item;

					iv[item.so_id + "_" + item.mi_id] = item.input_value;
					iv_inch[item.so_id + "_" + item.mi_id] = (item.input_value * centi_to_inch).toFixed(1);;
					
					if(item.mi_name == "가슴둘레") chest_mi_id = item.mi_id;
					
				});

				$.each(so, function(index, item) {
					$("#size_option_block").append(
							"<button id='btn_so_" + item.so_id
									+ "' onclick='f_size_option_click(\""
									+ item.so_id + "\")'>" + item.so_name
									+ "</button>")
				});

				$("#size_table_header_block").append("<th>상품명</th>");
				$.each(mi, function(index, item) {
					$("#size_table_header_block").append(
							"<th>" + item.mi_name + "</th>");
				});

				$("#size_table_body_block")
						.append("<td>" + item.name + "</td>");
				$.each(mi, function(index, item) {
					$("#size_table_body_block").append("<td></td>");
				});

			});

	var f_init = function() {
		$("#value_height").val("");
		$("#value_weight").val("");
		$("#value_chest").val("");
		$("#value_waist").val("");
		
		
		$("#result_after_block").hide();
	};

	var f_compare = function() {
		$("#size_body_block").hide();
		$("#size_cloth_block").show();
	};

	var f_toggle_unit = function() {
		
		var now_height = $("#value_height").val();
		var now_chest = $("#value_chest").val();
		var now_waist = $("#value_waist").val();
		
		
		if (in_unit_display) {
			// 센치로 전환
			in_unit_display = false;
			$(".in_unit_class").hide();
			$(".cm_unit_class").show();	
			
			
			var c_height = now_height / centi_to_inch;
			var c_chest = now_chest / centi_to_inch;
			var c_waist = now_waist / centi_to_inch;
			
			c_height = c_height.toFixed(1);
			c_chest = c_chest.toFixed(1);
			c_waist = c_waist.toFixed(1);
			
			
			
			$("#value_height").val(c_height);
			$("#value_chest").val(c_chest);
			$("#value_waist").val(c_waist);

			
			if(input_mode == "SIMPLE"){
				
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			
		} else {
			in_unit_display = true;
			// 인치로 전환 
			$(".in_unit_class").show();
			$(".cm_unit_class").hide();
			
			var c_height = now_height * centi_to_inch;
			var c_chest = now_chest * centi_to_inch;
			var c_waist = now_waist * centi_to_inch;
			
			c_height = c_height.toFixed(1);
			c_chest = c_chest.toFixed(1);
			c_waist = c_waist.toFixed(1);
			
			
			
			$("#value_height").val(c_height);
			$("#value_chest").val(c_chest);
			$("#value_waist").val(c_waist);
			
			
			
			if(input_mode == "SIMPLE"){
					
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			
		}
		
		if(selected_so_id != "") f_size_option_click(selected_so_id);
	};


	
	var f_detail_insert = function() {
		$("#detail_insert_block").show();
		$("#simple_insert_btn").show();
		$("#detail_insert_btn").hide();
		$("#simple_result_block_in").hide();
		$("#simple_result_block_cm").hide();
		$("#simple_insert_block").hide();
		
		
		input_mode = "DETAIL";
	};

	var f_simple_insert = function() {
		$("#simple_insert_block").show();
		$("#detail_insert_block").hide();
		$("#simple_insert_btn").hide();
		$("#detail_insert_btn").show();
		

		
		input_mode = "SIMPLE";
	};

	var f_select_body_type_open = function() {
		$("#size_body_block").show();
		$("#size_cloth_block").hide();

	};

	var f_size_option_click = function(p_so_id) {
		
		if(p_so_id == 0){
			so_index = Object.keys(so);
			
			if(so_index.length > 0){
				f_size_option_click(so[so_index[0]].so_id);	 
			}
			
			
		}else{
			
			selected_so_id = p_so_id;
			
			$.each(so, function(index, item) {
				$("#btn_so_" + item.so_id).removeClass("on");
			});
			$("#btn_so_" + p_so_id).addClass("on");

			$("#size_table_header_block").empty();
			$("#size_table_body_block").empty();

			$("#size_table_header_block").append("<th>상품명</th>");
			$.each(mi, function(index, item) {
				$("#size_table_header_block").append(
						"<th>" + item.mi_name + "</th>");
			});

			$("#size_table_body_block").append("<td>" + item.name + "</td>");
			$.each(mi, function(index, item) {
				
				if(in_unit_display){
					
					$("#size_table_body_block").append(
							"<td>" + iv_inch[p_so_id + "_" + item.mi_id] + "</td>");
				}else{
					
					$("#size_table_body_block").append(
							"<td>" + iv[p_so_id + "_" + item.mi_id] + "</td>");				
				}

			});

			$("#result_before_block").hide();
			f_get_result();
		}
		

	};




	var f_select_body_type = function(p_body_type) {
		body_type = p_body_type;
		f_compare();
		f_size_option_click(0);
	};

	var f_buy = function() { 
		window.close();
	};
	
	
	var f_get_result = function(){
		
		$("#simple_result_block_in").hide();
		$("#simple_result_block_cm").hide();

		
		if(selected_so_id == ""){
			alert("사이즈 옵션을 선택해주세요");
			return;
		}
		
		
		var X  = $("#value_height").val();
		var Y  = $("#value_weight").val();
		
		X = Number(X);
		Y = Number(Y); 

		
		if(input_mode == "SIMPLE"){
			
			
			if(body_type == "NOT"){
				alert("신체 타입을 선택해주세요!");
				return;
			}
			
			
			if(X == ""){
				alert("키를 입력하세요");
				return;
			}
			
			if(Y  == ""){
				alert("몸무게를 입력하세요");
				return;
			}
			
			
			if(chest_mi_id == 0){
				alert("사이즈에 대한 가슴둘레 값이 존재하지 않습니다.");
				return;
			}

			if (in_unit_display) {
				X = X / centi_to_inch;
				Y = Y / centi_to_inch; 
			} 
			
			var Z = (((((((X * (X + Y)) / Y) + X) * (X / Y)) + ((((X * (X + ((X - 100) * 0.9))) / ((X - 100) * 0.9)) + X) * (X / ((X - 100) * 0.9))) + ((((X * (X + ((X * X * 22) / 10000))) / ((X * X * 22) / 10000)) + X) * (X / ((X * X * 22) / 10000)))) / 3) * (Y / X)) + ((X * X * 22) / 10000);
			
 			
			
			if(body_type == "A"){
				Z = Z-(Z*0.05);
			}else if(body_type == "B"){
				
			}else if(body_type == "C"){
				Z = Z+(Z*0.035);
			}else if(body_type == "D"){
				Z = Z+(Z*0.075);
			}
					
			
			var user_cm_Z = Z / 10 ;
			var user_in_Z = (Z / 10 ) * centi_to_inch;
			
			user_cm_Z = user_cm_Z.toFixed(1);
			user_in_Z = user_in_Z.toFixed(1);
			
			var cloth_chest_value = iv[selected_so_id+"_"+chest_mi_id];
			var cloth_chest_value_in = iv[selected_so_id+"_"+chest_mi_id] * centi_to_inch;
			
			var R =  cloth_chest_value - user_cm_Z;
			var R_in = cloth_chest_value_in - user_in_Z;
			
			R = R.toFixed(1);
			R_in = R_in.toFixed(1);
			
			
			if(R > 0 ){ 
				$("#result_cm").removeClass("red");
				$("#result_cm").addClass("blue");
				$("#result_cm").html(R+" cm 큽니다");

				
			}else{ 
				
				$("#result_cm").removeClass("blue");
				$("#result_cm").addClass("red");
				$("#result_cm").html(Math.abs(R)+" cm 작습니다.");
			}
			
			
			if(R_in > 0 ){ 
				
				$("#result_in").removeClass("blue");
				$("#result_in").addClass("red");
				$("#result_in").html(R_in+" in 큽니다");
				
				
				
			}else{ 
				$("#result_in").removeClass("red");
				$("#result_in").addClass("blue"); 
				$("#result_in").html(Math.abs(R_in)+" in 작습니다.");
			}
			
 
			
			
			
			$("#simple_result_in").html(user_in_Z+ " in");
			$("#simple_result_cm").html(user_cm_Z+ " cm");
			
			
			if(in_unit_display){
				$("#simple_result_block_in").show();
			}else{
				$("#simple_result_block_cm").show();
			}
			 
		}else{
			
			var V_chest  = $("#value_chest").val();
			var V_waist  = $("#value_waist").val();
			
			if(V_chest == ""){
				alert("가슴둘레를 입력하세요");
				return;
			}
			
			if(V_waist  == ""){
				alert("허리둘레를 입력하세요");
				return;
			}
			
			
			if(in_unit_display){
				
				var cloth_chest_value_in = iv[selected_so_id+"_"+chest_mi_id] * centi_to_inch;
				
				var R_in = V_chest - cloth_chest_value_in;
				
				
				if(R_in < 0 ){ 					
					$("#result_in").removeClass("red");
				$("#result_in").addClass("blue"); 
				$("#result_in").html(R_in+" in 큽니다");
					
				}else{  
					$("#result_in").removeClass("blue");
					$("#result_in").addClass("red");
					$("#result_in").html(R_in+" in 작습니다.");
				}
				
				 
			}else{
				
				var cloth_chest_value = iv[selected_so_id+"_"+chest_mi_id];
				
				
				var R =  cloth_chest_value - V_chest;
				
				
				if(R < 0 ){
					$("#result_cm").removeClass("blue");
					$("#result_cm").addClass("red");
					$("#result_cm").html(R+" cm 작습니다.");
				

					
					
				}else{ 
					$("#result_cm").removeClass("red");
					$("#result_cm").addClass("blue"); 
					$("#result_cm").html(R+" cm 큽니다");
				}
				
			} 
			

		}
		

		
		if (in_unit_display) {
			$(".in_unit_class").show();
			$(".cm_unit_class").hide();  
			
		} else {

			$(".in_unit_class").hide();
			$(".cm_unit_class").show(); 
		}
		 
		$("#result_after_block").show();
		
		if(input_mode == "SIMPLE"){
		}else{
			$("#simple_result_block_in").hide();
			$("#simple_result_block_cm").hide(); 

		}

	};

	//]]>
</script>
</head>
<body>

	<!--[s] Layer -->
	<div class="layerArea">
		<div class="layerWrap">
			<h1 class="popuplogo">
				<img
					src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-logo.png"
					alt="" />
			</h1>


			<div class="contLy">
				<!-- 
			<a href="javascript:void(0);" class="closeLy"><img src="https://www.zeyo.co.kr/static/zeyo_app/images/common/popup-close.png" alt="팝업 닫기"></a>
			 -->
				<div class="writeSizeForm">
					<div class="thumb">
						<c:if test="${item_object.imageMode eq 'A'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/no_image.png" alt="" />
						</c:if>
						<c:if test="${item_object.imageMode eq 'B'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/subcategory/item/${sub_category_object.itemImage}"
								alt="" />

						</c:if>
						<c:if test="${item_object.imageMode eq 'C'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/client/${seller}/item/${item_object.image}"
								alt="" />
						</c:if>
						<c:if test="${item_object.imageMode eq 'D'}">
							<img width="232" height="232" src="${item_object.shop_image}"
								alt="" />
						</c:if>
					</div>
					<div class="sizeForm">
						<p class="title">${item_object.name}</p>
						<p class="price">${item_object.price}원</p>


						<div class="size type02">
							<div>
								<!-- 
								<span class="rdo_inline"><input type="radio" id="radio1" name="sel"><label for="radio1">상품으로 비교</label></span>
							 -->
								<span class="rdo_inline"><input type="radio" id="radio2"
									name="sel" checked="checked"><label for="radio2">기본
										정보로 비교</label></span> <span class="btn" id="detail_insert_btn"><button
										type="button" onclick="f_detail_insert();">상세 정보 입력</button></span> <span
									class="btn" id="simple_insert_btn" style="display: none"><button
										type="button" onclick="f_simple_insert();">단순 정보 입력</button></span> <span
									class="btn"><button type="button"
										onclick="f_select_body_type_open();">체형 선택</button></span>
							</div>
							<div class="mt10"  id="simple_insert_block" >
								<span class="tit">키</span>
								<div class="inputbox">
									<input type="text" id="value_height" /> <span
										class="in_unit_class" style="display: none">in</span><span
										class="cm_unit_class">cm</span>
								</div>

								<span class="tit ml30">체중</span>
								<div class="inputbox">
									<input type="text" id="value_weight" /> <span>kg</span>
								</div>
							</div>

							<div class="mt10" id="detail_insert_block" style="display: none;">
								<span class="tit">가슴둘레</span>
								<div class="inputbox">
									<input type="text" id="value_chest" /> <span
										class="in_unit_class" style="display: none">in</span><span
										class="cm_unit_class">cm</span>
								</div>

								<span class="tit ml30">허리둘레</span>
								<div class="inputbox">
									<input type="text" id="value_waist" /> <span
										class="in_unit_class" style="display: none">in</span><span
										class="cm_unit_class">cm</span>
								</div>
							</div>
							<div class="mt10 in_unit_class" id="simple_result_block_in"
								style="display: none">
								<span>회원님의 가슴둘레 측정치는 약 <span id="simple_result_in"></span>
									입니다.
								</span>
							</div>
							<div class="mt10 cm_unit_class" id="simple_result_block_cm"
								style="display: none">
								<span>회원님의 가슴둘레 측정치는 약 <span id="simple_result_cm"></span>
									입니다.
								</span>
							</div>
							<div class="btn2">
								<button type="button" onclick="f_toggle_unit();">
									단위변환<br>(cm ↔ inch)
								</button>
							</div>
						</div>
						<div class="btn-refresh">
							<button type="button" onclick="f_get_result();">결과보기</button>
							<button type="button" onclick="f_init();">초기화</button>
						</div>

					</div>

				</div>

				<div class="sizeBody type02" id="size_body_block">
					<p class="text">정확한 사이즈 추천을 위해 본인의 체형을 선택 해 주세요.</p>

					<div class="bodyCheck">
						<button type="button" onclick="f_select_body_type('A');"
							class="check">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/images/body-01.png"
								alt="" /> <span>마른체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('B');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/images/body-02.png"
								alt="" /> <span>보통체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('C');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/images/body-03.png"
								alt="" /> <span>근육형</span>
						</button>
						<button type="button" onclick="f_select_body_type('D');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/images/body-04.png"
								alt="" /> <span>비만형</span>
						</button>
					</div>
<!-- 
					<div class="btn" >
						<button type="button" onclick="f_compare();">사이즈옵션 선택하기</button>
					</div>
					
					 -->
				</div>
				<div class="sizeCloth" id="size_cloth_block" style="display: none;padding-top=30px;">
					<p class="text" id="result_after_block" style="display: none;">
						구매하시려는 옷은 회원님의 가슴둘레보다 
						<span class="result blue in_unit_class" id="result_in" style="display: none"></span>
						<span class="result blue cm_unit_class" id="result_cm"></span>
					</p>
					<p class="text" id="result_before_block">구매하시려는 옷의 사이즈를 선택 해
						주세요.</p>


					<div class="sizebutton" id="size_option_block"></div>

					<div class="clothimg">
						<img
							src="https://www.zeyo.co.kr/static/zeyo_app/images/@temp-cloth.png"
							alt="" />
					</div>

					<div class="tar mt10 in_unit_class" style="display: none">단위
						: in</div>
					<div class="tar mt10 cm_unit_class">단위 : cm</div>
					<div class="sizetable" id="size_table_block">
						<table>
							<caption></caption>
							<colgroup>
								<col />
							</colgroup>
							<thead>
								<tr id="size_table_header_block">
								</tr>
							</thead>
							<tbody>
								<tr id="size_table_body_block">
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn">
						<button type="button" onclick="f_buy();">구매하기</button>
					</div>
				</div>

			</div>

		</div>
	</div>
	<!--[e] Layer -->
</body>
</html>