package net.heronation.zeyo.rest.email_validation.repository;

import org.joda.time.DateTime;

import lombok.Value;

@Value
public class EmailValidationDto {

	private String email;

	private String otp;

	private DateTime createDt;

}