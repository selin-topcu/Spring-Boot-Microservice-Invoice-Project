package com.user.service.controller;

import com.user.service.dto.UserDto;
import com.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers(@RequestParam("ids") List<String> ids) {
        log.info("Received request for ids : {} ", ids);
        return userService.getUsers(ids);
    }
}
