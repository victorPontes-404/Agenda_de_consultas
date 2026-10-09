CREATE UNIQUE INDEX idx_users_email_lower
    ON users (LOWER(email));