#####Ref: 4QTFHS150004
#####Company: Marquis Business and Technology Solutions
#####GSA Contract GS-35F-250CA 

The following instructions details the installation of the prototype on Linus Ubuntu 15.04

Install the dependencies
1.	Java Runtime Environment  (JRE)
2.	Apache Tomcat 

**Install Java Runtime Environment (JRE)**

The following steps will install OpenJRE Ubuntu. Installing Java with apt-get 
1.	Update the package index: sudo apt-get update
2.	Then, check if Java is not already installed: java –version
3.	If it returns "The program java can be found in the following packages", Java hasn't been installed yet, so execute the following command: sudo apt-get install default-jre. This will install the Java Runtime Environment (JRE). 
4.	Set the "JAVA_HOME" environment variable in, sudo nano /etc/environment JAVA_HOME= /usr/lib/jvm/java-8-oracle
5.	Reload this file: source /etc/environment
6.	Test it by executing: echo $JAVA_HOME

**Install Apache Tomcat**

The following steps will install Apache Tomcat on the Linux server>
1.	Update your apt-get package lists: sudo apt-get update
2.	Run the following command to start the installation: sudo apt-get install tomcat7. This will install Tomcat and its dependencies and it will also create the tomcat7 user. It also starts Tomcat with its default settings.
3.	Check that Tomcat has been installed and running by accessing the default splash page by going to the domain: [http://server_IP_address:8080](http://server_IP_address:8080)

**Install Prototype**

The following steps will install the prototype
1.	Cut directories under [tomcat install dir]/webapps/ROOT
2.	Cut directories MarsGov and FDARESTful under [tomcat install dir]/webapps/
3.	Paste FDARESTful.war and MarsGov.war to [tomcat install dir]/webapps from /tmp
4.	Restart tomcat service: sudo service tomcat7 restart
5.	Copy [tomcat install dir]/webapps/MarsGov to [tomcat install dir]/webapps/ROOT sudo cp -r [tomcat install dir]/webapps/MarsGov/.  [tomcat install dir]/webapps/ROOT
6.	Restart tomcat service: sudo service tomcat7 restart
