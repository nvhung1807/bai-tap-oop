package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatWindow extends Application {

    private ListView<Message> messageListView;
    private TextField messageField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Window");

        // Tạo các thành phần GUI
        messageListView = new ListView<>();
        messageListView.setCellFactory(param -> new MessageListCell());

        messageField = new TextField();
        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        // Nút để thêm một tin nhắn mẫu
        Button addSampleButton = new Button("Add Sample");
        addSampleButton.setOnAction(e -> addSampleMessage());

        // Tạo layout và đặt các thành phần vào layout
        HBox messageBox = new HBox(10);
        messageBox.getChildren().addAll(messageField, sendButton, addSampleButton);

        BorderPane layout = new BorderPane();
        layout.setCenter(messageListView);
        layout.setBottom(messageBox);

        // Đặt layout vào scene
        Scene scene = new Scene(layout, 600, 400);

        // Đặt scene vào stage
        primaryStage.setScene(scene);

        // Hiển thị cửa sổ
        primaryStage.show();
    }

    private void sendMessage() {
        String messageText = messageField.getText().trim();
        if (!messageText.isEmpty()) {
            Message message = new Message("You", messageText);
            messageListView.getItems().add(message);
            messageField.clear();
        }
    }

    private void addSampleMessage() {
        Message sampleMessage = new Message("Huy", "Hi");
        messageListView.getItems().add(sampleMessage);
    }

    // Lớp đại diện cho một tin nhắn
    private static class Message {
        private String sender;
        private String content;

        public Message(String sender, String content) {
            this.sender = sender;
            this.content = content;
        }

        public String getSender() {
            return sender;
        }

        public String getContent() {
            return content;
        }
    }

    // Lớp tùy chỉnh để hiển thị tin nhắn
    private static class MessageListCell extends ListCell<Message> {
        @Override
        protected void updateItem(Message message, boolean empty) {
            super.updateItem(message, empty);

            if (empty || message == null) {
                setText(null);
            } else {
                setText(message.getSender() + ": " + message.getContent());

                // Tùy chỉnh căn chỉnh dựa trên người gửi
                if ("You".equals(message.getSender())) {
                    setAlignment(Pos.CENTER_RIGHT);
                } else {
                    setAlignment(Pos.CENTER_LEFT);
                }
            }
        }
    }
}


