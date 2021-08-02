CREATE TABLE company (
	 id BIGINT primary key identity (1,1),
	 corporate_name_ VARCHAR (255) NOT NULL unique,
	 cnpj_ VARCHAR(255) NOT NULL unique,
	 creation_date_ DATETIME NOT NULL,
	 update_date_ DATETIME NULL
);

CREATE TABLE employee (
	id BIGINT primary key identity (1,1),
	name_ VARCHAR (255) NOT NULL unique,
	email_ VARCHAR (255) NOT NULL unique,
	password_ VARCHAR (255) NOT NULL,
	cpf_ VARCHAR(255) NOT NULL unique,
	hour_value_ BIGINT NULL,
	hours_per_day_ REAL NULL,
	hours_per_lunch_ REAL NULL, 
	profile_ VARCHAR (255) NOT NULL,
	creation_date_ DATETIME NOT NULL,
	update_date_ DATETIME NULL,
	company_id BIGINT NOT NULL,
	FOREIGN KEY (company_id) REFERENCES company (id)
	);

CREATE TABLE launch(
	id BIGINT primary key identity (1,1),
	date_ DATETIME NOT NULL,
	description_ VARCHAR(255) NULL,
	localization_ VARCHAR(255) NULL,
	creation_date_ DATETIME NOT NULL,
	update_date_ DATETIME NOT NULL,
	type_ VARCHAR(255) NOT NULL,
	employee_id BIGINT NOT NULL,
	FOREIGN KEY (employee_id) REFERENCES employee (id)
	)
