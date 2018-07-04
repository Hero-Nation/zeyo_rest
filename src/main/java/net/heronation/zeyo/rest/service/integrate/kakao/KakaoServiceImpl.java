package net.heronation.zeyo.rest.service.integrate.kakao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.AccessTokenByOauthCode;
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.ScriptCreateDto;
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.ScriptCreateRequestDto;
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.ScriptCreateResponseDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;

@Slf4j
@Service
@Transactional
public class KakaoServiceImpl implements KakaoService {
	
	@Autowired
	ShopmallRepository shopmallRepository;

	@Autowired
	ItemShopmallMapRepository itemShopmallMapRepository;

	@Autowired
	ItemMaterialMapRepository itemMaterialMapRepository;

	
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ClothColorRepository clothColorRepository;
	
	@Autowired
	SizeOptionRepository sizeOptionRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	SubCategoryRepository subCategoryRepository;
	
	@Autowired 
	BrandRepository brandRepository;
	
	@Autowired
	MadeinRepository madeinRepository;
	 
	@Autowired
	WarrantyRepository warrantyRepository;
	
	@Autowired
	MaterialRepository materialRepository;
	
	@Autowired
	ItemClothColorMapRepository itemClothColorMapRepository;
	
	
	@Autowired
	ItemSizeOptionMapRepository itemSizeOptionMapRepository;
	
	
	
	
	@Autowired
	KindofRepository kindOfRepository;
	
	
	
	
	@Autowired 
	EntityManager entityManager;


	@Value(value = "${oauth.cafe24.client.key}")
	private String app_key;

	@Value(value = "${oauth.cafe24.client.secret}")
	private String app_secret;

	@Value(value = "${oauth.cafe24.client.credential}")
	private String header_credential;

	@Value(value = "${oauth.cafe24.client.redirect_url}")
	private String redirect_url;

	@Value(value = "${oauth.cafe24.client.oauth_code_url}")
	private String oauth_code_url;
	
	
	@Value(value = "${zeyo.config.index.kindof.direct}")
	private String index_kindof_direct;
	
	@Value(value = "${zeyo.config.index.sizeoption.kindof.direct}")
	private String index_sizeoption_kindof_direct;
	
	
	
	@Value(value = "${zeyo.config.index.default.import.category}")
	private String index_default_import_category;
	
	
	@Value(value = "${zeyo.config.index.default.import.subcategory}")
	private String index_default_import_subcategory;
	
	
	@Value(value = "${zeyo.config.index.default.import.brand}")
	private String index_default_import_brand;
	
	@Value(value = "${zeyo.config.index.default.import.madein}")
	private String index_default_import_madein;
	
	@Value(value = "${zeyo.config.index.default.import.warranty}")
	private String index_default_import_warranty;
	
	@Value(value = "${zeyo.config.index.default.import.material}")
	private String index_default_import_material;
	
