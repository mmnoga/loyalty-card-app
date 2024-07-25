package pl.careaboutit.backend.services.websocket;

import java.io.IOException;

public interface NotificationService {

    /**
     * Sends a notification to the specified user.
     *
     * <p>This method checks if the notification is non-null and if the user is currently connected.
     * If either condition is not met, the method throws an appropriate exception.
     * Otherwise, it sends the notification using the WebSocket session.</p>
     *
     * @param user         The username to whom the notification should be sent. The username is used
     *                     to locate the appropriate WebSocket session.
     * @param notification The content of the notification to be sent. It cannot be null.
     * @throws IOException              If an error occurs while sending the message via the WebSocket session.
     * @throws IllegalArgumentException If the notification content is null.
     * @throws IllegalStateException    If the user is not currently connected (i.e., no active WebSocket session).
     */
    void sendNotification(String user, String notification) throws IOException;

    /**
     * Closes the WebSocket connection for the specified user.
     *
     * @param user The username for whom the WebSocket connection should be closed.
     * @throws IOException           If an error occurs while closing the connection.
     * @throws IllegalStateException If the user is not connected.
     */
    void closeConnection(String user) throws IOException, IllegalStateException;

}
