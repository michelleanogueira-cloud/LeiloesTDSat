# Leilões TDSat

Sistema desktop desenvolvido para apoiar as rotinas de uma casa de leilões. A aplicação permite cadastrar produtos, consultar os itens registrados e acompanhar a situação de cada produto, como **A Venda** ou **Vendido**.

## Tecnologias utilizadas

- Java 18
- Java Swing
- MySQL/MariaDB
- JDBC (MySQL Connector/J)
- Apache Ant
- NetBeans IDE
- Git e GitHub

## Banco de dados

O script de criação e carga inicial está disponível em [`database/uc11.sql`](database/uc11.sql). Ele cria o banco `uc11`, a tabela `produtos` e insere dados de exemplo.

## Como executar

1. Importe `database/uc11.sql` em um servidor MySQL ou MariaDB.
2. Confirme as credenciais de acesso em `src/conectaDAO.java`.
3. Abra o projeto no NetBeans.
4. Compile e execute a aplicação.

## Versionamento

Este projeto foi versionado com Git e publicado no GitHub como parte da Atividade 1 do curso Técnico em Desenvolvimento de Sistemas do SENAC.

