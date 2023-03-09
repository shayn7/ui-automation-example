Introduction
This project is a sample UI automation project for google translate site


Requirements
Make sure you have java, maven, and allure installed in your system

Running the project
1. clone the project
2. open the terminal and navigate to the project location
3. run the command: mvn clean test (it will run the tests configured on testng.xml file by default)

You can also run the project with specific parameters if you wish to use different browsers, URLs, etc.
The parameters supported are: 
baseUrl - the base url to use
suiteXmlFile - the xml file of the desired tests (url_entries.xml, etc.)
browser - local_chrome, local_firefox, remote
hub - the remote URL (for example jenkins machine)

The command to use with any of the parameters above for example is:
mvn clean test -DbaseUrl=www.google.com -DsuiteXmlFile=url_entries.xml -Dbrowser=local_firefox -Dhub=192.0.0.5

In order to generate the report:
1. enter the terminal
2. navigate to the project location
3. enter the command: serve allure-results

