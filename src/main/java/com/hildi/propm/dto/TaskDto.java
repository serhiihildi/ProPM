package com.hildi.propm.dto;

import lombok.*;

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

