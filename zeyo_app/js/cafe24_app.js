(function(){
  
  $("div[class^='ec-base-button']:not(.xans-product-action)").append( $( "<a href='javascript:zeyo_app.start();' class='zeyo_btn' ><img src='https://www.zeyo.co.kr/app/img/button.png'></a>" ) );


   
  
})();


var zeyo_app = zeyo_app || {};
zeyo_app.start = function(){
	
	var popupWidth = 1200;
	var popupHeight = 1000;
	
	var popupX = (window.screen.width / 2) - (popupWidth / 2);

	var popupY= (window.screen.height /2) - (popupHeight / 2);

	var shop_type = "cafe24";
	var shop_id = window.location.href.split("://")[1].split(".")[0];
	var product_id = iProductNo;
	
	window.open('https://www.zeyo.co.kr/zeyo_app/main?shop_type=cafe24&shop_id='+shop_id+'&product_id='+product_id, '_blank', 'toolbar=no,status=no,menubar=no,resizable=no, location=no, height='+ popupHeight + ', width='+ popupWidth + ', left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
	
};




