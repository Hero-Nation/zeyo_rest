package net.heronation.zeyo.rest.dmodel_measure_map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "dmodel_measure_maps", path = "dmodel_measure_maps")
public interface DmodelMeasureMapRepository
		extends JpaRepository<DmodelMeasureMap, Long>, QueryDslPredicateExecutor<DmodelMeasureMap> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QDmodelMeasureMap dmodel_measure_map) {

	}
	
	@Override
	// 테스트를 위하 임시 권한 허락
	@PreAuthorize("permitAll()")
	<S extends DmodelMeasureMap> S save(S entity);

}