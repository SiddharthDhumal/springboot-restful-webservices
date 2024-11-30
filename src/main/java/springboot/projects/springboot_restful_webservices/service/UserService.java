package springboot.projects.springboot_restful_webservices.service;

import springboot.projects.springboot_restful_webservices.dto.UserDto;
import springboot.projects.springboot_restful_webservices.entity.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto getUserById(Long Id);

    public List<UserDto> getAllUsers();

    public UserDto updateUser(UserDto userDto);

    public void deleteUser(Long userId);
}
