package com.dacdoliveira.smartspot.repositories;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dacdoliveira.smartspot.model.Company;
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest
{
    @Autowired
    private CompanyRepository companyRepository;
    
    private static final String CNPJ = "73031540000106";
    
    @Before
    public void setUp()
    {
        Company company = new Company();
        company.setCnpj(CNPJ);
        company.setCorporateName("X2ID");
        companyRepository.save(company);
    }
    
    @After
    public void tearDown()
    {
        companyRepository.deleteAll();
    }
    
    @Test
    public void testFindByCnpj()
    {
        Company recorded = companyRepository.findByCnpj(CNPJ);
        assertEquals(CNPJ, recorded.getCnpj());
    }
    
}
