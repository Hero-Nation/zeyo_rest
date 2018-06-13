package net.heronation.zeyo.rest.repository.sub_category_fit_info_map;
  

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

import net.heronation.zeyo.rest.repository.fit_info.FitInfo;

 
@RepositoryRestResource(collectionResourceRel = "sub_category_fit_info_maps", path = "sub_category_fit_info_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface SubCategoryFitInfoMapRepository extends JpaRepository<SubCategoryFitInfoMap, Long> , QueryDslPredicateExecutor<SubCategoryFitInfoMap>{
 

 	default void customize(QuerydslBindings bindings, QSubCategoryFitInfoMap sub_category_fit_info_map) {

 
	}

 	
	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<SubCategoryFitInfoMap> findAll(Pageable arg0);
	
	@RestResource(path = "select_by_sub_cate", rel = "select_by_sub_cate",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select fi from SubCategoryFitInfoMap scfim left join scfim.fitInfo fi where  scfim.subCategory.id = ?1 and  fi.useYn = 'Y' and  scfim.useYn = 'Y' ")
	List<FitInfo> select_by_sub_cate(@Param("scid") Long scid);
	
	
	// new version
}