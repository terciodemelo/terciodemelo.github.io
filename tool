#!/usr/bin/env bash

function serve-hugo() {
    cd $(dirname $0)/src
    hugo server
}

function build-hugo() {
    cd $(dirname $0)/src
    hugo -D -d ../ --ignoreCache
}

function main() {
    if [[ "$1" == "s" ]] || [[ "$1" == "serve" ]] || [[ "$1" == "server" ]]; then
        serve-hugo
    elif [[ "$1" == "b" ]] || [[ "$1" == "build" ]]; then
        build-hugo
    else
        echo "Usage: ./tool <arguments>"
        echo ""
        echo "Arguments:"
        echo "  server, serve, s                        Serve with hot reloading"
        echo "  build, b                                Build for production"
    fi
}

main $@