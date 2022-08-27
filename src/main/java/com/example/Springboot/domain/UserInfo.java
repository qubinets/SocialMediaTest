package com.example.Springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userinfoid")
    private Long userInfoId;
    @Column(name="username")
    private String userName;
    @Column(name="userlastname")
    private String userLastName;
    @Column(name="country")
    private String country;
    @Column(name="aboutme")
    private String aboutMe;
    @Column(name="logourl")
    private String logoUrl;
    @Column(name="dateofbirth")
    private LocalDate dateOfBirth;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public UserInfo() {
    }


    public UserInfo(String userName, String userLastName, String country, String aboutMe, String logoUrl, LocalDate dateOfBirth) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.country = country;
        this.aboutMe = aboutMe;
        this.logoUrl = logoUrl;
        this.dateOfBirth = dateOfBirth;

    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {

        this.userId = userId;
    }
}
