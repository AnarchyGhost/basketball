FROM docker
WORKDIR .
ARG buildPath
COPY $buildPath $buildPath
RUN ls -a $buildPath