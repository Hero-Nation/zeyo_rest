package net.heronation.zeyo.rest.controller.item;

import net.heronation.zeyo.rest.common.authentication.AppUserDetails;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.ItemResourceAssembler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.item.ItemService;

@Slf4j
@RepositoryRestController
@RequestMapping("/items")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository repository;
	@Autowired
	private ItemResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ItemController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

//	@RequestMapping(path = "/{item_id}")
//	public ResponseEntity<ResultVO> test(@PathVariable long item_id) {
//		log.debug("/{item_id}");
//		
//		Item target = repository.getOne(item_id);
//		
//		return return_success(target);
//	}
 

	@PreAuthorize("@AppSecurityEL.hasScope('bar') and  hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "download_excel")
	public ResponseEntity<ResultVO> download_excel(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/items/download_excel");
 

		return return_success();
	}


//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(path = "",method=RequestMethod.POST)
//	public ResponseEntity<ResultVO> upload(Item item) {
//		log.debug("/api/items");
// 
//		log.debug(item.toString());
//		
//		return return_success();
//	}
//	
}