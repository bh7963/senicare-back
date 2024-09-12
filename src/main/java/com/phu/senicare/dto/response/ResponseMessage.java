package com.phu.senicare.dto.response;

// Response DTO의 message 상수

public interface ResponseMessage {
    
    String SUCCESS = "Success. ";

    // Http Status 400(BAD_REQUEST)
    String VALIDATION_FAILE = "Validation failed";
    String DUPLICATE_USER_ID = "Duplicated user id. ";
    String DUPLICATE_TELNUMBER = "Duplicated user tel number. ";

    String TEL_AUTH_FAILE = "Tel number authentication failed. ";

    // http Status 500(INTERNER_SERVER_ERROR)
    String MESSAGE_SEND_FAILE = "Auth number send failed. ";
    String DATABASE_ERROR = "Database error. ";

}
