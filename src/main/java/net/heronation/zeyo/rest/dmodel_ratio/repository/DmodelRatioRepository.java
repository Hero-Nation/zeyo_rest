package net.heronation.zeyo.rest.dmodel_ratio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "dmodel_ratios", path = "dmodel_ratios")
public interface DmodelRatioRepository
		extends JpaRepository<DmodelRatio, Long>, QueryDslPredicateExecutor<DmodelRatio> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QDmodelRatio dmodel_ratio) {

	}
	
	// 테스트를 위하 임시 권한 허락
		@PreAuthorize("permitAll()")
		<S extends DmodelRatio> S save(S entity);

}