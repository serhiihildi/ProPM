package com.hildi.propm.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomMapper {

    private final ModelMapper modelMapper;

    public CustomMapper() {
        modelMapper = new ModelMapper();
    }

    public <S, T> T toDto(S entity, Class<T> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <S, T> S toEntity(T dto, Class<S> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <S, T> List<T> toDtoList(List<S> entityList, Class<T> dtoClass) {
        return entityList.stream().map(entity -> toDto(entity, dtoClass)).collect(Collectors.toList());
    }

    public <S, T> List<S> toEntityList(List<T> dtoList, Class<S> entityClass) {
        return dtoList.stream().map(dto -> toEntity(dto, entityClass)).collect(Collectors.toList());
    }

    public <S, T> Set<S> toEntitySet(Set<T> dtoList, Class<S> entityClass) {
        return dtoList.stream().map(dto -> toEntity(dto, entityClass)).collect(Collectors.toSet());
    }

}
