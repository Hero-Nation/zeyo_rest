var MAIN_CANVAS = null;
var data_svg_element = [];
var data_unit_path = [];
var data_line = [];
var data_circle = {};
var data_text = {};
var data_point = [];
var data_point_map = {};
var extracted_data = {};
const circle_radius = 5;
const element_line_width = 2;

(function() {

})();

var loading = function() {
	var svg_data_text = $("#input_data_editor").val();

	if (MAIN_CANVAS != null) {
		MAIN_CANVAS.clear();

	} else {
		MAIN_CANVAS = SVG('MAIN_CANVAS');
	}

	MAIN_CANVAS.svg(svg_data_text);
	MAIN_CANVAS.on("mousemove", function(e) {
		$("#mouse_x").val(e.x);
		$("#mouse_y").val(e.y);
	});

};

var extract_element = function() {
	var target_index = $("#target_index").val();
	try {
		var target = MAIN_CANVAS.get(target_index);
		var elements = target.children();
		console.dir(elements);
		for ( var element_index in elements) {
			var element = elements[element_index];

			data_svg_element.push(element);

		}
		// console.dir(data_svg_element);

		extract_data();

	} catch (e) {
		alert("인덱스값을 높여보세요");
		console.dir(e);
	}
};

var extract_data = function() {

	if (data_svg_element.length == 0) {

		alert("loading 후 path를 추출해주세요!");

	} else {

		var output_return = "";
		var total_unit_count = 0;

		var before_end_point = null;

		for ( var data_svg_element_index in data_svg_element) {

			console.log("**************************")
			console.log("★ FULL PATH NUMBER  " + data_svg_element_index);

			var this_element = data_svg_element[data_svg_element_index];
			console.dir(this_element);

			if (this_element.type == "path") {

				var element_unit_data = this_element.array();

				for ( var unit_path_index in element_unit_data.value) {

					console.log("     ◎ UNIT PATH NUMBER  " + unit_path_index);

					if (unit_path_index != 0) {
						if (unit_path_index == 1) {

							var full_path_first_move_point = element_unit_data.value[0];

							var string_unit_data = "";
							string_unit_data += full_path_first_move_point[0];
							string_unit_data += full_path_first_move_point[1];
							string_unit_data += " ";
							string_unit_data += full_path_first_move_point[2];

							var start_point_name = "";
							var start_point_key = ""
									+ float_fixed(full_path_first_move_point[1])
									+ "-"
									+ float_fixed(full_path_first_move_point[2]);

							if (data_point_map[start_point_key]) {
								start_point_name = data_point_map[start_point_key];
							} else {
								total_unit_count++;
								start_point_name = "P" + total_unit_count;
								data_point_map[start_point_key] = start_point_name;
							}

							var start_point = {
								name : start_point_name,
								x : float_fixed(full_path_first_move_point[1]),
								y : float_fixed(full_path_first_move_point[2])
							};

							console.log("     		  START POINT  ");
							console.dir(start_point);
							console.log("     		  PATH  ");
							console.dir(element_unit_data.value[1]);

							var this_unit_path_data = element_unit_data.value[1];

							string_unit_data += element_data_processor(this_unit_path_data);

							var unit_path_svg_object = MAIN_CANVAS
									.path(string_unit_data);

							unit_path_svg_object.fill('none')
							unit_path_svg_object.stroke({
								color : '#000',
								width : element_line_width,
								linecap : 'round',
								linejoin : 'round'
							});

							unit_path_svg_object.data("start", start_point);
							data_point.push(start_point);

							var this_path_length = unit_path_svg_object
									.length();
							var this_path_last_point = unit_path_svg_object
									.pointAt(this_path_length);

							var end_point_name = null;
							var end_point_key = ""
									+ float_fixed(this_path_last_point.x) + "-"
									+ float_fixed(this_path_last_point.y);

							if (data_point_map[end_point_key]) {
								end_point_name = data_point_map[end_point_key];
							} else {
								total_unit_count++;
								end_point_name = "P" + total_unit_count;
								data_point_map[end_point_key] = end_point_name;
							}

							var end_point = {
								name : end_point_name,
								x : float_fixed(this_path_last_point.x),
								y : float_fixed(this_path_last_point.y)
							};

							console.log("     		  END POINT  ");
							console.dir(end_point);
							before_end_point = end_point;
							unit_path_svg_object.data("end", end_point);
							data_point.push(end_point);

							unit_path_svg_object.on('mouseenter', function(e) {

								element_focus(this);
							});

							unit_path_svg_object.on('mouseleave', function(e) {

								element_unfocus(this);
							});

							data_unit_path.push(unit_path_svg_object);

						} else {
							// 2이상부터는 draw data 만 존재한다.

							var start_point_name = "";
							var start_point_key = ""
									+ float_fixed(before_end_point.x) + "-"
									+ float_fixed(before_end_point.y);

							if (data_point_map[start_point_key]) {
								start_point_name = data_point_map[start_point_key];
							} else {
								total_unit_count++;
								start_point_name = "P" + total_unit_count;
								data_point_map[start_point_key] = start_point_name;
							}

							var start_point = {
								name : start_point_name,
								x : float_fixed(before_end_point.x),
								y : float_fixed(before_end_point.y)
							};

							console.log("     		  START POINT  ");
							console.dir(start_point);
							console.log("     		  PATH  ");
							console
									.dir(element_unit_data.value[unit_path_index]);

							var string_unit_data = "";
							string_unit_data += "M";
							string_unit_data += start_point.x;
							string_unit_data += " ";
							string_unit_data += start_point.y;

							var full_path_next_path = element_unit_data.value[unit_path_index];
							string_unit_data += element_data_processor(full_path_next_path);

							var unit_path_svg_object = MAIN_CANVAS
									.path(string_unit_data);
							unit_path_svg_object.fill('none')
							unit_path_svg_object.stroke({
								color : '#000',
								width : element_line_width,
								linecap : 'round',
								linejoin : 'round'
							});

							unit_path_svg_object.data("start", start_point);
							// 중복되어서 여기서는 하지 않는다.
							// data_point.push(start_point);

							var this_path_length = unit_path_svg_object
									.length();
							var this_path_last_point = unit_path_svg_object
									.pointAt(this_path_length);
							var end_point_name = null;
							var end_point_key = ""
									+ float_fixed(this_path_last_point.x) + "-"
									+ float_fixed(this_path_last_point.y);

							if (data_point_map[end_point_key]) {
								end_point_name = data_point_map[end_point_key];
							} else {
								total_unit_count++;
								end_point_name = "P" + total_unit_count;
								data_point_map[end_point_key] = end_point_name;
							}

							var end_point = {
								name : end_point_name,
								x : float_fixed(this_path_last_point.x),
								y : float_fixed(this_path_last_point.y)
							};

							console.log("     		  END POINT  ");
							console.dir(end_point);

							before_end_point = end_point;
							unit_path_svg_object.data("end", end_point);
							data_point.push(end_point);

							unit_path_svg_object.on('mouseenter', function(e) {
								console.log("PATH 2");
								element_focus(this);
							});

							unit_path_svg_object.on('mouseleave', function(e) {
								console.log("PATH 2");
								element_unfocus(this);
							});

							data_unit_path.push(unit_path_svg_object);

						}

					}// if (unit_path_index != 0) {
				}// for ( var unit_path_index in element_unit_data.value) {

			} else if (this_element.type == "line") {

				console.log("     ◎ UNIT LINE  ");

				var element_unit_data = this_element.array();

				var element_start_point = element_unit_data.value[0];
				var element_end_point = element_unit_data.value[1];

				var start_point_name = "";
				var start_point_key = "" + float_fixed(element_start_point[0])
						+ "-" + float_fixed(element_start_point[1]);

				if (data_point_map[start_point_key]) {
					start_point_name = data_point_map[start_point_key];
				} else {
					total_unit_count++;
					start_point_name = "P" + total_unit_count;
					data_point_map[start_point_key] = start_point_name;
				}

				var start_point = {
					name : start_point_name,
					x : float_fixed(element_start_point[0]),
					y : float_fixed(element_start_point[1])
				};

				console.log("     		  START POINT  ");
				console.dir(start_point);
				console.log("     		  PATH  ");
				console.dir(element_unit_data);

				var string_unit_data = "";
				string_unit_data += start_point.x;
				string_unit_data += ",";
				string_unit_data += start_point.y;

				var unit_line_svg_object = MAIN_CANVAS.line(element_unit_data);
				unit_line_svg_object.fill('none')
				unit_line_svg_object.stroke({
					color : '#000',
					width : element_line_width,
					linecap : 'round',
					linejoin : 'round'
				});

				unit_line_svg_object.data("start", start_point);
				// 중복되어서 여기서는 하지 않는다.
				// data_point.push(start_point);

				var end_point_name = null;
				var end_point_key = "" + float_fixed(element_end_point[0])
						+ "-" + float_fixed(element_end_point[1]);

				if (data_point_map[end_point_key]) {
					end_point_name = data_point_map[end_point_key];
				} else {
					total_unit_count++;
					end_point_name = "P" + total_unit_count;
					data_point_map[end_point_key] = end_point_name;
				}

				var end_point = {
					name : end_point_name,
					x : float_fixed(element_end_point[0]),
					y : float_fixed(element_end_point[1])
				};

				console.log("     		  END POINT  ");
				console.dir(end_point);

				before_end_point = end_point;
				unit_line_svg_object.data("end", end_point);
				data_point.push(end_point);

				data_unit_path.push(unit_line_svg_object);

				unit_line_svg_object.on('mouseenter', function(e) {
					element_focus(this);
				});

				unit_line_svg_object.on('mouseleave', function(e) {

					element_unfocus(this);
				});

			}

		}// for ( var data_svg_element_index in data_svg_element) {

		var component = [];
		var max_x = 0;
		var max_y = 0;

		for ( var unit_index in data_unit_path) {

			var this_e = data_unit_path[unit_index];
			var this_data = {};
			this_data.type = this_e.type;
			this_data.start = this_e.data("start");
			this_data.end = this_e.data("end");

			if (Number(this_data.start.x) > Number(max_x)) {
				max_x = Number(this_data.start.x);
			}

			if (Number(this_data.end.x) > Number(max_x)) {
				max_x = Number(this_data.end.x);
			}

			if (Number(this_data.start.y) > Number(max_y)) {
				max_y = Number(this_data.start.y);
			}

			if (Number(this_data.end.y) > Number(max_y)) {
				max_y = Number(this_data.end.y);
			}

			this_data.data = this_e.array().value;
			component.push(this_data);
		}

		extracted_data.center = {
			x : max_x / 2,
			y : max_y / 2
		};

		extracted_data.component = component;

		$("#output_data_editor").val("");
		$("#output_data_editor").val(JSON.stringify(extracted_data));
	}
};

