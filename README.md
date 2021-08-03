# Spring Boot Demo for SRE with Prometheus and Reliably

This is a very basic [Spring Boot](https://spring.io/projects/spring-boot)
application that exposes a web application at http://localhost:8080/

Run it as follows:

```
$ ./mvnw spring-boot:run
```

The application is configured to expose a Prometheus endpoint at
http://localhost:8080/actuator/prometheus

If you don't have a Prometheus at hand, simply
[download it](https://prometheus.io/download/#prometheus), unzip it somewhere
and start with:

```
$ ./prometheus --config.file=`pwd`/prometheus.yml
```

Or you can configure your own Prometheus by adding a
[scrape config](https://prometheus.io/docs/prometheus/latest/configuration/configuration/#scrape_config)
section as follows:

```yaml
  - job_name: 'my-spring-boot-app'

    static_configs:
    - targets: ['127.0.0.1:8080']
    metrics_path: /actuator/prometheus
```

Once that is done, you can run
[reliably](https://reliably.com/docs/getting-started/) as follows (you need
at least reliably 0.23.0):

```
# refresh indicators every 10s
$ reliably slo agent -i10 -m reliably.yaml
```

```
$ reliably slo report -w -m reliably.yaml
```
