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

### Carro

Endpoint: `/carros`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all `Carro` entities. |
| GET | `/{id}` | Retrieve a `Carro` by ID. |
| POST | - | Create a new `Carro`. |
| PUT | `/{id}` | Update an existing `Carro`. |
| DELETE | `/{id}` | Delete an existing `Carro`, otherwise responds _Not Found_. |

### Cliente

Endpoint: `/clientes`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all `Cliente` entities. |
| GET | `/{id}` | Retrieve a `Cliente` by ID. |
| POST | - | Create a new `Cliente`. |
| PUT | `/{id}` | Update an existing `Cliente`. |
| DELETE | `/{id}` | Delete an existing `Cliente`, otherwise responds _Not Found_. |

### Cor

Endpoint: `/cores`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all available Cor enum values. |

### Entrada

Endpoint: `/entradas`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all active `Entrada` entities. |
| GET | `?ativas=false` | Retrieve all `Entrada` entities, including closed entries. |
| GET | `/{carroId}?entrada={timestamp}` | Retrieve an `Entrada` by composite ID (`carroId` and entry timestamp). |
| POST | `/{carroId}` | Create a new `Entrada` for a specific car. |
| PUT | `/{carroId}?entrada={timestamp}` | Update an existing `Entrada`. |
| DELETE | `/{carroId}?entrada={timestamp}` | Delete an existing `Entrada`, otherwise responds _Not Found_. |
| PATCH | `/{carroId}/saida` | Register vehicle exit for an active `Entrada`. |

### Preço

Endpoint: `/precos`

| METHOD | PATH | DESCRIPTION |
|---|---|---|
| GET | - | Retrieve all `Preco` entities. |
| GET | `/{id}` | Retrieve a `Preco` by ID. |
| POST | - | Create a new `Preco`. |
| PUT | `/{id}` | Update an existing `Preco`. |
| DELETE | `/{id}` | Delete an existing `Preco`, otherwise responds _Not Found_. |

## Requests

Expected JSON bodies for POST and PUT routes:

| Request | Fields | Used in |
|---|---|---|
| `MarcaRequest` | `{ "nome": "string" }` | POST, PUT `/marcas` |
| `ModeloRequest` | `{ "nome": "string", "marca_id": "short" }` | POST, PUT `/modelos` |
| `CarroRequest` | `{ "placa": "string", "cor_id": "integer", "modelo_id": "short" }` | POST, PUT `/carros` |
| `ClienteRequest` | `{ "nome": "string", "telefone": "string" }` | POST, PUT `/clientes` |
| `EntradaRequest` | `{ "cliente_id": "long", "preco_id": "short" }` | POST, PUT `/entradas/{carroId}` |
| `SaidaEntradaRequest` | `{ "saida": "ISO-8601 datetime" }` | PATCH `/entradas/{carroId}/saida` |
| `PrecoRequest` | `{ "nome": "string", "preco": "double", "descricao": "string" }` | POST, PUT `/precos` |

The `descricao` field in `PrecoRequest` and `saida` in `SaidaEntradaRequest` are optional — omitting them defaults to empty string and current datetime, respectively.

## License

This project is licensed under MIT.
