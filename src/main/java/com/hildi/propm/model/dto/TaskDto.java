package com.hildi.propm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
}

