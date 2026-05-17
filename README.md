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

Endpoint: `/marcas`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all `Marca` entities. |
| GET | `/{id}` | Retrieve a `Marca` by ID. |
| POST | - | Create a new `Marca`. |
| PUT | `/{id}` | Update an existing `Marca`. |
| DELETE | `/{id}` | Delete an existing `Marca`, otherwise responds _Not Found_ |

### Modelo

Endpoint: `/modelos`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all `Modelo` entities. |
| GET | `/{id}` | Retrieve a `Modelo` by ID. |
| POST | - | Create a new `Modelo`. |
| PUT | `/{id}` | Update an existing `Modelo`. |
| DELETE | `/{id}` | Delete an existing `Modelo`, otherwise responds _Not Found_. |

## License

This project is licensed under MIT.
