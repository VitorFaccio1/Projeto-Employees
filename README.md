# API de Gerenciamento de Funcionários

Esta é uma API RESTful simples para gerenciar funcionários de uma empresa. Permite a inserção, atualização, exclusão e recuperação de funcionários, bem como a listagem de todos os funcionários paginados.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA

## Endpoints

### Recuperar um Funcionário

Recupera as informações de um funcionário com base no ID fornecido.

- **URL:** `/employee/{id}`
- **Método HTTP:** `GET`
- **Parâmetros:**
  - `id` (obrigatório): ID do funcionário a ser recuperado.
- **Respostas:**
  - Código 200 OK: Retorna os detalhes do funcionário.
  - Código 404 Not Found: Se o funcionário não for encontrado.

### Listar Funcionários Paginados

Recupera uma lista paginada de todos os funcionários.

- **URL:** `/employee`
- **Método HTTP:** `GET`
- **Parâmetros:**
  - `page` (obrigatório): Número da página a ser recuperada (começando do zero).
  - `size` (obrigatório): Tamanho da página (número máximo de funcionários por página).
- **Respostas:**
  - Código 200 OK: Retorna a lista de funcionários na página especificada.
  - Código 400 Bad Request: Se os parâmetros de paginação forem inválidos.

### Inserir um Novo Funcionário

Insere um novo funcionário no sistema.

- **URL:** `/employee`
- **Método HTTP:** `POST`
- **Corpo da Requisição:** Deve conter os dados do novo funcionário em formato JSON.
- **Respostas:**
  - Código 200 OK: Retorna os detalhes do funcionário recém-inserido.

### Excluir um Funcionário

Remove um funcionário do sistema com base no ID fornecido.

- **URL:** `/employee/{id}`
- **Método HTTP:** `DELETE`
- **Parâmetros:**
  - `id` (obrigatório): ID do funcionário a ser removido.
- **Respostas:**
  - Código 200 OK: Retorna uma mensagem indicando que o funcionário foi removido com sucesso.
  - Código 404 Not Found: Se o funcionário não for encontrado.

### Atualizar os Dados de um Funcionário

Atualiza os dados de um funcionário existente com base no ID fornecido.

- **URL:** `/employee/{id}`
- **Método HTTP:** `PUT`
- **Parâmetros:**
  - `id` (obrigatório): ID do funcionário a ser atualizado.
- **Corpo da Requisição:** Deve conter os novos dados do funcionário em formato JSON.
- **Respostas:**
  - Código 200 OK: Retorna os detalhes do funcionário atualizado.
  - Código 404 Not Found: Se o funcionário não for encontrado.

## Executando o Projeto

Para executar o projeto localmente, siga estas etapas:

1. Certifique-se de ter o Java JDK instalado na sua máquina.
2. Clone este repositório.
3. Navegue até o diretório do projeto no terminal.
4. Execute o comando `./gradlew bootRun` para iniciar o servidor local.
