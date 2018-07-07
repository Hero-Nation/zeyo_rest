package net.heronation.zeyo.rest.controller.bbs;

import lombok.Data;

@Data
public class UpdateStatusDto {
	private long id;
	private String reply_content;
	private String status;
}
