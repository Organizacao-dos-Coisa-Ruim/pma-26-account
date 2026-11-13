# pma-26-account

Interface do serviço **Account**: `@FeignClient` + DTOs. É uma lib, não roda sozinha.
A implementação fica em [`pma26-account-service`](https://github.com/Organizacao-dos-Coisa-Ruim/pma26-account-service).

## Instalar no repositório Maven local

Precisa ser feito **antes** de buildar o `account-service` (ou qualquer serviço que chame o Account):

```bash
./mvnw clean install
```

## Endpoints

| Método | Rota | Corpo | Resposta |
|--------|------|-------|----------|
| `POST` | `/accounts` | `AccountIn {name, email, password}` | `201` + `AccountOut` · `400` campo faltando · `409` email já existe |
| `GET` | `/accounts` | – | `200` + lista de `AccountOut` |
| `GET` | `/accounts/{id}` | – | `200` + `AccountOut` · `404` |
| `DELETE` | `/accounts/{id}` | – | `204` · `404` |
| `POST` | `/accounts/login` | `LoginIn {email, password}` | `200` + `AccountOut` · `401` credenciais inválidas |
| `GET` | `/accounts/health-check` | – | `200` |

`AccountOut` nunca traz a senha.

## Usando como cliente Feign (ex.: no Auth)

```xml
<dependency>
  <groupId>pma</groupId>
  <artifactId>account</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```

```java
@EnableFeignClients(basePackages = "pma.account")
```

A URL padrão é `http://account:8080` e pode ser trocada pela propriedade `pma.account.url`.
