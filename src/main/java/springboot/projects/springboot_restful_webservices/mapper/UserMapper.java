package springboot.projects.springboot_restful_webservices.mapper;

import springboot.projects.springboot_restful_webservices.dto.UserDto;
import springboot.projects.springboot_restful_webservices.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }


    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
