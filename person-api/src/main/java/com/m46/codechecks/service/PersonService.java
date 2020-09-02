package com.m46.codechecks.service;

import com.m46.codechecks.model.dto.PersonDto;
import com.m46.codechecks.model.entity.PersonEntity;
import com.m46.codechecks.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final ModelMapper modelMapper;

    public void addPerson(PersonDto personDto) {
        personRepository.save(modelMapper.map(personDto, PersonEntity.class));
    }
}
