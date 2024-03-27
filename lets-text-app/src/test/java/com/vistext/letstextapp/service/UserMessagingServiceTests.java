package com.vistext.letstextapp.service;

import com.vistext.letstextapp.model.Message;
import com.vistext.letstextapp.model.User;
import com.vistext.letstextapp.repository.UserAccountRepository;
import com.vistext.letstextapp.repository.UserMessagesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserMessagingServiceTests {

    private final Message messageMock = new Message("testUser1", "testUser2");
    private final User userMock1 = new User("testUser1");
    private final User userMock2 = new User("testUser2");
    @Mock
    private UserMessagesRepository userMessagesRepositoryMock;
    @Mock
    private UserAccountRepository userAccountRepositoryMock;
    @InjectMocks
    private UserMessagingService userMessagingServiceMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllSentMessagesThenReturnAllSentMessages() {
        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.of(userMock1));
        when(userMessagesRepositoryMock.findBySenderId(userMock1.getUserNickName())).thenReturn(Collections.singletonList(messageMock));

        List<Message> result = userMessagingServiceMock.getAllSentMessages(userMock1.getUserNickName());

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllReceivedMessagesThenReturnAllReceivedMessages() {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.of(userMock1));
        when(userMessagesRepositoryMock.findByReceiverId(userMock1.getUserNickName())).thenReturn(Collections.singletonList(messageMock));

        List<Message> result = userMessagingServiceMock.getAllReceivedMessages(userMock1.getUserNickName());

        assertEquals(1, result.size());
    }

    @Test
    void whenUserDoesNotExistsThenThrowsException() {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.empty());
        when(userAccountRepositoryMock.findById(userMock2.getUserNickName())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(messageMock));
        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(messageMock));

    }

    @Test
    void whenSenderIsReceiverThenThrowsException() {

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(messageMock));
    }

    @Test
    void whenSenderAndReceiverCorrectThenSendMessage() throws Exception {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.of(userMock1));
        when(userAccountRepositoryMock.findById(userMock2.getUserNickName())).thenReturn(Optional.of(userMock2));

        userMessagingServiceMock.sendMessage(messageMock);

        verify(userMessagesRepositoryMock, times(1)).save(messageMock);
    }

    @Test
    void whenSenderExistsAndReceiverDoesNotExistsThenThrowsException() {

        when(userAccountRepositoryMock.findById(userMock1.getUserNickName())).thenReturn(Optional.of(new User()));
        when(userAccountRepositoryMock.findById(userMock2.getUserNickName())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(messageMock));
    }
}
