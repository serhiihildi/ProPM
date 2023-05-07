package com.hildi.propm.model.dto;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    @NotBlank(message = "Task name is required")
    private String name;

    @NotBlank(message = "Task description is required")
    private String description;

    @NotNull(message = "Task project is required")
    private Long projectId;

    @NotNull(message = "Task role is required")
    private Role role;

    @NotNull(message = "Task status is required")
    private TaskStatus status;

    @NotNull(message = "Task creator is required")
    private Long creatorId;

    private Long assigneeId;

}
