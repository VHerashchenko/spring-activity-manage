package com.vh.activitymanage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vh.activitymanage.model.enums.ActivityStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vh_activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long time;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ActivityStatus status;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String creator;

    @Column(name = "user_count")
    private Integer userCounter;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "vh_user_activity",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @JsonIgnoreProperties("activities")
    private Set<User> users;
}