package net.heronation.zeyo.rest.common.value;
 

import java.io.Serializable;

public class BaseDto implements Serializable {
	 
    private static final long serialVersionUID = 1L;

 
    protected String code;
 
    protected String message;
 
    protected Object data;
    
 
 
    public BaseDto() {
    }

    public BaseDto(boolean isSuccess) {
 
    }
 
}