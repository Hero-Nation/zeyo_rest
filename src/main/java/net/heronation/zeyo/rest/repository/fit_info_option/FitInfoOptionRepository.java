package net.heronation.zeyo.rest.repository.fit_info_option;
  

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.dsl.StringPath;

import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.member.Member;

 
@RepositoryRestResource(collectionResourceRel = "fit_info_options", path = "fit_info_options")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface FitInfoOptionRepository extends JpaRepository<FitInfoOption, Long> , QueryDslPredicateExecutor<FitInfoOption>{
 
 	default void customize(QuerydslBindings bindings, QFitInfoOption fit_info_option) {

 
	}
 	
	@Override
	@RestResource(path = "", rel = "",exported = true)
	Page<FitInfoOption> findAll(Pageable arg0);


	@RestResource(path = "select_options", rel = "select_options",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from FitInfoOption m where  m.fitInfo = ?1 and m.useYn = 'Y'")
	List<FitInfoOption> select_options(@Param("fitInfo") FitInfo fitInfo);
	
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	FitInfoOption findOne(Long arg0);
 	
	
}