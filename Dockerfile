# specific image which is used as base image
FROM openjdk:11
# app's labels (metadata)
LABEL org.opencontainers.image.title="WhateverAppMaven" \
      org.opencontainers.image.description="WhateverApp build with Maven" \
      org.opencontainers.image.authors="@tardigrada029"
# create directory in container image for app code
RUN mkdir -p /home/app
# copy app code (.) to /home/app in container image
COPY src/main/kotlin/com/tardigrada/WhateverAppMaven /home/app
COPY target/whateverappmaven.jar /home/app
# set working directory context
WORKDIR /home/app
# command for container to execute
ENTRYPOINT ["java", "-jar","whateverappmaven.jar"]