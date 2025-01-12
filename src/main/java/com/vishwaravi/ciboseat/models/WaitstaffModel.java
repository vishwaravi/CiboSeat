package com.vishwaravi.ciboseat.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "waitstaff_table")
@Component
public class WaitstaffModel implements UserDetails{

    @Id
    @Column(name = "ID", nullable = false)
    private Long Id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "password",nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("WAITSTAFF"));
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
