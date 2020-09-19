insert into user(id,name,password, email,IS_SUBSCRIBED_FOR_NOTIFICATIONS, IS_SUBSCRIBED_FOR_MAIL, days_between_notifications, max_days_before_upcoming)
 values (100,'Ivan', 'password', 'kostadin3443@gmail.com',1, 1, 1, 7);
insert into user(id,name,password, email,IS_SUBSCRIBED_FOR_NOTIFICATIONS, IS_SUBSCRIBED_FOR_MAIL, days_between_notifications, max_days_before_upcoming)
 values (101,'Georgi', 'password', 'kostadin3443@gmail.com',1, 1, 1, 7);
insert into user(id,name,password, email,IS_SUBSCRIBED_FOR_NOTIFICATIONS, IS_SUBSCRIBED_FOR_MAIL, days_between_notifications, max_days_before_upcoming) 
values (102,'Ivelina', 'password', 'kostadin3443@gmail.com',1, 1, 1, 7);
insert into user(id,name,password, email,IS_SUBSCRIBED_FOR_NOTIFICATIONS, IS_SUBSCRIBED_FOR_MAIL, days_between_notifications, max_days_before_upcoming)
 values (103,'Pavlina', 'password', 'kostadin3443@gmail.com',1, 1, 1, 7);

insert into wheel(id,make,model,name, user_id) values (100,'Lamborghini', 'Aventador S', 'Aventador',100);
insert into wheel(id,make,model,name, user_id) values (101,'Lamborghini', 'URUS', 'Lambo',101);
insert into wheel(id,make,model,name, user_id) values (102,'Porsche', '718 Boxster S', '718',102);
insert into wheel(id,make,model,name, user_id) values (103,'Porsche', '911 Carrera  4', '911',103);

--CURRENT_DATE()
--parseDateTime('20140101000000','yyyyMMddHHmmss')
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(97,CURRENT_DATE ,current_date -3, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 100, true);
          insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(98,CURRENT_DATE ,current_date +2, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 100, false);
                    insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(100,CURRENT_DATE ,current_date -10, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 100, true );
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(104,CURRENT_DATE ,current_date +2, 'TRANSMISSION_OIL_CHANGE','TRANSMISSION_OIL_CHANGE ', 100,false);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(101,CURRENT_DATE ,current_date +1, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 101,false);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(105,CURRENT_DATE ,current_date +2, 'TRANSMISSION_OIL_CHANGE','TRANSMISSION_OIL_CHANGE ', 101,false);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(102,CURRENT_DATE ,current_date +1, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 102,false);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(106,CURRENT_DATE ,current_date +2, 'TRANSMISSION_OIL_CHANGE','TRANSMISSION_OIL_CHANGE ', 103,false);

insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(103,CURRENT_DATE ,current_date +1, 'ENGINE_OIL_CHANGE','ENGINE_OIL_CHANGE ', 103,false);
insert into Task(id,date_created, date_scheduled, task_type, details, wheel_id, is_completed)
          values(107,CURRENT_DATE ,current_date +4, 'type','other type details', 100,false);

commit;
