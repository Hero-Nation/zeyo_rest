package net.heronation.zeyo.rest.size_option.repository;

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

@RepositoryRestResource(collectionResourceRel = "size_options", path = "size_options")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface SizeOptionRepository extends JpaRepository<SizeOption, Long>, QueryDslPredicateExecutor<SizeOption> {

	default void customize(QuerydslBindings bindings, QSizeOption size_option) {

	}

	@Override
	@RestResource(path = "", rel = "", exported = false)
	Page<SizeOption> findAll(Pageable arg0);

	@RestResource(path = "findByName", rel = "findByName", exported = true)
	@Query("select m from SizeOption m where m.name = ?1 and  m.useYn = 'Y'")
	List<SizeOption> findByName(@Param("name") String name);

//	@Query("select m from SizeOption m where m.name = ?1 and  m.useYn = 'Y' and m.category = 5 and m.subCategory = 7 ")
//	List<SizeOption> findByNameDirect(@Param("name") String name);
	

	@RestResource(path = "select_options_symbol", rel = "select_options_symbol",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from SizeOption m where (m.kindof = 6  or  m.kindof = 8 ) and  m.useYn = 'Y'")
	List<SizeOption> select_options_symbol();
	
	@RestResource(path = "select_options_number", rel = "select_options_number",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from SizeOption m where (  m.kindof = 7  ) and  m.useYn = 'Y'")
	List<SizeOption> select_options_number();
	
	@RestResource(path = "select_options_number_bottom", rel = "select_options_number_bottom",exported = true)
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@Query("select m from SizeOption m where ( m.kindof = 9) and  m.useYn = 'Y'")
	List<SizeOption> select_options_number_bottom();
	
	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	SizeOption findOne(Long arg0);
 	
 	@Override
 	@PreAuthorize("hasRole('ROLE_CLIENT')")
 	<S extends SizeOption> S save(S arg0);
}