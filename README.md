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
 

<p align="right">(<a href="#readme-top">back to top</a>)</p>



## Built With

[![My Skills](https://skillicons.dev/icons?i=java)](https://www.python.org/)
[![My Skills](https://skillicons.dev/icons?i=spring)](https://www.spring.io/)
[![My Skills](https://skillicons.dev/icons?i=postgres)](https://www.postgresql.org/)
[![My Skills](https://skillicons.dev/icons?i=kafka)](https://kafka.apache.org/)



<p align="right">(<a href="#readme-top">back to top</a>)</p>



## Getting Started
To correctly import and run this project locally, please follow below guidelines and instructions for smooth development process.

### Prerequisites
As mentioned above in the "Built with" section, please have Java installed on your system.
You can use [homebrew](https://brew.sh) or straightforward Installation for both [Python](https://www.python.org/) as mentioned on their homepage.

MATLAB comes with its own tool called [MATLAB](https://matlab.mathworks.com) which needs to be a licensed version.
You can have it for free if you're registered with a University or your workplace has its licensed copy.

Python can be run and programmed on any IDE like [IntelliJ](https://www.jetbrains.com/idea/), [PyCharm](https://www.jetbrains.com/pycharm/), [VS Code](https://code.visualstudio.com), etc.

### Installation

Below is an example of how you can set up the project on your local machine.

1. For python packages and libraries, refer to the requirements.txt to install all the required packages.

   Note : psycopg2 is required to connect to the PostgreSQL database. However, psycopg2 is not available anymore and you will find compile time error.
   Please install psycopg2-binary instead using pip/pip3 command.

    ```
    pip3 install psycopg2-binary
    ```

2. Install [PostgreSQL](https://www.postgresql.org) on your machine for the database. You don't need an altogether a different UI to run queries because your IDE(IntelliJ, VS Code, etc.) will directly give you plugins to access them directly from the IDE.
   However, in case, you want a separate UI for it, use [pgAdmin](https://www.pgadmin.org)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

1. The athlete ID for which the biking ride activities needs to be collected, must be added in resources/pro-athlete-id.txt
2. The year and week number for which the data needs to be collected must be added in resources/application-config.properties
3. The database credentials must be added in resources/db-config.properties
4. The credentials for the user login to Strava must be added in resources/strava-config.properties.
4. Run the application using the following command

    ```
    python3 DataCollectRunner.py
    ```

5. The model are present in models folder in HR_Model.mlx which needs to be run in MATLAB.
6. Model results can be analysed on MATLAB or src/services/ModelAnalysis.ipynb

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

