package net.heronation.zeyo.rest.controller.company_no_history;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.bbs.BbsClientValidator;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryResourceAssembler;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.QMember;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.company_no_history.CompanyNoHistoryService;

@Slf4j
@RepositoryRestController
@RequestMapping("/company_no_historys")
public class CompanyNoHistoryController extends BaseController {

	@Autowired
	private CompanyNoHistoryService company_no_historyService;

	@Autowired
	private CompanyNoHistoryRepository repository;
	@Autowired
	private CompanyNoHistoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public CompanyNoHistoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

 

	@Bean
	public CompanyNoHistoryDistinctNameConverter getCompanyNoHistoryDistinctNameConverter() {
		return new CompanyNoHistoryDistinctNameConverter();
	}
 
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,

			@RequestParam(value = "cn1", required = false) String cn1,
			@RequestParam(value = "cn2", required = false) String cn2,
			@RequestParam(value = "cn3", required = false) String cn3,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {
 
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("cn1", cn1);
		param.put("cn2", cn2);
		param.put("cn3", cn3); 
		if(start == null) {
			param.put("start", start);	
		}else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if(end == null) {
			param.put("end", end);	
		}else {
			param.put("end", end.toString(Format.ISO_DATETIME));
		}
		  
		
		return return_success((Object) company_no_historyService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/my_list")
	@ResponseBody
	public ResponseEntity<ResultVO> my_list(@RequestParam(value = "member_id", required = false) String member_id,
			Pageable pageable) {
		
		Long member = Long.valueOf(String.valueOf(member_id));

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;
 
		builder.and(target.member.id.eq(member));
	  
		return return_success((Object) company_no_historyService.mylist(builder.getValue(), pageable));
	}

}