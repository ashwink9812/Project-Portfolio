### About The Project

Created a reusable VBA macro in excel to chart deterioration of road pavement quality over time in accordance with government 508 compliance guidelines.
Analyzed historical climate data from AASHTOWare Pavement Design Software to identify areas in the state of Mississippi most susceptible to thermal cracking. 

### How it works

The macro searches for the filenames (listed in column E) located in the path on your drive (listed in F1) and retrieves relevant cracking data (Columns G,H, etc.). It then plots the data in a line graph (axes, title, scale are specified in column B). VBA code can be viewed by the Developer tab > Visual Basic.

### How to use it

* I've included three folders (fake_canton, fake_memphis, fake_vancleave) that will need to be saved to your hard drive.
* Copy the path where the folders are saved and insert into cell F1
* Set parameters as you please in column B
* Click the blue "Collect and Graph: button to generate a 508 compliant line graph that compares thermal cracking in the cities over time. 



#### Example Graph:

![image](https://user-images.githubusercontent.com/98720733/151862799-dd5d5f7d-13a9-462d-aaea-228af0d759f4.png)
