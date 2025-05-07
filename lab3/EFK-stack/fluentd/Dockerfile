FROM fluent/fluentd:v1.16-1

USER root

RUN buildDeps="make gcc g++ libc-dev ruby-dev" \
 && apk add --no-cache --virtual .build-deps $buildDeps \
 && gem install elasticsearch -v 7.13.3 \
 && gem install elasticsearch-transport -v 7.13.3 \
 && gem install fluent-plugin-elasticsearch -v 4.0.0 \
 && apk del .build-deps \
 && gem sources --clear-all \
 && rm -rf /home/fluent/.gem \
 && mkdir -p /fluentd/buffer/es /logs/myapp \
 && chown -R fluent /fluentd /logs

USER fluent
