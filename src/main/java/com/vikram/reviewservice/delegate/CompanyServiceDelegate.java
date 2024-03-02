package com.vikram.reviewservice.delegate;

import com.vikram.reviewservice.client.CompanyServiceClient;
import com.vikram.reviewservice.exception.DownstreamServiceException;
import com.vikram.reviewservice.exception.ResourceNotFoundException;
import com.vikram.reviewservice.model.Company;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceDelegate {
    private final CompanyServiceClient companyServiceClient;
    public Company getCompanyById(Long companyId){
        ResponseEntity<Company> response;
        try{
            response = companyServiceClient.getCompanyById(companyId);
            return response.getBody();
        }catch (FeignException.NotFound feignNotFoundException){
            throw new ResourceNotFoundException("Company","Company ID", companyId);
        }
        catch (FeignException feignException){
            throw new DownstreamServiceException("Company",feignException.status(),feignException.getMessage());
        }
    }

}

