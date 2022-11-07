# CSU33012 Software Engineering Assignment Two

## Description of Application
This application is a calculator web application. The application takes in a user inputted string and ,if the expression is valid, returns the answer of that expression. The application is displayed on a sleek UI. The application allows for both simple(+,-,*,^,/) and complex expressions(ln,exp) to be inputted.

## Setup Instructions

### Running App from Dockerhub
  1. Pull docker image of web application using ```docker pull ciannamac/calc-image:v3.0.0.0``` .
  
  2. Run the docker image using ```docker run --name=calc-container --rm -d -p 8080:8080 ciannamac/calc-image:v3.0.0.0``` .
      - ```-p 8080:8080``` allows you to access our app on the web through this indicated port number
  
  3. Open your web browser and enter the following URL: ```localhost:8080/welcome``` .
  
  4. To stop running our app use the following: ``` docker stop calc-container``` .
     - This stops running the container and this will also delete the local container created from our image.
