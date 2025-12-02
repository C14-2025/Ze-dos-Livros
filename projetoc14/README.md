# 📚 Sistema de Biblioteca

Este projeto implementa um **Sistema de Gerenciamento de Biblioteca em Java**, permitindo a administração de **usuários, livros e empréstimos**.

O sistema possibilita **cadastrar, consultar, atualizar e remover registros**, além de controlar **empréstimos ativos e devoluções**.

---

## 🚀 Funcionalidades

### 📖 Gestão de Livros
- ➕ Adicionar novo livro: Cadastro completo com ISBN, título, autor, editora, ano, quantidade e categoria
- 🔍 Consultar livro por **ISBN**: Busca rápida pelo identificador único.
- 📑 Listar todos os livros cadastrados
- ✏️ Atualizar informações de um livro
- 🗑️ Remover livro do acervo
- 📊 Controle de estoque: Gerenciamento de quantidade disponível.

### 👤 Usuários
- ➕ Cadastrar usuários
- 🔍 Buscar usuários por **ID** ou **nome**
- 📑 Listar todos os usuários
- ✏️ Atualizar dados de usuários
- 🗑️ Remover usuários

### *📦 Empréstimos*
- ➕ Registrar novo empréstimo
- 🔍 Consultar empréstimo por **ID**
- 📑 Listar todos os empréstimos
- 📌 Listar empréstimos **ativos**
- 👥 Buscar empréstimos por usuário
- 🗑️ Remover empréstimo
- ⚠️ Tratamento de exceções para **usuário ou livro não encontrado**

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura em **camadas**, organizada em pacotes:

- **model** → Entidades principais
  - `Livro`
  - `Usuario`
  - `Emprestimo`

- **implementacao** → Implementação das regras de negócio
  - `LivroIMPL`
  - `UsuarioIMPL`
  - `EmprestimoIMPL`

- **interfaces** → Contratos (regras) das entidades
  - `LivroRegras`
  - `UsuarioRegras`
  - `EmprestimoRegras`

- **exceptions** → Tratamento de erros específicos
  - `ControladoraLivros`
  - `ControladoraUsuarios`
  - `ControladoraEmprestimos`

- **controllers** → Camada de apresentação
  - `EmprestimoException`
  - `UsuarioNaoEncontradoException`
  - `LivroNaoEncontradoException`

- **Main.java** → Classe principal para execução do programa

---

## 📂 Estrutura de Pastas

```bash
src/
 ├── exceptions/
 │   ├── NaoEncontradoException.java
 │   └── RegistroDuplicadoException.java
 │
 ├── implementacao/
 │   ├── LivroIMPL.java
 │   ├── UsuarioIMPL.java
 │   └── EmprestimoIMPL.java
 │
 ├── interfaces/
 │   ├── LivroRegras.java
 │   ├── UsuarioRegras.java
 │   └── EmprestimoRegras.java
 │
 ├── model/
 │   ├── Livro.java
 │   ├── Usuario.java
 │   └── Emprestimo.java
 │
 ├── controllers/
 │   ├── ControladoraLivros.java
 │   ├── ControladoraUsuarios.java
 │   └── ControladoraEmprestimos.java
 │
 └── Main.java
````
---

## ⚙️ Recursos  Utilizadas

- ☕ **Java 17+ (21.0.9)**
- 📦 **Coleções Java (List, HashMap, etc.)**
- 📅 java.time - Para manipulação de datas (LocalDate)
- ⌨️ Scanner - Para entrada de dados via console
- 🏗️ Design Patterns - Interface Segregation, Dependency Injection
- ⚡ **Exceções personalizadas** para melhor legibilidade, clareza e controle de erros.
- 🔧 **Maven para gerenciamento de dependências e build do projeto**
---
