insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1);
 	INSERT INTO SEC_USER (userName, encryptedPassword, ENABLED) VALUES 
('Lawyer1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Lawyer2', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Lawyer3', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Lawyer4', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Lawyer5', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1);

INSERT INTO SEC_USER (userName, encryptedPassword, ENABLED) VALUES 
('Guest1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Guest2', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Guest3', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Guest4', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1),
('Guest5', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu',1);

insert into sec_role (roleName)
values ('ROLE_BOSS');

insert into sec_role (roleName)
values ('ROLE_LAWYER');

insert into sec_role (roleName)
values ('ROLE_GUEST');
 
insert into user_role (userId, roleId)
values (1, 1);
insert into user_role (userId, roleId)
values (2,3);
insert into user_role (userId, roleId)
values (3, 3);
insert into user_role (userId, roleId)
values (4, 3);
insert into user_role (userId, roleId)
values (5, 3);
insert into user_role (userId, roleId)
values (6, 3);
 
INSERT INTO Lawyer ( name, salary, yearsExperience)
VALUES
( 'Lawyer1', 600.00, 23),
( 'Lawyer2', 400.00, 24),
( 'Lawyer3', 500.00, 25),
('Lawyer4', 800.00, 26),
( 'Lawyer5', 700.00, 27);

