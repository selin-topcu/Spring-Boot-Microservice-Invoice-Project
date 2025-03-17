package com.user.service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.service.dto.UserDto;
import com.user.service.entities.User;
import com.user.service.exception.UserException;
import com.user.service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InvoiceService invoiceService;

    private final ObjectMapper objectMapper;

    public List<UserDto> getUsers(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new UserException("Bad request", List.of("userIds are missing in request."), HttpStatus.BAD_REQUEST);
        }

        log.info("Calling database with ids : {} ", ids);
        List<User> users = userRepository.findAllById(ids);

        List<UserDto> userDtoList = objectMapper.convertValue(users, new TypeReference<>() {});
        userDtoList.forEach(user -> user.setInvoiceDTOList(invoiceService.callInvoiceService(user.getUserId())));

        log.info("Sending response back.");
        return userDtoList;
    }

}
