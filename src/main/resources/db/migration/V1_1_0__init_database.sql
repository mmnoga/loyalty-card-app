CREATE TABLE Users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255),
                       password VARCHAR(255) NOT NULL,
                       phone VARCHAR(255),
                       role VARCHAR(50) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE CardStatus (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            status VARCHAR(50) NOT NULL
);

CREATE TABLE LoyaltyCards (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              card_number VARCHAR(255) NOT NULL UNIQUE,
                              user_id BIGINT,
                              status_id INT NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES Users(id),
                              FOREIGN KEY (status_id) REFERENCES CardStatus(id)
);

CREATE TABLE LoyaltyPoints (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               card_id BIGINT NOT NULL,
                               points INT NOT NULL,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (card_id) REFERENCES LoyaltyCards(id)
);

INSERT INTO CardStatus (status) VALUES ('active');
INSERT INTO CardStatus (status) VALUES ('inactive');