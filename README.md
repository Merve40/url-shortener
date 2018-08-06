# URL-Shortener
A simple URL Shortener implementation using Spring Boot

## Deployment with docker
```
docker-compose up -d
```

### Retrieving IP Address of Docker Container
```
docker network inspect urlshortener_default | grep IPv4Address
```
