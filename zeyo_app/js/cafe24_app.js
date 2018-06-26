(function(){
  
  $("div[class^='ec-base-button']:not(.xans-product-action)").append( $( "<a href='#none' class='zeyo_openpop' ><img src='https://www.zeyo.co.kr/app/img/button.png'></a>" ) );
  $("div[class^='ec-base-button']:not(.xans-product-action)").append( $( "<div class='wrapper'><div class='zeyo_popup'><iframe id='zeyo_frame' src=''><p>Your browser does not support iframes.</p></iframe><a href='#' class='zeyo_close'>X</a></div></div>"));
  

  $(".zeyo_popup").hide();
  $(".zeyo_openpop").click(function (e) {
      e.preventDefault();
      $("#zeyo_frame").attr("src", "www.naver.com"); 
      $(".zeyo_popup").fadeIn('slow');
  });

  $(".zeyo_close").click(function () {
      $(this).parent().fadeOut("slow");
  });
   
  
})();


var zeyo_app = zeyo_app || {};
zeyo_app.start = function(){
	  alert("비교 시작");ㅣ
};




