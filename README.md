# java_postgresql

1. database setup
   create a new database (the name default in java is 'student_db')
   in the databse property, check the port (default : 5432)
   go to the query tool of the new database student_db
   import the student_db.sql in the db folder
   run once to initiate the default data

2. java setup
   the application is built on java 8
   compile the Main.java in the src/ folder 
   run the application, the program will call getAllStudents(), addStudent(), updateStudentEmail() deleteStudent() each once
