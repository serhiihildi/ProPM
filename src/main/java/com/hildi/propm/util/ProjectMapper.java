package com.hildi.propm.util;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.model.Project;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toEntity(ProjectDto projectDto);

    List<ProjectDto> toDtoList(List<Project> projectList);

    List<Project> toEntityList(List<ProjectDto> projectDtoList);

    void updateProjectFromDto(ProjectDto projectDto, Project project);
}

