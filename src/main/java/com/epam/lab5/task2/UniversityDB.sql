CREATE database IF NOT EXISTS university;

USE university;

#DROP DATABASE university;

CREATE TABLE IF NOT EXISTS address(
	address_id int primary key auto_increment,
	country varchar(20),
    region varchar(20),
    city varchar(25) not null,
    street varchar(100) not null,
    building varchar(100) not null,
    flat varchar(30)
);

CREATE TABLE IF NOT EXISTS cathedra(
	cathedra_id int primary key auto_increment,
    cathedra_name varchar(100) not null,
	cathedra_email varchar(50) not null,
    cathedra_phone varchar(15) not null,
    cathedra_description varchar(500),
    cathedra_address int,
    foreign key (cathedra_address) references address(address_id)
		on update cascade 
        on delete cascade
);

CREATE TABLE IF NOT EXISTS student(
	student_id int primary key unique auto_increment,
    student_name varchar(20) not null,
    student_surname varchar(30) not null,
    student_gender varchar(6) not null,
    student_birth_day varchar(10),
    student_phone varchar(15) not null,
    student_exam_book_number int,
    student_start_day varchar(10),
    student_study_form varchar(10) not null,
    student_course int not null,
    student_address int not null,
    cathedra_id int not null,
    foreign key (student_address) references address(address_id)
    	on update cascade 
        on delete cascade,
    foreign key (cathedra_id) references cathedra(cathedra_id)
    	on update cascade 
        on delete cascade
);

CREATE TABLE IF NOT EXISTS subject(
	subject_id int primary key unique auto_increment,
    subject_name varchar(100) not null,
    subject_description varchar(255),
    cathedra_id int not null,
    foreign key (cathedra_id) references cathedra(cathedra_id)
		on update cascade 
        on delete cascade
);
