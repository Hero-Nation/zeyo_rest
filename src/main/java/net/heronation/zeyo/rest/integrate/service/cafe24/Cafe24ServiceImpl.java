package net.heronation.zeyo.rest.integrate.service.cafe24;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.brand.repository.Brand;
import net.heronation.zeyo.rest.brand.repository.BrandRepository;
import net.heronation.zeyo.rest.category.repository.Category;
import net.heronation.zeyo.rest.category.repository.CategoryRepository;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColor;
import net.heronation.zeyo.rest.cloth_color.repository.ClothColorRepository;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.AccessTokenByOauthCode;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Product;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Products;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.ScriptCreateDto;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.ScriptCreateRequestDto;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.ScriptCreateResponseDto;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Variant;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.VariantOption;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Variants;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item.repository.ItemRepository;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMap;
import net.heronation.zeyo.rest.item_cloth_color_map.repository.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.kindof.repository.Kindof;
import net.heronation.zeyo.rest.kindof.repository.KindofRepository;
import net.heronation.zeyo.rest.madein.repository.Madein;
import net.heronation.zeyo.rest.madein.repository.MadeinRepository;
import net.heronation.zeyo.rest.material.repository.Material;
import net.heronation.zeyo.rest.material.repository.MaterialRepository;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.member.repository.MemberRepository;
import net.heronation.zeyo.rest.shopmall.repository.QShopmall;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.shopmall.repository.ShopmallRepository;
import net.heronation.zeyo.rest.size_option.repository.SizeOption;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionRepository;
import net.heronation.zeyo.rest.sub_category.repository.SubCategory;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.warranty.repository.Warranty;
import net.heronation.zeyo.rest.warranty.repository.WarrantyRepository;

@Slf4j
@Service
@Transactional
public class Cafe24ServiceImpl implements Cafe24Service {

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
	@Transactional(readOnly = true)
	public Shopmall get_shopmall_temp_identity(Long shopmall_id) {
		log.debug("shopmall_id  " + shopmall_id);
		return shopmallRepository.findOne(shopmall_id);
	}