	@Override
	public String update_oauth_code_and_get_access_token(String auth_code, String oauth_id) {
		log.debug("update_oauth_code_and_get_access_token");
		QShopmall sm = QShopmall.shopmall;

		Shopmall this_shopmall = shopmallRepository.findOne(sm.oauthID.eq(oauth_id));
		log.debug(this_shopmall.toString());
		
		if (this_shopmall == null) {
			return "shopmall.data.null";
		} else {

			

			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setOutputStreaming(false);

			List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
			converters.add(new MappingJackson2HttpMessageConverter());
			converters.add(new FormHttpMessageConverter());
			converters.add(new StringHttpMessageConverter());

			RestTemplate restTemplate = new RestTemplate(requestFactory);
			restTemplate.setMessageConverters(converters);

			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
			body.add("grant_type", "authorization_code");
			body.add("code", auth_code);
			body.add("redirect_uri", redirect_url);

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", header_credential);
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

			try {

				log.debug(String.format(oauth_code_url, this_shopmall.getStoreId()));
				
				AccessTokenByOauthCode result = restTemplate.postForObject(String.format(oauth_code_url, this_shopmall.getStoreId()), request,
						AccessTokenByOauthCode.class);

				this_shopmall.setAccessToken(result.getAccess_token());
				this_shopmall.setRefreshToken(result.getRefresh_token());
				this_shopmall.setStoreId(result.getMall_id());
				this_shopmall.setOauthCode(auth_code);
				
				log.debug(result.toString());
				

				
				// app script 등록
				
//				curl -X POST \
//				  'https://{mallid}.cafe24api.com/api/v2/admin/scripttags' \
//				  -H 'Authorization: Bearer {access_token}' \
//				  -H 'Content-Type: application/json' \
//				  -d '{
//				    "shop_no": 1,
//				    "request": {
//				        "src": "https:\/\/js-aplenty.com\/bar.js",
//				        "display_location": [
//				            "PRODUCT_LIST",
//				            "PRODUCT_DETAIL"
//				        ],
//				        "skin_no": [
//				            3,
//				            4
//				        ]
//				    }
//				}'
//				
				
				
				HttpHeaders create_headers = new HttpHeaders();
				create_headers.set("Authorization", "Bearer ".concat(result.getAccess_token()));
				create_headers.setContentType(MediaType.APPLICATION_JSON);
				
				
//				Map<String,  Object> create_request_body = new HashMap<String,  Object>();
//				create_request_body.put("src", "https://www.zeyo.co.kr/app/js/cafe24_app.js"); 
//				create_request_body.put("display_location", Arrays.asList("PRODUCT_DETAIL")); 
//				create_request_body.put("skin_no", Arrays.asList(8)); 
//				
//				Map<String, Object> create_body = new HashMap<String, Object>();
//				create_body.put("shop_no", 1); 
//				create_body.put("request", create_request_body); 

				ScriptCreateDto create_body = new ScriptCreateDto();
				create_body.setShop_no(1);
				
				ScriptCreateRequestDto script_create_req=  new ScriptCreateRequestDto();
				script_create_req.setDisplay_location(new String[]{"PRODUCT_DETAIL"});
				script_create_req.setSkin_no(new int[]{8});
				script_create_req.setSrc("https://www.zeyo.co.kr/app/js/cafe24_app.js");
				
				
				create_body.setRequest(script_create_req);
				
				HttpEntity<ScriptCreateDto> create_request = new HttpEntity<>(create_body, create_headers);
				
				
				ScriptCreateResponseDto create_Script_response = restTemplate.postForObject(
						String.format("https://%s.cafe24api.com/api/v2/admin/scripttags", this_shopmall.getStoreId()), create_request,
						ScriptCreateResponseDto.class);
				
				log.debug(create_Script_response.toString());
				
			//	{	
//			    "scripttag": {
//			        "shop_no": 1,
//			        "script_no": "1527128695613925",
//			        "client_id": "AMj8UZhBC9zsyTlFGI6PzC",
//			        "src": "https:\\\/\\\/js-aplenty.com\\\/bar.js",
//			        "display_location": [
//			            "PRODUCT_LIST",
//			            "PRODUCT_DETAIL"
//			        ],
//			        "skin_no": [
//			            3,
//			            4
//			        ],
//			        "created_date": "2017-03-15T13:27:53+09:00",
//			        "updated_date": "2017-03-15T13:27:53+09:00"
//			    }
//			}
//				
				
				
				
				
				
				return CommonConstants.SUCCESS;
			} catch (HttpClientErrorException e) {
				
				 log.debug(app_key);
				 log.debug(app_secret);
				 log.debug(header_credential);
				 log.debug(redirect_url);
				 log.debug(oauth_code_url);
				
				
				e.printStackTrace();
				log.error(e.getResponseBodyAsString());
				return e.getResponseBodyAsString();

			}

		}
	}

}
