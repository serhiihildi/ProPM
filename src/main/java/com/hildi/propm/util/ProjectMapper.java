package com.hildi.propm.util;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.model.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);

    List<ProjectDto> toDtoList(List<Project> projectList);

    List<Project> toEntityList(List<ProjectDto> projectDtoList);
}

