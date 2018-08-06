package net.heronation.zeyo.rest.v2_rule.repository;
  

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

 
@RepositoryRestResource(collectionResourceRel = "v2_rules", path = "v2_rules")
public interface V2RuleRepository extends JpaRepository<V2Rule, Long> , QueryDslPredicateExecutor<V2Rule>{
    /****

  @RestResource(path = "names", rel = "names",exported = false)
  List<Person> findByName(String name);

***/

 	default void customize(QuerydslBindings bindings, QV2Rule v2_rule) {

 
	}

}