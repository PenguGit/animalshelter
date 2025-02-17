-- import to SQLite by running: sqlite3.exe db.sqlite3 -init sqlite.sql

PRAGMA journal_mode = MEMORY;
PRAGMA synchronous = OFF;
PRAGMA foreign_keys = OFF;
PRAGMA ignore_check_constraints = OFF;
PRAGMA auto_vacuum = NONE;
PRAGMA secure_delete = OFF;
BEGIN TRANSACTION;

DROP TABLE IF EXISTS `adopter`;

CREATE TABLE `adopter` (
`id` INTEGER NOT NULL ,
`last_name` TEXT NOT NULL,
`first_name` TEXT NOT NULL,
`phone_number` TEXT NOT NULL,
`email` TEXT DEFAULT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `adopter` WRITE;
UNLOCK TABLES;
DROP TABLE IF EXISTS `adoption`;

CREATE TABLE `adoption` (
`id` INTEGER NOT NULL ,
`date` date NOT NULL,
`Adopter_id` INTEGER NOT NULL,
`Animal_id` INTEGER NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`Adopter_id`) REFERENCES `adopter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`Animal_id`) REFERENCES `animal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
LOCK TABLES `adoption` WRITE;
UNLOCK TABLES;
DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
`id` INTEGER NOT NULL ,
`name` TEXT NOT NULL,
`gender` tinyINTEGER DEFAULT NULL,
`date_of_birth` date NOT NULL,
`additional_info` text DEFAULT NULL,
`AnimalType_id` INTEGER NOT NULL,
`Patron_id` INTEGER DEFAULT NULL,
`Room_id` INTEGER NOT NULL,
`image` longblob DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`AnimalType_id`) REFERENCES `animaltype` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (`Patron_id`) REFERENCES `patron` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
FOREIGN KEY (`Room_id`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);
LOCK TABLES `animal` WRITE;
INSERT INTO `animal` VALUES (3,'name',2,'1970-01-01','hahaha',2,1,2,NULL),(10,'Aylmao',0,'1111-11-11',NULL,2,1,2,NULL),(11,'Hey',2,'1212-12-12',NULL,3,1,2,NULL),(13,'Pog',0,'1212-12-12','BigTest',2,1,2,NULL),(14,'Ay',0,'1111-11-11',NULL,3,NULL,2,NULL),(15,'Sam',0,'1111-11-11',NULL,2,1,2,NULL),(18,'name',2,'1212-12-12',NULL,2,NULL,2,NULL),(19,'; DROP TABLE animal; --',1,'1212-12-12','232',2,1,2,NULL),(20,'Jimmy',2,'2111-12-11','Heftig',2,2,2,NULL),(21,'Jimmy Jones',1,'1111-11-11','as',2,NULL,2,NULL),(22,'Jones Jimmy',1,'1313-12-13','Stalling',2,1,2,NULL);
UNLOCK TABLES;
DROP TABLE IF EXISTS `animaltype`;

CREATE TABLE `animaltype` (
`id` INTEGER NOT NULL ,
`name` TEXT NOT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `animaltype` WRITE;
INSERT INTO `animaltype` VALUES (2,'eggse'),(3,'jimbo');
UNLOCK TABLES;
DROP TABLE IF EXISTS `caretaker`;

CREATE TABLE `caretaker` (
`id` INTEGER NOT NULL ,
`last_name` TEXT NOT NULL,
`first_name` TEXT NOT NULL,
`email` TEXT DEFAULT NULL,
`phone_number` TEXT NOT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `caretaker` WRITE;
INSERT INTO `caretaker` VALUES (1,'jimmy','jones','max@max.max','123456789');
UNLOCK TABLES;
DROP TABLE IF EXISTS `examination`;

CREATE TABLE `examination` (
`id` INTEGER NOT NULL ,
`title` TEXT NOT NULL,
`date` date NOT NULL,
`description` text DEFAULT NULL,
`Vet_id` INTEGER DEFAULT NULL,
`Animal_id` INTEGER NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`Animal_id`) REFERENCES `animal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`Vet_id`) REFERENCES `vet` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
);
LOCK TABLES `examination` WRITE;
INSERT INTO `examination` VALUES (1,'Yup','0032-01-02','Simon',1,3),(2,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','1111-11-11','11sd',2,19);
UNLOCK TABLES;
DROP TABLE IF EXISTS `incident`;

CREATE TABLE `incident` (
`id` INTEGER NOT NULL ,
`title` TEXT NOT NULL,
`date` date NOT NULL,
`description` text DEFAULT NULL,
`Caretaker_id` INTEGER DEFAULT NULL,
`Animal_id` INTEGER NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`Animal_id`) REFERENCES `animal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`Caretaker_id`) REFERENCES `caretaker` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
);
LOCK TABLES `incident` WRITE;
INSERT INTO `incident` VALUES (1,'As','0021-11-06','Wasgeht',1,3),(2,'Neues','1111-11-11','Hier ist etwas Passiert',1,10),(3,'Mehr Test','1212-12-12','Mehr passier',1,10),(4,'A','1111-11-11','B',1,10),(5,'2222222222222222222222222222222','2222-12-22','2222222222222222222222222222222',1,10);
UNLOCK TABLES;
DROP TABLE IF EXISTS `patron`;

CREATE TABLE `patron` (
`id` INTEGER NOT NULL ,
`last_name` TEXT NOT NULL,
`first_name` TEXT NOT NULL,
`email` TEXT DEFAULT NULL,
`phone_number` TEXT NOT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `patron` WRITE;
INSERT INTO `patron` VALUES (1,'Martins','Jim','154785552','mellow@yellow.com'),(2,'Animal','Fresh','787878587','check@up.com');
UNLOCK TABLES;
DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
`id` INTEGER NOT NULL ,
`name` TEXT NOT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `room` WRITE;
INSERT INTO `room` VALUES (2,'hallo');
UNLOCK TABLES;
DROP TABLE IF EXISTS `vet`;

CREATE TABLE `vet` (
`id` INTEGER NOT NULL ,
`last_name` TEXT NOT NULL,
`first_name` TEXT NOT NULL,
`email` TEXT DEFAULT NULL,
`phone_number` TEXT NOT NULL,
PRIMARY KEY (`id`)
);
LOCK TABLES `vet` WRITE;
INSERT INTO `vet` VALUES (1,'jimmy','jones','hallo@hallo','2135412'),(2,'Valentin','Tina','1023231','mmm@mmm.com');
UNLOCK TABLES;



CREATE INDEX `adoption_fk_Adoption_Adopter1` ON `adoption` (`Adopter_id`);
CREATE INDEX `adoption_fk_Adoption_Animal1` ON `adoption` (`Animal_id`);
CREATE INDEX `animal_fk_Animal_AnimalType` ON `animal` (`AnimalType_id`);
CREATE INDEX `animal_fk_Animal_Patron1` ON `animal` (`Patron_id`);
CREATE INDEX `animal_fk_Animal_Room1` ON `animal` (`Room_id`);
CREATE INDEX `examination_fk_Examination_Vet1` ON `examination` (`Vet_id`);
CREATE INDEX `examination_fk_Examination_Animal1` ON `examination` (`Animal_id`);
CREATE INDEX `incident_fk_Incident_Caretaker1` ON `incident` (`Caretaker_id`);
CREATE INDEX `incident_fk_Incident_Animal1` ON `incident` (`Animal_id`);

COMMIT;
PRAGMA ignore_check_constraints = ON;
PRAGMA foreign_keys = ON;
PRAGMA journal_mode = WAL;
PRAGMA synchronous = NORMAL;
