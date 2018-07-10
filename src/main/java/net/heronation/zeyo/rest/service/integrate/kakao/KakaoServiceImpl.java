package net.heronation.zeyo.rest.service.integrate.kakao;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.LoggingRequestInterceptor;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.controller.integrate.kakao.dto.AccessTokenByOauthCode;
import net.heronation.zeyo.rest.controller.integrate.kakao.dto.ProfileDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.consumer.Consumer;
import net.heronation.zeyo.rest.repository.consumer.ConsumerRepository;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
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
	ConsumerRepository consumerRepository;
	
	
	
	
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
	
	
	@Value(value = "${oauth.kakao.client.id}")
	private String kakao_client_id;

	@Value(value = "${oauth.kakao.client.secret}")
	private String kakao_client_secret;
	
	
	@Value(value = "${oauth.kakao.client.redirect.url}")
	private String kakao_client_redirect_url;
	
	
	
	
	
	@Override
	public String update_oauth_code_and_get_access_token(String auth_code, String p_session) throws CommonException  {
		log.debug("update_oauth_code_and_get_access_token");

		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(httpRequestFactory);
		// requestFactory.setOutputStreaming(false);

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		// converters.add(new StringHttpMessageConverter()); 
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		restTemplate.setMessageConverters(converters);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", kakao_client_id);
		body.add("client_secret", kakao_client_secret); 
		body.add("code", auth_code);
		body.add("redirect_uri", kakao_client_redirect_url);

		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", header_credential);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		try {

			AccessTokenByOauthCode result = restTemplate.postForObject(String.format("https://kauth.kakao.com/oauth/token", ""),
					request, AccessTokenByOauthCode.class);

			if(result != null) {
				log.debug(result.toString());
			}else {
				log.debug((result == null) + "");	
			}
			
			
			// 프로필 정보 가지고 오기
			HttpHeaders get_profile_headers = new HttpHeaders();
			get_profile_headers.set("Authorization", String.format("Bearer %s",result.getAccess_token()));
			get_profile_headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity get_profile_entity = new HttpEntity(get_profile_headers);

			try {

				URI uri = UriComponentsBuilder.newInstance().scheme("https")
						.host("kapi.kakao.com").path("/v2/user/me")
//						.queryParam("limit", 100)
//						.queryParam("offset", 0)
						.build()
						.encode()
						.toUri();

				ResponseEntity<ProfileDto> response = restTemplate.exchange(uri, HttpMethod.GET, get_profile_entity, ProfileDto.class);
				log.debug(response.getStatusCodeValue()+ " " );
				ProfileDto profile = response.getBody();
				log.debug(profile.toString());
				 
				
				
//				{
//				    "id": 796464350,
//				    "kakao_account": {
//				        "has_email": true,
//				        "is_email_valid": true,
//				        "is_email_verified": true,
//				        "email": "heronation@naver.com",
//				        "has_age_range": false,
//				        "has_birthday": false,
//				        "has_gender": false
//				    }
//				}

				
				List<Consumer> cl = consumerRepository.findByCorpId("kakao",profile.getId());
				if(cl.size() > 0) {
					Consumer this_consumer = cl.get(0);
					this_consumer.setAccessToken(result.getAccess_token());
					this_consumer.setRefreshToken(result.getRefresh_token());
					this_consumer.setOauthCode(auth_code);
					this_consumer.setSession(p_session);
					this_consumer.setLastAccessDt(new DateTime());  
				}else {
					Consumer this_consumer = new Consumer();
					this_consumer.setAccessToken(result.getAccess_token());
					this_consumer.setCorpId(profile.getId());
					this_consumer.setCorpType("kakao");
					this_consumer.setCreateDt(new DateTime());
					this_consumer.setLastAccessDt(new DateTime());
					this_consumer.setOauthCode(auth_code);
					this_consumer.setSession(p_session);
					this_consumer.setRefreshToken(result.getRefresh_token());
					this_consumer.setUseYn("Y");  
					
					consumerRepository.save(this_consumer);
				}
				
				//return state;
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				log.error(e.getResponseBodyAsString());
				throw new CommonException(e.getResponseBodyAsString());
			}
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				log.error(e.getResponseBodyAsString());
				throw new CommonException(e.getResponseBodyAsString());
			}
	 
		
		
		return "";
		
	}

	@Override
	public String get_access_token_by_refresh_token(Long shopmall_id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}

}
