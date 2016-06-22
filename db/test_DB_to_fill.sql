use testhotel;

insert into user (fullname, login, password, isadmin)
values ('Andrew Kochkin', 'Andrew', 'Kochkin', FALSE);
insert into user (fullname, login, password, isadmin)
values ('Peter Pan', 'Peter', 'Pan', FALSE);
insert into user (fullname, login, password, isadmin)
values ('Doctor Zlo', 'Doctor', 'Zlo', FALSE);
insert into user (fullname, login, password, isadmin)
values ('Jack Sparrow', 'Jack', 'Sparrow', FALSE);
insert into user (fullname, login, password, isadmin)
values ('Super Admin', 'Super', 'Admin', true);

insert into request (user, roomclass, personsquantity, start, end, processed)
values (1, 'STANDARD', 3, '2016-07-10', '2016-08-15', false);
insert into request (user, roomclass, personsquantity, start, end, processed)
values (2, 'LUX', 2, '2016-07-12', '2016-08-12', true);
insert into request (user, roomclass, personsquantity, start, end, processed)
values (3, 'JUNIOR', 1, '2016-07-11', '2016-08-11', false);
insert into request (user, roomclass, personsquantity, start, end, processed)
values (4, 'JUNIOR', 2, '2016-07-15', '2016-08-15', false);
insert into request (user, roomclass, personsquantity, start, end, processed)
values (5, 'STANDARD', 3, '2016-07-17', '2016-08-17', true);

insert into bill (request, room, cost, paid) 
values (1, 101, 500, false);
insert into bill (request, room, cost, paid) 
values (2, 108, 1500, false);
insert into bill (request, room, cost, paid) 
values (3, 105, 1100, false);
insert into bill (request, room, cost, paid) 
values (4, 106, 1100, true);
insert into bill (request, room, cost, paid) 
values (5, 102, 1500, true);

insert into room (number, roomclass, personsmax, costperperson, available) 
values (101, 'STANDARD', 4, 50, 4);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (102, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (103, 'STANDARD', 5, 50, 5);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (104, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (105, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (106, 'JUNIOR', 2, 100, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (107, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (108, 'LUX', 2, 150, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (109, 'LUX', 1, 150, 1);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (110, 'LUX', 2, 150, 2);

insert into room (number, roomclass, personsmax, costperperson, available) 
values (201, 'STANDARD', 4, 50, 4);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (202, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (203, 'STANDARD', 5, 50, 5);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (204, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (205, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (206, 'JUNIOR', 2, 100, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (207, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (208, 'LUX', 2, 150, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (209, 'LUX', 1, 150, 1);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (210, 'LUX', 2, 150, 2);

insert into room (number, roomclass, personsmax, costperperson, available) 
values (301, 'STANDARD', 4, 50, 4);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (302, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (303, 'STANDARD', 5, 50, 5);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (304, 'STANDARD', 3, 50, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (305, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (306, 'JUNIOR', 2, 100, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (307, 'JUNIOR', 3, 100, 3);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (308, 'LUX', 2, 150, 2);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (309, 'LUX', 1, 150, 1);
insert into room (number, roomclass, personsmax, costperperson, available) 
values (310, 'LUX', 2, 150, 2);