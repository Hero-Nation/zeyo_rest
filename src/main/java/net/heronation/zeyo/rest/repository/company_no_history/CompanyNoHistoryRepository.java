package net.heronation.zeyo.rest.repository.company_no_history;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.member.Member;

@RepositoryRestResource(collectionResourceRel = "company_no_historys", path = "company_no_historys")
//@PreAuthorize("hasRole('ROLE_CLIENT')")

public interface CompanyNoHistoryRepository
		extends JpaRepository<CompanyNoHistory, Long>, QueryDslPredicateExecutor<CompanyNoHistory> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QCompanyNoHistory company_no_history) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = true)
	Page<CompanyNoHistory> findAll(Pageable arg0);
	
	
	@RestResource( rel = "findByMemberId", path = "findByMemberId")
	CompanyNoHistory findByMemberId(@Param("memberId") long memberId);

}