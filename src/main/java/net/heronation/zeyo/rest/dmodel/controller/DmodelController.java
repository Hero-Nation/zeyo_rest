package net.heronation.zeyo.rest.dmodel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.dmodel.repository.DmodelRepository;
import net.heronation.zeyo.rest.dmodel.repository.DmodelResourceAssembler;
import net.heronation.zeyo.rest.dmodel.service.DmodelService;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;
import net.heronation.zeyo.rest.sub_category.service.V2CategoryService;

@Slf4j
@RestController
@RequestMapping("/api/dmodels")
@Validated
public class DmodelController extends BaseController {

	@Autowired
	private DmodelService dmodelService;

	@Autowired
	private V2CategoryService v2Service;
	
	@Autowired
	private DmodelRepository repository;

	@Autowired
	private DmodelMeasureMapRepository dmmrepo;

	@Autowired
	private MeasureItemRepository mirepo;

	@Autowired
	private DmodelRatioRepository drrepo;

	@Autowired
	private DmodelResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@InitBinder
	private void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new DmodelDtoValidator());
	}

	@Autowired
	public DmodelController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> list(@RequestParam(value = "name", required = false) String name,
			Pageable pageable) {
		log.debug("GET /api/dmodels/");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);

		return return_success((Object) dmodelService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public ResponseEntity<ResultDto> single(@PathVariable(value = "id", required = true) long id) {
		log.debug("GET /api/dmodels/{id}");
	 
		return return_success((Object) dmodelService.single(id));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultDto> delete( 
			@RequestBody List<String> param, @AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("PATCH /api/dmodels");
		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (param == null || param.size() == 0) {
			return return_fail("target.empty");
		} else {
			return return_success(dmodelService.delete(param, seq));
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> insert(@Valid @RequestBody DmodelDto insertDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("POST /api/dmodels/insert");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		log.debug(bindingResult.getErrorCount() + "");
		log.debug(bindingResult.hasErrors() + "");

		if (bindingResult.hasErrors()) {
			log.debug("/api/dmodels/insert bindingResult.hasErrors()");
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(insertDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
					.getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success(dmodelService.insert(insertDto));
		}

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> update(@Valid @RequestBody DmodelDto updateDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("POST /api/dmodels/upate");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		log.debug(bindingResult.getErrorCount() + "");
		log.debug(bindingResult.hasErrors() + "");

		if (bindingResult.hasErrors()) {
			log.debug("/api/dmodels/upate bindingResult.hasErrors()");
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(updateDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
					.getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success(dmodelService.update(updateDto));
		}

	}
	

	@RequestMapping(path = "/test")
	public ResponseEntity<ResultDto> test(
			@Valid
			@NotEmpty(message="email.empty")
			@NotNull(message="email.null")
			@Email(message="email.wrong.form")
            @RequestParam("email")
            String email ) {
		log.debug("/test");
		
		return return_success();

		// 초기 개발 데이터 입력 로직

		/*
		 * for(int a = 1; a < 10000;a++) { // Dmodel dm = new Dmodel(); //
		 * dm.setController("controller "+a); // dm.setCreateDt(new DateTime()); //
		 * dm.setSvgdata("svgdata "+a); // dm.setTitle("dynamic mode title "+a); //
		 * dm.setUpdateDt(new DateTime()); // dm.setUseYn("Y"); // repository.save(dm);
		 * 
		 * Dmodel this_model = repository.findOne(Long.parseLong(String.valueOf(a)));
		 * 
		 * int rc = RandomUtils.nextInt(1, 5);
		 * 
		 * 
		 * for(int b = 0;b < rc;b++) {
		 * 
		 * int ri = RandomUtils.nextInt(1, 9); MeasureItem this_mi =
		 * mirepo.findOne(Long.parseLong(String.valueOf(ri)));
		 * 
		 * int rmin = RandomUtils.nextInt(50,100); int rmax =
		 * RandomUtils.nextInt(100,200);
		 * 
		 * DmodelMeasureMap dmm = new DmodelMeasureMap(); dmm.setDmodel(this_model);
		 * dmm.setMeasureItem(this_mi); dmm.setMaxValue(rmin+"");
		 * dmm.setMinValue(rmax+""); dmm.setUseYn("Y");
		 * 
		 * dmmrepo.save(dmm); }
		 * 
		 * 
		 * int wmin = RandomUtils.nextInt(10,500); int wmax =
		 * RandomUtils.nextInt(500,1000); float rratio = RandomUtils.nextFloat();
		 * 
		 * DmodelRatio this_ratio = new DmodelRatio(); this_ratio.setDmodel(this_model);
		 * this_ratio.setMaxValue(wmin+""); this_ratio.setMinValue(wmax+"");
		 * this_ratio.setRatioValue(rratio+""); this_ratio.setUseYn("Y");
		 * this_ratio.setDefaultYn("N");
		 * 
		 * drrepo.save(this_ratio);
		 * 
		 * }
		 */
		
	}

}