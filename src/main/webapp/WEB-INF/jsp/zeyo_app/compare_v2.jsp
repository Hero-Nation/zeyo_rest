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

	var in_unit_display = false;
	var input_mode = "SIMPLE";
	var centi_to_inch = 0.393701;
	var body_type = "NOT";
	var selected_so_id = "";
	var chest_mi_id = 0;
	
	
	
	

	$(window)
			.load(
					function() {
						openLay('layerArea');

						$("body").on(
								"click",
								".bodyCheck button",
								function() {
									$(this).addClass("check");
									$(".bodyCheck button").not($(this))
											.removeClass("check");
								});

						$
								.each(
										ItemScmmSoValue.size,
										function(index, item) {
											so[item.so_id] = item;
											mi[item.mi_id] = item;

											iv[item.so_id + "_" + item.mi_id] = item.input_value;
											iv_inch[item.so_id + "_"
													+ item.mi_id] = (item.input_value * centi_to_inch)
													.toFixed(1);
											;

											if (item.mi_name == "가슴둘레")
												chest_mi_id = item.mi_id;

										});

						$
								.each(
										so,
										function(index, item) {
											$(".sizebutton")
													.append(
															"<button id='btn_so_"
																	+ item.so_id
																	+ "' onclick='f_size_option_click(\""
																	+ item.so_id
																	+ "\")'>"
																	+ item.so_name
																	+ "</button>")
										});

						$("#size_table_header_block").append("<th>상품명</th>");
						$.each(mi, function(index, item) {
							$("#size_table_header_block").append(
									"<th>" + item.mi_name + "</th>");
						});

						$("#size_table_body_block").append(
								"<td>" + item.name + "</td>");
						$.each(mi, function(index, item) {
							$("#size_table_body_block").append("<td></td>");
						});

					});

	var f_compare_by_product = function() {
		
		compare_mode = CTS_COMPARE_MODE_BY_PRODUCT;
		
		$("#form_panel_by_info").hide();
		$("#form_panel_by_product").show();
		$("#form_panel_by_product_radio_by_info").prop("checked", false);
		$("#form_panel_by_product_radio_by_product").prop("checked", true);

	};

	var f_compare_by_info = function() {
		
		
		compare_mode = CTS_COMPARE_MODE_BY_INFO;

		$("#form_panel_by_info").show();
		$("#form_panel_by_product").hide();
		$("#form_panel_by_info_radio_by_product").prop("checked", false);
		$("#form_panel_by_info_radio_by_info").prop("checked", true);

	};

	var f_show_panel_size_body = function() { 
		$("#main_panel_size_body").show();
		$("#main_panel_compare_by_product").hide();
		$("#main_panel_compare_by_info").hide(); 
		
		
		
	};
	
	var f_form_init = function(){
		$("#form_panel_by_product_input_search").val("");
		$("#form_panel_by_info_input_height").val("");
		$("#form_panel_by_info_input_weight").val("");
		$("#form_panel_by_info_input_chest").val("");
		$("#form_panel_by_info_input_waist").val(""); 
	};
	
	var f_select_body_type = function(){
		$("#main_panel_size_body").hide();
		
		if(compare_mode = CTS_COMPARE_MODE_BY_PRODUCT){
			$("#main_panel_compare_by_product").show();	
		}else{
			$("#main_panel_compare_by_info").show(); 	
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

						<div class="size type01" id="form_panel_by_info" 	style="display: none;">

							<div class="radioWrap">
								<span class="rdo_inline"> 
								<input type="radio" id="form_panel_by_info_radio_by_product" onclick="f_compare_by_product();" /> 
								<label for="form_panel_by_info_radio_by_product">상품으로 비교</label>
								</span> 
								<span class="rdo_inline"> 
									<input type="radio" id="form_panel_by_info_radio_by_info" checked="checked" onclick="f_compare_by_info();" /> 
								<label for="form_panel_by_info_radio_by_info">기본 정보로 비교</label>
								</span> 
								<span class="btn ml40"><button type="button">상세정보 입력</button></span>
							</div>
							<div class="inputWrap mt10">

								<span class="tit ml5">키</span>
								<div class="inputbox">
									<input type="text" id="form_panel_by_info_input_height"/> <span class="ft14">cm</span>
								</div>

								<span class="tit ml50">체중</span>
								<div class="inputbox">
									<input type="text"  id="form_panel_by_info_input_weight" /> <span class="ft14">kg</span>
								</div>
								<span class="btn mt-1 ml32"><button type="button">결과보기</button></span>
							</div>

							<div class="mt10">
								<span class="tit ml5">가슴둘레</span>
								<div class="inputbox">
									<input type="text"  id="form_panel_by_info_input_chest"/> <span class="ft14">cm</span>
								</div>

								<span class="tit ml20">허리둘레</span>
								<div class="inputbox">
									<input type="text"  id="form_panel_by_info_input_waist"/> <span class="ft14">cm</span>
								</div>

								<span class="btn mt-5 ml30"><button type="button"
										style="height: 45px">
										단위변환<br />( cm > inch)
									</button></span>
								<!--<div class="btn2"><button type="button">단위변환<br />( cm > inch)</button></div>-->
							</div>

							<div class="resultWrap mt15">
								<!--<span class="restxt">회원님의 가슴둘레 측정치는 약</span><span class="result">&nbsp;97.2 cm</span><span class="restxt">&nbsp;(972mm)&nbsp; 입니다.</span>-->
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
								<!--<span class="btn"><button type="button">상세정보 입력</button></span>-->
							</div>
							<div class="mt5">
								<div class="inputbox schBx">
									<input type="text" placeholder="예) 나드랑" id="form_panel_by_product_input_search"/>
								</div>
								<button class="schbt">검색</button>
							</div>
							<div class="pinkWrap">
								<span class="pinktx">※ 구매하신 상품을 선택 해 주세요.</span>
							</div>
							<div class="selectBx">
								<select name="shplist">
									<option value="" hidden>구매상품을 선택 해 주세요.</option>
									<option value="shp01">구매상품1</option>
									<option value="shp02">구매상품2</option>
									<option value="shp03">구매상품3</option>
									<option value="shp04">구매상품4</option>
									<option value="shp05">구매상품5</option>
									<option value="shp06">구매상품6</option>
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

				<div class="sizeBody type02 mt20" 
					id="main_panel_size_body">
					<p class="text restxtbox">
						<span class="top_txt">※정확한 사이즈 추천을 위해 본인의 체형을 선택 해 주세요.</span>
					<div class="bodyCheck">
						<button type="button" onclick="f_select_body_type('D');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-01.png"
								alt="" /> <span>마른체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('D');">
							<img
								src="https://www.zeyo.co.kr/static/zeyo_app/v2/images/body-02.png"
								alt="" /> <span>보통체형</span>
						</button>
						<button type="button" onclick="f_select_body_type('D');">
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
			<div class="sizeCloth" id="main_panel_compare_by_product"  style="display: none;">
				<!--<p class="text">구매하시려는 옷은 회원님의 가슴둘레보다 <span class="result red">0.8 in</span> 작습니다.</p>-->
				<div class="guide">
					<p class="buy_pro">
						<span></span>봄 신상 박스 롱 티셔츠..
					</p>
					<p class="compare_pro">
						<span></span>비교하려는 상품명
					</p>

				</div>

				<div class="sizebutton">
				</div>

				<div class="clothimg mt5">
					<div class="big_tshirt">
						<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
							width="378.047px" height="357.5px" viewBox="0 0 378.047 357.5"
							enable-background="new 0 0 378.047 357.5" xml:space="preserve">
<path fill="#FFF" stroke="#38499d" stroke-width="3"
								stroke-miterlimit="10"
								d="M293.081,121.25c0,0,8.363,171.5,13.863,234H70.979
	c5.5-62.5,13.863-234,13.863-234l-2.388,47.078l-9.976,22.232L1.28,152.438c1.5-3.5,65.069-115.46,65.533-115.46
	s74.333-34.647,74.333-34.647c3.321,7.107,12.258,13.347,20.321,16.982c12.063,5.438,27.517,5.25,27.517,5.25
	s15.409,0.188,27.472-5.25c8.063-3.635,17-9.875,20.322-16.982c0,0,73.869,34.647,74.333,34.647s64.148,113.189,65.532,115.46
	l-71.199,38.123l-9.975-22.232" />
<path fill="none" stroke="#38499d" stroke-width="3"
								stroke-miterlimit="10"
								d="M141.312,2.031
	c21.637,6.914,47.629,7.063,47.629,7.063l0,0c0,0,26.046-0.149,47.684-7.063" />
<path fill="none" stroke="#38499d" stroke-miterlimit="10"
								d="M223.989,15.248c0,0-10.951,4.189-35.049,4.189
	s-35.005-4.189-35.005-4.189" />
<path fill="none" stroke="#38499d" stroke-miterlimit="10"
								d="M66.843,36.917c0,0,19.965,49.166,17.982,85.5" />
<path fill="none" stroke="#38499d" stroke-miterlimit="10"
								d="M311.081,36.917c0,0-19.965,49.166-17.982,85.5" />
<path fill="none" stroke="#38499d" stroke-miterlimit="10"
								d="M247.372,7.297c0,0-5.616,10.775-20.541,19.453
	c-13.973,8.125-32.625,8.313-37.869,8.313s-23.896-0.188-37.869-8.313c-14.924-8.678-20.541-19.453-20.541-19.453" />
</svg>
					</div>


					<div class="small_tshirt">
						<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
							width="378.047px" height="357.5px" viewBox="0 0 378.047 357.5"
							enable-background="new 0 0 378.047 357.5" xml:space="preserve">
<path fill="rgba(255,255,255,0.3)" stroke="#ff1a77" stroke-width="3"
								stroke-miterlimit="10"
								d="M293.081,121.25c0,0,8.363,171.5,13.863,234H70.979
	c5.5-62.5,13.863-234,13.863-234l-2.388,47.078l-9.976,22.232L1.28,152.438c1.5-3.5,65.069-115.46,65.533-115.46
	s74.333-34.647,74.333-34.647c3.321,7.107,12.258,13.347,20.321,16.982c12.063,5.438,27.517,5.25,27.517,5.25
	s15.409,0.188,27.472-5.25c8.063-3.635,17-9.875,20.322-16.982c0,0,73.869,34.647,74.333,34.647s64.148,113.189,65.532,115.46
	l-71.199,38.123l-9.975-22.232" />
<path fill="none" stroke="#ff1a77" stroke-width="3"
								stroke-miterlimit="10"
								d="M141.312,2.031
	c21.637,6.914,47.629,7.063,47.629,7.063l0,0c0,0,26.046-0.149,47.684-7.063" />
<path fill="none" stroke="#ff1a77" stroke-miterlimit="10"
								d="M223.989,15.248c0,0-10.951,4.189-35.049,4.189
	s-35.005-4.189-35.005-4.189" />
<path fill="none" stroke="#ff1a77" stroke-miterlimit="10"
								d="M66.843,36.917c0,0,19.965,49.166,17.982,85.5" />
<path fill="none" stroke="#ff1a77" stroke-miterlimit="10"
								d="M311.081,36.917c0,0-19.965,49.166-17.982,85.5" />
<path fill="none" stroke="#ff1a77" stroke-miterlimit="10"
								d="M247.372,7.297c0,0-5.616,10.775-20.541,19.453
	c-13.973,8.125-32.625,8.313-37.869,8.313s-23.896-0.188-37.869-8.313c-14.924-8.678-20.541-19.453-20.541-19.453" />
</svg>
					</div>




				</div>

				<div class="tar">단위 : cm</div>

				<div class="sizetable">
					<table>
						<caption></caption>
						<colgroup>
							<col width="25%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
							<col width="12.5%" />
						</colgroup>
						<thead>
							<tr>
								<th>상품명</th>
								<th>사이즈</th>
								<th>어깨너비</th>
								<th>가슴둘레</th>
								<th>소매길이</th>
								<th>밑단둘레</th>
								<th>총기장</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>봄신상 박스 롱티셔츠..</td>
								<td>95</td>
								<td>34</td>
								<td>132</td>
								<td>44</td>
								<td>124</td>
								<td>75</td>
							</tr>
							<tr>
								<td>비교하려는 상품명</td>
								<td>one size</td>
								<td>40</td>
								<td>94</td>
								<td>56</td>
								<td>96</td>
								<td>64</td>
							</tr>
							<tr>
								<td>사이즈비교</td>
								<td>-</td>
								<td class="ft-red">-6</td>
								<td>48</td>
								<td class="ft-red">-12</td>
								<td>28</td>
								<td>11</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="btn minus">
					<button type="button" onclick="f_buy()">닫기</button>
				</div>
			</div>
			<div class="sizeCloth" id="main_panel_compare_by_info"  style="display: none;">

				<div class="guide">
					<p class="buy_pro">
						<span></span>봄 신상 박스 롱 티셔츠
					</p>
				</div>


				<div class="sizebutton"> 
				</div>

				<div class="clothimg mt20">


					<div class="big_tshirt">
						<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
							xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
							width="378.047px" height="357.5px" viewBox="0 0 378.047 357.5"
							enable-background="new 0 0 378.047 357.5" xml:space="preserve">
<path fill="#FFF" stroke="#38499c" stroke-width="3"
								stroke-miterlimit="10"
								d="M293.081,121.25c0,0,8.363,171.5,13.863,234H70.979
	c5.5-62.5,13.863-234,13.863-234l-2.388,47.078l-9.976,22.232L1.28,152.438c1.5-3.5,65.069-115.46,65.533-115.46
	s74.333-34.647,74.333-34.647c3.321,7.107,12.258,13.347,20.321,16.982c12.063,5.438,27.517,5.25,27.517,5.25
	s15.409,0.188,27.472-5.25c8.063-3.635,17-9.875,20.322-16.982c0,0,73.869,34.647,74.333,34.647s64.148,113.189,65.532,115.46
	l-71.199,38.123l-9.975-22.232" />
<path fill="none" stroke="#38499c" stroke-width="3"
								stroke-miterlimit="10"
								d="M141.312,2.031
	c21.637,6.914,47.629,7.063,47.629,7.063l0,0c0,0,26.046-0.149,47.684-7.063" />
<path fill="none" stroke="#38499c" stroke-miterlimit="10"
								d="M223.989,15.248c0,0-10.951,4.189-35.049,4.189
	s-35.005-4.189-35.005-4.189" />
<path fill="none" stroke="#38499c" stroke-miterlimit="10"
								d="M66.843,36.917c0,0,19.965,49.166,17.982,85.5" />
<path fill="none" stroke="#38499c" stroke-miterlimit="10"
								d="M311.081,36.917c0,0-19.965,49.166-17.982,85.5" />
<path fill="none" stroke="#38499c" stroke-miterlimit="10"
								d="M247.372,7.297c0,0-5.616,10.775-20.541,19.453
	c-13.973,8.125-32.625,8.313-37.869,8.313s-23.896-0.188-37.869-8.313c-14.924-8.678-20.541-19.453-20.541-19.453" />
</svg>
					</div>
				</div>
				<p class="text restxtbox">
					<span class="restxt">구매하시려는 옷은 회원님의 가슴둘레보다&nbsp;</span><span
						class="result blue">7.8 cm</span><span class="restxt">&nbsp;큽니다.</span>
				</p>
				<div class="tar">단위 : cm</div>

				<div class="sizetable">
					<table>
						<caption></caption>
						<colgroup>
							<col width="25%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr>
								<th>상품명</th>
								<th>사이즈</th>
								<th>어깨너비</th>
								<th>가슴둘레</th>
								<th>소매길이</th>
								<th>총기장</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>봄신상 박스 롱티셔츠</td>
								<td>100</td>
								<td>39</td>
								<td>105</td>
								<td>62</td>
								<td>75</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn">
					<button type="button">구매하기</button>
				</div>
			</div>
		</div>
	</div>
	<!--[e] Layer -->
</body>
</html>