package com.spring.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperUtil {
    ModelMapper mapper = new ModelMapper();
    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass){
        return source.stream().map(element -> mapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
