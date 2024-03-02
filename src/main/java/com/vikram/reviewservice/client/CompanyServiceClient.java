package com.vikram.reviewservice.client;

import com.vikram.reviewservice.model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${company.service.name}")
public interface CompanyServiceClient {

    @GetMapping("${company.service.basepath}/{companyId}")
    ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Long companyId);
}
