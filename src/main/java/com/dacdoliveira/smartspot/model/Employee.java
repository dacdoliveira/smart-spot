package com.dacdoliveira.smartspot.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dacdoliveira.smartspot.enums.ProfileEnum;

@Entity
@Table(name = "employee")
public class Employee implements Serializable
{

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private BigDecimal hourValue;
    private Float hoursPerDay;
    private Float hoursPerLunch;
    private ProfileEnum profile;
    private Date creationDate;
    private Date updateDate;
    private Company company;
    private List<Launch> launches;

    public Employee()
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

    @Column(name = "name_", nullable = false)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "email_", nullable = false)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(name = "password_", nullable = false)
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name = "cpf_", nullable = false)
    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    @Column(name = "hour_value_", nullable = true)
    public BigDecimal getHourValue()
    {
        return hourValue;
    }

    public void setHourValue(BigDecimal hourValue)
    {
        this.hourValue = hourValue;
    }

    @Transient
    public Optional<BigDecimal> getHourValueOpt()
    {
        return Optional.ofNullable(hourValue);
    }

    @Column(name = "hours_per_day_", nullable = true)
    public Float getHoursPerDay()
    {
        return hoursPerDay;
    }

    public void setHoursPerDay(Float hoursPerDay)
    {
        this.hoursPerDay = hoursPerDay;
    }

    @Transient
    public Optional<Float> getHoursPerDayOpt()
    {
        return Optional.ofNullable(hoursPerDay);
    }

    @Column(name = "hours_per_lunch_", nullable = true)
    public Float getHoursPerLunch()
    {
        return hoursPerLunch;
    }

    public void setHoursPerLunch(Float hoursPerLunch)
    {
        this.hoursPerLunch = hoursPerLunch;
    }

    @Transient
    public Optional<Float> getHoursPerLunchOpt()
    {
        return Optional.ofNullable(hoursPerLunch);
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_", nullable = false)
    public ProfileEnum getProfile()
    {
        return profile;
    }

    public void setProfile(ProfileEnum profile)
    {
        this.profile = profile;
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

    @ManyToOne(fetch = FetchType.EAGER)
    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Launch> getLaunches()
    {
        return launches;
    }

    public void setLaunches(List<Launch> launches)
    {
        this.launches = launches;
    }

    @PreUpdate
    public void preUpdate()
    {
        this.updateDate = new Date();
    }

    @PrePersist
    public void prePersist()
    {
        this.creationDate =  new Date();
    }

    @Override
    public String toString()
    {
        String toStringFormat = "Employee [id=%s, name=%s, email=%s, password=%s, cpf=%s, hourValue=%s, hourPerDay=%s, hoursPerLunch=%s, profile=%s, creationDate=%s, updateDate=%s, company=%s]";
        return String.format(toStringFormat, this.id, this.name, this.email, this.password, this.cpf, this.hourValue,
                this.hoursPerDay, this.hoursPerLunch, this.profile, this.creationDate, this.updateDate, this.company);
    }
}
