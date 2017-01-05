package com.sbsk.service.validators;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

  private static final Integer VALID_NAME_THRESHOLD = 2;
  private static final Integer AGE_LOWER_BOUND = 0;
  private static final Integer AGE_UPPER_BOUND = 120;

  @Autowired
  private UserRepository userRepository;

  public Boolean validateUser(UserRequestDto userRequestDto) {
      return isNameValid(userRequestDto.getFirstName()) &&
              isNameValid(userRequestDto.getLastName()) &&
              isAgeValid(userRequestDto.getAge());
  }

  public Boolean isNameValid(String name) {
    return name != null &&
            name.length() > VALID_NAME_THRESHOLD;
  }

  public Boolean isAgeValid(Integer age) {
    return age != null &&
            age > AGE_LOWER_BOUND && age < AGE_UPPER_BOUND;
  }

  public Boolean userExists(Long id) {
    return id != null &&
            userRepository.exists(id);
  }

}
