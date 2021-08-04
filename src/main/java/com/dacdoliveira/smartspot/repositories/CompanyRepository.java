package com.dacdoliveira.smartspot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dacdoliveira.smartspot.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>
{
    @Transactional(readOnly=true)
    Company findByCnpj(String cnpj);
}
