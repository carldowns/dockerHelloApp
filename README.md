
# Dropwizard Microservice with Docker Plug-in

Building a Dropwizard Microservice with Docker and Maven.
This post shows how to integrate Docker into a Maven build and create a Docker image/container containing a Dropwizard microservice and run tests against it.
[philipp hauer's blog post](https://blog.philipphauer.de/building-dropwizard-microservice-docker-maven/)


## Prerequisites

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

### Maven POM file variable referencing the above IP

    <docker.host.address>192.168.99.100</docker.host.address>

### the plug-in

    we are using this plugin recommended by Phillip:
    
        <groupId>org.jolokia</groupId>
        <artifactId>docker-maven-plugin</artifactId>
                    
### make the project

The docker machine must be running and the eval $(...) executed otherwise you get

    build failed: No url given, no DOCKER_HOST environment variable and no read/writable

    $ mvn clean install
    
    This plug-in makes the docker image.
    
### see the docker image
    
    $ docker image ls 
    REPOSITORY                  TAG                 IMAGE ID            CREATED             SIZE
    cdowns/hello-docker-app-1   1.0.0-SNAPSHOT      703c90431fd2        41 seconds ago      325 MB

### plugin commands to run build image, start/stop container:

    $ mvn docker:build
    $ mvn docker:start
    $ mvn docker:stop

### Maven plug-in: pre and post integration tests

    the build, start and stop commands above are executed as part of the pre- and post-integration tests
    which means the image is replaced at this point.  No need to build it directly.
    
### Stop the docker machine

    this stops the 'default' machine
    
    $ docker-machine stop

### Restart the docker machine

    this restarts the 'default' machine
    
    $ docker-machine restart
    
    