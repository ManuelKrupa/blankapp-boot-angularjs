# blankapp-boot-angularjs
Blank Application developped with Spring Boot and Angular JS

This blank application goal is to provide a template for new developments on a similar stack.
To increase time to market, some features have already been implemented :
* Authentication
* User Management
* Profile Management
* i18n

The web/frontoffice side of this blank application is the UI layer and has been developped with :
* AngularJS
* Bootstrap
* Karma                                                          
* Jasmine
* Phantom JS

The backoffice side of this blank application exposed REST services and has been developped with :
* Java
* Spring Boot
* Spring Data JPA
* HSQLDB

Industrialization has been done with Maven for backoffice side and Yeoman/Grunt/Bower for the frontoffice one.


So, to play with this blankapp, you have to install :
* Java (http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)
* Maven (https://maven.apache.org/download.cgi)
* NodeJS (https://nodejs.org/en/)
* Yeoman (npm install -g yo)
* Bower (npm install -g bower)
* Grunt (npm install -g grunt-cli)                

To verify that all is well configured, simply test with Maven command "mvn clean install" in the root project folder

Then, you can lauch the blank application with :
* "mvn jetty:run" in "/blankapp-backoffice" folder
* "grunt serve" in "/blankapp-web/blankapp" folder