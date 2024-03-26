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

    @Mock
    private UserMessagesRepository userMessagesRepositoryMock;

    @Mock
    private UserAccountRepository userAccountRepositoryMock;

    @InjectMocks
    private UserMessagingService userMessagingServiceMock;

    private static final String TEST_USER_1 = "testUser1";
    private static final String TEST_USER_2 = "testUser2";
    private static final String TEST_USER_3 = "testUser3";


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetAllSentMessagesThenReturnAllSentMessages() {
        Message message = new Message();
        message.setSenderId(TEST_USER_1);
        when(userMessagesRepositoryMock.findBySenderId(TEST_USER_1)).thenReturn(Collections.singletonList(message));

        List<Message> result = userMessagingServiceMock.getAllSentMessages(TEST_USER_1);

        assertEquals(1, result.size());
    }

    @Test
    void whenGetAllReceivedMessagesThenReturnAllReceivedMessages() {
        Message message = new Message();
        message.setReceiverId(TEST_USER_1);
        when(userMessagesRepositoryMock.findByReceiverId(TEST_USER_1)).thenReturn(Collections.singletonList(message));

        List<Message> result = userMessagingServiceMock.getAllReceivedMessages(TEST_USER_1);

        assertEquals(1, result.size());
    }

    @Test
    void whenUserDoesNotExistsThenThrowsException() {
        Message message = new Message();
        message.setSenderId(TEST_USER_1);
        message.setReceiverId(TEST_USER_2);

        when(userAccountRepositoryMock.findById(TEST_USER_1)).thenReturn(Optional.empty());
        when(userAccountRepositoryMock.findById(TEST_USER_2)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(message));
        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(message));

    }

    @Test
    void whenSenderIsReceiverThenThrowsException() {
        Message message = new Message();
        message.setSenderId(TEST_USER_1);
        message.setReceiverId(TEST_USER_1);

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(message));
    }

    @Test
    void whenSenderAndReceiverCorrectThenSendMessage() throws Exception {
        Message message = new Message();
        message.setSenderId(TEST_USER_1);
        message.setReceiverId(TEST_USER_2);

        when(userAccountRepositoryMock.findById(TEST_USER_1)).thenReturn(Optional.of(new User()));
        when(userAccountRepositoryMock.findById(TEST_USER_2)).thenReturn(Optional.of(new User()));

        userMessagingServiceMock.sendMessage(message);

        verify(userMessagesRepositoryMock, times(1)).save(message);
    }

    @Test
    void whenSenderExistsAndReceiverDoesNotExistsThenThrowsException() throws Exception {
        Message message = new Message();
        message.setSenderId(TEST_USER_1);
        message.setReceiverId(TEST_USER_3);

        when(userAccountRepositoryMock.findById(TEST_USER_1)).thenReturn(Optional.of(new User()));
        when(userAccountRepositoryMock.findById(TEST_USER_3)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userMessagingServiceMock.sendMessage(message));
    }
}