var move_to_xy = function() {

	if (MAIN_CANVAS == null) {
		alert("그리기,path 가지고 오기 를 먼저해주세요");
		return;
	}

	MAIN_CANVAS.clear();

	var xy_x = $("#x_point").val();
	var xy_y = $("#y_point").val();

	var component = extracted_data.component;
	var before_center = extracted_data.center;

	var new_center_gap_x = xy_x - before_center.x;
	var new_center_gap_y = xy_y - before_center.y;

	for ( var element_index in component) {

		var this_element = component[element_index];

		if (this_element.type == "path") {

		} else if (this_element.type == "line") {
			console.log("line");
		}

	}

};

var element_focus = function(p_element) {

	var start_point = p_element.data("start");
	var end_point = p_element.data("end");
	console.log("element_focus");
	console.dir(start_point);
	console.dir(end_point);

	if (data_circle[start_point.name]) {
		var this_circle = data_circle[start_point.name];
		this_circle.show();

	} else {
		var this_circle = MAIN_CANVAS.circle(circle_radius).fill('#f06').move(
				Number(start_point.x), Number(start_point.y));
		data_circle[start_point.name] = this_circle;
		this_circle.show();
	}

	if (data_circle[end_point.name]) {
		var this_circle = data_circle[end_point.name];
		this_circle.show();

	} else {
		var this_circle = MAIN_CANVAS.circle(circle_radius).fill('#f06').move(
				Number(end_point.x), Number(end_point.y));
		data_circle[end_point.name] = this_circle;
		this_circle.show();
	}

	if (data_text[start_point.name]) {
		var this_text = data_text[start_point.name];
		this_text.show();

	} else {
		var this_text = MAIN_CANVAS.text(start_point.name).move(
				Number(start_point.x), Number(start_point.y)).font({
			fill : '#f06',
			family : 'Inconsolata'
		});
		data_text[start_point.name] = this_text;
		this_text.show();
	}

	if (data_text[end_point.name]) {
		var this_text = data_text[end_point.name];
		this_text.show();

	} else {
		var this_text = MAIN_CANVAS.text(end_point.name).move(
				Number(end_point.x), Number(end_point.y)).font({
			fill : '#f06',
			family : 'Inconsolata'
		});
		data_text[end_point.name] = this_text;
		this_text.show();
	}

	p_element.stroke({
		color : '#f00'
	});

	var info_data = "START POINT";
	info_data += "\n";
	info_data += JSON.stringify(start_point);
	info_data += "\n";
	info_data += "ELEMENT TYPE";
	info_data += "\n";
	info_data += JSON.stringify(p_element.type);
	info_data += "\n";
	info_data += "ELEMENT DATA";
	info_data += "\n";
	info_data += JSON.stringify(p_element.array().value);
	info_data += "\n";
	info_data += "END POINT";
	info_data += "\n";
	info_data += JSON.stringify(end_point);

	$("#mouse_info").val(info_data);
};

