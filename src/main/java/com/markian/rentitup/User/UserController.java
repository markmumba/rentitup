package com.markian.rentitup.User;

import com.markian.rentitup.User.UserDto.UserListResponseDto;
import com.markian.rentitup.User.UserDto.UserRequestDto;
import com.markian.rentitup.User.UserDto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserListResponseDto>> getAllUsers() {
        List<UserListResponseDto> userListResponseDtoList = userService.getAllUsers();
        return ResponseEntity.ok(userListResponseDtoList);
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/owners")
    public ResponseEntity<List<UserListResponseDto>> getAllOwners() {
        List<UserListResponseDto> userListResponseDtoList = userService.getAllOwners();
        return ResponseEntity.ok(userListResponseDtoList);
    }


    @GetMapping("/user-profile")
    public ResponseEntity<UserResponseDto> getLoggedInUserProfile() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserResponseDto userResponseDto= userService.getLoggedInUserInfo(email);
        return ResponseEntity.ok(userResponseDto);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto userResponseDto = userService.getUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDto userRequestDto) {
        String response = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        String response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }

}
