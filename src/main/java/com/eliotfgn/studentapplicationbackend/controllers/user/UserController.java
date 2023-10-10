package com.eliotfgn.studentapplicationbackend.controllers.user;

import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<ResourceResponse<List<UserDto>>> getAll() {
        return ResponseEntity.ok(new ResourceResponse<>(true, userService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponse<UserDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, userService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponse<UserDto>> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, userService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, "User with id " + id + " deleted successfully"));
    }
}
