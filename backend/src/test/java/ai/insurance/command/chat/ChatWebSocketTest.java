//package ai.insurance.command.chat;
//
//import ai.insurance.ai.ChatBot;
//import io.quarkus.websockets.next.WebSocketConnection;
//import io.smallrye.mutiny.Multi;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class ChatWebSocketTest {
//
//    private WebsocketConnectionManager connectionManager;
//    private WebSocketConnection connection;
//    private ChatBot chatBot;
//    private ChatWebSocket chatWebSocket;
//
//    @BeforeEach
//    void setUp() {
//        connectionManager = mock(WebsocketConnectionManager.class);
//        connection = mock(WebSocketConnection.class);
//        chatBot = mock(ChatBot.class);
//        chatWebSocket = new ChatWebSocket(connectionManager, connection, chatBot);
//    }
//
//    @Test
//    void onOpenAddsConnectionToManager() {
//        chatWebSocket.onOpen(connection);
//        verify(connectionManager).addConnection(connection);
//    }
//
//    @Test
//    void onCloseRemovesConnectionFromManager() {
//        when(connection.id()).thenReturn("123");
//        chatWebSocket.onClose(connection);
//        verify(connectionManager).removeConnection("123");
//    }
//
//    @Test
//    void onMessageReturnsExpectedResponse() {
//        ChatInputDTO input = new ChatInputDTO("Hello");
//        when(connection.pathParam("userId")).thenReturn("1");
//        when(chatBot.chat(1, "Hello")).thenReturn(Multi.createFrom().item("Hi there!"));
//
//        Multi<String> response = chatWebSocket.onMessage(input);
//
//        assertEquals("Hi there!", response.collect().first().await().indefinitely());
//    }
//
//    @Test
//    void onMessageHandlesEmptyMessage() {
//        ChatInputDTO input = new ChatInputDTO("");
//        when(connection.pathParam("userId")).thenReturn("1");
//        when(chatBot.chat(1, "")).thenReturn(Multi.createFrom().item("Please provide a message."));
//
//        Multi<String> response = chatWebSocket.onMessage(input);
//
//        assertEquals("Please provide a message.", response.collect().first().await().indefinitely());
//    }
//
//    @Test
//    void onMessageHandlesNullMessage() {
//        ChatInputDTO input = new ChatInputDTO(null);
//        when(connection.pathParam("userId")).thenReturn("1");
//        when(chatBot.chat(1, null)).thenReturn(Multi.createFrom().item("Message cannot be null."));
//
//        Multi<String> response = chatWebSocket.onMessage(input);
//
//        assertEquals("Message cannot be null.", response.collect().first().await().indefinitely());
//    }
//}