package net.heronation.zeyo.rest.common.value;
 

import java.io.Serializable;

public class BaseVO implements Serializable {
	 
    private static final long serialVersionUID = 1L;

 
    protected String code;
 
    protected String message;
 
    protected Object data;
    
 
 
    public BaseVO() {
    }

    public BaseVO(boolean isSuccess) {
 
    }
 
}