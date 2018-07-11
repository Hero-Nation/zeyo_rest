package net.heronation.zeyo.rest.email_validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "email_validations", path = "email_validations")
public interface EmailValidationRepository
		extends JpaRepository<EmailValidation, Long>, QueryDslPredicateExecutor<EmailValidation> {

	default void customize(QuerydslBindings bindings, QEmailValidation email_validation) {

	}

}