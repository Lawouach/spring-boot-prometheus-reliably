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

<pre><font color="#AE81FF">Refreshing SLO report every 3 seconds.</font> Press CTRL+C to quit.
                                                  <font color="#AE81FF"><b> </b></font>  <font color="#AE81FF"><b>  Current</b></font>  <font color="#AE81FF"><b>Objective</b></font>  <font color="#AE81FF"><b>/ Time Window</b></font>  <font color="#AE81FF"><b> </b></font>  <font color="#AE81FF"><b>Type</b></font>  <font color="#AE81FF"><b> </b></font>  <font color="#AE81FF"><b>Trend</b></font>      
  Service #1: my spring boot app                  
  ✅ 99% of requests return 2xx over last 1 hour        <font color="#A6E22E"><b>99.42%</b></font>        99%  / 1h0m0s                   <font color="#F92672">✕</font> <font color="#F92672">✕</font> <font color="#F92672">✕</font> <font color="#F92672">✕</font> <font color="#A6E22E">✓</font>  

</pre>