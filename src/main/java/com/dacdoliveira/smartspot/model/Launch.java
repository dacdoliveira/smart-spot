package com.dacdoliveira.smartspot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dacdoliveira.smartspot.enums.TypeEnum;

@Entity
@Table(name = "launch")
public class Launch implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date date;
    private String description;
    private String localization;
    private Date creationDate;
    private Date updateDate;
    private TypeEnum type;
    private Employee employee;

    public Launch()
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_", nullable = false)
    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Column(name = "description_", nullable = true)
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "localization_", nullable = true)
    public String getLocalization()
    {
        return localization;
    }

    public void setLocalization(String localization)
    {
        this.localization = localization;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type_", nullable = false)
    public TypeEnum getType()
    {
        return type;
    }

    public void setType(TypeEnum type)
    {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
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
        String toStringFormat = "Launch [id=%s,date=%s,description=%s,localization=%s,creationDate=%s,updateDate=%s,type=%s,employee=%s]";
        return String.format(toStringFormat, this.id, this.date, this.description, this.localization, this.creationDate,
                this.updateDate, this.type, this.employee);
    }

}
