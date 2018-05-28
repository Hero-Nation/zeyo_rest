package net.heronation.zeyo.rest.repository.email_validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Value;

@Value
public class EmailValidationDto {

	private String email;

	private String otp;

	private DateTime createDt;

}