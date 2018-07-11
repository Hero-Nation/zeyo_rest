package net.heronation.zeyo.rest.item_scmm_so_value.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

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