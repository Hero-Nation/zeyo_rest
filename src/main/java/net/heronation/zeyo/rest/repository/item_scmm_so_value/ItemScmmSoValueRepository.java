package net.heronation.zeyo.rest.repository.item_scmm_so_value;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.querydsl.core.types.dsl.StringPath;

@RepositoryRestResource(collectionResourceRel = "item_scmm_so_values", path = "item_scmm_so_values")
public interface ItemScmmSoValueRepository
		extends JpaRepository<ItemScmmSoValue, Long>, QueryDslPredicateExecutor<ItemScmmSoValue> {
	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QItemScmmSoValue item_scmm_so_value) {

	}

	// @Override
	// @RestResource(path = "", rel = "", exported = false)
	// Page<CompanyNoHistory> findAll(Pageable arg0);

}