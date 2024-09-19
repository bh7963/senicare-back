package com.phu.senicare.dto.response;

// Response DTO의 code 상수

public interface ResponseCode {
    
    String SUCCESS = "SU";

    // ERROR_CODE 400
    String VALIDATION_FAILE = "VF";
    String DUPLICATE_USER_ID = "DI";
    String DUPLICATE_TELNUMBER = "DT";
    String NO_EXIST_USER_ID = "NI";
    
    // ERROR_CODE 401
    String TEL_AUTH_FAILE = "TAF"; 
    String SIGN_IN_FAIL = "SF";
    String AUTHENTICATION_FAIL = "AF";

    // ERROR_CODE 500
    String MESSAGE_SEND_FAILE = "TF";
    String TOKEN_CREATE_FAIL= "TCF";
    String DATABASE_ERROR = "DBE"; 

}
