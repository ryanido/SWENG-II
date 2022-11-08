# CSU33012 Software Engineering Assignment Two

## Description of Application
This application is a calculator web application. The application takes in a user inputted string and ,if the expression is valid, returns the answer of that expression. The application is displayed on a sleek UI. The application allows for both simple(+,-,*,^,/) and complex expressions(ln,exp) to be inputted.

## Setup Instructions

### Running App from Dockerhub
  1. Pull docker image of web application using ```docker pull ciannamac/calc-image:v3.0.0.0``` .
  
  2. Run the docker image using ```docker run --name=calc-container --rm -d -p 8080:8080 ciannamac/calc-image:v3.0.0.0``` .
      - ```-p 8080:8080``` allows you to access our api on the web through this indicated port number
  
  3. Clone this github repository on your local machine
  
  4. Open the terminal in the repository and enter ```cd src/my-app``` to locate the frontend files
  
  5. Run ```npm install``` to install the frontend dependencies
  
  6. Run ```npm start``` to run the frontend of our application
  
  7. Open your web browser and enter the following URL: ```localhost:3000``` .
  
  8. To stop running our backend api use the following: ``` docker stop calc-container``` .
     - This stops running the container and this will also delete the local container created from our image.
     
  9. To stop running the frontend of our application press ctrl+c in the terminal
      - This will terminate the react application
