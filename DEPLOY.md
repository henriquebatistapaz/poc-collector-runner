# Deploy e execução local

Biblioteca Maven e API Spring Boot que grava itens coletados no PostgreSQL.

## Validação local

Use OpenJDK 27.0.1.0.3, Spring Boot 4.1.2 e Maven 3.9 ou superior. Execute os comandos a partir da raiz do repositório.

```sh
mvn -f ../poc-collector/pom.xml clean install
mvn -f ../poc-rss-collector/pom.xml clean install
docker compose up -d postgres
mvn clean verify
mvn spring-boot:run
```

## Pendências de publicação

Provisionar PostgreSQL e definir `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`. O build remoto precisa acessar os dois artefatos Maven ou compilar os projetos irmãos antes. Ver o [README](README.md) para o teste com PostgreSQL.
