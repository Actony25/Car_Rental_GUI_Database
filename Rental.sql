DROP TABLE customers;
DROP TABLE fleet;
DROP TABLE employees;
DROP TABLE location;
DROP TABLE rentals;

CREATE TABLE customers (
	customer_id INT NOT NULL,
	firstName varchar(20) NOT NULL,
	lastName varchar(30) NOT NULL,
	country_of_residence varchar(30) NOT NULL,
	PRIMARY KEY (customer_id)
);

CREATE TABLE fleet (
	vehicle_id INT NOT NULL,
	vehicle_make varchar (20) NOT NULL,
	vehicle_model varchar (20) NOT NULL,
	vehicle_group varchar (20) NOT NULL,
	vehicle_miles INT NOT NULL,
	PRIMARY KEY (vehicle_id)
);

CREATE TABLE location (
	location_id INT NOT NULL,
	location_name varchar(30) NOT NULL,
	address varchar(50) NOT NULL,
	PRIMARY KEY (location_id)
);

CREATE TABLE employees (
	employee_id INT NOT NULL,
	firstName varchar(20) NOT NULL,
	lastName varchar(30) NOT NULL,
	country_of_residence varchar(30) NOT NULL,
	PRIMARY KEY (employee_id)
);

CREATE TABLE rentals (
	rental_id INT NOT NULL GENERATED ALWAYS AS IDENTITY,
	customer_id INT NOT NULL,
	vehicle_id INT NOT NULL,
	employee_id INT NOT NULL,
	location_id INT NOT NULL,
	revenue INT NOT NULL,
	insurance_purchase varchar(1) NOT NULL,
	insurance_revenue INT NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
	FOREIGN KEY (vehicle_id) REFERENCES fleet (vehicle_id),
	FOREIGN KEY (employee_id) REFERENCES employees (employee_id),
	FOREIGN KEY (location_id) REFERENCES location (location_id),
	PRIMARY KEY (rental_id)
);

INSERT INTO customers (customer_id, firstName, lastName, country_of_residence)
VALUES
	(1,'Gil','Jericho','United States'),
	(2,'Nelly','Barret','United Kingdom'),
	(3,'Felicia','Cletus','United States'),
	(4,'Urbain','Josette','France'),
	(5,'Marguerite','Olympe','France'),
	(6,'Rolan','Alma','Italy'),
	(7,'Rafferty','Darell','United Kingdom'),
	(8,'Janey','Raynard','United Kingdom'),
	(9,'Jaci','Shalmaneser','United Kingdom'),
	(10,'Sarika','Isabel','United States'),
	(11,'Jenny','Genoveva','Spain'),
	(12,'Aurobindo','Delshad','United States'),
	(13,'Marylyn','Virgil','United States'),
	(14,'Raj','Gabin','Spain'),
	(15,'Sebastien','Alan','France'),
	(16,'Everitt','Kacie','United States'),
	(17,'Benoît','Vivien','France'),
	(18,'Dezi','Pol','United States'),
	(19,'Toni','Quirinus','United States'),
	(20,'Nikomachos','Trajan','Spain'),
	(21,'Naomhán','Renat','United Kingdom'),
	(22,'Quincey','Darrell','United States'),
	(23,'Sera','Valorie','United States'),
	(24,'Kalysta','Cayden','United States'),
	(25,'Tadhg','Tristram','United States'),
	(26,'Lara','Amilcar','Spain');
	
	
INSERT INTO fleet (vehicle_id, vehicle_make, vehicle_model, vehicle_group, vehicle_miles)
VALUES
	(39388,'Toyota','Camry','Mid-Size',11396),
	(67028,'Dodge','Challenger','Muscle',8059),
	(99449,'Honda','CR-V','SUV',3952),
	(41130,'Nissan','Altima','Sedan',4734),
	(94566,'Toyota','Camry','Mid-Size',6592),
	(10799,'Corvette','Stingray','Sport',13305),
	(14487,'Nissan','Altima','Sedan',7510),
	(41980,'Honda','CR-V','SUV',8312),
	(46008,'Toyota','Camry','Mid-Size',1054),
	(77400,'Toyota','Camry','Mid-Size',2242),
	(18874,'Dodge','Challenger','Muscle',8356),
	(27195,'Honda','CR-V','SUV',14779),
	(74037,'Honda','CR-V','SUV',4886),
	(16222,'Toyota','Camry','Mid-Size',13695),
	(54599,'Nissan','Altima','Sedan',4327),
	(53510,'Nissan','Altima','Sedan',2083),
	(69823,'Dodge','Challenger','Muscle',6072),
	(82817,'Toyota','Camry','Mid-Size',8645),
	(48442,'Honda','CR-V','SUV',5842),
	(38430,'Corvette','Stingray','Sport',12039),
	(21022,'Toyota','Camry','Mid-Size',13330),
	(65947,'Honda','CR-V','SUV',2667),
	(20357,'Nissan','Altima','Sedan',12977),
	(68563,'Toyota','Camry','Mid-Size',2637),
	(17313,'Nissan','Altima','Sedan',6286),
	(45825,'Corvette','Stingray','Sport',8503),
	(44516,'Dodge','Challenger','Muscle',13656),
	(93215,'Corvette','Stingray','Sport',11693);
	
