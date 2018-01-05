/*************************************************
** drops database if it hasn't been created yet **
*************************************************/

drop user trms_user cascade;

/*********************
** creates database **
*********************/

CREATE USER trms_user
IDENTIFIED BY trmsp4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect to trms_user;
GRANT resource to trms_user;
GRANT create session TO trms_user;
GRANT create table TO trms_user;
GRANT create view TO trms_user;

conn trms_user/trmsp4ssw0rd;


/*******************
** creates tables **
*******************/

create table Employee
(
    EmployeeID number not null,
    FirstName varchar2(20) not null,
    LastName varchar2(20) not null,
    EmpPassword varchar2(20) not null,
    Email varchar2(100) not null unique,
    TitleID number not null,
    AvailableReimbursement number default 1000,
    DepartmentHead number,
    Supervisor number,
    constraint PK_Employee primary key (EmployeeID)
);

create table Title
(
    TitleID number not null,
    TitleName varchar2(30) not null,
    constraint PK_Title primary key (TitleID)
);

create table Reimbursement
(
    ReimbursementID number not null,
    TypeID number not null,
    CostTotal number not null,
    StatusID number not null,
    EmployeeID number not null,
    Information varchar2(100) not null,
    SubmitDate date not null,
    EventLocation varchar2(100) not null,
    FinalTime date,
    FinalGrade varchar2(10),
    FormatID number,
    DenialReason varchar2(100),
    constraint PK_Reimbursement primary key (ReimbursementID)
);

create table EventType
(
    TypeID number not null,
    TypeDesc varchar(50) not null,
    Coverage number not null,
    constraint PK_Type primary key (TypeID)
);

create table Status
(
    StatusID number not null,
    StatusDesc varchar(50) not null,
    constraint PK_Status primary key (StatusID)
);

create table GradeFormat
(
    FormatID number not null,
    GradeFormat varchar(50) not null,
    constraint PK_GradeFormat primary key (FormatID)
);

/*******************************
** adds constraints to tables **
*******************************/

alter table trms_user.Reimbursement 
add constraint FK_ReimbursementTypeID
FOREIGN KEY (TypeID) 
REFERENCES trms_user.EventType (TypeID);

alter table trms_user.Reimbursement 
add constraint FK_ReimbursementEmployeeID
FOREIGN KEY (EmployeeID) 
REFERENCES trms_user.Employee (EmployeeID);

alter table trms_user.Reimbursement 
add constraint FK_ReimbursementStatusID
FOREIGN KEY (StatusID) 
REFERENCES trms_user.Status (StatusID);

alter table trms_user.Reimbursement 
add constraint FK_ReimbursementFormatID
FOREIGN KEY (FormatID) 
REFERENCES trms_user.GradeFormat (FormatID);

alter table trms_user.Employee 
add constraint FK_EmployeeTitleID
FOREIGN KEY (TitleID) 
REFERENCES trms_user.Title (TitleID);

alter table trms_user.Employee 
add constraint FK_EmployeeDepartmentHead
FOREIGN KEY (DepartmentHead) 
REFERENCES trms_user.Employee (EmployeeID);

alter table trms_user.Employee 
add constraint FK_EmployeeSupervisor
FOREIGN KEY (Supervisor) 
REFERENCES trms_user.Employee (EmployeeID);

/**********************
** creates sequences **
**********************/
create sequence SQ_Reim_PK 
start with 1
increment by 1;

create or replace trigger TR_Insert_Reim 
before insert on trms_user.Reimbursement 
for each row

begin
  select SQ_Reim_PK.NEXTVAL
  into   :new.ReimbursementID
  from   dual;
