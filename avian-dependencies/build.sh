#!/bin/bash
set -e
set -x

SRC_ROOT=$(dirname "$(realpath "$0")")
TARGET_OS=$(uname -s | tr '[:upper:]' '[:lower:]')
TARGET_ARCH=$(uname -m)
TARGET_CMD=""

USAGE="\
Parameters:\n\
\t-h|--help\n\
\t-c|--clean\n\
\t-a|--cleanavian\n\
\t-b|--build\n\
\t-o|--os\n\
\t-m|--machine"

cd $SRC_ROOT

_check_docker_image() {
    echo "Check image: \"$1\""
    if [[ "$(docker images -q $1 2> /dev/null)" == "" ]]; then
        return -1
    fi
        return 0
}

# temporary hack to install avian dependencies user-wide
_install () {
    DEST=~/.cache/crossmobile/avian/0.1
    rm -rf $DEST
    mkdir -p $DEST
    cp -r target/* $DEST
}

_build () {
    local IMAGE_NAME="aroma/dep-builder-$TARGET_OS-$TARGET_ARCH"

    if [[ "$(_check_docker_image $IMAGE_NAME)" != "0" ]]; then
        echo "Building image: \"$IMAGE_NAME\""
        # SRC_DIR=$SRC_ROOT \
        # TARGET_OS=$TARGET_OS \
        # TARGET_ARCH=$TARGET_ARCH \
        #sudo docker-compose -f docker/docker-compose.yml build --build-args SRC_DIR=$SRC_ROOT TARGET_OS=$TARGET_OS TARGET_ARCH=$TARGET_ARCH aromadepbuilder
        docker build -t $IMAGE_NAME docker/ --build-arg ARCH=$TARGET_ARCH
    fi

    echo "Run image: \"$IMAGE_NAME\""
    sudo docker run --rm -it -v ${SRC_ROOT}:/src $IMAGE_NAME bash "/src/docker/dep_builder.sh" $TARGET_OS $TARGET_ARCH release
    echo "After docker"
    sudo chown -R $USER:$USER .
    _install
}

_clean_avian () {
    chown -R $USER .
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
        TARGET_CMD=_build
        shift
        ;;
    -c|--clean)
        TARGET_CMD=_clean
        shift
        ;;
    -o|--os)
         TARGET_OS=$2
         shift
         shift
         ;;
    -m|--machine)
        TARGET_ARCH=$2
        shift
        shift
        ;;
    -a|--avian)
        TARGET_CMD=_clean_avian
        shift
        ;;
    *)
        echo "Unknown parameter"
        exit 1
        ;;
    esac
done


if [[ $TARGET_CMD == "" ]]; then
    echo "ERROR: No command provided!"
    echo -e $USAGE
else
    $TARGET_CMD
fi
