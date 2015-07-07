## Pool 2: Development Pool

[**Marquis Business and Technology Solutions**](http://mbtsllc.com/) is pleased to present The Agile Project prototype to the GSA.

**Prototype Website**: [**mbtsllc.cloudapp.net:8080**](mbtsllc.cloudapp.net:8080)

**GitHub Project Website**: [**https://github.com/mbtsllc/Agile**](https://github.com/mbtsllc/Agile)

#### 1. Approach

From the initiation of this development project, the Marquis team have followed the US Digital Service Playbook as closely as possible. The technology, design, infrastructure and development methodology utilized in this project follow the rules stated in the US Digital Service Playbook.

Proven and reliable open source technology is used for the development of the prototype. All the software used is modern and is currently in use in both the commercial and government sectors.Where possible, we have automated parts of the development process including some testing, build and deployment of the software. The prototype is deployed on Microsoft Azure.

#### 2. Development Team

The team involved in the design, development and testing of the prototype consist of the following categories:

1. Technical Architect (Category 2)
2. Front End Developer (Category 6)
3. DevOps Engineer (Category 8)
4. Quality Assurance Expert

The Maquis team fulfills the GSA's requirement of assembling a multidisciplinary and collaborative team that includes at a minimum two of the categories from the development pool.

#### 3. Open Source Software

A list of Open Source Software used along with their puprpose, version and licensing can be found in the [List-of-Open-Source-Software-used.md file](https://github.com/mbtsllc/Agile/blob/master/List-of-Open-Source-Software-used.md)

##### 3.1 Open Source Standards

The following Open Source protocols and formats were used to implement the protoype:

1.	HTTP
2.	JSON
3.	AJAX
4.	RESTful

#### 4. Application Design

The architecture of the prototype consists of four distinct layers. They are described below.

![Figure 1: Application design - Prototype Layers](http://)

##### 4.1 View Layer

JavaServer Pages (JSP) is implemented as the view component of a server-side model-view-controller design. JQuery UI provides the GUI widgets used in the prototype.

##### 4.2 Control Layer

The Apache Struts 2 forms the control layer of the prototype designed

##### 4.3 Model Layer

The Model layers consists of three main components: the Format Converter, the Search Adapter and the Data Retriever. The Format Convertor formats and maps data between the data and the view layer. The search adaptor takes the search criteria from the GUI and creates a query for the Data retriever to execute. The data retriever communicates with the data layer. It sends queries to the data layer and receives results from the data layer.

##### 4.4 Data Layer

The Open.FDA.gov forms as the data layer for the prototype. The website receives a query from the Data Retriever and returns the results.

#### 5. Development Software and Infrastructure

##### 5.1 Platform as a Service (PaaS)

The prototype is deployed on Microsoft Azure Cloud Services. A virtual machhine was configured with Linux and the above open source software was installed. The Microsoft Azure portal allows users to change the configuration of the virtual machine, such as CPU type and RAM, with little or no downtime. Each functional parameter of the virtual machine, such as CPU usage, disk read/writes and Network input/output, can be monitored from the portal. We configured the virtual machine  with Linux Ubuntu v15.04.

##### 5.2 Continuous Integration System

Jenkins ([https://jenkins-ci.org/](https://jenkins-ci.org/)) is an open source software widely used for automatically building testing and deployment software. For this project, Jenkins was installed and configured to automatically checkout source code from GitHub, compile and build the application and deploy it into the Microsoft Azure Cloud Sevices.

![Figure 2: Implementation of Jenkins continuous integration system](http://)

##### 5.3 Containerization

Docker ([www.docker.com](https://www.docker.com)) was used as a container to distrbute the application on test machine and on the PaaS cloud.

##### 5.4 Configuration Management

The Marquis team utilized Chef [www.chef.io](http://www.chef.io) for provisioning our test virtual box. Chef treats infrastructure as code and allow us to provision and configue the VM from the Chef server.

![](http://)
![](http://)
![](http://)
![Figure 3: Implementation of Docker and Chef](http://)

##### 5.5 Development, Build and Deployment Flow

The diagram below shows the high-level process flow from development to deployment.

![Figure 4: Development, Build and Deployment Flow](http://)

#### Installation

Detailed instructions for installation can be found in the [Intallation-Instructions.md file.](https://github.com/mbtsllc/Agile/blob/master/Installation-Instructions.md)

#### Unit Testing

The JUnit test framework is incorporated within the prototype design to assist with the testing. Information on testing can be found in the [Test.md file.](https://github.com/mbtsllc/Agile/blob/master/Test.md)

















