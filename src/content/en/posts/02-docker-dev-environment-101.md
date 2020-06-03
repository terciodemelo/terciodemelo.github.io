---
title: Docker development environment 101
description: >-
    Let's talk about the very basics of building a development environment in Docker
    using software Diagrams template as example
date: 2020-06-03
tags:
- docker
- development
- diagrams
- documentation
image: images/icons/undraw_container_ship.png
---

## Production Context
[Docker](https://en.wikipedia.org/wiki/Docker_(software)) has been a very successful technology to
build reproducible production environments. It doesn't matter the hardware, nor the
operating system, nor version of programming languages installed in the host machines. If it has 
Docker installed and you're running your software in Docker you won't, probably, have any
compatibility issue. This can also be applied to development environment.

# A bit of history
Over the years I've had the opportunity to deal with many ongoing projects in which the first issue
was to be able to run them in my computer. All of us know this situation. You gotta install the right
programming language version, the right development libs in the right version in your operating
system, configure VPN to be able to connect to the development or staging database, or run the 
database in your local machine which means more dependency versions to deal with. You usually go
installing stuff in your system that will eventually conflict with another project you may need to 
run or compile. At some point I decided I didn't want to have to deal with it anymore, and thus in every project
that I start and every ongoing project that I have to handle, the first thing that I do is to setup 
a development environment in Docker if none is already present.

Today I bring to you the most basic example I have available in the open. It's not a web server, nor
any fancy CLI software, its just a script runner to make diagrams using
[Diagrams](https://diagrams.mingrammer.com/).

## Sources

All the sources are available in my
[diagrams-template](https://github.com/terciodemelo/diagrams-template) repository that I've made to
stop copy-pasting this template from project to project, and have a single source for it. At any
project that I want to document with diagrams, I download this repo sources to a `docs/` subdirectory
or something like that.

There are actually only two pieces of code that are of special interest to us: the Dockerfile
`docs.dockerfile` and the bash script `make-diagrams`. The usage of the project is explained in the
repository's `README`, so I won't repeat myself here.

## Diving in

#### docs.dockerfile
The dockerfile sets up all dependencies that are needed to run the development environment smoothly.
In our diagrams case all we need to run `Diagrams` is [Python](https://en.wikipedia.org/wiki/Python_(programming_language)) in the version we wanna use, [Graphviz](https://en.wikipedia.org/wiki/Graphviz)
which is a `Diagrams` system-level dependency, [poetry](https://python-poetry.org/) as our Python
dependencies manager and a default working directory. None of this will have impact on the software
that are directly installed in your host machine, and thus can even run projects with different, say,
Python versions at the same time without monstruosities like [pyenv](https://github.com/pyenv/pyenv).

```Dockerfile
FROM python:3.8.3-slim

RUN apt update && apt install -y graphviz curl
RUN curl -sSL https://raw.githubusercontent.com/python-poetry/poetry/master/get-poetry.py | python

RUN echo 'source $HOME/.poetry/env' >> ~/.bashrc

ADD . /root/workspace
WORKDIR /root/workspace
```

#### make-diagrams
This is the cherry on top of the cake. Our "entrypoint" script is what we run; `./make-diagrams`.
When we run it no `RUNNING_IN_DOCKER` environment variable is set, and therefore we run a container
from `docs.dockerfile` setting this enviroment variable, mounting the current working directory, and
running the very same `make-diagrams` bash script. This will cause us to enter the `else` branch 
only once we are inside the container, an so run the commands we wanted to run all along.

```bash
#!/usr/bin/env bash

if [[ -z "$RUNNING_IN_DOCKER" ]]; then
    docker run --rm -it \
        -e RUNNING_IN_DOCKER=true \
        -v $(pwd):/root/workspace \
        -v /tmp/poetry-cache:/root/.cache \
        -w /root/workspace \
        $(docker build -q -f docs.dockerfile .) \
        ./make-diagrams
else
    source ~/.bashrc
    poetry install

    for diagram in src/*; do
        if [[ "$diagram" != __init__* ]]; then
            poetry run python $diagram
        fi
    done
fi
```

In this example its just running a bunch of `poetry` commands, but could be booting a web server, or
a database, or queue, or any type of services and/or jobs.

## More possibilities

This type of recursive approach of running bash scripts combined with Docker can be generalized to
do fancier stuff:

1. In the above script I also mounted my local `/tmp/poetry-cache` directory to the container's
   `/root/.cache` directory in order to not need to download all Python dependency packages over and
   over again. This can also be used to, say, reuse a database files directory to be able to preserve
   data over multiple usages as well
2. Instead of a single container, you could run [docker-compose](https://docs.docker.com/compose/)
   or even [Kubernetes](https://en.wikipedia.org/wiki/Kubernetes) local clusters using things like
   [K3S](https://k3s.io/) or [kind](https://kind.sigs.k8s.io/)
3. Instead of Docker as container engine, you could use other container engines, or even VMs

In the future I may bring more cool usages of this project organization paradigm.