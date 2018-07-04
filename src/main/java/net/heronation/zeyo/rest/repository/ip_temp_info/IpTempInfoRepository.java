package net.heronation.zeyo.rest.repository.ip_temp_info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ip_temp_infos", path = "ip_temp_infos")
public interface IpTempInfoRepository extends JpaRepository<IpTempInfo,Long>,QueryDslPredicateExecutor<IpTempInfo>{

	/****
	 * 
	 * @RestResource(path = "names", rel = "names",exported = false) List<Person>
	 *                    findByName(String name);
	 * 
	 ***/

	default void customize(QuerydslBindings bindings, QIpTempInfo ip_temp_info) {

	}

	// @Override
	// @RestResource(path = "", rel = "", exported = false)
	// Page<CompanyNoHistory> findAll(Pageable arg0);

}