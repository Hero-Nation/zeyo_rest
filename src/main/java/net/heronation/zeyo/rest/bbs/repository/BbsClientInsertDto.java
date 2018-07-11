package net.heronation.zeyo.rest.bbs.repository;

import org.joda.time.DateTime;

import lombok.Data;
import net.heronation.zeyo.rest.common.dto.FileDto;

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
