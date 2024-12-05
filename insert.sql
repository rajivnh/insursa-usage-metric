drop table usage_matric;

create table usage_matric (
    id int NOT NULL AUTO_INCREMENT,
    rate_definition_id varchar(100) NOT NULL,	 
    company_id varchar(100) NOT NULL,	
    created_date timestamp DEFAULT (CURRENT_DATE),
    primary key(id)
);


