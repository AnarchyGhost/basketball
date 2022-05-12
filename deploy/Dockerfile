FROM docker
COPY ../modules/ modules/
RUN ls -a
RUN #!/usr/bin/bash for f in modules/*/build/libs/*-boot.jar; do echo "$f" docker build --build-arg buildPath="$f" . done