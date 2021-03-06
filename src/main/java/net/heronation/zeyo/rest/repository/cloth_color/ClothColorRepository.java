package net.heronation.zeyo.rest.repository.cloth_color;

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

import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

@RepositoryRestResource(collectionResourceRel = "cloth_colors", path = "cloth_colors")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface ClothColorRepository extends JpaRepository<ClothColor, Long>, QueryDslPredicateExecutor<ClothColor> {

	default void customize(QuerydslBindings bindings, QClothColor cloth_color) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = false)
	Page<ClothColor> findAll(Pageable arg0);

	@RestResource(path = "findByName", rel = "findByName", exported = true)
	@Query("select m from ClothColor m where m.name = ?1 and  m.useYn = 'Y'")
	List<ClothColor> findByName(@Param("name") String name);
	
	
	
	
	@RestResource(path = "select_options", rel = "select_options",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from ClothColor m where (m.kindof = 1 or m.kindof = 2  ) and  m.useYn = 'Y'")
	List<ClothColor> select_options();
	
}