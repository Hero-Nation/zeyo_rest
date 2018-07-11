package net.heronation.zeyo.rest.bbs.controller;

import lombok.Data;

@Data
public class UpdateStatusDto {
	private long id;
	private String reply_content;
	private String status;
}
