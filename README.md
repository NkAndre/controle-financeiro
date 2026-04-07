## CONTROLE FINANCEIRO 💵 – Java Swing + MySQL

Aplicação desktop desenvolvida em Java Swing para controle de despesas e receitas, com autenticação de usuários e persistência de dados em banco MySQL.

## 🚀 FUNCIONALIDADES
- Login com autenticação no MySQL
- Cadastro de usuários
- Lançamentos financeiros (Entrada / Saída)
- Filtragem por id(entrada ou saida)
- Integração total com banco de dados
- Interface gráfica com Java Swing


 ## 🛠️ TECNOLOGIAS UTILIZADAS
- Java (JDK 8+)
- Java Swing
- MySQL
- XAMPP (Apache + MySQL)
- JDBC
- IDE: Eclipse

- ## 📦 ESTRUTURA DO PROJETO
src/
├── app
│ └── Main.java<br>
|
├── dao

│

├── model

├── service

│
├── view

│ ├── dashboard

│ │ └── DashboardView.java

│ │

│ ├── lancamento

│ │ └── LancamentoView.java
│ │

│ └── login

│ ├── CadastroUsuarioView.java

│ └── LoginView.java


## ⚙️ Configuração do Ambiente

1. Instale o **XAMPP**
2. Inicie o **MySQL**
3. Crie o banco de dados:
 sql
CREATE DATABASE controle_financeiro;
Importe o script SQL 

Configure a conexão JDBC:

String url = "jdbc:mysql://localhost:3306/controle_financeiro";
String user = "root";
String password = "";

##  ▶️ COMO EXECUTAR

1-Abra o projeto na IDE

2-Execute a classe principal

3- Faça login ou cadastre um usuário

## 📸 PREVIEW

Veja abaixo algumas telas do sistema:

<img width="1918" height="1030" alt="tela-login" src="https://github.com/user-attachments/assets/3ce627a6-c962-4ee8-8fb8-7ed9bc27b031" />
<img width="1918" height="1030" alt="tela-login" src="https://github.com/user-attachments/assets/3ce627a6-c962-4ee8-8fb8-7ed9bc27b031" />

<img width="1916" height="1021" alt="dashboard" src="https://github.com/user-attachments/assets/4fd88e4b-4baa-43f9-b39b-4bd0938638d4" />
<img width="1916" height="1021" alt="dashboard" src="https://github.com/user-attachments/assets/4fd88e4b-4baa-43f9-b39b-4bd0938638d4" />


## Projeto pessoal com foco em lógica, Poo & integraçao com banco de dados. 

## André santos 👨🏽‍💻


