package com.dacdoliveira.smartspot.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dacdoliveira.smartspot.enums.ProfileEnum;
import com.dacdoliveira.smartspot.enums.TypeEnum;
import com.dacdoliveira.smartspot.model.Company;
import com.dacdoliveira.smartspot.model.Employee;
import com.dacdoliveira.smartspot.model.Launch;
import com.dacdoliveira.smartspot.utils.CryptUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LaunchRepositoryTest
{
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private LaunchRepository launchRepository;

    private Long employeeId;
    @Before
    public void setUp()
    {
        Company company = getCompany();
        companyRepository.save(company);
        Employee employee = getEmployee(company);
        employee= employeeRepository.save(employee);
        this.employeeId = employee.getId();
        Launch launch = getLaunch(employee);
        launchRepository.save(launch);
        launch = getLaunch(employee);
        launchRepository.save(launch);
    }
    
    @After
    public void tearDown()
    {
        companyRepository.deleteAll();
    }

    @Test
    public void findLaunchByEmployee()
    {
        List<Launch> launches = launchRepository.findByEmployeeId(employeeId);
        assertEquals(launches.size(), 2);
    }
    
    @Test
    public void findLaunchByEmployeeWithPagination()
    {
        PageRequest page = new PageRequest(0, 10);
        Page<Launch> launches = launchRepository.findByEmployeeId(employeeId, page);
        assertEquals(launches.getTotalElements(), 2);
    }
    
    private Launch getLaunch(Employee employee)
    {
        Launch launch = new Launch();
        launch.setDate(new Date());
        launch.setEmployee(employee);
        launch.setDescription("test");
        launch.setLocalization("Recife-PE");
        launch.setType(TypeEnum.START);
        return launch;
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
        employee.setCpf("70487600010");
        employee.setEmail("email@email.email");
        employee.setName("Joao Silva");
        employee.setPassword(CryptUtils.generateBCrypt("123456"));
        employee.setProfile(ProfileEnum.ROLE_USER);
        return employee;
    }


}
