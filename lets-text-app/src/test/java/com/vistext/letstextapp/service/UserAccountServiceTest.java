package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.User;
import com.vistext.letstextapp.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepositoryMock;

    @InjectMocks
    private UserAccountService userAccountServiceMock;

    private static final String TEST_USER_1 = "testUser1";
    private static final String TEST_USER_2 = "testUser2";

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenUserDoesNotExistCreatesUser() throws Exception {
        User user = new User();
        user.setUserNickName(TEST_USER_1);

        when(userAccountRepositoryMock.findById(TEST_USER_1)).thenReturn(Optional.empty());

        userAccountServiceMock.createUserAccount(user);

        verify(userAccountRepositoryMock, times(1)).save(user);
    }

    @Test
    void whenUserExistsThrowsException() {
        User user = new User();
        user.setUserNickName(TEST_USER_1);

        when(userAccountRepositoryMock.findById(TEST_USER_1)).thenReturn(Optional.of(user));

        assertThrows(Exception.class, () -> userAccountServiceMock.createUserAccount(user));
    }
}
