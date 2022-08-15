#!/bin/sh
docker run -d -p 9090:8080 --name=jenkinscicd \
-v /home/$USER/jenkins:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \
sjseo85/jenkins
