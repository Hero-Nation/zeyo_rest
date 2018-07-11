package net.heronation.zeyo.rest.consumer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.querydsl.core.types.Predicate;

@RepositoryRestResource(collectionResourceRel = "consumers", path = "consumers")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public interface ConsumerRepository extends JpaRepository<Consumer, Long>, QueryDslPredicateExecutor<Consumer> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QConsumer consumer) {

	}
 
	@Query("select m from Consumer m where m.corpType = ?1 and  m.corpId = ?2 and m.useYn = 'Y'")
	@PreAuthorize("permitAll()")
	List<Consumer> findByCorpId(@Param("corp_type") String corp_type,@Param("corp_id") String corp_id);
	
	// @Override
	// @RestResource(path = "", rel = "", exported = false)
	// Page<CompanyNoHistory> findAll(Pageable arg0);

	
	@Override
	@PreAuthorize("permitAll()")
	<S extends Consumer> S save(S arg0);
	
	@Override
	Consumer findOne(Predicate arg0);
	
}