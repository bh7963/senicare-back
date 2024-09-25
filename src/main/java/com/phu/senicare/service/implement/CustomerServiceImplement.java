package com.phu.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import com.phu.senicare.common.object.CareRecord;
import com.phu.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.phu.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.phu.senicare.dto.request.customer.PostCustomerRequestDto;
import com.phu.senicare.dto.response.ResponseDto;
import com.phu.senicare.dto.response.customer.GetCareRecordListResponseDto;
import com.phu.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.phu.senicare.dto.response.customer.GetCustomerResponseDto;
import com.phu.senicare.entity.CareRecordEntity;
import com.phu.senicare.entity.CustomerEntity;
import com.phu.senicare.entity.ToolEntity;
import com.phu.senicare.repository.CareRecordRepository;
import com.phu.senicare.repository.CustomerRepository;
import com.phu.senicare.repository.NurseRepository;
import com.phu.senicare.repository.ToolRepository;
import com.phu.senicare.repository.resultSet.GetCustomerResultSet;
import com.phu.senicare.repository.resultSet.GetCustomersResultSet;
import com.phu.senicare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {

    private final ToolRepository toolRepository;
    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;
    private final CareRecordRepository careRecordRepository;

    @Override
    public ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto) {
        
        try {
            
            String charger = dto.getCharger();
            boolean isExistedNurse = nurseRepository.existsByUserId(charger);
            if(!isExistedNurse) return ResponseDto.noExistUserId();

            CustomerEntity customerEntity = new CustomerEntity(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCustomerListResponseDto> getCustomerList() {
        
        List<GetCustomersResultSet> resultSets = new ArrayList<>();

        try {
            resultSets = customerRepository.getCustomers();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCustomerListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber) {
        
        GetCustomerResultSet resultSets;

        try {
            
            resultSets = customerRepository.getCustomer(customerNumber);
            if(resultSets == null) return ResponseDto.noEixstCustomer();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCustomerResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(PatchCustomerRequestDto dto, Integer customerNumber, String userId) {
        
        try {
            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if (customerEntity == null) return ResponseDto.noEixstCustomer();
            
            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            customerEntity.patch(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId) {
        
        try {
            
            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if(customerEntity == null) return ResponseDto.noEixstCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if(!isCharger) return ResponseDto.noPermission();

            careRecordRepository.deleteByCustomerNumber(customerNumber);
            customerRepository.delete(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> postCareRecord(PostCareRecordRequestDto dto, Integer customNumber, String userId) {
        
        try {
            ToolEntity toolEntity = null;
            String usedToolName = null;

            Integer usedToolNumber = dto.getUsedToolNumber();
            Integer usedCount = dto.getCount();
            if(usedToolNumber != null) {
                toolEntity = toolRepository.findByToolNumber(usedToolNumber);
                if(toolEntity == null) return ResponseDto.noEixstTool();

                Integer count = toolEntity.getCount();
                
                if(usedCount > count) return ResponseDto.toolInsufficient(); 
            
                usedToolName = toolEntity.getName();
            }
            
            CareRecordEntity careRecordEntity = new CareRecordEntity(dto, usedToolName, userId, customNumber);
            careRecordRepository.save(careRecordEntity);

            if(usedToolNumber != null) {
                toolEntity.decreaseCount(usedCount);
                toolRepository.save(toolEntity);
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCareRecordListResponseDto> getCareRecord(Integer customerNumber) {
        
        List<CareRecordEntity> careRecordEntities = new ArrayList<>();

        try {

            careRecordEntities = careRecordRepository.findByCustomerNumberOrderByRecordNumberDesc(customerNumber);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCareRecordListResponseDto.success(careRecordEntities);
    }
    
}