INSERT INTO employees (employee_id, firstName, lastName, country_of_residence)
VALUES
	(1,'Allyson','Cevahir','United States'),
	(2,'Colby','Bertie','United States'),
	(3,'Fredric','Milica','United States'),
	(4,'Adaline','Elenora','United States'),
	(5,'Aubree','Philander','United States'),
	(6,'Gil','Dayton','United States'),
	(7,'Michele','Lyndsea','United States'),
	(8,'Ivo','Burcu','United States'),
	(9,'Inigo','Teagan','United States'),
	(10,'Patricia','Reannon','United States'),
	(11,'Janella','Caris','United States'),
	(12,'Firat','Tess','United States'),
	(13,'Ryley','Syd','United States'),
	(14,'Seyyit','Cairo','United States');

	
INSERT INTO location (location_id, location_name, address)
VALUES
	(1,'MIAMI AIRPORT','2100 NW 42nd Ave, Miami, FL 33126'),
	(2,'ATLANTA AIRPORT','6000 N Terminal Pkwy, Atlanta, GA 30320'),
	(3,'LOS ANGELES AIRPORT','1 World Way, Los Angeles, CA 90045'),
	(4,'DENVER AIRPORT','8500 Pena Blvd, Denver, CO 80249'),
	(5,'ORLANDO AIRPORT','1 Jeff Fuqua Blvd, Orlando, FL 32827');
	
INSERT INTO rentals (customer_id, vehicle_id, employee_id, location_id, revenue, insurance_purchase, insurance_revenue)
VALUES
	(3,39388,10,1,660,'Y',24),
	(5,77400,1,3,372,'N',0),
	(8,16222,9,4,644,'Y',80),
	(2,38430,11,5,501,'N',0),
	(4,16222,14,2,362,'N',0),
	(15,20357,8,2,314,'N',0),
	(2,20357,1,4,235,'Y',29),
	(18,14487,13,4,46,'N',0),
	(9,48442,11,5,140,'Y',24),
	(6,68563,3,1,234,'Y',70),
	(16,21022,1,2,320,'N',0),
	(5,48442,2,3,774,'N',0),
	(19,65947,3,4,190,'N',0),
	(4,69823,1,4,274,'Y',81),
	(17,21022,4,1,2478,'Y',210),
	(2,16222,5,5,459,'N',0),
	(4,68563,2,2,73,'N',0),
	(1,27195,7,5,297,'Y',19),
	(7,39388,1,4,1098,'N',0),
	(11,82817,4,5,150,'N',0),
	(26,18874,12,5,1430,'N',0),
	(12,53510,10,1,342,'Y',56),
	(25,67028,2,2,63,'N',0),
	(8,10799,8,3,729,'Y',19),
	(17,16222,10,4,773,'Y',44),
	(13,54599,9,4,1051,'Y',140),
	(24,74037,2,1,79,'N',0),
	(19,18874,1,4,45,'N',0),
	(11,46008,5,2,562,'Y',189),
	(20,77400,1,3,340,'N',0),
	(10,68563,4,1,291,'Y',33),
	(11,44516,3,2,244,'N',0),
	(25,41130,2,3,494,'Y',91),
	(16,45825,1,5,252,'N',0),
	(21,17313,3,4,240,'Y',56),
	(11,94566,2,1,971,'N',0),
	(22,39388,5,5,280,'Y',24),
	(12,93215,14,3,554,'Y',89),
	(23,41980,12,2,495,'N',0),
	(25,99449,6,4,183,'N',0),
	(3,67028,13,1,67,'N',0),
	(13,39388,4,3,466,'N',0);