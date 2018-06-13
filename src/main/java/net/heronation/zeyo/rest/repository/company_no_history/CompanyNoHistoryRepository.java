package net.heronation.zeyo.rest.repository.company_no_history;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "company_no_historys", path = "company_no_historys")

@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface CompanyNoHistoryRepository
		extends JpaRepository<CompanyNoHistory, Long>, QueryDslPredicateExecutor<CompanyNoHistory> {
 
	default void customize(QuerydslBindings bindings, QCompanyNoHistory company_no_history) {

	}

	
	
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RestResource(path = "", rel = "", exported = true)
	Page<CompanyNoHistory> findAll(Pageable arg0);
	
	
	@RestResource( rel = "findByMemberId", path = "findByMemberId")
	List<CompanyNoHistory> findByMemberId(@Param("memberId") long memberId);
	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true)
	@Query("select distinct m  from CompanyNoHistory m   ")
	List<CompanyNoHistory> distinct_name();
	
	
 	@Override 
 	@PreAuthorize("permitAll()")
 	<S extends CompanyNoHistory> S save(S arg0);
}