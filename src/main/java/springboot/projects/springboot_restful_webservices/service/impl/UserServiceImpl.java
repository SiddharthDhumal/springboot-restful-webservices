package springboot.projects.springboot_restful_webservices.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springboot.projects.springboot_restful_webservices.dto.UserDto;
import springboot.projects.springboot_restful_webservices.entity.User;
import springboot.projects.springboot_restful_webservices.exception.EmailAlreadyExistsException;
import springboot.projects.springboot_restful_webservices.exception.ResourceNotFoundException;
import springboot.projects.springboot_restful_webservices.repository.UserRepository;
import springboot.projects.springboot_restful_webservices.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

//        User user = UserMapper.mapToUser(userDto);

        Optional<User> isEmailAlreadyExists = userRepository.findByEmail(userDto.getEmail());

        if(isEmailAlreadyExists.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exsists for User");
        }

        User user = modelMapper.map(userDto,User.class);

        User savedUser = userRepository.save(user);

//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto getUserById(Long Id){
       User user = userRepository.findById(Id).orElseThrow(
               () -> new ResourceNotFoundException("User","id",Id)
       );
//        return  UserMapper.mapToUserDto(optionalUser.get());

        return modelMapper.map(user,UserDto.class);

    }


    @Override
    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
//        return userList.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

        return userList.stream().map((user)->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User","Id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);

        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",userId)
        );

        userRepository.deleteById(userId);
    }


}
