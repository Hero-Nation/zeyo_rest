package net.heronation.zeyo.rest.repository.fit_info;
  

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
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

 
@RepositoryRestResource(collectionResourceRel = "fit_infos", path = "fit_infos")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface FitInfoRepository extends JpaRepository<FitInfo, Long> , QueryDslPredicateExecutor<FitInfo>{
 

 	default void customize(QuerydslBindings bindings, QFitInfo fit_info) {

 
	}

 	
//	@Override
//	@RestResource(path = "", rel = "",exported = true)
//	Page<FitInfo> findAll(Pageable arg0);
	
	

	@RestResource(path = "use_list", rel = "use_list", exported = true)
	Page<FitInfo> findByUseYn(@Param("useYn")  String useYn , Pageable arg0);
	
	
	@RestResource(path = "select_options", rel = "select_options",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from FitInfo m where   m.useYn = 'Y'")
	List<FitInfo> select_options();

	
	@RestResource(path = "distinct_name", rel = "distinct_name",exported = true) 
	@Query("select distinct m from FitInfo m where m.useYn = 'Y'")
	List<FitInfo> distinct_name();
	
	
	@RestResource(path = "findByName", rel = "findByName", exported = true)
	@Query("select m from FitInfo m where m.name = ?1 and  m.useYn = 'Y'")
	List<FitInfo> findByName(@Param("name") String name);
	
}