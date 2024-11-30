package springboot.projects.springboot_restful_webservices.controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.projects.springboot_restful_webservices.dto.UserDto;
import springboot.projects.springboot_restful_webservices.service.UserService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user){
        UserDto createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto userDto){
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Successfully deleted",HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    ResponseEntity<ErrorDetailsException> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
//
//        ErrorDetailsException errorDetailsException = new ErrorDetailsException(
//                LocalDateTime.now(),
//                resourceNotFoundException.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetailsException,HttpStatus.NOT_FOUND);
//    }
}
