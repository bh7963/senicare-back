package com.phu.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phu.senicare.common.util.AuthNumberCreater;
import com.phu.senicare.dto.request.auth.IdCheckRequestDto;
import com.phu.senicare.dto.request.auth.SignInRequestDto;
import com.phu.senicare.dto.request.auth.SignUpRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthCheckRequestDto;
import com.phu.senicare.dto.request.auth.TelAuthRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.auth.SignInResponseDto;
import com.phu.senicare.entity.NurseEntity;
import com.phu.senicare.entity.TelAuthNumberEntity;
import com.phu.senicare.provider.JwtProvider;
import com.phu.senicare.provider.SmsProvider;
import com.phu.senicare.repository.NurseRepository;
import com.phu.senicare.repository.TelAuthNumberRepository;
import com.phu.senicare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final SmsProvider smsProvider;
    private final JwtProvider jwtProvider;

    private final NurseRepository nurseRepository;
    private final TelAuthNumberRepository telAuthNumberRepository; 

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    

    @Override
    public ResponseEntity<ResponseDto> idCheck(IdCheckRequestDto dto) {

        String userId = dto.getUserId();
        
        try {
            
            boolean isExistedId = nurseRepository.existsByUserId(userId);
            if(isExistedId) return ResponseDto.duplicatedUserId();

            

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuth(TelAuthRequestDto dto) {

        String telNumber = dto.getTelNumber();
        // String authNumber = dto.getAuthNumber():

        try {
            
            boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
            if(isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        String authNumber = AuthNumberCreater.number4();

        boolean isSendSuccess = smsProvider.sendMessage(telNumber, authNumber);
        if(!isSendSuccess) return ResponseDto.messageSendFail();

        try {
            TelAuthNumberEntity telAuthNumberEntity = new TelAuthNumberEntity(telNumber, authNumber);
            telAuthNumberRepository.save(telAuthNumberEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> telAuthCheck(TelAuthCheckRequestDto dto) {
        
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();

        try {
            
            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched) return ResponseDto.telAuthFail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String userId = dto.getUserId();
        String telNumber = dto.getTelNumber();
        String authNumber = dto.getAuthNumber();
        String password = dto.getPassword();

        try {
            
            boolean isExistedId = nurseRepository.existsByUserId(userId);
            if(isExistedId) return ResponseDto.duplicatedUserId();

            boolean isExistedTelNumber = nurseRepository.existsByTelNumber(telNumber);
            if(isExistedTelNumber) return ResponseDto.duplicatedTelNumber();

            boolean isMatched = telAuthNumberRepository.existsByTelNumberAndAuthNumber(telNumber, authNumber);
            if(!isMatched) return ResponseDto.telAuthFail();

            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            NurseEntity nurseEntity = new NurseEntity(dto);
            nurseRepository.save(nurseEntity);
            



        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        String userId = dto.getUserId();
        String password = dto.getPassword();

        String accessToken = null;
        
        try {
            
            NurseEntity nurseEntity = nurseRepository.findByUserId(userId);
            if(nurseEntity == null) return ResponseDto.signInFail(); 

            String encodedPassword = nurseEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return ResponseDto.signInFail(); 

            // 아이디도 틀리고 비번도 틀리면 signInFail을 return

            accessToken = jwtProvider.create(userId);
            if (accessToken == null) return ResponseDto.tokenCreateFail();


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(accessToken);
    }
    

}
