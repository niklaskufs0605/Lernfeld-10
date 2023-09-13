#!/bin/bash

#Start MySQL Server
brew services start mysql

sleep 5

#Start Java-API
java -jar WaterMeterAPI.jar

sleep 5

#Start Webserver
python3 -m http.server 8000
