package net.heronation.zeyo.rest.controller.material;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.FileDto;
import net.heronation.zeyo.rest.repository.material.Material;

@Data
public class MateriaApiDto { 
	private Long id;
	private FileDto[] files; 
	private String metaDesc; 
	private String name;
	
	
	public Material convertToEntity() {
		
		Material a = new Material();
		a.setCreateDt(new DateTime());
		if(id != 0) {
			a.setId(id);
		}
		if(files != null && files.length >0) {
			a.setImage(files[0].getReal_name());
		}
		a.setMetaDesc(metaDesc);
		a.setName(name);
		a.setUseYn("Y");
		
		
		return a;
	}
}
