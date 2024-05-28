INSERT INTO user(user_id, name, email, password, authority)
VALUES (1, 'vadim', 'email@gmail.com', '$2a$10$Hzdg8upvCxY8wqZAyq79Ou1szV6sS6Xy55GmDyOqgz8ZKbMsklZ1C', 1);

INSERT INTO books(author, title, owner_id, holder_id) VALUES ('Joshua Bloch', 'Effective Java', 1, 1);
INSERT INTO presents(box_color, content, owner_id, holder_id) VALUES ('red', 'books', 1, 1);