end;
/
/******************************
** creates stored procedures **
******************************/
create or replace procedure
SP_Add_Reimbursement(R_TYPE in number, R_COST in number, R_EID in number, R_INFO in varchar2, R_LOCATION in varchar2) as
begin
    if true then
        DBMS_OUTPUT.PUT_LINE('VALID DATA');
    else
        DBMS_OUTPUT.PUT_LINE('INVALID DATA');
    end if;
    
    savepoint do_insert;
    
    insert into trms_user.Reimbursement
    (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
    values
    (R_TYPE, R_COST, 1, R_EID, R_INFO, SYSDATE, R_LOCATION, (null), 'NA', (null), 'NA');
        
    DBMS_OUTPUT.PUT_LINE('SUCCESS');
    commit;
    
    exception
        when others
        then DBMS_OUTPUT.PUT_LINE('FAILURE');
        rollback to do_insert;
end;
/

create or replace procedure
SP_Approve_Status(R_ID in number) as
begin
    if true then
        DBMS_OUTPUT.PUT_LINE('VALID DATA');
    else
        DBMS_OUTPUT.PUT_LINE('INVALID DATA');
    end if;
    
    savepoint do_change;
        
    update Reimbursement
    set StatusID = (StatusID + 1)
    where ReimbursementID = R_ID;


    commit;
    
    exception
        when others
        then DBMS_OUTPUT.PUT_LINE('FAILURE');
        rollback to do_change;
end;
/

create or replace procedure
SP_Deny_Status(R_ID in number) as
begin
    if true then
        DBMS_OUTPUT.PUT_LINE('VALID DATA');
    else
        DBMS_OUTPUT.PUT_LINE('INVALID DATA');
    end if;
    
    savepoint do_change;
        
    update Reimbursement
    set StatusID = 6
    where ReimbursementID = R_ID; 
        
    commit;
    exception
        when others
        then DBMS_OUTPUT.PUT_LINE('FAILURE');
        rollback to do_change;
end;
/
/*************************
** populates the tables **
**************************/

insert into trms_user.EventType values (1, 'University Courses', .8);
insert into trms_user.EventType values (2, 'Seminars', .6);
insert into trms_user.EventType values (3, 'Certification Preparation Classes', .75);
insert into trms_user.EventType values (4, 'Certification', 1);
insert into trms_user.EventType values (5, 'Technical Training', .9);
insert into trms_user.EventType values (6, 'Other', .3);

insert into trms_user.Title values (1, 'Department Head');
insert into trms_user.Title values (2, 'Benefits Coordinator');
insert into trms_user.Title values (3, 'Employee');
insert into trms_user.Title values (4, 'Direct Supervisor');

insert into trms_user.Status values (1, 'Submitted');
insert into trms_user.Status values (2, 'Pending');
insert into trms_user.Status values (3, 'Pending - Approved by Direct Supervisor');
insert into trms_user.Status values (4, 'Pending - Approved by Department Head');
insert into trms_user.Status values (5, 'Approved');
insert into trms_user.Status values (6, 'Denied');

insert into trms_user.GradeFormat values (1, 'GPA');
insert into trms_user.GradeFormat values (2, 'Out of 100');

insert into trms_user.Employee values (1, 'Jake', 'Johnson', 'pa55word', 'jjohnson@trms.com', 1, 1000, (null), (null));
insert into trms_user.Employee values (2, 'Janet', 'Jameson', 'default', 'jjameson@trms.com', 2, 1000, (null), (null));
insert into trms_user.Employee values (4, 'John', 'Jakeson', 'mypassw07d', 'jjakeson@trms.com', 4, 1000, 1, (null));
insert into trms_user.Employee values (3, 'Jonah', 'Jonas', '9455w0rd', 'jjonas@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (5, 'Jim', 'Juice', 'koolaidm4n', 'jjuice@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (6, 'Janise', 'Jefferson', 'securepassword', 'jjefferson@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (7, 'Jeff', 'Josephs', 'lollipop', 'jjosephs@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (8, 'Jessica', 'Johns', 'l0lip0pe', 'jjohns@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (9, 'Jolene', 'Jenkins', 'gherkin', 'jjenkins@trms.com', 3, 1000, 1, 4);
insert into trms_user.Employee values (10, 'Julia', 'Johnston', 'sauce6055', 'jjohnston@trms.com', 3, 1000, 1, 4);

insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason) 
values (4, 19.99, 1, 3, 'Certification', SYSDATE, 'The Crab Shack', SYSDATE, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (4, 19.99, 2, 4, 'Certification', SYSDATE, 'The Crab Shack', SYSDATE, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (1, 19.99, 3, 5, 'How To Eat Crabs class', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 4, 6, 'Crab De-shelling Course', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 3, 6, 'Example', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 2, 6, 'Example2', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 5, 6, 'Example3', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 3, 6, 'Example4', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');
insert into trms_user.Reimbursement (TypeID, CostTotal, StatusID, EmployeeID, Information, SubmitDate, EventLocation, FinalTime, FinalGrade, FormatID, DenialReason)
values (5, 19.99, 4, 6, 'Example5', CURRENT_TIMESTAMP, 'The Crab Shack', CURRENT_TIMESTAMP, '90.99', 2, 'NA');

SELECT * FROM trms_user.Reimbursement;