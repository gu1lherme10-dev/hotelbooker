CREATE TABLE users (
    id serial PRIMARY KEY,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);


