INSERT INTO parent_task (parent_id,parent_task) VALUES (1,"parent task1");
INSERT INTO parent_task (parent_id,parent_task) VALUES (2,"parent task2");
INSERT INTO parent_task (parent_id,parent_task) VALUES (3,"parent task3");
INSERT INTO parent_task (parent_id,parent_task) VALUES (4,"parent task4");


INSERT INTO task(end_date,priority,start_date,task,parent_task_id, status )VALUES(CURDATE() ,10,CURDATE() ,"task 1",1, false);
INSERT INTO task(end_date,priority,start_date,task,parent_task_id, status)VALUES(CURDATE() ,10,CURDATE() ,"task 2",1, false);
INSERT INTO task(end_date,priority,start_date,task,parent_task_id, status)VALUES(CURDATE() ,10,CURDATE() ,"task 3",1,true);
