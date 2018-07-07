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
	var body_type = "A";
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
					iv_inch[item.so_id + "_" + item.mi_id] = item.input_value * centi_to_inch;
					
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
		alert(in_unit_display);
		var now_height = $("#value_height").val();
		var now_chest = $("#value_chest").val();
		var now_waist = $("#value_waist").val();
		
		
		if (in_unit_display) {
			// 센치로 전환
			
			$(".in_unit_class").hide();
			$(".cm_unit_class").show();	
			
			$("#value_height").val(now_height / centi_to_inch);
			$("#value_chest").val(now_chest / centi_to_inch);
			$("#value_waist").val(now_waist / centi_to_inch);

			
			if(input_mode == "SIMPLE"){
				
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			in_unit_display = false;
		} else {
			// 인치로 전환 
			$(".in_unit_class").show();
			$(".cm_unit_class").hide();
			
			$("#value_height").val(now_height * centi_to_inch);
			$("#value_chest").val(now_chest * centi_to_inch);
			$("#value_waist").val(now_waist * centi_to_inch);
			
			
			
			if(input_mode == "SIMPLE"){
					
			}else{
				$("#simple_result_block_in").hide();
				$("#simple_result_block_cm").hide();
				
			}
			in_unit_display = true;
		}
		
		if(selected_so_id != "") f_size_option_click(selected_so_id);
	};


	
	var f_detail_insert = function() {
		$("#detail_insert_block").show();
		$("#simple_insert_btn").show();
		$("#detail_insert_btn").hide();
		$("#simple_result_block_in").hide();
		$("#simple_result_block_cm").hide();
		
		
		
		input_mode = "DETAIL";
	};

	var f_simple_insert = function() {
		$("#detail_insert_block").hide();
		$("#simple_insert_btn").hide();
		$("#detail_insert_btn").show();
		
		if(in_unit_display){
			$("#simple_result_block_in").show();
		}else{
			$("#simple_result_block_cm").show();
		}
		
		input_mode = "SIMPLE";
	};

	var f_select_body_type_open = function() {
		$("#size_body_block").show();
		$("#size_cloth_block").hide();

	};

	var f_size_option_click = function(p_so_id) {
		
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
	};




	var f_select_body_type = function(p_body_type) {
		body_type = p_body_type;
	};

	var f_buy = function() {
		alert("f_buy");
	};
	
	
	var f_get_result = function(){
		alert(in_unit_display);
		if(selected_so_id == ""){
			alert("사이즈 옵션을 선택해주세요");
			return;
		}
		
		
		var X  = $("#value_height").val();
		var Y  = $("#value_weight").val();
		
		if(X == ""){
			alert("키를 입력하세요");
			return;
		}
		
		if(Y  == ""){
			alert("몸무게를 입력하세요");
			return;
		}
		
		if(input_mode == "SIMPLE"){
			
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
			
			
			var cm_Z = Z;
			var in_Z = Z * centi_to_inch;
			
			var this_chest_value = iv[selected_so_id+"_"+chest_mi_id];
			var this_chest_value_in = iv[selected_so_id+"_"+chest_mi_id] * centi_to_inch;
			
			var R = this_chest_value - cm_Z;
			var R_in = this_chest_value_in - in_Z;
			
			if(R >= 0 ){ 
				$("#result_cm").removeClass("red");
				$("#result_cm").addClass("blue");

				$("#result_cm").html(R+" cm 큽니다");
			}else{ 
				$("#result_cm").removeClass("blue");
				$("#result_cm").addClass("red");
				$("#result_cm").html(R+" cm 작습니다.");
			}
			
			
			if(R_in > 0 ){ 
				$("#result_in").removeClass("red");
				$("#result_in").addClass("blue"); 
				$("#result_in").html(R_in+" in 큽니다");
			}else{ 
				$("#result_in").removeClass("blue");
				$("#result_in").addClass("red");
				$("#result_in").html(R_in+" in 작습니다.");
			}
			
			
			$("#simple_result_in").html(in_Z+ " in");
			$("#simple_result_cm").html(cm_Z+ " cm");
			 
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
				
				var this_chest_value_in = iv[selected_so_id+"_"+chest_mi_id] * centi_to_inch;
				
				var R_in = V_chest - this_chest_value_in;
				
				
				if(R_in > 0 ){ 
					$("#result_in").removeClass("red");
					$("#result_in").addClass("blue"); 
					$("#result_in").html(R_in+" in 큽니다");
				}else{ 
					$("#result_in").removeClass("blue");
					$("#result_in").addClass("red");
					$("#result_in").html(R_in+" in 작습니다.");
				}
				
				
				
			}else{
				
				var this_chest_value = iv[selected_so_id+"_"+chest_mi_id];
				
				
				var R = V_chest - this_chest_value;
				
				
				if(R >= 0 ){ 
					$("#result_cm").removeClass("red");
					$("#result_cm").addClass("blue");

					$("#result_cm").html(R+" cm 큽니다");
				}else{ 
					$("#result_cm").removeClass("blue");
					$("#result_cm").addClass("red");
					$("#result_cm").html(R+" cm 작습니다.");
				}
				
			} 
			
			
			$("#simple_result_block_in").hide();
			$("#simple_result_block_cm").hide();
			
			
		}
		
		
		if (in_unit_display) {
			$(".in_unit_class").hide();
			$(".cm_unit_class").show(); 
			
		} else {

			$(".in_unit_class").show();
			$(".cm_unit_class").hide(); 
		}
		 
		$("#result_after_block").show();

	};