package net.heronation.zeyo.rest.common;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;

public interface ResourcesAssembler<T, D extends ResourceSupport> {
 
	Resources<D> toResources(Iterable<? extends T> entities);

}