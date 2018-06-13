package net.heronation.zeyo.rest.repository.email_validation;

import org.joda.time.DateTime;

import lombok.Value;

@Value
public class EmailValidationDto {

	private String email;

	private String otp;

	private DateTime createDt;

}