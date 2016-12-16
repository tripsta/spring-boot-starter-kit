package com.sbsk.service.services.user;

import com.sbsk.dtos.user.UserRequestDto;
import com.sbsk.persistence.entities.user.UserEntity;
import com.sbsk.persistence.repositories.UserRepository;
import com.sbsk.service.converters.user.UserConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserConverter.class)
public class UserServiceImplTests {

  @Mock
  private UserRequestDto userRequestDto;

  @Mock
  private UserEntity userEntity;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createUser_shouldFollowFlow() {
    PowerMockito.mockStatic(UserConverter.class);
    when(userRepository.save(userEntity)).thenReturn(userEntity);

    userServiceImpl.createUser(userRequestDto);

    PowerMockito.verifyStatic(times(1));
    UserConverter.userRequestDtoToDao(any(UserRequestDto.class));
    PowerMockito.verifyStatic(times(1));
    UserConverter.userDaoToResponseDto(any(UserEntity.class));
  }

}
