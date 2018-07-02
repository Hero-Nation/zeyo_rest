package net.heronation.zeyo.rest.repository.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.Value;

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