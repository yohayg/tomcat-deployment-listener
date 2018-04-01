Tomcat listener will abort Tomcat process when a webapp fail to deploy
=========================
 [![Build Status](https://travis-ci.org/yohayg/tomcat-deployment-listener.svg?branch=master)](https://travis-ci.org/yohayg/tomcat-deployment-listener)
 [![Coverage Status](https://coveralls.io/repos/github/yohayg/tomcat-deployment-listener/badge.svg?branch=master)](https://coveralls.io/github/yohayg/tomcat-deployment-listener?branch=master) 

Description
-----------

According to Docker best practices a Docker container should not be up if the process fails to load properly.

Apache Tomcat deploys all the web applications loaded in its webapp folder. This is a resilient solution for web
applications not influencing the application server.

However, In a Docker environment according to best practice, the process should fail in case the web application
failed to initialize properly.

By adding the additional jar created by this project to Tomcat classpath and adding the new listener in the Tomcat
conf/server.xml the user can make sure the Tomcat process fails in case there is an initialization exception in
their web application which will cause the Tomcat used the tomcat container to abort in immediately indicate
that there was an initialization error
Abort tomcat when a webapp fails to deploy in Docker container
Docker container best practice. Tomcat does not stop if a webapp fail to deploy and the container will continue
to run.
By adding this listener the tomcat will abort in case the Tomcat failes to deploy the webapp,
And the container will not start.
You will need to place the listener in Tomcat server.xml and set the Tomcat classpath to contain this jar


Usage
-----

Mount the jar in the tomcat classpath and add to server.xml:

    <Listener className="com.marketo.rtp.server.web.StrictStateCheckListener" />



License
-------
This project is licensed under [MIT license](http://opensource.org/licenses/MIT).