package com.vh.activitymanage.model.dto;

import com.vh.activitymanage.model.enums.ActivityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityUserDTO {

    private Long id;

    private String name;

    private Long time;

    private ActivityStatus status;

    private UserDTO userDTO;
    private CategoryDTO categoryDTO;
}