package com.vh.activitymanage.model.dto;

import com.vh.activitymanage.model.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUserDTO {

    private Long id;

    private String name;

    private Long time;

    private String creator;

    private Integer userCounter;

    private ActivityStatus status;

    Integer[] usersId;

    private Set<UserDTO> users;
    private CategoryDTO category;

    public ActivityUserDTO(Set<UserDTO> users, CategoryDTO category){
        this.users = users;
        this.category = category;
    }
}