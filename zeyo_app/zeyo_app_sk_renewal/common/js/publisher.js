var scrollTop,
	  pageNaviTop,
	  pageWidth,
	  lyNums = 0,
	  lyOne = 0;



/* publish script */
$(function () {
	
})

// 레이어 열기
function openLay(name){
	lyNums++;
	$(".layerArea").each(function(index){
		if($(this).hasClass(name)){
			$(this).layerScript({divs : name});
		}
	});
}
// 레이어 닫기
function layerClose(name){
	$(".layerArea").each(function(index){
		if($(this).hasClass(name)){
			var e = $(this);

			e.hide().attr("style","");
			$("#layerBg").attr("style","");

			lyNums --;
			if(lyNums == 0){
				$("html").css("overflow-y","auto");
				$("body").removeClass("lyOn");
				$("#layerBg").remove();
			}
		}
	});
}

// Plugin Script
jQuery(function($){
	//[s] Layer Script
	$.fn.layerScript = function(o){
		o = $.extend({
			divs : ''
		}, o || {});

		var e = $(this),
			  bg = $('<div id="layerBg"></div>'),
			  ly_w,
			  ly_h,
			  closeDiv = o.divs;

		//tab
		if(e.hasClass("tab")){
			// 플러그인 텝 메뉴
			$(".tabArea.ly").tabScript({
				btns : '.btnTab>a',
				conts : '.tabConts',
				classd: 'active'
			});
		}

		$("body").attr("lyNums", lyNums)
		// 열기
		if(!$("body").hasClass("lyOn")){
			$("html").css("overflow-y","hidden");
			$("body").addClass("lyOn");
			bg.prependTo($("body"));
		}

		ly_w = e.width();
		ly_h = e.height();
		e.css({"margin-left":-(ly_w/2), "margin-top":-(ly_h/2)}).show();

		if(lyNums == 2){
			$("#layerBg").css("z-index",12);
			e.css("z-index",13);
		}

		// 닫기
		$(this).find(".closeLy").off("click");
		$(this).find(".closeLy").on('click', function(){
			layerClose(closeDiv);
		});
	}
	//[e] Layer Script
});
