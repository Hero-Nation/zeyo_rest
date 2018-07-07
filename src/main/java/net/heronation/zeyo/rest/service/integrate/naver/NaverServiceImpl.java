package net.heronation.zeyo.rest.service.integrate.naver;

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
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.AccessTokenByOauthCode;
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
public class NaverServiceImpl implements NaverService {

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
	ConsumerRepository consumerRepository;

	
	@Autowired
	EntityManager entityManager;
	
	

	@Value(value = "${oauth.naver.client.id}")
	private String naver_client_id;

	@Value(value = "${oauth.naver.client.secret}")
	private String naver_client_secret;

	@Override
	public String update_oauth_code_and_get_access_token(String p_auth_code, String state, String session_id) throws CommonException {
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
		body.add("client_id", naver_client_id);
		body.add("client_secret", naver_client_secret);
		body.add("code", p_auth_code);
		body.add("state", state);

		HttpHeaders headers = new HttpHeaders();
		// headers.set("Authorization", header_credential);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		try {

			AccessTokenByOauthCode result = restTemplate.postForObject(String.format("https://nid.naver.com/oauth2.0/token", ""),
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
						.host("openapi.naver.com").path("/v1/nid/me")
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
//				    "resultcode": "00",
//				    "message": "success",
//				    "response": {
//				        "id": "51554177",
//				        "nickname": "\ud558\uc580\ucf54\ubfd4\uc18c",
//				        "profile_image": "https:\/\/phinf.pstatic.net\/contact\/39\/2016\/4\/19\/superpeace_1461027919942.jpg",
//				        "age": "40-49",
//				        "gender": "M",
//				        "email": "superpeace@naver.com",
//				        "name": "\uae40\uc218\uc5b8",
//				        "birthday": "07-30"
//				    }
//				}

				
				List<Consumer> cl=consumerRepository.findByCorpId("naver",profile.getResponse().getId());
				if(cl.size() > 0) {
					Consumer this_consumer = cl.get(0);
					this_consumer.setAccessToken(result.getAccess_token());
					this_consumer.setRefreshToken(result.getRefresh_token());
					this_consumer.setOauthCode(p_auth_code);
					this_consumer.setSession(state);
					this_consumer.setLastAccessDt(new DateTime());  
				}else {
					Consumer this_consumer = new Consumer();
					this_consumer.setAccessToken(result.getAccess_token());
					this_consumer.setCorpId(profile.getResponse().getId());
					this_consumer.setCorpType("naver");
					this_consumer.setCreateDt(new DateTime());
					this_consumer.setLastAccessDt(new DateTime());
					this_consumer.setOauthCode(p_auth_code);
					this_consumer.setSession(state);
					this_consumer.setRefreshToken(result.getRefresh_token());
					this_consumer.setUseYn("Y");  
					
					consumerRepository.save(this_consumer);
				}
				
				return state;
				
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				log.error(e.getResponseBodyAsString());
				throw new CommonException(e.getResponseBodyAsString());
			}
			
			 
			
		} catch (HttpClientErrorException e) {

			e.printStackTrace();
			throw new CommonException(e.getResponseBodyAsString());

		}

	}

	@Override
	public String get_access_token_by_refresh_token(Long shopmall_id) throws CommonException {
		// TODO Auto-generated method stub
		return null;
	}
 


}
