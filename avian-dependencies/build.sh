#!/bin/bash
set -e
cd $(dirname $0)

AVIAN_DEPS=$(pwd)

# temporary hack to install avian dependencies user-wide
_install () {
    cd $AVIAN_DEPS
    DEST=~/.cache/crossmobile/avian/0.1
    rm -rf $DEST
    mkdir -p $DEST
    cp -r target/* $DEST
}

_build () {
    cd $AVIAN_DEPS/docker
    sudo docker-compose build
    cd ..
    sudo docker run --rm -it -v ${PWD}:/src aroma/dep-builder bash /src/docker/dep_builder.sh
    sudo chown -R $USER .
    _install
}

_clean_avian () {
    cd $AVIAN_DEPS
    sudo chown -R $USER .
    (cd avian ; make clean)
    rm -rf target/common/bin/*/binaryToObject target/common/classpath.jar target/*/driver.o target/*/libavian.zip
}


_clean_others () {
    _clean_avian
}

while [[ $# -gt 0 ]] ; do
    case $1 in
    -h|--help)
        echo -e "Parameters:\n  -h|--help\n  -c|--clean\n  -a|--cleanavian\n  -b|--build"
        exit 0
        ;;
    -b|--build)
        _build
        shift
        ;;
    -c|--clean)
        _clean
        shift
        ;;
    -a|--avian)
        _clean_avian
        shift
        ;;
    *)
        echo "Unknown parameter"
        exit 1
        ;;
    esac
done
