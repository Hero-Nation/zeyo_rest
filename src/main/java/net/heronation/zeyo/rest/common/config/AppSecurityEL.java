package net.heronation.zeyo.rest.common.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;

@Slf4j
@Component("AppSecurityEL")
public class AppSecurityEL {

	@Autowired
	ItemRepository itemRepository;

	public boolean isMyItem(Authentication auth, Long item_id) {
		log.debug("AppSecurityEL auth " + auth);
		log.debug("AppSecurityEL isMyItem " + item_id);

		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();
		
		Long user_seq = Long.valueOf((Integer) user.get("member_seq"));


		
		Item target_item = itemRepository.getOne(item_id);
		
		log.debug("AppSecurityEL user_seq " + user_seq);
		log.debug("AppSecurityEL target_item.getMember().getId() " + target_item.getMember().getId());
		
		
		if (target_item.getMember().getId() == user_seq) {
			return true;
		}

		return false;
	}

	
	public boolean itemcheck() {
		log.debug("AppSecurityEL itemcheck "); 
 

		return true;
	}
}