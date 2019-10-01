package com.plash.configurator.repository;

import com.plash.configurator.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CompanyRepository extends JpaRepository<Company,Long> {

    public Company findByCompanyName(String companyName);

    public  Company findByCompanyId(Long companyId);
}
