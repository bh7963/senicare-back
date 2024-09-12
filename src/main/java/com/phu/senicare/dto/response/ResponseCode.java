package com.phu.senicare.dto.response;

// Response DTO의 code 상수

public interface ResponseCode {
    
    String SUCCESS = "SU";

    // ERROR CODE 400
    String VALIDATION_FAILE = "VF";
    String DUPLICATE_USER_ID = "DI";
    String DUPLICATE_TELNUMBER = "DT";
    
    // ERROR CODE 401
    String TEL_AUTH_FAILE = "TAF"; 

    // ERROR CODE 500
    String MESSAGE_SEND_FAILE = "TF";
    String DATABASE_ERROR = "DBE"; 

}
