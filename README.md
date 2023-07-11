# Repositório de soluções técnicas

O objetivo deste projeto é providenciar aos usuários um repositório comunitário de soluções técnicas para inúmeros defeitos encontrados no dia a dia de um técnico em eletrônica. Cada usuário pode fazer uma contribuição, seja ela um defeito ou uma solução para um defeito já existente postada por outro usuário.

O trabalho foi desenvolvido como uma API, portanto pode ser consumido por qualquer aplicação que utilize o protocolo HTTP.

## Utilização

A API foi desenvolvida utilizando o ecossistema Spring. Para executar o projeto, basta utilizar os comandos abaixo:

### Localmente

- Clonar este repositório
- Construir o projeto com o Maven:

```
$ ./mvnw clean package
$ java -jar techrepo/target/techrepo-0.0.1-SNAPSHOT.jar
```
