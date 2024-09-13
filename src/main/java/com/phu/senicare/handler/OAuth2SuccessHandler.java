package com.phu.senicare.handler;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.phu.senicare.common.object.CustomOAuth2User;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OAuth2 유저 서비스 작업이 성공 했을 때 처리 
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = customOAuth2User.getAttributes();
        boolean existed = customOAuth2User.isExisted();

        // 회원가입 O
        if(existed) {
            String accessToken = (String) attributes.get("accessToken");
            response.sendRedirect("http://localhost:3000/sns-success?accessToken=" +accessToken+"&expiration=36000");
        // 회원가입 x
        }else{
            String snsId = (String) attributes.get("snsId");
            String joinPath= (String) attributes.get("joinPath");
            response.sendRedirect("http://localhost:3000/auth?snsId="+snsId+"&joinPath=" +joinPath);
        }
    }

}
