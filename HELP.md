# Loyalty Card App

**Note: This project is currently under development.**

Loyalty Card App is a comprehensive application designed to manage loyalty cards. It provides functionalities for user management, login via Google, real-time chat, and integration with the PayU payment system.

## Technologies

The application is built using the following technologies:

- Java 17
- Spring Boot 3.3.2
- MySQL 8.1
- Flyway 10.16.0
- Google API Client 2.2.0
- JJWT 0.12.6
- WebSocket
- Docker

## Features

- **User Management**: Manage users, including registration and login.
- **OAuth2 Login**: Login via Google OAuth2.
- **Chat**: Real-time chat functionality using WebSocket.
- **Payment Integration**: Integration with PayU for payment processing.

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker

### Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/mmnoga/loyalty-card-app.git
    cd loyalty-card-app
    ```

2. Build the project:

    ```sh
    mvn clean install
    ```

3. Run the database using Docker Compose:

    ```sh
    docker-compose up -d
    ```

4. Configure application properties

    - Obtain Google OAuth 2.0 client credentials:
        - Visit the Google Cloud Console to create OAuth 2.0 client credentials.
    - Obtain PayU API credentials:
        - For information on PayU API integration, visit PayU Developer Documentation.

5. Update `application.yml` file with the obtained credentials.