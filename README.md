# Sistema de Biblioteca - Microsserviços

Este repositório contém os códigos-fonte dos microsserviços que compõem o sistema de biblioteca.

## Microsserviços Disponíveis

### 1. **ms-book**
- Responsável pela gestão de livros na biblioteca.


### 2. **ms-user**
- Gerencia os usuários do sistema.

### 3. **ms-transaction**
- Gerencia as transações de empréstimos e devoluções.


### 4. **ms-email**
- Responsável pelo envio de notificações por e-mail.

### 5. **ms-eureka**
- Serviço de descoberta de microsserviços (Service Discovery).
- Funciona como um registro central para os microsserviços.

### 6. **ms-gateway**
- API Gateway responsável por gerenciar as requisições e redirecioná-las aos microsserviços apropriados.
- Centraliza os pontos de entrada do sistema.

## Estrutura do Projeto

Cada microsserviço é um projeto independente e possui:
- **Configuração**: Arquivos de configuração para conexões com o banco de dados, porta de comunicação e integração com outros microsserviços.
- **APIs**: Endpoints para interação com outros microsserviços ou com o cliente.

## Tecnologias Utilizadas

- **Java com Spring Boot**: Para desenvolvimento dos microsserviços.
- **Eureka Server**: Para Service Discovery.
- **API Gateway**: Para gerenciar o fluxo de requisições.
- **Banco de Dados**: PostgreSQL
- **RabbitMQ**: Para comunicação assíncrona.

## Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/gustavbarbosaa/bookstore
   ```

2. Configure as variáveis de ambiente necessárias (porta, conexão com banco de dados, etc.).

3. Inicie o Eureka Server (**ms-eureka**):
   ```bash
   cd ms-eureka
   mvn spring-boot:run
   ```

4. Inicie os outros microsserviços na ordem desejada.

5. Acesse o sistema através do Gateway (**ms-gateway**).

Vídeo explicativo:

https://drive.google.com/file/d/1Q5cqpnZ8tJLfNGbnnwzrV2ZaQlUXZfsj/view?usp=sharing


