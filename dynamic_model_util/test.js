var target = SVG.get("target");
var length = target.length();
var rect = SVG.get("marker");

var cursor = 0;
var constants_fourth = 0.1;
var constants_third = 0.01;
var constants_double = 0.001;
var constants = 0.0001;

var move_rect = function(p_step) {
	cursor += p_step;

	var p = target.pointAt(cursor * length);
	rect.center(p.x, p.y);
};

$("#btn_third_back").bind("click", function() {
	move_rect(-constants_fourth);
});

$("#btn_third_back").bind("click", function() {
	move_rect(-constants_third);
});

$("#btn_double_back").bind("click", function() {
	move_rect(-constants_double);
});
$("#btn_back").bind("click", function() {
	move_rect(-constants);
});

$("#btn_forth").bind("click", function() {
	move_rect(constants);
});
$("#btn_double_forth").bind("click", function() {
	move_rect(constants_double);
});
$("#btn_third_forth").bind("click", function() {
	move_rect(constants_third);
});

$("#btn_fourth_forth").bind("click", function() {
	move_rect(constants_fourth);
});