FROM java:8

# set environment
ENV SPRING_PROFILES_ACTIVE development

WORKDIR /srv/www/application/current

ADD docker/init.sh /init.sh
RUN chmod +x /init.sh

CMD ["/init.sh"]
