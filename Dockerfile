FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

RUN apt-get update && \
    apt-get install -y --no-install-recommends

RUN apt-get install -y libxrender1
RUN apt-get install -y libxtst6
RUN apt-get install -y libxi6
RUN apt-get install -y openjfx

EXPOSE 8080 8080
WORKDIR /CardsAgainstHumanity
ADD . /CardsAgainstHumanity
CMD sbt run