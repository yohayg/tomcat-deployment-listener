Tomcat listener will abort Tomcat process when a webapp fail to deploy
=========================
 [![Build Status](https://travis-ci.org/yohayg/tomcat-deployment-listener.svg?branch=master)](https://travis-ci.org/yohayg/tomcat-deployment-listener)
 [![Coverage Status](https://coveralls.io/repos/github/yohayg/tomcat-deployment-listener/badge.svg?branch=master)](https://coveralls.io/github/yohayg/tomcat-deployment-listener?branch=master) 


Usage
-----

Mount the jar in the tomcat classpath and add to server.xml:

    <Listener className="com.marketo.rtp.server.web.StrictStateCheckListener" />



License
-------
This project is licensed under [MIT license](http://opensource.org/licenses/MIT).