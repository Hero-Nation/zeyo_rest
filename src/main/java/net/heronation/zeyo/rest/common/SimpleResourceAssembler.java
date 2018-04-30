package net.heronation.zeyo.rest.common;



import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.util.Assert;

 
public class SimpleResourceAssembler<T> implements ResourceAssembler<T, Resource<T>>, ResourcesAssembler<T, Resource<T>> {

 
	@Override
	public Resource<T> toResource(T entity) {
		
		Resource<T> resource = new Resource<T>(entity);

		addLinks(resource);

		return resource;
	}

 
	public Resources<Resource<T>> toResources(Iterable<? extends T> entities) {

		Assert.notNull(entities, "Entities must not be null!");
		List<Resource<T>> result = new ArrayList<Resource<T>>();

		for (T entity : entities) {
			result.add(toResource(entity));
		}

		Resources<Resource<T>> resources = new Resources<>(result);

		addLinks(resources);

		return resources;
	}

 
	protected void addLinks(Resource<T> resource) {
		// Default adds no links
	}

 
	protected void addLinks(Resources<Resource<T>> resources) {
		// Default adds no links.
	}
}