package com.hildi.propm.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomMapper {

    private final ModelMapper modelMapper;

    public CustomMapper() {
        modelMapper = new ModelMapper();
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public <S, T> List<T> map(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream().map(source -> map(source, targetClass)).toList();
    }

    public <S, T> HashSet<T> map(HashSet<S> sourceSet, Class<T> targetClass) {
        return sourceSet.stream().map(source -> map(source, targetClass)).collect(Collectors.toCollection(HashSet::new));
    }

}
