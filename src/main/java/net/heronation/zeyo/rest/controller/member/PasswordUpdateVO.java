package net.heronation.zeyo.rest.controller.member;

import lombok.Data;

@Data
public class PasswordUpdateVO {
	private String new_pw;
	private String old_pw;
}
