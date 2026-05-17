# Estaciona API

REST API for vehicle parking management built with Java and Spring Boot.

## Status

Project under development.

## Stack

- Java 21
- Spring Boot
- PostgreSQL
- Docker
- Gradle

## Development

Development environment uses:

- `docker-compose.override.yaml`
- Spring DevTools

## Running project

### Environment variables

Create a `.env` file in project root using [.env.example](./.env.example).

### Using Docker

```bash
docker compose up --build
```

API will be available at:

```text
http://localhost:8080
```

## Routes

### Marca

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | `/marcas` | Retrieve all `Marca` entities. |
| GET | `/marcas/{id}` | Retrieve a `Marca` by ID. |
| POST | `/marcas` | Create a new `Marca`. |
| PUT | `/marcas/{id}` | Update an existing `Marca`. |

## License

This project is licensed under MIT.

