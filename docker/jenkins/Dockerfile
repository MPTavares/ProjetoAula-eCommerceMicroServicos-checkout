FROM jenkins/jenkins

USER root
RUN adduser jenkins users
USER jenkins
COPY jdks/ /opt/java/
RUN chown -R jenkins.jendokins /opt/java/