<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/slotap/PandemiaSimulator">
    <img src="https://github.com/slotap/PandemiaSimulator/blob/master/src/main/resources/owl_logo-02_1x.jpg" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Pandemia Simulator</h3>

  <p align="center">
     Application enables you to create and manage simulations of Pandemic using custom factors
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#Functionalities">Functionalities</a></li>
        <li><a href="# To implement"> To implement</a></li>
      </ul>
    </li>
    <li><a href="#Code">Code</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

![Simulation Screenshot](https://snipboard.io/9SdnJM.jpg)


The simulation represents a population  where an entity can have a state of either healthy, infected, recovered or dead. There is an accompanying chart that keeps track of the states of the entities.

The simulation is interactive and allows you to select different  measures to change the spread of the virus like an amount of days after which infected person is either healed or dead, mortality index, reproduction number.

This app is currently under development , not all functionalities are yet implemented.





### Built With

* Maven
* Spring-Boot[]()
* MySQL[]()
* Hibernate []()
* JUnit []()
* Mockito[]()
* Angular[]()




<!-- GETTING STARTED -->
## Getting Started



### Functionalities

1. Application has a form that takes initial simulation data, upon submitting the Simulation will be saved in a database, processed data will be returned and displayed in a reactive chart.

2. By changing values in Sliders module, data will be recalculated and chart will be updated.

3. All saved simulations are accessible and can be modified or deleted.

4. There is a Feedback option which enables user to send an email to admin.

### To implement

1. Implement sliders to update charts (Angular)

2.  Implement get all and delete simulations functionality (Angular)

3. Implement Feedback (Angular, SpringBoot)

<!-- USAGE EXAMPLES -->
## Code

Backend application is available at https://github.com/slotap/PandemiaSimulator 

Frontend application is availabe at https://github.com/slotap/pandemia-ng

