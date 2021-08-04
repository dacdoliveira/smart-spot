package com.dacdoliveira.smartspot.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dacdoliveira.smartspot.enums.ProfileEnum;
import com.dacdoliveira.smartspot.model.Company;
import com.dacdoliveira.smartspot.model.Employee;
import com.dacdoliveira.smartspot.utils.CryptUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest
{
    private static final String EMAIL = "email@email.email";

    private static final String CPF = "70487600010";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setUp()
    {
        Company company = getCompany();
        companyRepository.save(company);
        Employee employee = getEmployee(company);
        employeeRepository.save(employee);

    }

    @After
    public void tearDown()
    {
        companyRepository.deleteAll();
    }

    @Test
    public void testFindByMail()
    {
        Employee employee = employeeRepository.findByEmail(EMAIL);
        assertEquals(employee.getEmail(), EMAIL);
    }
    
    @Test
    public void testFindByCpf()
    {
        Employee employee = employeeRepository.findByCpf(CPF);
        assertEquals(employee.getCpf(), CPF);
    }
    
    @Test
    public void testFindByCpfOrEmail()
    {
        Employee employee = employeeRepository.findByCpfOrEmail(CPF, EMAIL);
        assertNotNull(employee);
    }

    @Test
    public void testFindByCpfOrEmailInvalid()
    {
        Employee employee = employeeRepository.findByCpfOrEmail(CPF, "invalidmail@mail.com");
        assertNotNull(employee);
    }
    
    @Test
    public void testFindByCpfInvalidOrEmail()
    {
        Employee employee = employeeRepository.findByCpfOrEmail("12345678900", EMAIL);
        assertNotNull(employee);
    }
    
    @Test
    public void testFindByCpfInvalidOrEmailInvalid()
    {
        Employee employee = employeeRepository.findByCpfOrEmail("12345678900",  "invalidmail@mail.com");
        assertNull(employee);
    }


    private Company getCompany()
    {
        Company company = new Company();
        company.setCnpj("73031540000106");
        company.setCorporateName("X2ID");
        return company;
    }

    private Employee getEmployee(Company company)
    {
        Employee employee = new Employee();
        employee.setCompany(company);
        employee.setCpf(CPF);
        employee.setEmail(EMAIL);
        employee.setName("Joao Silva");
        employee.setPassword(CryptUtils.generateBCrypt("123456"));
        employee.setProfile(ProfileEnum.ROLE_USER);
        return employee;
    }

}
