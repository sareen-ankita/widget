QA Challenge 

Introduction:  

Based on the QA challenge provided by Ceri Pritchard. I have successfully written a program in Java using Eclipse IDE and SQLite(database) for Widget Ltd company to produce a number of reports based around Departments and Employees. This is stored in GIT repository 

https://github.com/sareen-ankita/widget.git 

Main features:  

Database file Creation in SQL lite tool where data records for Employees and Department are added for Widget company. 

The database file and tables will be created by program. 

Database file structure created from the code is as follows: 

--> Records. dB (Main database file) 

--> Table: Departments  

--> Table: Employees  

Note: Records will be added to these tables by the Program. A snapshot of database records can be found in "Introductions and Solution Document". 

User can fetch records based on the following requirements. 

 
Requirement 1: For a specified Department Id, output the following columns: 

● Employee Id 

● Employee Name 

● Job Title 

● Salary 


Requirement 2: For a specified Location, output the following columns: 

● Employee Id 

● Employee Name 

● Job Title 

● Salary 

● Department Name 

User can extract report for Employees in department List. 

Records will be deleted once the operation is complete as requested for GDPR. 
***********************************************************************************************************************
Pre requisites - 

Eclipse Editor 

SQLite Database: 

(Instructions to download in the link below) 

https://www.sqlitetutorial.net/download-install-sqlite/ 

https://www.sqlite.org/download.html 

Please Download the SQLite binaries depending on windows (32–64-bit OS). 

Java (JDK1.8 version) 

Permission Access to the C: and D: drive in your local machine – to create table folders and check extract outputs (in case if any of them is not available you may have to amend the path in code) 

Access to Git hub repository: https://github.com/sareen-ankita/widget.git 

 
**************************************************************************************************************
Program Work Flow 

Step 1: The code successfully run and creates the Database in C:\sqlite\db\records.db--> Name of the Main database file 

Step 2: Creates 2 tables called – Employees and Departments. These will be created automatically by the program once the software is installed and code is executed successfully. 

Step 3: The program at execution will ask for input from user to enter location and Department id to fetch records and generate reports based on user’s input on the program console. 
The program will also show the choices to select the user’s input. 
Once user chooses the input the records will be fetched and user will be asked again whether a report needs to be generated. 
If they select ‘Y’ - yes then extract output.csv will be created  

Step 4: User will then be asked to enter Department id (allowed values 1-4) 
Please note: I have only created CSV extract functionality for Employee by Location query. 

Step 5: After the successful execution, all records will be deleted. 
This completes the working and execution of the program. 

*********************************************************************************************************************************** 

Test scenarios that are considered while creating and executing the code are as follows. 

--> Check whether database file and tables are created successfully. 

--> Check whether data is inserted into tables correctly. 

--> Check user is able to select the location and department id from the command prompt with the correct input. 

--> Check if user enters invalid values for location and department id then user should get error message – cannot retrieve the records – records does not exist in the system and no records should be fetched. 

--> Check user is able to extract report on Employee by location query and is in .CSV format 

--> Check if user selects not to extract report then Output file should not be created or override with new set of data if Output file already exist. 

--> Check records are deleted from tables and tables are empty once the operation is complete. 

--> Check if we repeat the steps again new records are created and all functionality works as expected. 

Test Outcomes: All the above scenarios have been considered while developing the code. 

*Please note Boundary value test of input fields were considered but I did not develop the logic in database.
************************************************************************************************************************************
 

 

 

 
