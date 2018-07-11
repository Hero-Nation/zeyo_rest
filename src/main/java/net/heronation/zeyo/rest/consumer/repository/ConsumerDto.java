package net.heronation.zeyo.rest.consumer.repository;

import org.joda.time.DateTime;

import lombok.Data;

@Data
public class ConsumerDto {

	private Long id;

	private String corpType;

	private String corpId;

	private String oauthId;

	private String oauthCode;

	private String accessToken;

	private String refreshToken;

	private DateTime createDt;

	private DateTime deleteDt;

	private String useYn;

}