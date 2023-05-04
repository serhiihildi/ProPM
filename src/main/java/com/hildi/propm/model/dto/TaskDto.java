package com.hildi.propm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    @NotBlank
    private String name;

    private String description;

    private Long projectId;

    private Long roleId;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDto taskDto)) return false;
        return Objects.equals(id, taskDto.id) && Objects.equals(name, taskDto.name) && Objects.equals(description, taskDto.description) && Objects.equals(projectId, taskDto.projectId) && Objects.equals(roleId, taskDto.roleId) && Objects.equals(createdDate, taskDto.createdDate) && Objects.equals(updatedDate, taskDto.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, projectId, roleId, createdDate, updatedDate);
    }
}

