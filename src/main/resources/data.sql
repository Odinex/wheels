insert into user(id,name,password, email)  values (100,'Ivan', 'password', 'kostadin3443@gmail.com');
insert into user(id,name,password, email)  values (101,'Georgi', 'password', 'kostadin3443@gmail.com');
insert into user(id,name,password, email)  values (102,'Ivelina', 'password', 'kostadin3443@gmail.com');
insert into user(id,name,password, email)  values (103,'Pavlina', 'password', 'kostadin3443@gmail.com');

insert into wheel(id,make,model,name, user_id) values (100,'Lamborghini', 'Aventador S', 'Aventador',100);
insert into wheel(id,make,model,name, user_id) values (101,'Lamborghini', 'URUS', 'Lambo',101);
insert into wheel(id,make,model,name, user_id) values (102,'Porsche', '718 Boxster S', '718',102);
insert into wheel(id,make,model,name, user_id) values (103,'Porsche', '911 Carrera  4', '911',103);

--CURRENT_DATE()
--parseDateTime('20140101000000','yyyyMMddHHmmss')
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(100,CURRENT_DATE ,current_date +1, 1,'ENGINE_OIL_CHANGE ', 100);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(104,CURRENT_DATE ,current_date +2, 2,'TRANSMISSION_OIL_CHANGE ', 100);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(101,CURRENT_DATE ,current_date +1, 1,'ENGINE_OIL_CHANGE ', 101);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(105,CURRENT_DATE ,current_date +2, 2,'TRANSMISSION_OIL_CHANGE ', 101);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(102,CURRENT_DATE ,current_date +1, 1,'ENGINE_OIL_CHANGE ', 102);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(106,CURRENT_DATE ,current_date +2, 2,'TRANSMISSION_OIL_CHANGE ', 103);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id)
          values(103,CURRENT_DATE ,current_date +1, 1,'ENGINE_OIL_CHANGE ', 103);
insert into Task(id,date_created, date_scheduled, task_type, details,other_task_type, wheel_id)
          values(107,CURRENT_DATE ,current_date +4, 3,'other type details', 'other type test' , 100);

