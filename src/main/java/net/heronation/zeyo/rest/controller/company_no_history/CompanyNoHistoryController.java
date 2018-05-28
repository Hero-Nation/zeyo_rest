package net.heronation.zeyo.rest.controller.company_no_history;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.bbs.BbsClientValidator;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryResourceAssembler;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.QMember;

import java.util.Date;

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

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}

		if (cn1 != null) {
			builder.and(target.companyNo.startsWith(cn1));
		}

		if (cn2 != null) {
			builder.and(target.companyNo.containsIgnoreCase("," + cn2 + ","));
		}

		if (cn3 != null) {
			builder.and(target.companyNo.endsWith(cn3));
		}

		if (start != null) {
			builder.and(target.changeDt.after(start));
		}

		if (end != null) {
			builder.and(target.changeDt.before(end));
		}

		return return_success((Object) company_no_historyService.search(builder.getValue(), pageable));
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