# Customer Management Application 1.0

This project contains a CLI customer management application along with
a JSP server. Please follow the instructions below to see how to
run tests, compile, run the programs, etc.

# Setting up the MySQL Server

Install the MySQL Server

Make sure to install the ODBC driver, J/Connector and MySQL Shell

Set the database user name to "root" and the password to "password"

In the customer-management-app directory, run the following command:

`mysqlsh -h localhost -u root -f create-customer-table.sql`

# Running the tests for the Customer Management Application

To run the tests for the Customer Management Application, in the
customer-management-app directory, run the following command:

`gradlew test`

Open build\reports\tests\test\index.html to view the test report

# Compiling and running the Customer Management Application

To compile the application, in the customer-management-app directory, run
the following command:

`gradlew bootJar`

This will produce a JAR in the directory build\libs

To run the application, run the following command:

`java -jar build\libs\customer-management-app-1.0-SNAPSHOT.jar`

# Compiling and running the JSP website

Go to the customerswebsite directory:

`cd customerswebsite`

To compile the application, run the following command:

`gradlew war`

This will produce a WAR in the directory build\libs

To start the GlassFish server, run the following command:

`glassfish6\glassfish\bin\startserv`

The server will start

Now, open a new Terminal, and cd to the customerswebsite directory

`cd customerswebsite`

You must now deploy the WAR file that was compiled earlier

Run the following command:

`glassfish6\glassfish\bin\asadmin deploy build\libs\customerswebsite-1.0-SNAPSHOT.war`

Now, you can go to localhost:8080/customerswebsite-1.0-SNAPSHOT/ in your browser
