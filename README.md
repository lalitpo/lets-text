<a name="readme-top"></a>

[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<div style="text-align: center;">
  <h3 align="center">Let's Text</h3>
  <p align="center">
    A backend of a messaging application.
    <br />
    <a href="https://github.com/lalitpo/lets-text/issues">Report Bug</a> 
  </p>
</div>


<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li><a href="#built-with">Built With</a></li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

The project is a backend of a messaging application. It sends messages from one user to another user. 
 
Below functionalities are implemented in the project:
1. As a non-user, I can create my account by providing my nickname (nicknames should be unique)
2. As a user, I can send a message to another user identified by his id you cannot send a message to yourself
3. As a user, I can view all messages that I received
4. As a user, I can view all messages that I sent
5. As a user, I can view all messages received from a particular user

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Built With

[![My Skills](https://skillicons.dev/icons?i=java)](https://www.oracle.com/java/)
[![My Skills](https://skillicons.dev/icons?i=spring)](https://www.spring.io/)
[![My Skills](https://skillicons.dev/icons?i=postgres)](https://www.postgresql.org/)
[![My Skills](https://skillicons.dev/icons?i=kafka)](https://kafka.apache.org/)
[![My Skills](https://skillicons.dev/icons?i=docker)](https://www.docker.com/)
[![My Skills](https://skillicons.dev/icons?i=maven)](https://maven.apache.org/)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started
To correctly import and run this project locally, please follow below guidelines and instructions for smooth development process.

### Prerequisites
As mentioned above in the "Built with" section, please have Java installed on your system.
You can use [homebrew](https://brew.sh) or straightforward Installation from their respective website's homepage.

You can use any IDE like [IntelliJ](https://www.jetbrains.com/idea/) [VS Code](https://code.visualstudio.com), etc.

### Installation

Below is an example of how you can set up the project on your local machine.

1. In your favourite IDE, import/clone the project.
2. Build the project using the following command

    ```
    mvn clean install
    ```
3. Run docker containers for PostgreSQL database and Kafka Messaging Queue system.
   Docker containers for each of them are configured in docker-compose.yml file at
   lets-text-infra/docker/docker-compose.yml location.

    ```
    docker-compose -f lets-text-infra/docker/docker-compose.yml up
    ```

4. Install [PostgreSQL](https://www.postgresql.org) on your machine for the database. You don't need an altogether a
   different UI to run queries because your IDE(IntelliJ, VS Code, etc.) will directly give you plugins to access them
   directly from the IDE.
   However, in case, you want a separate UI for it, use [pgAdmin](https://www.pgadmin.org)

5. Run the application by running file : LetsTextAppApplication.java located at
   lets-text-app/src/main/java/com/vistext/letstextapp/LetsTextAppApplication.java

   1. The application will be running on port 8080. You can access the application using the following URL:

       ```
       http://localhost:8080
       ```

   2. In your favourite REST API client, like [Postman](https://www.postman.com/), you can test below REST APIs to test
      the functionalities of the application.
   3. Below are the sample requests for the functionalities using userNickname as 'jd1' and receiverId as 'jd2'. You can
      replace these values with the values you want to test.

           ```
           POST http://localhost:8080/users/create
           {
               "userNickName": "jd1",
               "userName": "John Doe2",
               "userPhoneNumber": 9764066736
           }
           ```
           ```
           ```
           POST http://localhost:8080/messages/sendMessage
           {
               "senderId": "jd1",
               "receiverId": "jd2",
               "messageContent": "Hello. How are you?",
               "messageSentTime": "2024-03-26T10:16:35Z"
           }
           ```
           ```
           GET http://localhost:8080/messages/allsent/jd1
           ```
           ```
           GET http://localhost:8080/messages/allreceived/jd2
           ```
           ```
           GET http://localhost:8080/messages/received/jd1?receiverUserId=jd3
           ```

6. Kafka is enabled in the application with its related configuration in application.properties file at
   lets-text-app/src/main/resources/application.properties location.
   All the sent messages through the REST API above are logged in the application console logs when consumed by the
   kafka consumer.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Contributions

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/feature-name`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Contact
### Developer :
[Lalit Poddar](https://www.linkedin.com/in/lalit-poddar/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links --> 
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/lalit-poddar/
[Python]: https://www.python.org/static/img/python-logo@2x.png
[python-url]: https://www.python.org/
[R]: https://www.r-project.org/Rlogo.png
[R-url]: https://www.r-project.org/
[Matlab]: https://play-lh.googleusercontent.com/UB0D2bAS6M4gGtaXPbhD8zK6bRrw_KkTeNMuZ_fkx32WC_OPPeQcKmH7AiID41xDc2k=w480-h960
[matlab-url]: https://in.mathworks.com/products/matlab.html/

