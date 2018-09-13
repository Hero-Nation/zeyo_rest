var zeyo_dmc = (function() {
	
	// field 
	var main_this = this; 
	var canvas_id = "zeyo_canvas";
	var template_store = {};
	var template_store_clone = {};
	var MAIN_CANVAS = null;
	
	var _init = function() {
		MAIN_CANVAS = null;
		MAIN_CANVAS = SVG(canvas_id);
		MAIN_CANVAS.clear();
	}
	
	var set_canvas = function(p_id){
		canvas_id = p_id;
		_init();
	};
	
	
	var clear_canvas = function(){
		/*
		
			설명
			
				캔버스를 지운다.
		
		*/
		
		_init();
	};
	
	
	var clear_template_data = function(){
		/*
		 
			 설명
			 
			 	모든 데이터를 초기화 한다.
		 
		 */
		template_store = null;
		template_store = {};
		
		_init();
	};
	
	var show_template_data = function(){
		/*
		 
		 	설명
		 		
		 		데이터를 보여준다. 
		 
		 */
		
		console.log("template_store");
		console.dir(template_store);
		console.log("template_store_clone");
		console.dir(template_store_clone);
		
	};
	
	var load_template = function(p_param) {
		
		/*
		 
		 설명 : 다수의 템플릿을 등록한다.
		 
		 p_param : [
		 
		 {
		 	name : "이름",
		 	dm : "수치모델",
		 	control_data : "통제 데이터",
		 	color : "색상" 
		 	width : "굵기"
		 }
		 .....
		 
		 ]
		 
		 
		 * 수치모델 자료구조
		 
		 dm : {
		 
		 		component : [
		 		
		 			{
		 			
		 				start : {},
		 				end : {},
		 				type : 문자열
		 				data : []
		 			
		 			}
		 		
		 		],
		 		center : {
		 			x : 숫자,
		 			y : 숫자
		 		},
		 		point_dic : [
		 		
		 		{
		 			"포인트 이름" : {
		 				"dme_index" : [
		 				
			 					{
					                "type": "start",
					                "component_index": 0
					            }
		 				
		 				]
		 			
		 			}
		 			
		 		
		 		}
		 		
		 			........
		 		
		 		],
		 		ratio : 숫자
		 }
		 
		 
		 
		 */
		
		// VALIDATION
		
		if(p_param.length == 0){
			alert("load_template : 입력한 데이터가 없습니다.");
			return;
		}
		
		template_store = {};
		
		for(var param_index in p_param){
			
			var template_data = p_param[param_index];
			
			// VALIDATION
			
			if(!template_data["name"]){
				alert("load_template : 입력한 템플릿의 이름이 존재하지 않습니다. 인덱스 :"+param_index);
				return;
			}
			
			if(!template_data["dm"]){
				alert("load_template : 입력한 템플릿이 존재하지 않습니다. 인덱스 :"+param_index);
				return;
			}
			
			if(!template_data["control_data"]){
				alert("load_template : 입력한 템플릿의 통제 데이터가 존재하지 않습니다. 인덱스 :"+param_index);
				return;
			}
			
			if(!template_data["color"]){
				alert("load_template : 입력한 템플릿의 색상이 존재하지 않습니다.  인덱스 :"+param_index);
				return;
			}
			
			if(!template_data["width"]){
				alert("load_template : 입력한 템플릿의 width가 존재하지 않습니다.  인덱스 :"+param_index);
				return;
			}
			
			var name = template_data["name"];
			template_data.dm = JSON.parse(decodeURIComponent(template_data["dm"]));
			template_data.control_data = JSON.parse(decodeURIComponent(template_data["control_data"]));
			
			template_store[name] = template_data;
			
		}
		
	};
	
	var set_template = function(p_param){
		
		/*
		
		설명
		
			하나의 템플릿만 업데이트 한다.
		
			객체를 받는다.
		
		 {
		 	name : "이름",
		 	dm : "수치모델",
		 	control_data : "통제 데이터",
		 	color : "색상",
		 	width : 굵기
		 }
		 
		 */
		
		
		
		if(!p_param["name"]){
			alert("set_template : 입력한 템플릿의 name이 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["dm"]){
			alert("set_template : 입력한 템플릿 dm이 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["control_data"]){
			alert("set_template : 입력한 템플릿의 control_data가 존재하지 않습니다.");
			return;
		}
		

		if(!p_param["color"]){
			alert("set_template : 입력한 템플릿의 color가 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["width"]){
			alert("set_template : 입력한 템플릿의 width 가 존재하지 않습니다.");
			return;
		}
		
		
		template_store[name] = p_param;
		
	};
	
	var move_template = function(p_param){
		/*
		
		설명 : 템플릿을 이동시킨다.
		
		객체를 받는다.
		
		 {
		 	name : "이름",
		 	center_x : "중심점 x 좌표",
		 	center_y : "중심점 y 좌표",
		 	ratio : 축소비율
		 }
		 
		 */
		 
		if(!p_param["name"]){
			alert("move_template : 입력한 템플릿의 name 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["center_x"]){
			alert("move_template : 입력한 템플릿의 center_x 존재하지 않습니다. 이름 : "+p_param["name"]);
			return;
		}
		
		if(!p_param["center_y"]){
			alert("move_template : 입력한 템플릿의 center_y 존재하지 않습니다. 이름 : "+p_param["name"]);
			return;
		}
		

		if(!p_param["ratio"]){
			alert("move_template : 입력한 템플릿의 ratio 존재하지 않습니다. 이름 : "+p_param["name"]);
			return;
		}
		
		
		var name 			= p_param["name"];
		var center_x 		= Number(p_param["center_x"]);
		var center_y 		= Number(p_param["center_y"]);
		var ratio 			= Number(p_param["ratio"]);
		
		var target_template = template_store_clone[name];
		
		var move_gap = {};
		move_gap.x = center_x - Number(target_template.dm.center.x);
		move_gap.y = center_y - Number(target_template.dm.center.y);

		var center = {
			x : center_x,
			y : center_y
		};

		// 복사해서 쓴다. 

		var clone_dm = JSON.parse(JSON.stringify(target_template.dm));
		
		for ( var component_index in clone_dm.component) {
			var component = clone_dm.component[component_index];
			var moved_component = _move_component(component, move_gap);
			clone_dm.component[component_index] = _scale_component_data(center,moved_component, ratio);
		}

		clone_dm.center = center;
		clone_dm.ratio = ratio;

		target_template.dm = _zip_model(clone_dm);
		
		template_store_clone[name] = target_template;
		
		_refresh_canvas();
	};
	
	
	var _scale_component_data = function(p_center, p_component, p_ratio) {
 
		var component_clone = JSON.parse(JSON.stringify(p_component));
		
		component_clone.start = _get_point_by_ratio(p_center,				component_clone.start, p_ratio);
		component_clone.start.name = p_component.start.name;
		component_clone.end = _get_point_by_ratio(p_center, component_clone.end,				p_ratio);
		component_clone.end.name = p_component.end.name;

		var data = component_clone.data;

		for ( var component_index in data) {

			var step_data = data[component_index];

			if (component_clone.type == "path") {

				if (component_index == 0) {

					var p_target = {
						x : step_data[1],
						y : step_data[2]
					};

					p_target = _get_point_by_ratio(p_center, p_target, p_ratio);

					step_data[1] = p_target.x;
					step_data[2] = p_target.y;

				} else {

					if (step_data[0] == "L") {

						var p_target = {
							x : step_data[1],
							y : step_data[2]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;
						step_data[2] = p_target.y;

					} else if (step_data[0] == "H") {

						var p_target = {
							x : step_data[1],
							y : component_clone.start.y
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;

					} else if (step_data[0] == "V") {

						var p_target = {
							x : component_clone.start.x,
							y : step_data[1]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.y;

					} else if (step_data[0] == "C") {

						var p_target = {
							x : step_data[1],
							y : step_data[2]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;
						step_data[2] = p_target.y;

						p_target = {
							x : step_data[3],
							y : step_data[4]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[3] = p_target.x;
						step_data[4] = p_target.y;

						p_target = {
							x : step_data[5],
							y : step_data[6]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[5] = p_target.x;
						step_data[6] = p_target.y;

					} else if (step_data[0] == "S") {

						var p_target = {
							x : step_data[1],
							y : step_data[2]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;
						step_data[2] = p_target.y;

						p_target = {
							x : step_data[3],
							y : step_data[4]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[3] = p_target.x;
						step_data[4] = p_target.y;

					} else if (step_data[0] == "Q") {

						var p_target = {
							x : step_data[1],
							y : step_data[2]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;
						step_data[2] = p_target.y;

						p_target = {
							x : step_data[3],
							y : step_data[4]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[3] = p_target.x;
						step_data[4] = p_target.y;

					} else if (step_data[0] == "T") {

						var p_target = {
							x : step_data[1],
							y : step_data[2]
						};

						p_target = _get_point_by_ratio(p_center, p_target,
								p_ratio);

						step_data[1] = p_target.x;
						step_data[2] = p_target.y;

					} else if (step_data[0] == "A") {
						//console.log("A _____");
					} else if (step_data[0] == "Z") {
						//console.log("A _____");
					}

					//R += " ";
					//R += "z";
				}

			} else if (component_clone.type == "line") {

				if (component_index == 0) {

					var p_target = {
						x : step_data[1],
						y : step_data[2]
					};

					p_target = _get_point_by_ratio(p_center, p_target, p_ratio);

					step_data[1] = p_target.x;
					step_data[2] = p_target.y;

				} else {

					var p_target = {
						x : step_data[1],
						y : step_data[2]
					};

					p_target = _get_point_by_ratio(p_center, p_target, p_ratio);

					step_data[1] = p_target.x;
					step_data[2] = p_target.y;

				}

			}

			data[component_index] = step_data;

		} // for ( var component_index in data) { 

		component_clone.data = data;
		//console.dir(component_clone);
		return component_clone;
	};
	
	var _get_point_by_ratio = function(p_center, p_original_point, p_ratio) {

		var target_x = null;
		var target_y = null;

		// scale 적용전의 두점을 거리를 구한다.

		var original_length = _distants_on_two_point(p_center, p_original_point);

		// scale이 적용된 거리

		var scale_length = original_length * p_ratio;
		scale_length = scale_length.toFixed(2);
		var scale_length_pow = Math.floor(scale_length * scale_length);
		scale_length_pow = scale_length_pow.toFixed(2);

		var x1 = Number(p_center.x).toFixed(2);
		var y1 = Number(p_center.y).toFixed(2);

		var x2 = Number(p_original_point.x).toFixed(2);
		var y2 = Number(p_original_point.y).toFixed(2);

		var a = 0;
		var b = 0;
		var c = 0;
		var eq_y = 0;

		var eq_x1 = 0;
		var eq_x2 = 0;

		var eq_y1 = 0;
		var eq_y2 = 0;

		if (x2 == x1) {

			a = 1;

			b = -2 * y1;

			c = y1 * y1 - scale_length_pow;

			eq_x1 = x1;
			eq_x1 = x2;

			eq_y1 = (-b + Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_y1 = Number(eq_y1).toFixed(2);
			eq_y2 = (-b - Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_y2 = Number(eq_y2).toFixed(2);

		} else {

			var eq1_slope = (y2 - y1) / (x2 - x1);

			a = 1 + eq1_slope * eq1_slope;

			b = -2 * x1 - 2 * eq1_slope * eq1_slope * x1;

			c = x1 * x1 + eq1_slope * eq1_slope * x1 * x1 - scale_length_pow;

			eq_x1 = (-b + Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_x1 = Number(eq_x1).toFixed(2);
			eq_x2 = (-b - Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_x2 = Number(eq_x2).toFixed(2);

			var eq1_slope = (y2 - y1) / (x2 - x1);

			eq_y1 = (eq1_slope * eq_x1) - (eq1_slope * x1) + Number(y1);
			eq_y2 = (eq1_slope * eq_x2) - (eq1_slope * x1) + Number(y1);

		}

		if (x2 >= x1) {
			return {
				x : Number(Number(eq_x1).toFixed(2)),
				y : Number(Number(eq_y1).toFixed(2))
			};
		} else {
			return {
				x : Number(Number(eq_x2).toFixed(2)),
				y : Number(Number(eq_y2).toFixed(2))
			};
		}

	}
	
	
	var _get_point_by_extension = function(p_center, p_original_point,	p_extension) {

		var target_x = null;
		var target_y = null;

		// scale 적용전의 두점을 거리를 구한다.

		var original_length = _distants_on_two_point(p_center, p_original_point);
		// scale이 적용된 거리

		var scale_length = original_length + p_extension;
		scale_length = scale_length.toFixed(2);
		var scale_length_pow = Math.floor(scale_length * scale_length);
		scale_length_pow = scale_length_pow.toFixed(2);

		var x1 = Number(p_center.x).toFixed(2);
		var y1 = Number(p_center.y).toFixed(2);

		var x2 = Number(p_original_point.x).toFixed(2);
		var y2 = Number(p_original_point.y).toFixed(2);

		var a = 0;
		var b = 0;
		var c = 0;
		var eq_y = 0;

		var eq_x1 = 0;
		var eq_x2 = 0;

		var eq_y1 = 0;
		var eq_y2 = 0;

		if (x2 == x1) {

			a = 1;

			b = -2 * y1;

			c = y1 * y1 - scale_length_pow;

			eq_x1 = x1;
			eq_x1 = x2;

			eq_y1 = (-b + Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_y1 = Number(eq_y1).toFixed(2);
			eq_y2 = (-b - Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_y2 = Number(eq_y2).toFixed(2);

		} else {

			var eq1_slope = (y2 - y1) / (x2 - x1);

			a = 1 + eq1_slope * eq1_slope;

			b = -2 * x1 - 2 * eq1_slope * eq1_slope * x1;

			c = x1 * x1 + eq1_slope * eq1_slope * x1 * x1 - scale_length_pow;

			eq_x1 = (-b + Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_x1 = Number(eq_x1).toFixed(2);
			eq_x2 = (-b - Math.sqrt(b * b - (4 * a * c))) / (2 * a);
			eq_x2 = Number(eq_x2).toFixed(2);

			var eq1_slope = (y2 - y1) / (x2 - x1);

			eq_y1 = (eq1_slope * eq_x1) - (eq1_slope * x1) + Number(y1);
			eq_y2 = (eq1_slope * eq_x2) - (eq1_slope * x1) + Number(y1);

		}
 
		if (x2 >= x1) {
			return {
				x : Number(Number(eq_x1).toFixed(2)),
				y : Number(Number(eq_y1).toFixed(2))
			};
		} else {
			return {
				x : Number(Number(eq_x2).toFixed(2)),
				y : Number(Number(eq_y2).toFixed(2))
			};
		}
	}

	var _distants_on_two_point = function(p_point1, p_point2) {

		var x1 = Number(p_point1.x);
		var y1 = Number(p_point1.y);

		var x2 = Number(p_point2.x);
		var y2 = Number(p_point2.y);

		var d = (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
		return Math.sqrt(d);
	};

	

	var _move_component = function(p_component, p_move_gap) {

		// 컴포넌트를 이동시킨다.
		
		var x_gap = Number(p_move_gap.x);
		var y_gap = Number(p_move_gap.y);

		var start_name = p_component.start.name;
		var start_x = Number(p_component.start.x) + x_gap;
		var start_y = Number(p_component.start.y) + y_gap;

		var end_name = p_component.end.name;
		var end_x = Number(p_component.end.x) + x_gap;
		var end_y = Number(p_component.end.y) + y_gap;

		var R = {};
		R.start = {};
		R.start.x = start_x;
		R.start.y = start_y;
		R.start.name = start_name;
		R.end = {};
		R.end.x = end_x;
		R.end.y = end_y;
		R.end.name = end_name;
		R.type = p_component.type;

		var data = p_component.data;

		for ( var component_index in data) {

			var step_data = data[component_index];
			if (p_component.type == "path") {

				if (component_index == 0) {

					step_data[1] = Number(step_data[1]) + x_gap;
					step_data[2] = Number(step_data[2]) + y_gap;

				} else {

					if (step_data[0] == "L") {

						step_data[1] = Number(step_data[1]) + x_gap;
						step_data[2] = Number(step_data[2]) + y_gap;

					} else if (step_data[0] == "H") {

						step_data[1] = Number(step_data[1]) + x_gap;

					} else if (step_data[0] == "V") {
						step_data[1] = Number(step_data[1]) + y_gap;

					} else if (step_data[0] == "C") {

						step_data[1] = Number(step_data[1]) + x_gap;
						step_data[2] = Number(step_data[2]) + y_gap;
						step_data[3] = Number(step_data[3]) + x_gap;
						step_data[4] = Number(step_data[4]) + y_gap;
						step_data[5] = Number(step_data[5]) + x_gap;
						step_data[6] = Number(step_data[6]) + y_gap;

					} else if (step_data[0] == "S") {

						step_data[1] = Number(step_data[1]) + x_gap;
						step_data[2] = Number(step_data[2]) + y_gap;
						step_data[3] = Number(step_data[3]) + x_gap;
						step_data[4] = Number(step_data[4]) + y_gap;

					} else if (step_data[0] == "Q") {

						step_data[1] = Number(step_data[1]) + x_gap;
						step_data[2] = Number(step_data[2]) + y_gap;
						step_data[3] = Number(step_data[3]) + x_gap;
						step_data[4] = Number(step_data[4]) + y_gap;

					} else if (step_data[0] == "T") {

						step_data[1] = Number(step_data[1]) + x_gap;
						step_data[2] = Number(step_data[2]) + y_gap;

					} else if (step_data[0] == "A") {
						//console.log("A _____");
					} else if (step_data[0] == "Z") {
						//console.log("A _____");
					}

					//R += " ";
					//R += "z";
				}

			} else if (p_component.type == "line") {

				if (component_index == 0) {

					step_data[1] = Number(step_data[1]) + x_gap;
					step_data[2] = Number(step_data[2]) + y_gap;

				} else {

					step_data[1] = Number(step_data[1]) + x_gap;
					step_data[2] = Number(step_data[2]) + y_gap;

				}

			}

			data[component_index] = step_data;

		} // for ( var component_index in data) { 

		R.data = data;
		return R;

	}

	
	var _zip_model = function(p_dme) {

		/*
			설명 : 수치 모델의 중복을 제거 한다.

		*/
		
		var point_dic = {};

		// 측정항목과 포인트 map을 parsing 한다. 

		var ctrl_data_dic = {};

		var dme = p_dme.component;
		var dme_center = p_dme.center;

		var pure_dme_index = {};
		var pure_dme = [];

		for ( var dme_index in dme) {
			
			var dme_item = dme[dme_index];
			var start_point = dme_item.start;
			var end_point = dme_item.end;

			var point_key = start_point.name + end_point.name;

			if (pure_dme_index.hasOwnProperty(point_key)) {
				continue;
			} else {
				pure_dme.push(dme_item);
				pure_dme_index[point_key] = "EXIST";
			}

			if (!point_dic.hasOwnProperty(start_point.name)) {

				point_dic[start_point.name] = {};
				point_dic[start_point.name].dme_index = [];
				point_dic[start_point.name].dme_index.push({
					"type" : "start",
					"component_index" : Number(dme_index)
				});

			} else {

				point_dic[start_point.name].dme_index.push({
					"type" : "start",
					"component_index" : Number(dme_index)
				});

			}

			if (!point_dic.hasOwnProperty(end_point.name)) {

				point_dic[end_point.name] = {};
				point_dic[end_point.name].dme_index = [];
				point_dic[end_point.name].dme_index.push({
					"type" : "end",
					"component_index" : Number(dme_index)
				});

			} else {

				point_dic[end_point.name].dme_index.push({
					"type" : "end",
					"component_index" : Number(dme_index)
				});

			}

		}

		var zip_dme = {};
		zip_dme.component = pure_dme;
		zip_dme.center = dme_center;
		zip_dme.point_dic = point_dic;
		zip_dme.ratio = Number(p_dme.ratio);

		return zip_dme;
	};
	
	
	var _draw_template = function(p_template) {
		/*

			설명
			
				template를 캔퍼스에 그린다. 

		 */
		
		var component_data = p_template.dm.component;

		for ( var component_index in component_data) {

			var component = component_data[component_index];

			var flat_data = _flat_component(component);

			if (component.type == "path") {

				var path = MAIN_CANVAS.path(flat_data);
				path.fill('none');
				path.stroke({
					color : p_template.color,
					width : p_template.width,
					linecap : 'round',
					linejoin : 'round'
				});

			} else if (component.type == "line") {

				var line = MAIN_CANVAS.line(flat_data).stroke({
					color : p_template.color,
					width : p_template.width
				});

			}
		}

	};
	

  
	var _flat_component = function(p_component) {
		
		/*
		
			설명 
			
				컴포넌트 데이터를 문자열로 변형시킨다.
		
		*/
		
		var flat_R = "";
		var data = p_component.data;
		
		for ( var component_index in data) {
			
			var step_data = data[component_index];
			
			if (p_component.type == "path") {

				if (component_index == 0) {

					flat_R = "M";
					flat_R += step_data[1];
					flat_R += " ";
					flat_R += step_data[2];
					flat_R += " ";

				} else {

					if (step_data[0] == "L") {

						flat_R += "L";
						flat_R += " ";
						flat_R += step_data[1];
						flat_R += " ";
						flat_R += step_data[2];

					} else if (step_data[0] == "H") {

						flat_R += "H";
						flat_R += " ";
						flat_R += step_data[1];

					} else if (step_data[0] == "V") {

						flat_R += "V";
						flat_R += " ";
						flat_R += step_data[1];

					} else if (step_data[0] == "C") {

						flat_R += "C";
						flat_R += " ";
						flat_R += step_data[1];
						flat_R += " ";
						flat_R += step_data[2];
						flat_R += ",";
						flat_R += step_data[3];
						flat_R += " ";
						flat_R += step_data[4];
						flat_R += ",";
						flat_R += step_data[5];
						flat_R += " ";
						flat_R += step_data[6];

					} else if (step_data[0] == "S") {

						flat_R += "S";
						flat_R += " ";
						flat_R += step_data[1];
						flat_R += " ";
						flat_R += step_data[2];
						flat_R += ",";
						flat_R += step_data[3];
						flat_R += " ";
						flat_R += step_data[4];

					} else if (step_data[0] == "Q") {

						flat_R += "Q";
						flat_R += " ";
						flat_R += step_data[1];
						flat_R += " ";
						flat_R += step_data[2];
						flat_R += ",";
						flat_R += step_data[3];
						flat_R += " ";
						flat_R += step_data[4];

					} else if (step_data[0] == "T") {

						flat_R += "T";
						flat_R += " ";
						flat_R += step_data[1];
						flat_R += " ";
						flat_R += step_data[2];

					} else if (step_data[0] == "A") {
						//console.log("A _____");
					} else if (step_data[0] == "Z") {
						//console.log("A _____");
					}

					//flat_R += " ";
					//flat_R += "z";
				}

			} else if (p_component.type == "line") {

				if (component_index == 0) {

					/* flat_R = [];
					flat_R.push(step_data[1]);
					flat_R.push(step_data[2]); */

					flat_R = "";
					flat_R += step_data[1];
					flat_R += ",";
					flat_R += step_data[2];

				} else {
					/* flat_R.push(step_data[3]);
					flat_R.push(step_data[4]); */

					flat_R = " ";
					flat_R += step_data[1];
					flat_R += ",";
					flat_R += step_data[2];

				}

			}
		}

		//console.log(flat_R);

		return flat_R;

	};

	var control_template = function(p_param){
		
		
		
		/*
		 	설명
		 	
		 		템플릿을 조작한다.
		 	
		 	파라미터
		 	
				target :  조작할 template 이름
				measure : 측정 지표 이름
				direction : 측정 지표 방향
				size : 측정 지표 이동 크기
		
		*/
		
		if(!p_param["target"]){
			alert("control_template : 파라미터에 target 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["measure"]){
			alert("control_template : 파라미터에 measure가 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["direction"]){
			alert("control_template : 파라미터에 direction 존재하지 않습니다.");
			return;
		}
		
		if(!p_param["size"]){
			alert("control_template : 파라미터에 size 존재하지 않습니다.");
			return;
		}
		
		var target_template_name = p_param["target"];
		var measure = p_param["measure"];
		var direction = p_param["direction"];
		var size = Number(p_param["size"]);
		
		var target_template = template_store[target_template_name];
		var clone_target_template = JSON.parse(JSON.stringify(target_template));

		template_store_clone[target_template_name] = clone_target_template;
		
		var current_control_data = clone_target_template.control_data;

		
		if (current_control_data[measure]) {

			var cd = current_control_data[measure];

			for ( var cd_index in cd) {

				var step_cd = cd[cd_index];
				var step_cd_fist_split = step_cd.split(":");
				var target_point = step_cd_fist_split[0].toUpperCase();
				var target_op = step_cd_fist_split[1].split(",");
				var op_type = target_op[0].toUpperCase();
				var op_direction = target_op[1];

				//console.log(target_point);
				//console.log(target_op);

				var op_data = {};
				op_data.step = size; 
				op_data.mi_direction = direction;
				op_data.target = target_point;
				op_data.data = target_op;

				if (op_type == "X") {
					_op_x_move(op_data, clone_target_template.dm);
				} else if (op_type == "Y") {
					_op_y_move(op_data, clone_target_template.dm);
				} else if (op_type == "TP") {
					_op_tp_move(op_data, clone_target_template.dm);
				}

			}
 
			

			

		} else {
			alert("control_template : 통제 데이터가 존재 하지 않습니다.");
		}
		

		_refresh_canvas();
	};
	
	var _refresh_canvas = function(){
		
		MAIN_CANVAS.clear();
		
		
		var tsc_keys = Object.keys(template_store_clone);
		for(var tsc_index  in tsc_keys){
			var tsc_name = tsc_keys[tsc_index];
			_draw_template(template_store_clone[tsc_name]);
		}
	}
	
	var _op_x_move = function(p_op_data, p_dm) {
 

		var move_ratio = p_dm.ratio;
		var op_data = p_op_data.data;
		var op_direction = op_data[1];
		var op_size = Number(op_data[2]);
		var mi_direction = p_op_data.mi_direction;

		if (p_dm.point_dic[p_op_data.target]) {

			var point_index_data = p_dm.point_dic[p_op_data.target].dme_index;

			for ( var pid_index in point_index_data) {

				var component_index = point_index_data[pid_index].component_index;
				var point_start_or_end = point_index_data[pid_index].type;
				var component = p_dm.component[component_index];
				var component_data = component.data;
				var point_data = component[point_start_or_end];
 
				var move_size = 0;

				if (mi_direction == "+" && op_direction == "+") {
					move_size = Number(p_op_data.step) * move_ratio * op_size;
				} else if (mi_direction == "+" && op_direction == "-") {
					move_size = (Number(p_op_data.step) * move_ratio * op_size)
							* -1;
				} else if (mi_direction == "-" && op_direction == "+") {
					move_size = (Number(p_op_data.step) * move_ratio * op_size)
							* -1;
				} else if (mi_direction == "-" && op_direction == "-") {
					move_size = Number(p_op_data.step) * move_ratio * op_size;
				}

				point_data.x = Number(point_data.x) + move_size;
				point_data.y = Number(point_data.y);

				if (point_start_or_end == "start") {

					var start_data = component_data[0];
					start_data[1] = Number(start_data[1]) + move_size;
					start_data[2] = Number(start_data[2]);

				} else {
					// end 포인트를 조작한다면...

					var end_data = null;

					if (component_data.length == 1) {
						end_data = component_data[0];
					} else {
						end_data = component_data[1];
					}

					if (component.type == "path") {

						if (end_data[0] == "L") { // 라인

							end_data[1] = Number(end_data[1]) + move_size;
							//end_data[2] = Number(end_data[2]) + y_gap;

						} else if (end_data[0] == "H") { // 수평

							end_data[1] = Number(end_data[1]) + move_size;

						} else if (end_data[0] == "V") { // 수직

							//end_data[1] = Number(end_data[1]) + y_gap;

						} else if (end_data[0] == "C") {

							end_data[1] = Number(end_data[1]) + move_size;
							//end_data[2] = Number(end_data[2]) + y_gap;
							end_data[3] = Number(end_data[3]) + move_size;
							//end_data[4] = Number(end_data[4]) + y_gap;
							end_data[5] = Number(end_data[5]) + move_size;
							//end_data[6] = Number(end_data[6]) + y_gap;

						} else if (end_data[0] == "S") {

							end_data[1] = Number(end_data[1]) + move_size;
							//end_data[2] = Number(end_data[2]) + y_gap;
							end_data[3] = Number(end_data[3]) + move_size;
							//end_data[4] = Number(end_data[4]) + y_gap;

						} else if (end_data[0] == "Q") {

							end_data[1] = Number(end_data[1]) + move_size;
							//end_data[2] = Number(end_data[2]) + y_gap;
							end_data[3] = Number(end_data[3]) + move_size;
							//end_data[4] = Number(end_data[4]) + y_gap;

						} else if (end_data[0] == "T") {

							end_data[1] = Number(end_data[1]) + move_size;
							//end_data[2] = Number(end_data[2]) + y_gap;

						} else if (end_data[0] == "A") {
							//console.log("A _____");
						} else if (end_data[0] == "Z") {
							//console.log("A _____");
						}

					} else if (component.type == "line") {

						end_data[1] = Number(end_data[1]) + move_size;
						//end_data[2] = Number(end_data[2]) + y_gap;

					}

				}

			}

		} else {
			alert("수치모델에 " + p_op_data.name + " 점이 존재하지 않습니다.");
		}

	};

	var _op_y_move = function(p_op_data, p_dm) {

		var move_ratio = p_dm.ratio;
		var op_data = p_op_data.data;
		var op_direction = op_data[1];
		var op_size = Number(op_data[2]);
		var mi_direction = p_op_data.mi_direction;

		if (p_dm.point_dic[p_op_data.target]) {

			var point_index_data = p_dm.point_dic[p_op_data.target].dme_index;

			for ( var pid_index in point_index_data) {

				var component_index = point_index_data[pid_index].component_index;
				var point_start_or_end = point_index_data[pid_index].type;
				var component = p_dm.component[component_index];
				var component_data = component.data;
				var point_data = component[point_start_or_end];
 

				var move_size = 0;

				if (mi_direction == "+" && op_direction == "+") {
					move_size = Number(p_op_data.step) * move_ratio * op_size;
				} else if (mi_direction == "+" && op_direction == "-") {
					move_size = (Number(p_op_data.step) * move_ratio * op_size)
							* -1;
				} else if (mi_direction == "-" && op_direction == "+") {
					move_size = (Number(p_op_data.step) * move_ratio * op_size)
							* -1;
				} else if (mi_direction == "-" && op_direction == "-") {
					move_size = Number(p_op_data.step) * move_ratio * op_size;
				}

				point_data.x = Number(point_data.x);
				point_data.y = Number(point_data.y) + move_size;

				if (point_start_or_end == "start") {

					var start_data = component_data[0];
					start_data[1] = Number(start_data[1]);
					start_data[2] = Number(start_data[2]) + move_size;

				} else {
					// end 포인트를 조작한다면...

					var end_data = null;

					if (component_data.length == 1) {
						end_data = component_data[0];
					} else {
						end_data = component_data[1];
					}

					if (component.type == "path") {

						if (end_data[0] == "L") { // 라인

							//end_data[1] = Number(end_data[1]) + move_size;
							end_data[2] = Number(end_data[2]) + move_size;

						} else if (end_data[0] == "H") { // 수평

							//end_data[1] = Number(end_data[1]) + move_size;

						} else if (end_data[0] == "V") { // 수직

							end_data[1] = Number(end_data[1]) + move_size;

						} else if (end_data[0] == "C") {

							//end_data[1] = Number(end_data[1]) + x_gap;
							end_data[2] = Number(end_data[2]) + move_size;
							//end_data[3] = Number(end_data[3]) + x_gap;
							end_data[4] = Number(end_data[4]) + move_size;
							//end_data[5] = Number(end_data[5]) + move_size;
							end_data[6] = Number(end_data[6]) + move_size;

						} else if (end_data[0] == "S") {

							//end_data[1] = Number(end_data[1]) + x_gap;
							end_data[2] = Number(end_data[2]) + move_size;
							//end_data[3] = Number(end_data[3]) + move_size;
							end_data[4] = Number(end_data[4]) + move_size;

						} else if (end_data[0] == "Q") {

							//end_data[1] = Number(end_data[1]) + x_gap;
							end_data[2] = Number(end_data[2]) + move_size;
							//end_data[3] = Number(end_data[3]) + move_size;
							end_data[4] = Number(end_data[4]) + move_size;

						} else if (end_data[0] == "T") {

							//end_data[1] = Number(end_data[1]) + move_size;
							end_data[2] = Number(end_data[2]) + move_size;

						} else if (end_data[0] == "A") {
							//console.log("A _____");
						} else if (end_data[0] == "Z") {
							//console.log("A _____");
						}

					} else if (component.type == "line") {

						//end_data[1] = Number(end_data[1]) + move_size;
						end_data[2] = Number(end_data[2]) + move_size;

					}

				}
 

			}

		} else {
			alert("수치모델에 " + p_op_data.name + " 점이 존재하지 않습니다.");
		}

	};
 

	var _op_tp_move = function(p_op_data, p_dm) {
 
		var move_ratio = p_dm.ratio;
		var op_data = p_op_data.data; // ["tp", "+", "p15-p14", "5"]
		var op_direction = op_data[1];
		var op_tp = op_data[2].split("-");
		var op_size = Number(op_data[3]);

		var mi_direction = p_op_data.mi_direction;

		// 시작점과 끝점을 정의 한다. 

		var tp_start_point = null;
		var tp_end_point = null;

		// 시작점
		// svg_center 는 예약어 : template의 중심점

		if (op_tp[0] == "svg_center") {

			tp_start_point = p_dm.center;

		} else {

			if (p_dm.point_dic[op_tp[0].toUpperCase()]) {

				var dme_first_data = p_dm.point_dic[op_tp[0].toUpperCase()].dme_index[0];
				var dme_first_start_or_end = dme_first_data.type;
				var first_component = p_dm.component[dme_first_data.component_index];
				tp_start_point = first_component[dme_first_start_or_end];

			} else {

				alert("point_dic에 tp_start_point가 존재하지 않습니다. "	+ op_tp[0].toUpperCase());
				return;

			}

		}

		if (op_tp[1] == "svg_center") {
			tp_end_point = p_dm.center;
		} else {

			if (p_dm.point_dic[op_tp[1].toUpperCase()]) {

				var dme_first_data = p_dm.point_dic[op_tp[1].toUpperCase()].dme_index[0];
				var dme_first_start_or_end = dme_first_data.type;
				var first_component = p_dm.component[dme_first_data.component_index];
				tp_end_point = first_component[dme_first_start_or_end];

			} else {

				alert("point_dic에 tp_start_point가 존재하지 않습니다. "	+ op_tp[1].toUpperCase());
				return;

			}
		}

		var move_size = 0;

		if (mi_direction == "+" && op_direction == "+") {
			move_size = Number(p_op_data.step) * move_ratio * op_size;
		} else if (mi_direction == "+" && op_direction == "-") {
			move_size = (Number(p_op_data.step) * move_ratio * op_size) * -1;
		} else if (mi_direction == "-" && op_direction == "+") {
			move_size = (Number(p_op_data.step) * move_ratio * op_size) * -1;
		} else if (mi_direction == "-" && op_direction == "-") {
			move_size = Number(p_op_data.step) * move_ratio * op_size;
		}

		var tp_moved_point = get_point_by_extension(tp_start_point,				tp_end_point, move_size);
 
		if (p_dm.point_dic[p_op_data.target]) {

			var point_index_data = p_dm.point_dic[p_op_data.target].dme_index;

			for ( var pid_index in point_index_data) {

				var component_index = point_index_data[pid_index].component_index;
				var point_start_or_end = point_index_data[pid_index].type;
				var component = p_dm.component[component_index];
				var component_data = component.data;
				var point_data = component[point_start_or_end];
 
				point_data.x = Number(tp_moved_point.x);
				point_data.y = Number(tp_moved_point.y);

				if (point_start_or_end == "start") {

					var start_data = component_data[0];
					start_data[1] = Number(tp_moved_point.x);
					start_data[2] = Number(tp_moved_point.y);

				} else {
					// end 포인트를 조작한다면...

					var end_data = null;

					if (component_data.length == 1) {
						end_data = component_data[0];
					} else {
						end_data = component_data[1];
					}

					if (component.type == "path") {

						if (end_data[0] == "L") { // 라인

							end_data[1] = Number(tp_moved_point.x);
							end_data[2] = Number(tp_moved_point.y);

						} else if (end_data[0] == "H") { // 수평

							end_data[1] = Number(tp_moved_point.x);

						} else if (end_data[0] == "V") { // 수직

							end_data[1] = Number(tp_moved_point.y);

						} else if (end_data[0] == "C") {
 

							end_data[5] = Number(tp_moved_point.x);
							end_data[6] = Number(tp_moved_point.y);

						} else if (end_data[0] == "S") {
 

							end_data[3] = Number(tp_moved_point.x);
							end_data[4] = Number(tp_moved_point.y);

						} else if (end_data[0] == "Q") {
 

							end_data[3] = Number(tp_moved_point.x);
							end_data[4] = Number(tp_moved_point.y);

						} else if (end_data[0] == "T") {
 
							end_data[1] = Number(tp_moved_point.x);
							end_data[2] = Number(tp_moved_point.y);

						} else if (end_data[0] == "A") {
							//console.log("A _____");
						} else if (end_data[0] == "Z") {
							//console.log("A _____");
						}

					} else if (component.type == "line") {
 
						end_data[1] = Number(tp_moved_point.x);
						end_data[2] = Number(tp_moved_point.y);

					}

				} 
			}

		} else {
			alert("수치모델에 " + p_op_data.name + " 점이 존재하지 않습니다.");
		}

	};
	
	_init();
	
	var test = function() {
		alert("dmc.js test");
	};
	
	return {
		load_template : load_template,
		set_template : set_template,
		move_template : move_template,
		set_canvas : set_canvas,
		clear_template_data :clear_template_data,
		clear_canvas : clear_canvas, 
		show_template_data : show_template_data,
		control_template : control_template,
		test : test
	};

})();