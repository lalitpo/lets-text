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

    private final User userMock1 = new User("testUser1");
    @Mock
    private UserAccountRepository userAccountRepositoryMock;
    @InjectMocks
    private UserAccountService userAccountServiceMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenUserDoesNotExistCreatesUser() throws Exception {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.empty());

        userAccountServiceMock.createUserAccount(userMock1);

        verify(userAccountRepositoryMock, times(1)).save(userMock1);
    }

    @Test
    void whenUserExistsThrowsException() {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.of(userMock1));

        assertThrows(Exception.class, () -> userAccountServiceMock.createUserAccount(userMock1));
    }
}
