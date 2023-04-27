package com.hildi.propm.util;

import com.hildi.propm.dto.TaskDto;
import com.hildi.propm.model.Task;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);
}
