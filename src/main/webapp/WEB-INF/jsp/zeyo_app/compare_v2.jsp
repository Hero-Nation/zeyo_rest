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
	href="https://www.zeyo.co.kr/static/zeyo_app/v2/common/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="https://www.zeyo.co.kr/static/zeyo_app/v2/common/css/jquery.ui.lastest.css" />

<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/v2/common/js/jquery.lastest.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/v2/common/js/jquery.ui.lastest.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/v2/common/js/publisher.js"></script>
<script type="text/javascript"
	src="https://www.zeyo.co.kr/static/zeyo_app/v2/common/js/jquery.customSelect.js"></script>

<script type="text/javascript">
	//<![CDATA[

	var item = ${item};
	var seller = '${seller}';
	var ItemScmmSoValue = ${ItemScmmSoValue};
	var sub_category = ${sub_category};
	var ordered_item = ${ordered_item};

	
	// 상수

	var CTS_COMPARE_MODE_BY_PRODUCT = "compare_by_product";
	var CTS_COMPARE_MODE_BY_INFO = "compare_by_info";

	var compare_mode = CTS_COMPARE_MODE_BY_PRODUCT;
	var so = {};
	var mi = {};
	var iv = {};
	var iv_inch = {};
	var so_index = Object.keys(so);
	var mi_index = Object.keys(mi);

	// status
	var in_unit_display = false;
	var input_mode = "SIMPLE";
	var centi_to_inch = 0.393701;
	var body_type = "NOT";
	var selected_so_id = "";
	var chest_mi_id = 0;
	var now_body_selecting = true;
	var ordered_product_name_object = {};
	$(window).load(
			function() {
				openLay('layerArea'); 
				
				if(ItemScmmSoValue.size == 0){
					alert("이 상품의 사이즈 치수정보가 존재하지 않습니다. 비교가 불가능합니다.");
					return;
				};
				
				
				
				$.each(ItemScmmSoValue.size, function(index, item) {
					so[item.so_id] = item;
					mi[item.mi_id] = item;

					iv[item.so_id + "_" + item.mi_id] = item.input_value;
					iv_inch[item.so_id + "_" + item.mi_id] = (item.input_value * centi_to_inch).toFixed(1);;
					
					if(item.mi_name == "가슴둘레") chest_mi_id = item.mi_id;
					
				});
				
				
				$.each(so, function(index, item) {
					 
					$("#sizebutton_by_product").append('<button  id="btn_so_by_profuct_'+ item.so_id +'" onclick="f_size_option_click('+item.so_id+',1)">' + item.so_name+ '</button>');
					$("#sizebutton_by_info").append('<button  id="btn_so_by_info_'+ item.so_id +'" onclick="f_size_option_click('+item.so_id+',2)">' + item.so_name+ '</button>');
					

					
				}); 
				
				$.each(so, function(index, item) {
					
					$('body').on('click', "#btn_so_by_profuct_"+ item.so_id + "", function(){f_size_option_click(item.so_id,1)});
					$('body').on('click', "#btn_so_by_info_"+ item.so_id + "",  function(){f_size_option_click(item.so_id,2)});
					
				 
				});
				
				
				
				$("#size_table_header_block").append("<th>상품명</th>");
				$.each(mi, function(index, item) {
					$("#size_table_header_block").append("<th>" + item.mi_name + "</th>");
				});

				$("#size_table_body_block").append("<td>" + item.name + "</td>");
				
				$.each(mi, function(index, item) {
					$("#size_table_body_block").append("<td></td>");
				});

				
				$('#ordered_select_box').empty();
				
				
				$('#ordered_select_box').append($('<option>', { 
			        value:  "",
			        text : "구매상품을 선택 해 주세요."
			    }));
				
				$.each(ordered_item, function(index, item) {
					//console.dir(item);
					ordered_product_name_object[item.item[0]] = {
							"name":item.item[12],
							"sub_cate" : item.item[29]
						};
					
				 $('#ordered_select_box').append($('<option>', { 
				        value: item.item[0],
				        text : item.item[12] 
				    }));
				});
				
				$('#ordered_select_box').width(580);
				
				
				
				
				if(sub_category.name == "반팔티셔츠"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
				}else if(sub_category.name == "긴팔티셔츠"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_round_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_round_blue.png");
				}else if(sub_category.name == "셔츠"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
				}else if(sub_category.name == "자켓"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
				}else if(sub_category.name == "점퍼/야상"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
				}else if(sub_category.name == "조끼"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
				}else if(sub_category.name == "청바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
				}else if(sub_category.name == "면바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
				}else if(sub_category.name == "반바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
				}else if(sub_category.name == "정장바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
				}
				
				
				

			});

	var f_change_event_ordered_product = function(){
		
		var this_val = $("#ordered_select_box").val();
 
		
		$("#ordered_product_name").empty();
		$("#ordered_product_name").html("<span></span>"+ordered_product_name_object[this_val].name);
		
		
		if(ordered_product_name_object[this_val].sub_cate == "반팔티셔츠"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/short_round_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "긴팔티셔츠"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/long_round_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "셔츠"){ 
			$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "자켓"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/long_shirt_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "점퍼/야상"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/long_shirt_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "조끼"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/short_round_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "청바지"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/pants_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "면바지"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/pants_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "반바지"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/pants_red.png");
		}else if(ordered_product_name_object[this_val].sub_cate == "정장바지"){ 
			$("#by_product_template_ordered").attr("src","https://www.zeyo.co.kr/static/pants_red.png");
		}
		
		f_size_option_click(0);
		
	};
	
	
	var f_compare_by_product = function() {

		compare_mode = CTS_COMPARE_MODE_BY_PRODUCT;

		$("#form_panel_by_info").hide();
		$("#form_panel_by_product").show();
		$("#form_panel_by_product_radio_by_info").prop("checked", false);
		$("#form_panel_by_product_radio_by_product").prop("checked", true);

		if (!now_body_selecting) {
			$("#main_panel_compare_by_product").show();
			$("#main_panel_compare_by_info").hide();
		}

	};

	var f_compare_by_info = function() {

		compare_mode = CTS_COMPARE_MODE_BY_INFO;

		$("#form_panel_by_info").show();
		$("#form_panel_by_product").hide();
		$("#form_panel_by_info_radio_by_product").prop("checked", false);
		$("#form_panel_by_info_radio_by_info").prop("checked", true);

		if (!now_body_selecting) {
			$("#main_panel_compare_by_product").hide();
			$("#main_panel_compare_by_info").show();
		}

	};

	var f_show_panel_size_body = function() {
		$("#main_panel_size_body").show();
		$("#main_panel_compare_by_product").hide();
		$("#main_panel_compare_by_info").hide();

		now_body_selecting = true;

	};

	var f_form_init = function() {
		$("#form_panel_by_product_input_search").val("");
		$("#form_panel_by_info_input_height").val("");
		$("#form_panel_by_info_input_weight").val("");
		$("#form_panel_by_info_input_chest").val("");
		$("#form_panel_by_info_input_waist").val("");
		
		$("#result_after_block").hide();
	};

	var f_select_body_type = function(p_body_type) {
		body_type = p_body_type;
		
		$("#main_panel_size_body").hide();

		if (compare_mode == CTS_COMPARE_MODE_BY_PRODUCT) {
			$("#main_panel_compare_by_product").show();
		} else {
			$("#main_panel_compare_by_info").show();
		}

		now_body_selecting = false;

	};
	
	
	var f_simple_input = function(){
		$("#form_panel_by_info_detail_form").hide();
		$("#form_panel_by_info_simple_result").hide();
		$("#form_panel_by_info_btn_get_simple").show();
		$("#form_panel_by_info_btn_get_detail").hide();
		
		
		
		$("#form_panel_by_info_btn_simple").hide();
		$("#form_panel_by_info_btn_detail").show();
		

		$("#simple_result_block_in").hide();
		$("#simple_result_block_cm").hide();
		
		input_mode = "SIMPLE";
	};
	
	var f_detail_input = function(){
		$("#form_panel_by_info_detail_form").show();
		$("#form_panel_by_info_simple_result").hide();
		$("#form_panel_by_info_btn_get_simple").hide();
		$("#form_panel_by_info_btn_get_detail").show();
		
		
		$("#form_panel_by_info_btn_simple").show();
		$("#form_panel_by_info_btn_detail").hide();
		
		
		$("#simple_result_block_in").hide();
		$("#simple_result_block_cm").hide();
		
		
		input_mode = "DETAIL";
		
	};
	
	var f_convert_unit = function(){
		
	};
	
	
	
	var f_ordered_product_search = function(){
		
		
		
		var v_keyword = $("#form_panel_by_product_input_search").val();
		
		if(v_keyword == ""){
			

			$('#ordered_select_box').empty();
			
			$('#ordered_select_box').append($('<option>', { 
		        value:  "",
		        text : "구매상품을 선택 해 주세요."
		    }));
			
			$.each(ordered_item, function(index, item) {
				
				
					if(item.item[12].indexOf(v_keyword) != -1){
					 	$('#ordered_select_box').append($('<option>', { 
					        value: item.item[0],
					        text : item.item[12] 
					    }));
					};	
			});
			
		}else{
			$('#ordered_select_box').empty();
			
			
			$('#ordered_select_box').append($('<option>', { 
		        value:  "",
		        text : "구매상품을 선택 해 주세요."
		    }));
			$.each(ordered_item, function(index, item) {
			
					if(item.item[12].indexOf(v_keyword) != -1){
					 	$('#ordered_select_box').append($('<option>', { 
					        value: item.item[0],
					        text : item.item[12] 
					    }));
					};	
			});	
		}
		
		
		
	};
	
	var f_get_result = function(){
 
		if(selected_so_id == ""){
			alert("사이즈 옵션을 선택해주세요");
			return;
		}
		
		
		if(compare_mode == CTS_COMPARE_MODE_BY_PRODUCT){
			
		}else{
			
			 
			
			var X  = $("#form_panel_by_info_input_height").val();
			var Y  = $("#form_panel_by_info_input_weight").val();
			
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
				
				var V_chest  = $("#form_panel_by_info_input_chest").val();
				var V_waist  = $("#form_panel_by_info_input_waist").val();
				
				if(V_chest == ""){
					alert("가슴둘레를 입력하세요");
					return;
				}
				
				if(V_waist  == ""){
					alert("허리둘레를 입력하세요");
					return;
				}
				
				
				if(in_unit_display){
					
					var cloth_chest_value_in = Number(iv[selected_so_id+"_"+chest_mi_id]) * centi_to_inch;
					
					
					var R_in = V_chest - cloth_chest_value_in;
					R_in = Number(R_in).toFixed(1);
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
					
					var cloth_chest_value = Number(iv[selected_so_id+"_"+chest_mi_id]);
					cloth_chest_value = cloth_chest_value.toFixed(1);
					
					
					var R =  cloth_chest_value - V_chest;
					R = Number(R).toFixed(1);
					
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
			
		}
		 
	};
	
	var f_size_option_click = function(p_so_id,p_type) {
		
		

		
		
		if(p_so_id == 0){
			so_index = Object.keys(so);
			
			if(so_index.length > 0){
				f_size_option_click(so[so_index[0]].so_id);	 
			}
			
			
		}else{
			
			selected_so_id = p_so_id;
			
			$.each(so, function(index, item) {
				$("#btn_so_by_profuct_" + item.so_id+"").removeClass("on");
				$("#btn_so_by_info_" + item.so_id+"").removeClass("on");
			});
			
			$("#btn_so_by_profuct_" + p_so_id+"").addClass("on");
			$("#btn_so_by_info_" + p_so_id+"").addClass("on");
			
			
			$("#size_table_header_block").empty();
			$("#size_table_body_block").empty();

			$("#size_table_header_block").append("<th>상품명</th>");
			$.each(mi, function(index, item) {
				$("#size_table_header_block").append("<th>" + item.mi_name + "</th>");
			});

			$("#size_table_body_block").append("<td>" + item.name + "</td>");
			$.each(mi, function(index, item) {
				
				if(in_unit_display){
					
					$("#size_table_body_block").append("<td>" + iv_inch[p_so_id + "_" + item.mi_id] + "</td>");
				}else{
					
					$("#size_table_body_block").append("<td>" + iv[p_so_id + "_" + item.mi_id] + "</td>");				
				}

			});
			 
			
			so_index = Object.keys(so);
			var select_count = 0;
			for(var a = 0 ; a < so_index.length;a++){
				if(so_index[a] == p_so_id){
					break;
				}
				select_count++;
			}
			
			var pic_width_percent = 30 + (select_count * 2);
			$("#by_info_template").attr("width",pic_width_percent+"%");
			$("#by_product_template_target").attr("width",pic_width_percent+"%");
			$("#by_product_template_target").css('margin-left',"-"+(15+select_count)+"%");
			
			
			
			
			////////////// by product target 테이블
			
			$("#size_table_header_block_by_product").empty();
			$("#size_table_body_block_by_product").empty();

			$("#size_table_header_block_by_product").append("<th>상품명</th>");
			$.each(mi, function(index, item) {
				$("#size_table_header_block_by_product").append("<th>" + item.mi_name + "</th>");
			});

			$("#size_table_body_block_by_product").append("<td>" + item.name + "</td>");
			$.each(mi, function(index, item) {
				
				$("#size_table_body_block_by_product").append("<td>" + iv[p_so_id + "_" + item.mi_id] + "</td>");	

			});
			
	 
			var this_ordered_id = $("#ordered_select_box").val();
			var this_ordered_size_option = [];
			var this_ordered_size = null;
			var this_ordered_name = "";
				for(var a = 0; a < ordered_item.length;a++){ 
					if(ordered_item[a].item[0] == this_ordered_id){
						this_ordered_size_option = ordered_item[a].size;
						this_ordered_size = ordered_item[a].order.option_value.split("=")[2];
						this_ordered_name = ordered_item[a].item[12];
					}
				}
				
				
				
				////////////// by product ordered 테이블
				
				
				$("#size_table_header_block_by_product_ordered").empty();
				$("#size_table_body_block_by_product_ordered").empty();

				if(this_ordered_size != null){
					
					$("#size_table_header_block_by_product_ordered").append("<th>상품명</th>");
					$("#size_table_body_block_by_product_ordered").append("<td>" + this_ordered_name + "</td>");
					
					
					$.each(this_ordered_size_option, function(index, item) {
	
						//input_value 						"81"
						//mi_id 					:						6
						//mi_name						:						"허리둘레"
						//so_id						:						12
						//so_name						:						"30"
						
						if(item.so_name == this_ordered_size){
							$("#size_table_header_block_by_product_ordered").append("<th>" + item.mi_name + "</th>");
							$("#size_table_body_block_by_product_ordered").append("<td>" + item.input_value + "</td>");							
						}
					});
				}
				 
			
			
			
			f_get_result();
			 
		}
		
 
		


	};
	
	
	
	var f_convert_unit = function() {
		 
		
		var now_height = $("#form_panel_by_info_input_height").val();
		var now_chest = $("#form_panel_by_info_input_chest").val();
		var now_waist = $("#form_panel_by_info_input_waist").val();
		
		
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
			
			
			
			$("#form_panel_by_info_input_height").val(c_height);
			$("#form_panel_by_info_input_chest").val(c_chest);
			$("#form_panel_by_info_input_waist").val(c_waist);

			
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
			
			
			
			$("#form_panel_by_info_input_height").val(c_height);
			$("#form_panel_by_info_input_chest").val(c_chest);
			$("#form_panel_by_info_input_waist").val(c_waist);
			
			
			
			if(input_mode == "SIMPLE"){
					
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			
		}
		
		if(selected_so_id != "") f_size_option_click(selected_so_id);
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
					src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/common/popup-logo.png"
					alt="" />
			</h1>

			<div class="contLy">
				<!-- 
				<a href="javascript:void(0);" class="closeLy"><img
					src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/common/popup-close.png"
					alt="팝업 닫기"></a>
 -->
				<div class="writeSizeForm">
					<div class="thumb">
						<c:if test="${item_object.imageMode eq 'A'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/no_image.png" />
						</c:if>
						<c:if test="${item_object.imageMode eq 'B'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/subcategory/item/${sub_category_object.itemImage}"
								alt="" />

						</c:if>
						<c:if test="${item_object.imageMode eq 'C'}">
							<img width="232" height="232"
								src="https://www.zeyo.co.kr/zeyo_image/client/${seller}/item/${item_object.image}" />
						</c:if>
						<c:if test="${item_object.imageMode eq 'D'}">
							<img width="232" height="232" src="${item_object.shop_image}" />
						</c:if>
					</div>
					<div class="sizeForm">
						<p class="title">${item_object.name}</p>
						<p class="price">${item_object.price}원</p>

						<div class="size type01" id="form_panel_by_info"
							style="display: none;">

							<div class="radioWrap">
								<span class="rdo_inline">
									<input type="radio" id="form_panel_by_info_radio_by_product" onclick="f_compare_by_product();" />
									<label for="form_panel_by_info_radio_by_product">상품으로 비교</label>
								</span> 
								<span class="rdo_inline"> 
									<input type="radio" id="form_panel_by_info_radio_by_info" checked="checked" onclick="f_compare_by_info();" />
									<label for="form_panel_by_info_radio_by_info">기본 정보로 비교</label>
								</span> 
								<span class="btn ml18" style="display: none;" id="form_panel_by_info_btn_simple">
									<button type="button" onclick="f_simple_input()">추론입력</button>
								</span>
								<span class="btn ml18" id="form_panel_by_info_btn_detail">
									<button type="button" onclick="f_detail_input()">상세입력</button>
								</span>
								<span class="btn ml18">
									<button type="button" onclick="f_convert_unit()" >cm ↔ inch</button>
								</span>
							</div>
							<div class="inputWrap mt10"  id="form_panel_by_info_simple_form">

								<span class="tit ml5">키</span>
								<div class="inputbox">
									<input type="text" id="form_panel_by_info_input_height" /> <span class="ft14 cm_unit_class">cm</span><span class="ft14 in_unit_class"  style="display: none">in</span>
								</div>

								<span class="tit ml50">체중</span>
								<div class="inputbox">
									<input type="text" id="form_panel_by_info_input_weight" /> <span class="ft14">kg</span>
								</div>
								<span class="btn mt-1 ml32"  id="form_panel_by_info_btn_get_simple"><button type="button" onclick="f_get_result()" >추론보기</button></span>
								<span class="btn mt-1 ml32"  id="form_panel_by_info_btn_get_detail"   style="display: none;"><button type="button" onclick="f_get_result()" >결과보기</button></span>
							</div>

							<div class="resultWrap mt15 in_unit_class" id="simple_result_block_in" style="display: none;" >
								<span class="restxt">회원님의 가슴둘레 측정치는 약&nbsp;</span><span class="result" id="simple_result_in"></span><span class="restxt"> 입니다.</span>
							</div>
							<div class="resultWrap mt15 cm_unit_class" id="simple_result_block_cm" style="display: none;"  >
								<span class="restxt">회원님의 가슴둘레 측정치는 약&nbsp;</span><span class="result" id="simple_result_cm"></span><span class="restxt"> 입니다.</span>
							</div> 
							
							<div class="mt10" id="form_panel_by_info_detail_form"  style="display: none;">
								<span class="tit ml5">가슴둘레</span>
								<div class="inputbox">
									<input type="text" id="form_panel_by_info_input_chest" /> <span class="ft14 cm_unit_class">cm</span><span class="ft14 in_unit_class"  style="display: none">in</span>
								</div>

								<span class="tit ml20">허리둘레</span>
								<div class="inputbox">
									<input type="text" id="form_panel_by_info_input_waist" /> <span class="ft14 cm_unit_class">cm</span><span class="ft14 in_unit_class"  style="display: none">in</span>
								</div>

								<span class="btn mt-5 ml30"></span> 
							</div> 
							<div class="top_btnWrap">
								<div class="btn-bodych">
									<button type="button" onclick="f_show_panel_size_body();"></button>
								</div>
								<div class="btn-refresh">
									<button type="button" onclick="f_form_init();"></button>
								</div>
							</div>
						</div>
						<div class="size type02" id="form_panel_by_product">
							<div class="radioWrap">
								<span class="rdo_inline"> <input type="radio"
									id="form_panel_by_product_radio_by_product"
									onclick="f_compare_by_product();" checked="checked" /> <label
									for="form_panel_by_product_radio_by_product">상품으로 비교</label>
								</span> <span class="rdo_inline"> <input type="radio"
									id="form_panel_by_product_radio_by_info"
									onclick="f_compare_by_info();" /> <label
									for="form_panel_by_product_radio_by_info">기본 정보로 비교</label>
								</span>
								<!--<span class="btn"><button type="button">상세정보 입력</button></span>-->
							</div>
							<div class="mt5">
								<div class="inputbox schBx">
									<input type="text" placeholder="예) 나드랑"
										id="form_panel_by_product_input_search" />
								</div>
								<button class="schbt" onclick="f_ordered_product_search();">검색</button>
							</div>
							<div class="pinkWrap">
								<span class="pinktx">※ 구매하신 상품을 선택 해 주세요.</span>
							</div>
							<div class="selectBx">
								<select id="ordered_select_box" onchange="f_change_event_ordered_product();">
									<option value="" hidden>구매상품을 선택 해 주세요.</option>
								</select>
							</div>
							<div class="top_btnWrap">
								<div class="btn-bodych">
									<button type="button" onclick="f_show_panel_size_body();"></button>
								</div>
								<div class="btn-refresh">
									<button type="button" onclick="f_form_init();"></button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="sizeBody type02 mt20" id="main_panel_size_body">
					<p class="text restxtbox">
						<span class="top_txt">※정확한 사이즈 추천을 위해 본인의 체형을 선택 해 주세요.</span>
					<div class="bodyCheck">
						<button type="button" onclick="f_select_body_type('A');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-01.png"
								alt="" /> <span>마른체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('B');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-02.png"
								alt="" /> <span>보통체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('C');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-03.png"
								alt="" /> <span>근육형</span>
						</button>
						<button type="button" onclick="f_select_body_type('D');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-04.png"
								alt="" /> <span>비만형</span>
						</button>
					</div>
					<!-- 
					<div class="btn">
						<button type="button">사이즈 추천 보기</button>
					</div>
					-->
				</div>
			</div>
			<div class="sizeCloth" id="main_panel_compare_by_product"
				style="display: none;">
				<!--<p class="text">구매하시려는 옷은 회원님의 가슴둘레보다 <span class="result red">0.8 in</span> 작습니다.</p>-->
				<div class="guide">
					<p class="buy_pro">
						<span></span>${item_object.name} 
					</p>
					<p id="ordered_product_name" class="compare_pro">
						<span></span>
					</p>

				</div>

				

				<div class="clothimg mt5">
				<div id="sizebutton_by_product" class="sizebutton"><p>SIZE</p></div>
				
				
					<div  >
<img id="by_product_template_target" width="30%" style="margin-top: 0%;position:absolute;margin-left:-15%; "/>
					</div>


					<div  >
 <img id="by_product_template_ordered" width="30%" style="margin-top: 0%;position:absolute;margin-left:-15%; "/>
					</div>




				</div>

				<div class="tar">단위 : cm</div>

				<div class="sizetable">
					<table>
						<caption></caption>
						<colgroup> 
						</colgroup>
						<thead>
							<tr id="size_table_header_block_by_product"> 
							</tr>
						</thead>
						<tbody>
							<tr id="size_table_body_block_by_product"> 
							</tr> 
						</tbody>
					</table>
					<table>
						<caption></caption>
						<colgroup> 
						</colgroup>
						<thead>
							<tr id="size_table_header_block_by_product_ordered" > 
							</tr>
						</thead>
						<tbody>
							<tr id="size_table_body_block_by_product_ordered"> 
							</tr> 
						</tbody>
					</table>
				</div>

				<div class="btn minus">
					<button type="button" onclick="f_buy()">닫기</button>
				</div>
			</div>
			<div class="sizeCloth" id="main_panel_compare_by_info"
				style="display: none;">

				<div class="guide">
					<p class="buy_pro">
						<span></span>${item_object.name}
					</p>
				</div>


				

				<div class="clothimg mt20">
<div id="sizebutton_by_info" class="sizebutton"><p>SIZE</p></div>

 				<img id="by_info_template" width="30%" style="margin-top: -5%"/>
				</div>
				<p class="text restxtbox" style="display: none;" id="result_after_block">
					<span class="restxt">구매하시려는 옷은 회원님의 가슴둘레보다&nbsp;</span>
						<span class="result blue in_unit_class" id="result_in" style="display: none"></span>
						<span class="result blue cm_unit_class" id="result_cm"></span>
				</p>
				<div class="tar cm_unit_class">단위 : cm</div>
				<div class="tar in_unit_class" style="display: none">단위 : in</div>

				<div class="sizetable">
					<table>
						<caption></caption>
						<colgroup> 
						</colgroup>
						<thead>
							<tr  id="size_table_header_block"> 
							</tr>
						</thead>
						<tbody>
							<tr  id="size_table_body_block"> 
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn">
					<button type="button" onclick="f_buy()">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<!--[e] Layer -->
</body>
</html>