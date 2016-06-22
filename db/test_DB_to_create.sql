create  database if not exists testhotel;

CREATE TABLE testhotel.user
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fullname VARCHAR(100) NOT NULL,
    login VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    isadmin BOOLEAN NOT NULL
);
ALTER TABLE testhotel.user ADD CONSTRAINT unique_id UNIQUE (id);

CREATE TABLE testhotel.room
(
    number INT PRIMARY KEY NOT NULL,
    roomclass ENUM('STANDARD', 'JUNIOR', 'LUX') NOT NULL,
    personsmax INT NOT NULL,
    costperperson INT NOT NULL,
    available INT NOT NULL
);
ALTER TABLE testhotel.room ADD CONSTRAINT unique_number UNIQUE (number);

CREATE TABLE testhotel.request
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user INT NOT NULL,
    roomclass ENUM('STANDARD', 'JUNIOR', 'LUX') NOT NULL,
    personsquantity INT NOT NULL,
    start DATE NOT NULL,
    end DATE NOT NULL,
    processed BOOLEAN NOT NULL
);
ALTER TABLE testhotel.request ADD CONSTRAINT unique_id UNIQUE (id);

CREATE TABLE testhotel.bill
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    request INT NOT NULL,
    room INT NOT NULL,
    cost INT NOT NULL,
    paid BOOLEAN NOT NULL
);
ALTER TABLE testhotel.bill ADD CONSTRAINT unique_id UNIQUE (id);