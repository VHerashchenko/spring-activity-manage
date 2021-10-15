package com.vh.activitymanage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vh.activitymanage.model.enums.Role;
import com.vh.activitymanage.model.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vh_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users", targetEntity = Activity.class)
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("users")
    private Set<Activity> activities;
}