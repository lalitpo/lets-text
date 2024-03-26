package com.vistext.letstextapp.repository;

import com.vistext.letstextapp.model.Message;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMessagesRepository {
        private static final String URL = "jdbc:postgresql://localhost:5432/your_database";
        private static final String USERNAME = "your_username";
        private static final String PASSWORD = "your_password";

        public List<Message> getMessagesByReceiverId(String receiverId) {
            List<Message> messages = new ArrayList<>();

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM messages WHERE receiver_id = ?")) {

                statement.setString(1, receiverId);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Message message = new Message();
                    message.setMessageId(resultSet.getString("message_id"));
                    message.setSenderId(resultSet.getString("sender_id"));
                    message.setReceiverId(resultSet.getString("receiver_id"));
                    message.setMessageContent(resultSet.getString("message_content"));

                    messages.add(message);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return messages;
        }
    }
