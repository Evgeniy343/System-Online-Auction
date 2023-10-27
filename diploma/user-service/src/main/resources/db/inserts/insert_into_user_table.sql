insert into auction_user (id, name, surname, patronymic, email, phone_number, password, role, photo,
                          copies_of_documents_id, identification_id, registration_address_id,
                          requisites_for_return_of_deposit_id)
values (1, 'Ivan', 'Teterskiy', 'Yurievcih', 'tets@mail.ru', '+375(33)234-41-08', '342rfds34',
        'USER', 'ivan.jpg', 1, 1, 1, 1),
       (2, 'Jeka', 'Churai', 'Sergeevich', 'ychuray@list.ru', '+375(33)684-91-06', '6849106jeka',
        'ADMIN', 'jeka.jpg', 2, 2, 2, 2),
       (3, 'Danik', 'Markov', 'Olegovich', 'markov@mail.ru', '+375(29)444-33-11', '12431414ewff',
        'USER', 'danik.jpg', 3, 3, 3, 3),
       (4, 'Andrei', 'Zharikov', 'Olegovich', 'zhar@inbox.ru', '+375(29)452-55-14', '32523423efwefw',
        'USER', 'andrei.jpg', 4, 4, 4, 4),
       (5, 'Maks', 'Shilo', 'Yurievcih', 'maks@gmail.ru', '+375(33)563-22-54', '32425weqefq112',
        'USER', 'maks.jpg', 5, 5, 5, 5);