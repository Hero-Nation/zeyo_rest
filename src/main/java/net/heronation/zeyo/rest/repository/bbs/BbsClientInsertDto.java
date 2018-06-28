package net.heronation.zeyo.rest.repository.bbs;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.value.FileDto;

@Data
public class BbsClientInsertDto {
	
	private String title;
	private String bbsContent;
	private FileDto[] attachFile;
	
	public Bbs getNewBbs() {
		Bbs a= new Bbs();
		a.setCreateDt(new DateTime());
		a.setUseYn("Y");
		a.setStatus("A");  
		a.setTitle(title);
		a.setBbsContent(bbsContent);
		return a;
		
	}
}
