Tomcat listener will abort Tomcat process when a webapp fail to deploy
=========================
 [![Build Status](https://travis-ci.org/yohayg/tomcat-deployment-listener.svg?branch=master)](https://travis-ci.org/yohayg/tomcat-deployment-listener)
 [![Coverage Status](https://coveralls.io/repos/github/yohayg/tomcat-deployment-listener/badge.svg?branch=master)](https://coveralls.io/github/yohayg/tomcat-deployment-listener?branch=master) 
 [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
 [![Maintainability](https://api.codeclimate.com/v1/badges/ceb43a5678ffe6d7e1de/maintainability)](https://codeclimate.com/github/yohayg/tomcat-deployment-listener/maintainability)
 [![Docker Automated build](https://img.shields.io/docker/automated/jrottenberg/ffmpeg.svg)](https://hub.docker.com/r/yohayg/tomcat-deployment-listener/)


Description
-----------

According to Docker best practices a Docker container should not be up if the process fails to load properly.

Apache Tomcat deploys all the web applications loaded in its webapp folder. This is a resilient solution for web
applications not influencing the application server.

However, In a Docker environment according to best practice, the process should fail in case the web application
failed to initialize properly.

By adding the additional jar created by this project to Tomcat classpath and adding the new listener in the Tomcat
conf/server.xml the user can make sure the Tomcat process fails in case there is an initialization exception in
their web application which will cause the Tomcat used in the tomcat container to abort, and immediately indicate
that there was an initialization error.


Usage
-----

Mount the jar in the tomcat classpath and add to server.xml:

    <Listener className="com.marketo.rtp.server.web.StrictStateCheckListener" />


Example
-------
See Dockerfile:

build:


    docker build -t yohayg/tomcat-deployment-listener:1.0.0 .

run:

    docker run -it --rm -p 8080:8080  yohayg/tomcat-deployment-listener:1.0.0


License
-------
This project is licensed under [MIT license](http://opensource.org/licenses/MIT).
