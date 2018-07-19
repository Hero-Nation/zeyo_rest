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
	var CTS_CENTI_TO_INCH = 0.393701;
	var CTS_PIXEL_BY_MILLI = 1 / 3; // 1pixcel을 3mm로 한다.  
	var CTS_CORRECTION_STD_WIDTH = 230;
	var CTS_CORRECTION_STD_HEIGHT = 250;
	
	var before_template_image_width_size = 0
	var before_template_image_height_size = 0;
	
	var compare_mode = CTS_COMPARE_MODE_BY_PRODUCT;
	var size_option = {};
	var measure_item = {};
	var table_value = {};
	var table_value_inch = {};
	
	var size_option_keys = Object.keys(size_option);
	var measure_item_keys = Object.keys(measure_item);
	
	var target_wear_type = "";
	var ordered_wear_type = "";

	// status
	var is_inch_mode = false;
	var by_info_input_mode = "SIMPLE";
	
	var choose_body_type = "NOT";
	var selected_size_option_id = "";
	
	var selected_measure_chest_id = 0;            // 가슴둘레
	var selected_measure_up_height_id = 0;        // 상위 전체길이
	
	var selected_measure_waist_id = 0;            // 허리둘레
	var selected_measure_down_height_id = 0;      // 총기장
	 
	var now_body_selecting = true;
	var ordered_product_name_object = {};
	
	$(window).load(
			function() {
				
				
				
				openLay('layerArea'); 
				
				if(ItemScmmSoValue.size == 0){
					alert("이 상품의 사이즈 치수정보가 존재하지 않습니다. 비교가 불가능합니다.");
					return;
				};
				
				
				
				var is_first = true;
				$.each(ItemScmmSoValue.size, function(index, item) {
					
					if(is_first){
						selected_size_option_id = item.so_id;
						is_first = false;
					}
					
					
					size_option[item.so_id] = item;
					measure_item[item.mi_id] = item;

					table_value[item.so_id + "_" + item.mi_id] = item.input_value;
					table_value_inch[item.so_id + "_" + item.mi_id] = (item.input_value * CTS_CENTI_TO_INCH).toFixed(1);
					if(item.mi_name == "가슴둘레") selected_measure_chest_id = item.mi_id;
					if(item.mi_name == "전체길이") selected_measure_up_height_id = item.mi_id;
					
					
					if(item.mi_name == "허리둘레") selected_measure_waist_id = item.mi_id;
					if(item.mi_name == "총기장") selected_measure_down_height_id = item.mi_id;
					
					
					
				});
				
				
				$.each(size_option, function(index, item) {
					 
					$("#sizebutton_by_product").append('<button  id="btn_so_by_profuct_'+ item.so_id +'" onclick="f_size_option_click('+item.so_id+',1)">' + item.so_name+ '</button>');
					$("#sizebutton_by_info").append('<button  id="btn_so_by_info_'+ item.so_id +'" onclick="f_size_option_click('+item.so_id+',2)">' + item.so_name+ '</button>');
					

					
				}); 
				
				/*
				$.each(size_option, function(index, item) {
					
					$('body').on('click', "#btn_so_by_profuct_"+ item.so_id + "", function(){f_size_option_click(item.so_id,1)});
					$('body').on('click', "#btn_so_by_info_"+ item.so_id + "",  function(){f_size_option_click(item.so_id,2)});
					
				 
				});
				*/
				
				
				$("#size_table_header_block").append("<th>상품명</th>");
				$.each(measure_item, function(index, item) {
					$("#size_table_header_block").append("<th>" + item.mi_name + "</th>");
				});

				$("#size_table_body_block").append("<td>" + item.name + "</td>");
				
				$.each(measure_item, function(index, item) {
					$("#size_table_body_block").append("<td></td>");
				});

				
				$('#ordered_select_box').empty();
				
				
				$('#ordered_select_box').append($('<option>', { 
			        value:  "",
			        text : "구매상품을 선택 해 주세요."
			    }));
				
				$.each(ordered_item, function(index, item) { 
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
					target_wear_type = "상의";
				}else if(sub_category.name == "긴팔티셔츠"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_round_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_round_blue.png");
					target_wear_type = "상의";
				}else if(sub_category.name == "셔츠"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					target_wear_type = "상의";
				}else if(sub_category.name == "자켓"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					target_wear_type = "상의";
				}else if(sub_category.name == "점퍼/야상"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/long_shirt_blue.png");
					target_wear_type = "상의";
				}else if(sub_category.name == "조끼"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/short_round_blue.png");
					target_wear_type = "상의";
				}else if(sub_category.name == "청바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					target_wear_type = "하의";
				}else if(sub_category.name == "면바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					target_wear_type = "하의";
				}else if(sub_category.name == "반바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					target_wear_type = "하의";
				}else if(sub_category.name == "정장바지"){
					$('#by_info_template').attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					$("#by_product_template_target").attr("src","https://www.zeyo.co.kr/static/pants_blue.png");
					target_wear_type = "하의";
				}
				
				

				f_draw_base_template();
			});
	 
	var f_draw_base_template = function(){
		// 초기 이미지 그리기
 		console.log("f_draw_base_template ");
		
/* 		$("#by_product_template_target").css('margin-top',"-3%");
		$("#by_info_template").css('margin-top',"-3%"); */
		
		/*
		
		var selected_measure_chest_id = 0;            // 가슴둘레
		var selected_measure_up_height_id = 0;        // 상위 전체길이
		
		var selected_measure_waist_id = 0;            // 허리둘레
		var selected_measure_down_height_id = 0;      // 총기장 
		
		*/
		
		var selected_template_image_width_size = 0;
		var selected_template_image_height_size = 0;

		if(target_wear_type == "하의"){
			
			
			selected_template_image_width_size = Number(table_value[selected_size_option_id+"_"+selected_measure_waist_id]) / 2;
			selected_template_image_height_size = Number(table_value[selected_size_option_id+"_"+selected_measure_down_height_id]);
			
			
			
		}else{
			selected_template_image_width_size = Number(table_value[selected_size_option_id+"_"+selected_measure_chest_id]);
			selected_template_image_height_size = Number(table_value[selected_size_option_id+"_"+selected_measure_up_height_id]);
		}
		
		
		if(before_template_image_width_size == 0){
			before_template_image_width_size = selected_template_image_width_size;
		}
		
		if(before_template_image_height_size == 0){
			before_template_image_height_size = selected_template_image_height_size;
		}
		
		
		console.log("f_draw_base_template >> selected_template_image_width_size " );
		console.log(selected_template_image_width_size);
		console.log("f_draw_base_template >> selected_template_image_height_size " );
		console.log(selected_template_image_height_size);
		
		// CTS_PIXEL_BY_MILLI
		// 수치 이미지
		// CTS_CORRECTION_STD_WIDTH = 100;
		// CTS_CORRECTION_STD_HEIGHT = 300;
		
		selected_template_image_width_size = selected_template_image_width_size * 10 * CTS_PIXEL_BY_MILLI;
		selected_template_image_height_size = selected_template_image_height_size * 10 * CTS_PIXEL_BY_MILLI;
		
		$("#by_info_template").attr("width",selected_template_image_width_size+"px");
		$("#by_info_template").attr("height",selected_template_image_height_size+"px");

		
		if(target_wear_type == "하의"){
			$("#by_info_template").css('margin-left',"-"+((selected_template_image_width_size - before_template_image_width_size)/2)+"px");
		}else{
			$("#by_info_template").css('margin-left',"-"+((selected_template_image_width_size - before_template_image_width_size)/1.5)+"px");
		}
		
		// 위치 보정
 		
		//$("#by_info_template").css('margin-top',""+((selected_template_image_height_size- before_template_image_height_size)/2)+"px");  
		
		
		// 구매이력 이미지 
 
		$("#by_product_template_target").attr("width",selected_template_image_width_size+"px");
		$("#by_product_template_target").attr("height",selected_template_image_height_size+"px");
		// 위치 보정
 
		
 		if(target_wear_type == "하의"){
			$("#by_product_template_target").css('margin-left',"-"+((selected_template_image_width_size - before_template_image_width_size)/2)+"px");
		}else{
			$("#by_product_template_target").css('margin-left',"-"+((selected_template_image_width_size - before_template_image_width_size)/1.5)+"px");
		}
		
		
		
/* 		console.log(" by_info_template  width");
		console.log($("#by_info_template").attr("width"));
		console.log(" by_info_template  height");
		console.log($("#by_info_template").attr("height"));
		console.log(" by_info_template  margin-left");
		console.log($("#by_info_template").css("margin-left"));
		console.log(" by_info_template  margin-top");
		console.log($("#by_info_template").css("margin-top"));
		
		
		console.log(" by_product_template_target  width");
		console.log($("#by_product_template_target").attr("width"));
		console.log(" by_product_template_target  height");
		console.log($("#by_product_template_target").attr("height"));
		console.log(" by_product_template_target  margin-left");
		console.log($("#by_product_template_target").css("margin-left"));
		console.log(" by_product_template_target  margin-top");
		console.log($("#by_product_template_target").css("margin-top")); */
		
	};
	
	var f_draw_ordered_template = function(p_order_key){
		console.log("f_draw_ordered_template ");
		console.log("p_order_key ");
		console.log(p_order_key);
		
		var size_option_value = "";
		var selected_template_image_width_size = 0;
		var selected_template_image_height_size = 0;


		
		$.each(ordered_item, function(index, item) {
			if(item.item[0] == p_order_key){ 
				size_option_value = item.order.option_value.split("=")[2];
				console.log("size_option_value ");
				console.log(size_option_value);
		
				$.each(item.size, function(size_index, size_value) {
					if(size_value.so_name == size_option_value){
				 
						
						if(target_wear_type == "하의"){	
							if(size_value.mi_name == "허리둘레"){
								selected_template_image_width_size = Number(size_value.input_value) / 2;	
							}
							
							if(size_value.mi_name == "총기장"){
								selected_template_image_height_size = Number(size_value.input_value);	
							}
						}else{
							
							if(size_value.mi_name == "가슴둘레"){
								selected_template_image_width_size = Number(size_value.input_value);	
							}
							
							if(size_value.mi_name == "전체길이"){
								selected_template_image_height_size = Number(size_value.input_value);	
							} 
						}		
						
					}
				});
				
						
			};
		}); 
		  
 
				
				
				if(before_template_image_width_size == 0){
					before_template_image_width_size = selected_template_image_width_size;
				}
				
				if(before_template_image_height_size == 0){
					before_template_image_height_size = selected_template_image_height_size;
				}
				
				
				console.log("f_draw_ordered_template >> selected_template_image_width_size " );
				console.log(selected_template_image_width_size);
				console.log("f_draw_ordered_template >> selected_template_image_height_size " );
				console.log(selected_template_image_height_size);
				
				// CTS_PIXEL_BY_MILLI
				// 수치 이미지
				// CTS_CORRECTION_STD_WIDTH = 100;
				// CTS_CORRECTION_STD_HEIGHT = 300;
				
				selected_template_image_width_size = selected_template_image_width_size * 10 * CTS_PIXEL_BY_MILLI;
				selected_template_image_height_size = selected_template_image_height_size * 10 * CTS_PIXEL_BY_MILLI;
				
				
				
				if(target_wear_type == "하의"){	
					$("#by_product_template_ordered").attr("width",(selected_template_image_width_size)+"px");
					$("#by_product_template_ordered").attr("height",selected_template_image_height_size+"px");					
					$("#by_product_template_ordered").css('margin-left',"-"+((selected_template_image_width_size - before_template_image_width_size)/2)+"px");
				}else{
					$("#by_product_template_ordered").attr("width",(selected_template_image_width_size - 84)+"px");
					$("#by_product_template_ordered").attr("height",selected_template_image_height_size+"px");
					$("#by_product_template_ordered").css('margin-left',"-"+((selected_template_image_width_size  - before_template_image_width_size)/2.1)+"px");
				}	
				

 
	};

	var f_change_event_ordered_product = function(){
		
		var this_val = $("#ordered_select_box").val();
 
		
		$("#ordered_product_name").empty();
		$("#ordered_product_name").html("<span></span>"+ordered_product_name_object[this_val].name);
		
		var img_src = "";
		if(ordered_product_name_object[this_val].sub_cate == "반팔티셔츠"){  
			img_src= "https://www.zeyo.co.kr/static/short_round_red.png";
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "긴팔티셔츠"){
			img_src= "https://www.zeyo.co.kr/static/long_round_red.png"; 
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "셔츠"){ 
			img_src= "https://www.zeyo.co.kr/static/long_shirt_red.png";  
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "자켓"){ 
			img_src= "https://www.zeyo.co.kr/static/long_shirt_red.png";   
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "점퍼/야상"){ 
			img_src= "https://www.zeyo.co.kr/static/long_shirt_red.png";    
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "조끼"){ 
			img_src= "https://www.zeyo.co.kr/static/short_round_red.png";     
			ordered_wear_type = "상의";
		}else if(ordered_product_name_object[this_val].sub_cate == "청바지"){ 
			img_src= "https://www.zeyo.co.kr/static/pants_red.png";      
			ordered_wear_type = "하의";
		}else if(ordered_product_name_object[this_val].sub_cate == "면바지"){ 
			img_src= "https://www.zeyo.co.kr/static/pants_red.png";       
			ordered_wear_type = "하의";
		}else if(ordered_product_name_object[this_val].sub_cate == "반바지"){ 
			img_src= "https://www.zeyo.co.kr/static/pants_red.png";       
			ordered_wear_type = "하의";
		}else if(ordered_product_name_object[this_val].sub_cate == "정장바지"){
			img_src= "https://www.zeyo.co.kr/static/pants_red.png";       
			ordered_wear_type = "하의";
		}
		
		
		if(target_wear_type == ordered_wear_type){
			$("#by_product_template_ordered").attr("src",img_src);
			f_size_option_click(0);	
			f_draw_ordered_template(this_val);
		}else{
			alert("상의와 하의는 비교할 수 없습니다.");
			return;
		}
	
		
		
		
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
		choose_body_type = p_body_type;
		
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
		
		by_info_input_mode = "SIMPLE";
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
		
		
		by_info_input_mode = "DETAIL";
		
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
 		
		if(selected_size_option_id == ""){
			alert("사이즈 옵션을 선택해주세요");
			return;
		}
		
		
		if(compare_mode == CTS_COMPARE_MODE_BY_PRODUCT){
			
			console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_PRODUCT");
			
		}else{//if(compare_mode != CTS_COMPARE_MODE_BY_PRODUCT){
			
			console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO");
			
			var X  = $("#form_panel_by_info_input_height").val();
			var Y  = $("#form_panel_by_info_input_weight").val();
			
			X = Number(X);
			Y = Number(Y); 

			
			if(by_info_input_mode == "SIMPLE"){
				
				console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> SIMPLE");
				
			// 단순 입력일 경우 
				if(  target_wear_type == "하의"){
					console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> SIMPLE >> 하의");
					alert("하의는 상세입력에서만 비교하실수 있습니다.");
					return;
				}
				
				
				if(choose_body_type == "NOT"){
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
				
				
				if(selected_measure_chest_id == 0){
					alert("사이즈에 대한 가슴둘레 값이 존재하지 않습니다.");
					return;
				}

				if (is_inch_mode) {
					X = X / CTS_CENTI_TO_INCH;
					Y = Y / CTS_CENTI_TO_INCH; 
				} 
				
				var Z = (((((((X * (X + Y)) / Y) + X) * (X / Y)) + ((((X * (X + ((X - 100) * 0.9))) / ((X - 100) * 0.9)) + X) * (X / ((X - 100) * 0.9))) + ((((X * (X + ((X * X * 22) / 10000))) / ((X * X * 22) / 10000)) + X) * (X / ((X * X * 22) / 10000)))) / 3) * (Y / X)) + ((X * X * 22) / 10000);
				
	 			
				
				if(choose_body_type == "A"){
					Z = Z-(Z*0.05);
				}else if(choose_body_type == "B"){
					
				}else if(choose_body_type == "C"){
					Z = Z+(Z*0.035);
				}else if(choose_body_type == "D"){
					Z = Z+(Z*0.075);
				}
						
				
				var user_cm_Z = Z / 10 ;
				var user_in_Z = (Z / 10 ) * CTS_CENTI_TO_INCH;
				
				user_cm_Z = user_cm_Z.toFixed(1);
				user_in_Z = user_in_Z.toFixed(1);
				
				var cloth_chest_value = table_value[selected_size_option_id+"_"+selected_measure_chest_id];
				var cloth_chest_value_in = table_value[selected_size_option_id+"_"+selected_measure_chest_id] * CTS_CENTI_TO_INCH;
				
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
				
	 
				$("#result_statement").html("구매하시려는 옷은 회원님의 가슴둘레보다&nbsp;");
				
				
				$("#simple_result_in").html(user_in_Z+ " in");
				$("#simple_result_cm").html(user_cm_Z+ " cm");
				
				
				if(is_inch_mode){
					$("#simple_result_block_in").show();
				}else{
					$("#simple_result_block_cm").show();
				}
				 
			}else{ // if(by_info_input_mode != "SIMPLE"){ 
				console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL");
				
				
				// 상의 && 하의 공통 로직 
				
				

				
				
				if(target_wear_type == "하의"){
					
					console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 하의");
				 
					//var V_chest  = $("#form_panel_by_info_input_chest").val();
					
					var V_waist  = $("#form_panel_by_info_input_waist").val();
					
					//if(V_chest == ""){
						//alert("가슴둘레를 입력하세요");
						//return;
					//}
					
					if(V_waist  == ""){
						alert("허리둘레를 입력하세요");
						return;
					}
					
					
					
					
					$("#result_statement").html("구매하시려는 옷은 회원님의 허리둘레 보다&nbsp;");
					
					if(is_inch_mode){ 
						console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 하의 >>  is_inch_mode true");
						console.log("selected_size_option_id");
						console.log(selected_size_option_id);
						console.log("selected_measure_chest_id");
						console.log(selected_measure_chest_id);
						
						var selected_table_value = Number(table_value[selected_size_option_id+"_"+selected_measure_waist_id]) * CTS_CENTI_TO_INCH;
							
						
						var R_in = selected_table_value - V_waist;
						R_in = Number(R_in).toFixed(1);
						if(R_in < 0 ){ 					
							
							$("#result_in").removeClass("blue");
							$("#result_in").addClass("red");
							$("#result_in").html(R_in+" in 작습니다.");
							
						}else{  
							
							
							$("#result_in").removeClass("red");
							$("#result_in").addClass("blue"); 
							$("#result_in").html(R_in+" in 큽니다");
						}
						
						
						
					}else{
						console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 하의 >>  is_inch_mode false");
						console.log("selected_size_option_id");
						console.log(selected_size_option_id);
						console.log("selected_measure_chest_id");
						console.log(selected_measure_chest_id);
						
						
						var selected_table_value = Number(table_value[selected_size_option_id+"_"+selected_measure_waist_id]);
						
						
						var R =  selected_table_value - V_waist;
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
					
					
					
				}else{ // if(target_wear_type != "하의"){
					
					console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 상의");
 
					$("#result_statement").html("구매하시려는 옷은 회원님의 가슴둘레보다&nbsp;");
					
					if(is_inch_mode){ 
				 
						
						console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 상의 >>  is_inch_mode true");
						
						var cloth_chest_value_in = Number(table_value[selected_size_option_id+"_"+selected_measure_chest_id]) * CTS_CENTI_TO_INCH;
						
						
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
						
						// if(is_inch_mode){ 
					}else{
						
						
						console.log("f_get_result   >>  CTS_COMPARE_MODE_BY_INFO  >> DETAIL   >> 상의 >>  is_inch_mode false");
						
						
						var cloth_chest_value = Number(table_value[selected_size_option_id+"_"+selected_measure_chest_id]);
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
						
					} // if(!is_inch_mode){ 
					 
				}  // if(target_wear_type != "하의"){
			} // if(by_info_input_mode != "SIMPLE"){ 
			

			// 수치입력 공통 부분...(추론 입력 && 상세 입력 )
			
			
			
			if (is_inch_mode) {
				$(".in_unit_class").show();
				$(".cm_unit_class").hide();  
				
			} else {

				$(".in_unit_class").hide();
				$(".cm_unit_class").show(); 
			}
			
			$("#result_after_block").show();
			
			if(by_info_input_mode == "SIMPLE"){
				
				
				
				
			}else{// if(by_info_input_mode == "SIMPLE"){
				
				
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide(); 

			} 

			
		}//if(compare_mode != CTS_COMPARE_MODE_BY_PRODUCT){  
		 
	};
	
 
	
	var f_size_option_click = function(p_so_id,p_type) {
		

		console.log("f_size_option_click");
		console.log("p_so_id");
		console.log(p_so_id); 
		console.log("p_type");
		console.log(p_type);
		


		
		
		if(p_so_id == 0){

			console.log("f_size_option_click    p_so_id == 0" );
			size_option_keys = Object.keys(size_option);
			
			if(size_option_keys.length > 0){
				f_size_option_click(size_option[size_option_keys[0]].so_id);	 
			}
			
			
		}else{
			
		
			console.log("f_size_option_click    p_so_id != 0 " );
			selected_size_option_id = p_so_id;
			
			$.each(size_option, function(index, item) {
				$("#btn_so_by_profuct_" + item.so_id+"").removeClass("on");
				$("#btn_so_by_info_" + item.so_id+"").removeClass("on");
			});
			
			$("#btn_so_by_profuct_" + p_so_id+"").addClass("on");
			$("#btn_so_by_info_" + p_so_id+"").addClass("on");
			
 
			size_option_keys = Object.keys(size_option);
			var select_count = 0;
			for(var a = 0 ; a < size_option_keys.length;a++){
				if(size_option_keys[a] == p_so_id){
					break;
				}
				select_count++;
			}
			 
			
			// 그리기
			f_draw_base_template();
			
			
			
			////////////// by product target 테이블
			
			$("#size_table_header_block_by_product").empty();
			$("#size_table_body_block_by_product").empty();

			$("#size_table_header_block_by_product").append("<th>상품명</th>");
			$.each(measure_item, function(index, item) {
				$("#size_table_header_block_by_product").append("<th>" + item.mi_name + "</th>");
			});

			$("#size_table_body_block_by_product").append("<td>" + item.name + "</td>");
			$.each(measure_item, function(index, item) { 
				$("#size_table_body_block_by_product").append("<td>" + table_value[p_so_id + "_" + item.mi_id] + "</td>");	 
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
				console.log("this_ordered_id");
				console.log(this_ordered_id); 
				console.log("this_ordered_name");
				console.log(this_ordered_name);
				console.log("this_ordered_size");
				console.log(this_ordered_size);
				
				
				////////////// by product ordered 테이블 만들기
				
				
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
				 
				f_draw_by_info_table();
			
			
			f_get_result();
			 
		}
		
 
		


	};
	
	
	
	var f_draw_by_info_table = function(){
		
		$("#size_table_header_block").empty();
		$("#size_table_body_block").empty();

		$("#size_table_header_block").append("<th>상품명</th>");
		$.each(measure_item, function(index, item) {
			$("#size_table_header_block").append("<th>" + item.mi_name + "</th>");
		});

		$("#size_table_body_block").append("<td>" + item.name + "</td>");
		
		$.each(measure_item, function(index, item) {
			
			if(is_inch_mode){
				
				console.log("$.each(measure_item, function(index, item) {   is_inch_mode is true " );
				
				$("#size_table_body_block").append("<td>" + table_value_inch[selected_size_option_id + "_" + item.mi_id] + "</td>");
			}else{ 
				
				
				console.log("$.each(measure_item, function(index, item) {   is_inch_mode is false " );
				
				$("#size_table_body_block").append("<td>" + table_value[selected_size_option_id + "_" + item.mi_id] + "</td>");				
			}

		});
	}
	
	var f_convert_unit = function() {
		console.log("f_convert_unit"); 
		
		var now_height = $("#form_panel_by_info_input_height").val();
		var now_chest = $("#form_panel_by_info_input_chest").val();
		var now_waist = $("#form_panel_by_info_input_waist").val();
		
		

		
		if (is_inch_mode) {
			
			console.log("f_convert_unit >> is_inch_mode == true"); 	
			
		
			// 센치로 전환
			is_inch_mode = false;
			$(".in_unit_class").hide();
			$(".cm_unit_class").show();	
			
			
			var c_height = now_height / CTS_CENTI_TO_INCH;
			var c_chest = now_chest / CTS_CENTI_TO_INCH;
			var c_waist = now_waist / CTS_CENTI_TO_INCH;
			
			c_height = c_height.toFixed(1);
			c_chest = c_chest.toFixed(1);
			c_waist = c_waist.toFixed(1);
			
			
			
			$("#form_panel_by_info_input_height").val(c_height);
			$("#form_panel_by_info_input_chest").val(c_chest);
			$("#form_panel_by_info_input_waist").val(c_waist);

			
			if(by_info_input_mode == "SIMPLE"){
				
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			
		} else {
			
			console.log("f_convert_unit >> is_inch_mode == false");	
			
		
			is_inch_mode = true;
			// 인치로 전환 
			$(".in_unit_class").show();
			$(".cm_unit_class").hide();
			
			var c_height = now_height * CTS_CENTI_TO_INCH;
			var c_chest = now_chest * CTS_CENTI_TO_INCH;
			var c_waist = now_waist * CTS_CENTI_TO_INCH;
			
			c_height = c_height.toFixed(1);
			c_chest = c_chest.toFixed(1);
			c_waist = c_waist.toFixed(1);
			
			
			
			$("#form_panel_by_info_input_height").val(c_height);
			$("#form_panel_by_info_input_chest").val(c_chest);
			$("#form_panel_by_info_input_waist").val(c_waist);
			
			
			
			if(by_info_input_mode == "SIMPLE"){
					
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			
		}
		
		f_draw_by_info_table();
		
		//if(selected_size_option_id != "") f_size_option_click(selected_size_option_id);
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

						<div class="size type01" id="form_panel_by_info" style="display: none;">

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
								<span class="rdo_inline">
									<input type="radio" id="form_panel_by_product_radio_by_product" onclick="f_compare_by_product();" checked="checked" />
									<label for="form_panel_by_product_radio_by_product">상품으로 비교</label>
								</span>
								<span class="rdo_inline">
									<input type="radio" id="form_panel_by_product_radio_by_info" onclick="f_compare_by_info();" />
									<label for="form_panel_by_product_radio_by_info">기본 정보로 비교</label>
								</span>
							</div>
							<div class="mt5">
								<div class="inputbox schBx">
									<input type="text" placeholder="예) 나드랑" id="form_panel_by_product_input_search" />
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
			<div class="sizeCloth" id="main_panel_compare_by_product" style="display: none;">
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
				
				
					<div>
						<img id="by_product_template_target" style="position:absolute"/>
					</div>


					<div>
 							<img id="by_product_template_ordered" style="position:absolute; stroke-dasharray: 4;"/>
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
			<div class="sizeCloth" id="main_panel_compare_by_info" style="display: none;"> 
				<div class="guide">
					<p class="buy_pro">
						<span></span>${item_object.name}
					</p>
				</div> 
				<div class="clothimg mt20">
				<div id="sizebutton_by_info" class="sizebutton"><p>SIZE</p></div>

 				<img id="by_info_template" style="position:absolute;"/>
				</div>
				<p class="text restxtbox" style="display: none;" id="result_after_block">
					<span class="restxt" id="result_statement"></span>
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