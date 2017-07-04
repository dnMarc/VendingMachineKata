Greetings Pillar Artisan!
  Thank you for taking the time to review my Code Kata written in Java.
  I did not use any sort of software project management tool like Maven
  or Gradle.  In previous correspondence with a Pillar Artisan, I was 
  told that it is okay to provide directions for building my project 
  and running the tests that are specific to the Eclipse IDE.  I have
  provided those details below.  I wish to stress that I am happy to 
  furnish additional information if desired, please don’t hesitate to
  reach out to me.  I look forward to reviewing your feedback, and 
  thank you again for your consideration.

Directions for building the project:
•	From GitHub, download a zip file of the repository.
•	After opening Eclipse, click “File” from the main menu and select
        “Open Projects From File System”.
•	This should open a new window with an “Archive” button in the top
        right corner; click the Archive button.
•	Select the downloaded zip file.
•	This will bring you back to the previous dialog box, which may be 
        populated with two options.  Be sure to only select the option
        that displays “Eclipse project” under the “Import as” column.
•	Click the Finish button in the bottom right corner.
•	The project should now be built and ready for inspection.

Directions for running the tests:
•	I have created three classes containing JUnit Tests, they are:
    - VendingMachineTest.java
    - CoinControllerTest.java
    - IntegrationTests.java
•	Both VendingMachineTest and CoinControllerTest contain unit and
        regression tests for their respective production code classes.
•	IntegrationTests contains integration tests to verify behaviors 
        that span the VendingMachine and CoinController classes.
•	The tests in any one of these three files can be run by selecting
        the file, and then running the file as a JUnit Test.  For those 
        unfamiliar with Eclipse, this can be accomplished by first 
        opening the desired test class.  After the class has been opened,
        click “Run” from the main menu, select “Run As” and finally click
        “JUnit Test”.
•	I also created a test suite (AllTests.java) that executes the tests
        from all three of the test classes listed above.  The tests can be
        run through the test suite using the same steps described above.