	@Override
	@Transactional
	public String update_oauth_code_and_get_access_token(String auth_code, String oauth_id) {
		log.debug("update_oauth_code_and_get_access_token");
		QShopmall sm = QShopmall.shopmall;

		Shopmall this_shopmall = shopmallRepository.findOne(sm.oauthID.eq(oauth_id));
	 
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

	@Override
	@Transactional
	public String get_access_token_by_refresh_token(Long shopmall_id) {
		log.debug("get_access_token_by_refresh_token");

		Shopmall this_shopmall = shopmallRepository.findOne(shopmall_id);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(converters);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", header_credential);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "refresh_token");
		body.add("refresh_token", this_shopmall.getRefreshToken());
		

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		log.debug(header_credential);
		log.debug(this_shopmall.toString());
		log.debug(String.format(oauth_code_url, this_shopmall.getStoreId())); 
		
		try {

			AccessTokenByOauthCode result = restTemplate.postForObject(
					String.format(oauth_code_url, this_shopmall.getStoreId()), request, AccessTokenByOauthCode.class);

			this_shopmall.setAccessToken(result.getAccess_token());
			this_shopmall.setRefreshToken(result.getRefresh_token());
			return CommonConstants.SUCCESS;
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			log.error(e.getResponseBodyAsString());
			return e.getResponseBodyAsString();

		}
	}

	@Override
	@Transactional
	public String sync_product(Long shopmall_id, int page, String last_id, Long member_id) {
		log.debug("sync_product");

		// 처음호출할때는 무조건 page 0 , last_id null 이다.
		
		if (last_id == null) { 
			
			StringBuffer query = new StringBuffer(); 
			query.append(" SELECT i.code,i.shop_product_id  ");
			query.append(" FROM   item_shopmall_map ism ");
			query.append("       LEFT JOIN item i ");
			query.append("              ON ism.item_id = i.id ");
			query.append(" WHERE  ism.shopmall_id = "+shopmall_id);
			query.append(" ORDER  BY i.shop_product_id DESC ");
			query.append(" LIMIT  1");

			Query q = entityManager.createNativeQuery(query.toString());
			List<Object[]> last = q.getResultList();
			
			
			log.debug("last.size()");
			log.debug(last.size() + " ");
			if (last.size() == 0) {
				last_id = "init";
			} else {
				
				Object[] item = last.get(0);
				log.debug(Arrays.toString(item));
				last_id = item[1] + "_" + item[0];
			}
		}

		log.debug("last_id");
		log.debug(last_id);

		// 직접 입력 종류값. 아래 cloth_color , size_option 을 입력할때 필요한 값들
		Kindof direct_kind = kindOfRepository.findOne(Long.valueOf(index_kindof_direct));
		Kindof size_option_direct_kind = kindOfRepository.findOne(Long.valueOf(index_sizeoption_kindof_direct));
		
		Category import_default_cateogry = categoryRepository.findOne(Long.valueOf(index_default_import_category));
		SubCategory import_default_sub_cateogry = subCategoryRepository.findOne(Long.valueOf(index_default_import_subcategory));
		Brand import_default_brand = brandRepository.findOne(Long.valueOf(index_default_import_brand));
		
		Madein import_default_madein = madeinRepository.findOne(Long.valueOf(index_default_import_madein));
		Warranty import_default_warranty = warrantyRepository.findOne(Long.valueOf(index_default_import_warranty));
		Material import_default_material = materialRepository.findOne(Long.valueOf(index_default_import_material));
		
		
		
		page = page + 1;

		Shopmall this_shopmall = shopmallRepository.findOne(shopmall_id);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(converters);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", String.format("Bearer %s", this_shopmall.getAccessToken()));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity entity = new HttpEntity(headers);

		try {
			int page_size = 2;

			URI uri = UriComponentsBuilder.newInstance().scheme("https")
					.host(String.format("%s.cafe24api.com", this_shopmall.getStoreId()))
					.path("/api/v2/admin/products")
					.queryParam("limit", page_size)
					.queryParam("offset", (page - 1) * page_size)
					.build()
					.encode()
					.toUri();

			ResponseEntity<Products> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Products.class);

			Products list = response.getBody();

			for (Product p : list.getProducts()) {

				log.debug("last_id  : " + last_id + " , product no + code " + p.getProduct_no() + "_"
						+ p.getProduct_code());

				if (last_id.equals(p.getProduct_no() + "_" + p.getProduct_code())) {
					return CommonConstants.SUCCESS;
				} else {
					
					
					
					Item i = p.convertToEntity(); 

					Member m = memberRepository.findOne(member_id);
					i.setMember(m);
					i.setSizeTableYn("N");
					i.setLinkYn("N");
					i.setBrand(import_default_brand);
					i.setCategory(import_default_cateogry);
					i.setSubCategory(import_default_sub_cateogry); 
					i.setMadein(import_default_madein);
					i.setWarranty(import_default_warranty);
					i.setBleachYn("N");
					i.setDrycleaningYn("N");
					i.setDrymethodYn("N");
					i.setIroningYn("N");
					i.setLaundryYn("N");
					i.setMadeinBuilder("IMPORT_DEFAULT");
					i.setMadeinDate(new DateTime());
					i = itemRepository.save(i);

					Shopmall s = shopmallRepository.findOne(shopmall_id);

					ItemShopmallMap ism = new ItemShopmallMap();
					ism.setItem(i);
					ism.setShopmall(s);
					ism.setUseYn("Y");

					itemShopmallMapRepository.save(ism);
					
					ItemMaterialMap imm = new ItemMaterialMap();
					imm.setContain("100");
					imm.setItem(i);
					imm.setMaterial(import_default_material);
					imm.setUseLocatoin("A");
					imm.setUseYn("Y");
					itemMaterialMapRepository.save(imm);
					
					
					
					
					URI get_option_variant_uri = UriComponentsBuilder.newInstance().scheme("https")
							.host(String.format("%s.cafe24api.com", this_shopmall.getStoreId()))
							.path(String.format("/api/v2/admin/products/%s/variants", p.getProduct_no()))
//							.queryParam("limit", page_size)
//							.queryParam("offset", (page - 1) * page_size)
							.build()
							.encode()
							.toUri();

					ResponseEntity<Variants> get_option_response = restTemplate.exchange(get_option_variant_uri, HttpMethod.GET, entity, Variants.class);
					
					
					Variants item_variants = get_option_response.getBody();
				 
					// 상품 옵션 가지고 오기 
					
					Map<String,String> color_store = new HashMap<String,String>();
					Map<String,String> size_store = new HashMap<String,String>();
					
					for (Variant  v : item_variants.getVariants()) { 
						for (VariantOption  vo : v.getOption()) {
							if(vo.getName().equals("색상")) {
								if(!color_store.containsKey(vo.getValue())) {
									color_store.put(vo.getValue(), "cafe24_value");
								}
							}else if(vo.getName().equals("사이즈")) {
								if(!size_store.containsKey(vo.getValue())) {
									size_store.put(vo.getValue(), "cafe24_value");
								}
							}
						}
						
					}
					
					Iterator<String> colors = color_store.keySet().iterator();
					
					while(colors.hasNext()) {
						//colors.next()
						
						String this_color_value = colors.next();
						List<ClothColor> color_db_list = clothColorRepository.findByName(this_color_value);
						
						ClothColor this_cc = new ClothColor();
						
						if(color_db_list.size() > 0) {
							
							this_cc = color_db_list.get(0);
							
							
						}else {
							
							
							this_cc.setCreateDt(new DateTime());
							this_cc.setKindof(direct_kind);
							this_cc.setName(this_color_value);
							this_cc.setUseYn("Y");
							this_cc = clothColorRepository.save(this_cc);							
							
						}
						
						
						ItemClothColorMap cc_map = new ItemClothColorMap();
						cc_map.setClothColor(this_cc);
						cc_map.setItem(i);
						cc_map.setOptionValue("store_import_value");
						cc_map.setUseYn("Y");
						
						
						itemClothColorMapRepository.save(cc_map);

							
						
					}
					
					Iterator<String> sizes = size_store.keySet().iterator();
					
					while(sizes.hasNext()) {
						
						String this_size_value = sizes.next();
						
						// category 5 
						// sub_category 7  >>> 기본값
						// findByNameDirect 값을 가지고 올때 where 절을 조절할것.. 
						
						List<SizeOption> sizeop_db_list = sizeOptionRepository.findByName(this_size_value);
						
						SizeOption this_so = new SizeOption();
						
						if(sizeop_db_list.size() > 0) {
							
							this_so = sizeop_db_list.get(0);
							
							
						}else {
							
							
							this_so.setCreateDt(new DateTime());
							this_so.setKindof(size_option_direct_kind);
							this_so.setCategory(import_default_cateogry);
							this_so.setSubCategory(import_default_sub_cateogry);
							
							this_so.setName(this_size_value);
							this_so.setUseYn("Y");
							
							this_so = sizeOptionRepository.save(this_so);							
							
						}
						
						
						ItemSizeOptionMap isom = new ItemSizeOptionMap();
						isom.setSizeOption(this_so);
						isom.setItem(i);
						isom.setOptionValue("store_import_value");
						isom.setUseYn("Y");
						
						
						itemSizeOptionMapRepository.save(isom);
					}
					 

				}
			}

			if (list.getProducts().length == 0) {
				return CommonConstants.SUCCESS;
			} else {
				sync_product(shopmall_id, page, last_id, member_id);
			}

		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			get_access_token_by_refresh_token(shopmall_id);
			sync_product(shopmall_id, page, last_id, member_id);

		}

		return CommonConstants.SUCCESS;
	}

}