var element_unfocus = function(p_element) {

	var start_point = p_element.data("start");
	var end_point = p_element.data("end");

	console.log("element_unfocus");
	console.dir(start_point);
	console.dir(end_point);

	var this_start_circle = data_circle[start_point.name];
	this_start_circle.hide();

	var this_end_circle = data_circle[end_point.name];
	this_end_circle.hide();

	var this_start_text = data_text[start_point.name];
	this_start_text.hide();

	var this_end_text = data_text[end_point.name];
	this_end_text.hide();

	p_element.stroke({
		color : '#000'
	});
};

var element_data_processor = function(p_step_data, p_type) {

	console.log("element_data_processor p_step_data");
	console.dir(p_step_data);

	var R = "";

	if (p_type != "LINE") {

		if (p_step_data[0] == "L") {

			R += "L";
			R += " ";
			R += p_step_data[1];
			R += " ";
			R += p_step_data[2];

		} else if (p_step_data[0] == "H") {

			R += "H";
			R += " ";
			R += p_step_data[1];

		} else if (p_step_data[0] == "V") {

			R += "V";
			R += " ";
			R += p_step_data[1];

		} else if (p_step_data[0] == "C") {

			R += "C";
			R += " ";
			R += p_step_data[1];
			R += " ";
			R += p_step_data[2];
			R += ",";
			R += p_step_data[3];
			R += " ";
			R += p_step_data[4];
			R += ",";
			R += p_step_data[5];
			R += " ";
			R += p_step_data[6];

		} else if (p_step_data[0] == "S") {

			R += "S";
			R += " ";
			R += p_step_data[1];
			R += " ";
			R += p_step_data[2];
			R += ",";
			R += p_step_data[3];
			R += " ";
			R += p_step_data[4];

		} else if (p_step_data[0] == "Q") {

			R += "Q";
			R += " ";
			R += p_step_data[1];
			R += " ";
			R += p_step_data[2];
			R += ",";
			R += p_step_data[3];
			R += " ";
			R += p_step_data[4];

		} else if (p_step_data[0] == "T") {

			R += "T";
			R += " ";
			R += p_step_data[1];
			R += " ";
			R += p_step_data[2];

		} else if (p_step_data[0] == "A") {
			console.log("A _____");
		} else if (p_step_data[0] == "Z") {
			console.log("A _____");
		}
	} else {

		R += " ";
		R += p_step_data[1];
		R += " ";
		R += p_step_data[2];

	}

	R += " ";
	// console.log(R);

	return R;
}

