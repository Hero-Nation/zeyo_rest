package net.heronation.zeyo.rest.repository.sub_category_measure_map;
  

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
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;

 
@RepositoryRestResource(collectionResourceRel = "sub_category_measure_maps", path = "sub_category_measure_maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface SubCategoryMeasureMapRepository extends JpaRepository<SubCategoryMeasureMap, Long> , QueryDslPredicateExecutor<SubCategoryMeasureMap>{
 
 	default void customize(QuerydslBindings bindings, QSubCategoryMeasureMap sub_category_measure_map) {

 
	}

	@Override
	@RestResource(path = "", rel = "",exported = false)
	Page<SubCategoryMeasureMap> findAll(Pageable arg0);
	
	
	@RestResource(path = "select_by_sub_cate", rel = "select_by_sub_cate", exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select mm from SubCategoryMeasureMap scmm left join scmm.measureItem mm where  scmm.subCategory.id = ?1 and  mm.useYn = 'Y' and  scmm.useYn = 'Y' ")
	List<MeasureItem> select_by_sub_cate(@Param("scid") Long scid);

}