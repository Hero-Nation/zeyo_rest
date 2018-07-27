package net.heronation.zeyo.rest.sub_category.repository;

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

import net.heronation.zeyo.rest.category.repository.Category;

@RepositoryRestResource(collectionResourceRel = "sub_categorys", path = "sub_categorys")
@PreAuthorize("hasRole('ROLE_ADMIN')")

public interface SubCategoryRepository
		extends JpaRepository<SubCategory, Long>, QueryDslPredicateExecutor<SubCategory> {

	default void customize(QuerydslBindings bindings, QSubCategory sub_category) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = true)
	Page<SubCategory> findAll(Pageable arg0);

	@Override
	@PreAuthorize("permitAll()")
	List<SubCategory> findAll();
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RestResource(path = "distinct_name", rel = "distinct_name", exported = true)
	@Query("select distinct m  from SubCategory m where  m.category = ?1 and m.useYn = 'Y'")
	List<SubCategory> distinct_name(@Param(value = "category") Category category);

	@RestResource(path = "findByName", rel = "findByName", exported = true)
	@Query(value="select * from sub_category sc join category c on sc.category_id = c.id  where  c.use_yn = 'Y' and  sc.use_yn = 'Y'  and  sc.name = ?1 ",nativeQuery=true)
	List<SubCategory> findByName(@Param("name") String ktype);

	@Override
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	SubCategory findOne(Long arg0);

	@Override
	//@PreAuthorize("hasRole('ROLE_CLIENT')")
	@PreAuthorize("permitAll()")
	<S extends SubCategory> S save(S arg0);
}