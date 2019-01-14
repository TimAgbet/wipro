This project was created using Maven.


To build
----------
mvn clean build

To package and run tests
-------------------------
mvn package

To Run in Intellij
-------------------
You can run the jar from the command line:
It takes parameters, 1st and second
i.e.:
testfile.txt http://www.wipro.com


If more time
-------------
As discussed, I have limited time 1.5hrs, so this is tbe basic draft.
More unit tests should be completed especially testing the "process" method as thats the main part using mocks.

Also giving the option of how many layers deep the links should go as it already does too much by default.

Removing the System.out messages and add a proper logger.