var float_fixed = function(p_num) {
	return Number(p_num).toFixed(2);
}

var find_point_target = null;

var before_point = function() {
	var point_number = Number($("#point_number").val());

	if (point_number <= 1) {
		alert("이전의  점이 존재하지 않습니다");
		return;
	}

	point_number--;

	$("#point_number").val(point_number);

	for ( var point_index in data_point) {

		var target_point_name = data_point[point_index].name;
		
		if (target_point_name == ("P" + point_number)) {
			
			//console.log(target_point_name + " " +point_index + " " +point_number);
			
			if (find_point_target != null) {
				find_point_target.hide();
				find_point_target = null;
			}
			find_point_target = MAIN_CANVAS.circle(10).fill('#f06').move(
					Number(data_point[point_index].x),
					Number(data_point[point_index].y));
			find_point_target.show();
			return;
		}
	}

};

var after_point = function() {

	var point_number = $("#point_number").val();
	point_number++;

	$("#point_number").val(point_number);

	for ( var point_index in data_point) {

		var target_point_name = data_point[point_index].name;

		if (target_point_name == ("P" + point_number)) {
			
			//console.log(target_point_name + " " +point_index + " " +point_number);
			
			if (find_point_target != null) {
				find_point_target.hide();
				find_point_target = null;
			}
			find_point_target = MAIN_CANVAS.circle(10).fill('#f06').move(
					Number(data_point[point_index].x),
					Number(data_point[point_index].y));
			find_point_target.show();
			return;

		}
	}
};