# wallet-service

A learning project: a wallet in Kazakhstani tenge (the money is simulated).
Java 25, Spring Boot 4, PostgreSQL 18, Flyway.

## Requirements

- JDK 25
- Docker with Compose

## Run

```bash
cp .env.example .env    # then set your own POSTGRES_PASSWORD
docker compose up -d
./mvnw spring-boot:run
```

The app starts on http://localhost:8080. Flyway creates the database schema on startup.

## Test

```bash
./mvnw verify
```

Tests start their own PostgreSQL with Testcontainers, so only Docker needs to be running.

## Stop

```bash
docker compose down
```

Add `-v` to also delete the database data.
