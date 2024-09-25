package com.phu.senicare.dto.response;

// Response DTO의 message 상수

public interface ResponseMessage {
    
    String SUCCESS = "Success. ";

    // Http Status 400(BAD_REQUEST)
    String VALIDATION_FAILE = "Validation failed. ";
    String DUPLICATE_USER_ID = "Duplicated user id. ";
    String DUPLICATE_TELNUMBER = "Duplicated user tel number. ";
    String NO_EXIST_USER_ID = "No exist user id. ";
    String NO_EXIST_TOOL = "No exist tool. ";
    String NO_EXIST_CUSTOMER = "No exist customer";
    String TOOL_INSUFFICIENT = "This Tool is insufficient in number";

    // Http Status 401(UNAUTHORIZED)
    String TEL_AUTH_FAILE = "Tel number authentication failed. ";
    String SIGN_IN_FAIL = "Sign in failed. ";
    String NO_PERMISSION = "No permission";
    String AUTHENTICATION_FAIL = "authentication fail. ";

    // http Status 500(INTERNER_SERVER_ERROR)
    String MESSAGE_SEND_FAILE = "Auth number send failed. ";
    String TOKEN_CREATE_FAIL = "Token creation failed. ";
    String DATABASE_ERROR = "Database error. ";

}
