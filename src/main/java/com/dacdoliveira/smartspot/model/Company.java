package com.dacdoliveira.smartspot.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String corporateName;
    private String cnpj;
    private Date creationDate;
    private Date updateDate;
    private List<Employee> employees;

    public Company()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "corporate_name_", nullable = false)
    public String getCorporateName()
    {
        return corporateName;
    }

    public void setCorporateName(String corporateName)
    {
        this.corporateName = corporateName;
    }

    @Column(name = "cnpj_", nullable = false)
    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    @Column(name = "creation_date_", nullable = false)
    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    @Column(name = "update_date_", nullable = true)
    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(List<Employee> employees)
    {
        this.employees = employees;
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updateDate = new Date();
    }

    @PrePersist
    public void prePersist()
    {
        Date currtentDate = new Date();
        this.creationDate = currtentDate;
    }

    @Override
    public String toString()
    {
        String toStringFormat = "Company [id=%s, corporateName=%s, cnpj=%s, creationDate=%s, updateDate=%s]";
        return String.format(toStringFormat, this.id, this.corporateName, this.cnpj, this.creationDate,
                this.updateDate);
    }
}
