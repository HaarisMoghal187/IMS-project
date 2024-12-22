Coverage: 60.5%

# Inventory Management System by Haaris Moghal

This is an Inventory Management System Application, which was built using Java and can be run with a Command-Line Interface. This application connects to a database which allows the user to use CRUD(Create,Read,Update and Delete) functions for Customers, Items and Orders. 
## Table of contents

* [Prerequisites](https://github.com/QACTrainers/haarismoghalqa_assessment#Prerequisites)
* [Installing](https://github.com/QACTrainers/haarismoghalqa_assessment#Installing)
* [Deployment](https://github.com/QACTrainers/haarismoghalqa_assessment#Deployment)
* [Testing](https://github.com/QACTrainers/haarismoghalqa_assessment#Testing)
* [Creating-a-Jar-file](https://github.com/QACTrainers/haarismoghalqa_assessment#Creating-a-Jar-file)
* [Built-With](https://github.com/QACTrainers/haarismoghalqa_assessment#Built-With)
* [Jira-Integeration](https://github.com/QACTrainers/haarismoghalqa_assessment#Integeration)
* [Versioning](https://github.com/QACTrainers/haarismoghalqa_assessment#Versioning)
* [Authors](https://github.com/QACTrainers/haarismoghalqa_assessment#Authors)
* [License](https://github.com/QACTrainers/haarismoghalqa_assessment#License)
* [Acknowledgments](https://github.com/QACTrainers/haarismoghalqa_assessment#Acknowledgments)


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites


* [Java JRE 14.0.1](https://www.oracle.com/uk/java/technologies/javase/jdk14-archive-downloads.html)
* [Eclipse or other IDE](https://www.eclipse.org/downloads/)
* [MySQL](https://www.mysql.com/downloads/)
* [Git Bash](https://git-scm.com/downloads)
* [Maven](https://maven.apache.org/)
* [GCP](https://cloud.google.com/)

### Installing

1. You will need to clone down the repo
2. On your local machine, do a Git Bash in the folder destination you'd like to clone to. 
3. Clone the repo to your local system using the command "Git clone" and the Repository URL 
4. Launch the project in your IDE of choice.
5. set up JDBCconnection so that it connects to your database

## Deployment

To deploy this software to a cloud database such as Google Cloud Platform (GCP) you will need to do the following;

### Open the project

1. In the main folder ```'src/main/resources' Open > db.properties``` 
2. Scroll down until you see ``` db.url= ``` declaring your connection point
3. change the connection point to your desired Database location

## Testing

This application has been tested using JUnit testing. Mockito was used along with JUnit which allows you to create and configure mock objects. Using Mockito greatly simplifies the development of tests for classes with external dependencies.

## Integeration

Integrating jira and github together means that you are able to track the progress of completed work through jira via the use of smart commits.

Here is a link to my Jira board. [Link](https://haarismoghalims.atlassian.net/jira/software/projects/IMS/boards/1)

### Creating a Jar file

1. Choose or create a folder so that you can download the application.
2. Right click inside the folder.
3. Select Git Bash.
4. Write the following ```mvn clean package```
6. This will now run all of the applications testing, ensuring it all passes, you'll then get JAR file produced in the project target folder.

To deploy this software to a cloud database such as Google Cloud Platform (GCP) you will need to do the following;


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

* Version Control System: Git 
* Source Code Management: GitHub 
* Database Management System: MySQL Server 5.7 (local or GCP instance)
* [Maven Versioning](https://maven.apache.org/)

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Haaris Moghal** - *Using the intial work, Completed the implementation* -[HaarisMoghalQA](https://github.com/HaarismoghalQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* I would like to thank *[Nicholas Johnson](https://github.com/nickrstewarttds) - QA trainer *[Aswene Sivaraj](https://github.com/Ash-siv4)  - QA trainer

