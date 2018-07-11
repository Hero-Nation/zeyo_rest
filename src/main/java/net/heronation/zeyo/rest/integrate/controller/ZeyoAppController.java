package net.heronation.zeyo.rest.integrate.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.category.repository.CategoryRepository;
import net.heronation.zeyo.rest.category.repository.CategoryResourceAssembler;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.integrate.service.cafe24.Cafe24Service;
import net.heronation.zeyo.rest.integrate.service.common.IntegrateCommonService;
import net.heronation.zeyo.rest.integrate.service.naver.NaverService;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;

@Slf4j
@Controller
@RequestMapping("/zeyo_app")
public class ZeyoAppController extends BaseController {

	@Autowired
	private Cafe24Service cafe24Service;

	@Autowired
	private NaverService naverService;

	@Autowired
	private IntegrateCommonService integrateCommonService;

	@Autowired
	private CategoryRepository repository;
	@Autowired
	private CategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	public ZeyoAppController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/integrate")
	public String integrate(@RequestParam(value = "id", required = false) long shopmall_id, Model m,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		Shopmall sm = cafe24Service.get_shopmall_temp_identity(shopmall_id);
		m.addAttribute("state", sm.getOauthID());

		return "zeyo_app/integrate";
	}

	public String generateState() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model m, HttpSession session) {
		m.addAttribute("state", session.getId());
		return "zeyo_app/login";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/main")
	public String main(Model m, @RequestParam(value = "shop_type", required = false) String shop_type,
			@RequestParam(value = "shop_id", required = false) String shop_id,
			@RequestParam(value = "product_id", required = false) String product_id, HttpSession session) {

		// 스프링의 session은 사용하지 않는다.

		log.debug("main");
		log.debug("session.getId()");
		log.debug(session.getId());
		
		if (integrateCommonService.haveIpSession(session.getId(), shop_type, shop_id, product_id)) {

			Map<String, Object> idr = integrateCommonService.get_zeyo_product_id(shop_type, shop_id, product_id);
			String id = String.valueOf(idr.get("id"));
			String link_yn = String.valueOf(idr.get("link_yn"));

			if (id == null) {
				return "redirect:https://www.zeyo.co.kr/zeyo_app/not_zeyo_product";

			} else {

				if (link_yn != null && link_yn.equals("Y")) {
					m.addAttribute("zeyo_pid", id);
					return "zeyo_app/main";
				} else {
					return "redirect:https://www.zeyo.co.kr/zeyo_app/not_link";
				}

			}

		} else {
			return "redirect:https://www.zeyo.co.kr/zeyo_app/login";
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/compare")
	public String compare(Model m, HttpSession session,

			@RequestParam(value = "zeyo_pid", required = true) Long zeyo_pid) {
		
		log.debug("compare");
		log.debug("session.getId()");
		log.debug(session.getId());
		

		if (integrateCommonService.haveIpSession(session.getId())) {

			Item i = integrateCommonService.get_zeyo_item(zeyo_pid);
			Map<String, Object> issv_list = integrateCommonService.get_zeyo_size(zeyo_pid);

			try {

				m.addAttribute("item", objectMapper.writeValueAsString(i));
				m.addAttribute("item_object", i);
				m.addAttribute("seller", i.getMember().getMemberId());
				m.addAttribute("sub_category", objectMapper.writeValueAsString(i.getSubCategory()));
				m.addAttribute("sub_category_object", i.getSubCategory()); 
				m.addAttribute("ItemScmmSoValue", objectMapper.writeValueAsString(issv_list));
				m.addAttribute("ItemScmmSoValue_object", issv_list);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "redirect:https://www.zeyo.co.kr/zeyo_app/error";
			}

			return "zeyo_app/compare";
		} else {
			return "redirect:https://www.zeyo.co.kr/zeyo_app/login";
		}

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/compare_v2")
	public String compare_v2(Model m, HttpSession session,

			@RequestParam(value = "zeyo_pid", required = true) Long zeyo_pid) {
		
		log.debug("compare");
		log.debug("session.getId()");
		log.debug(session.getId());
		

		if (integrateCommonService.haveIpSession(session.getId())) {

			Item i = integrateCommonService.get_zeyo_item(zeyo_pid);
			Map<String, Object> issv_list = integrateCommonService.get_zeyo_size(zeyo_pid);

			try {

				m.addAttribute("item", objectMapper.writeValueAsString(i));
				m.addAttribute("item_object", i);
				m.addAttribute("seller", i.getMember().getMemberId());
				m.addAttribute("sub_category", objectMapper.writeValueAsString(i.getSubCategory()));
				m.addAttribute("sub_category_object", i.getSubCategory()); 
				m.addAttribute("ItemScmmSoValue", objectMapper.writeValueAsString(issv_list));
				m.addAttribute("ItemScmmSoValue_object", issv_list);

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return "redirect:https://www.zeyo.co.kr/zeyo_app/error";
			}

			return "zeyo_app/compare_v2";
		} else {
			return "redirect:https://www.zeyo.co.kr/zeyo_app/login";
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/error")
	public String error(Model m) {

		return "zeyo_app/error";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/not_zeyo_product")
	public String not_zeyo_product(Model m) {

		return "zeyo_app/not_zeyo_product";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/not_link")
	public String not_link(Model m) {

		return "zeyo_app/not_link";
	}

}