package com.spomprt.user.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spomprt.user.aggregate.Event;
import com.spomprt.user.aggregate.EventTypes;
import com.spomprt.user.aggregate.User;
import com.spomprt.user.controller.dto.UserCreateDto;
import com.spomprt.user.controller.dto.UserDto;
import com.spomprt.user.controller.dto.UserUpdateDto;
import com.spomprt.user.repository.EventRepository;
import com.spomprt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> objectMapper.convertValue(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto get(String id) {
        return objectMapper.convertValue(
                userRepository.findById(id).orElseThrow(EntityNotFoundException::new),
                UserDto.class
        );
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public void update(String id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        try {
            user = objectMapper.updateValue(user, userUpdateDto);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e.getMessage());
        }

        userRepository.save(user);
    }

    @Transactional
    public void create(UserCreateDto userCreateDto) {

        if (userRepository.existsById(userCreateDto.getUsername())) {
            throw new RuntimeException("User already exist");
        }

        User user = objectMapper.convertValue(userCreateDto, User.class);

        userRepository.save(user);

        JsonNode userCreatePayload = objectMapper.valueToTree(userCreateDto);

        Event command = Event.builder()
                .aggregateid(userCreateDto.getUsername())
                .aggregatetype(User.class.getSimpleName())
                .type(EventTypes.USER_CREATED_EVENT)
                .payload(userCreatePayload)
                .build();

        eventRepository.save(command);
    }
}
