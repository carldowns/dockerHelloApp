
# Dropwizard Microservice with Docker Plug-in

Building a Dropwizard Microservice with Docker and Maven.
This post shows how to integrate Docker into a Maven build and create a Docker image/container containing a Dropwizard microservice and run tests against it.
[philipp hauer's blog post](https://blog.philipphauer.de/building-dropwizard-microservice-docker-maven/)


## prerequisites

    docker installed 
    maven installed   

### start the docker machine

    $ docker-machine start

### get the environment set for the default machine including assigned IP

$ docker-machine env

    export DOCKER_TLS_VERIFY="1"
    export DOCKER_HOST="tcp://192.168.99.100:2376"
    export DOCKER_CERT_PATH="/Users/carl_downs/.docker/machine/machines/default"
    export DOCKER_MACHINE_NAME="default"

Run this command to configure your shell:

    $eval $(docker-machine env)
    $ echo $DOCKER_HOST
    tcp://192.168.99.100:2376

### maven POM file variable referencing the above IP

    <docker.host.address>192.168.99.100</docker.host.address>
    make sure this address reflects the IP of your docker machine

### the org.jolokia docker plug-in

    we are using this plugin recommended by Phillip:
    
        <groupId>org.jolokia</groupId>
        <artifactId>docker-maven-plugin</artifactId>
                   
    the commands it supports
                    
        $ mvn docker:build
        $ mvn docker:start
        $ mvn docker:stop
                    
### make the project

    The docker machine must be running and the eval $(...) executed otherwise you get
        --> build failed: No url given, no DOCKER_HOST environment variable and no read/writable

    $ mvn clean install
    
    this plug-in makes the docker image.
    docker build, start and stop commands are executed as part of the pre- and post-integration tests
    which means the images are built / replaced at this point.  No need to build it directly.
        
### see the docker images built and downloaded 
    
    $ docker image ls 
    REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
    cdowns/hello-docker-app-1   1.0.0-SNAPSHOT      703c90431fd2        41 seconds ago      325 MB
    mongo                       2.6.11              f36fb0070896        14 months ago       391 MB

### stop the docker machine

    this stops the 'default' machine
    
    $ docker-machine stop

### restart the docker machine

    this restarts the 'default' machine
    
    $ docker-machine restart
    
    