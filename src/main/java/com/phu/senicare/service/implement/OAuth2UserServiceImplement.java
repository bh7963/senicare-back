package com.phu.senicare.service.implement;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.HashMap;

import com.phu.senicare.common.object.CustomOAuth2User;
import com.phu.senicare.entity.NurseEntity;
import com.phu.senicare.provider.JwtProvider;
import com.phu.senicare.repository.NurseRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService{

    private final JwtProvider jwtProvider;

    private final NurseRepository nurseRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        String registration = request.getClientRegistration().getClientName().toLowerCase();    
    
        String snsId = getSnsId(oAuth2User, registration);
        
        NurseEntity nurseEntity = nurseRepository.findBysnsIdAndJoinPath(snsId, registration);

        CustomOAuth2User customOAuth2User = null;

        if(nurseEntity == null) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("snsId", snsId);
            attributes.put("joinpath", registration);
            customOAuth2User = new CustomOAuth2User(snsId, attributes, false);
        }else {
            String userId = nurseEntity.getUserId();
            String token = jwtProvider.create(userId);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("accessToken", token);

            customOAuth2User = new CustomOAuth2User(userId, attributes, true);
        }

        return customOAuth2User;

    }

    private String getSnsId(OAuth2User oAuth2User, String registration) {
        String snsId = null;
        if(registration.equals("kakao")){
            snsId = oAuth2User.getName();
        }
        if(registration.equals("naver")){
            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
            snsId = response.get("id");
        }
        return snsId;
    }

}
