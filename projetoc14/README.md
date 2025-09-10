# 📚 Sistema de Biblioteca

Este projeto implementa um **Sistema de Gerenciamento de Biblioteca em Java**, permitindo a administração de **usuários, livros e empréstimos**.

O sistema possibilita **cadastrar, consultar, atualizar e remover registros**, além de controlar **empréstimos ativos e devoluções**.

---

## 🚀 Funcionalidades

### 📖 Livros
- ➕ Adicionar novo livro
- 🔍 Consultar livro por **ISBN**
- 📑 Listar todos os livros cadastrados
- ✏️ Atualizar informações de um livro
- 🗑️ Remover livro do acervo
- 🔎 *(Em Desenvolvimento) Buscar livros por título ou autor*

### 👤 Usuários
- ➕ Cadastrar usuários
- 🔍 Buscar usuários por **ID** ou **nome**
- 📑 Listar todos os usuários
- ✏️ Atualizar dados de usuários
- 🗑️ Remover usuários

### *📦 Empréstimos (EM DESENVOLVIMENTO)*
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

- **interfaces** → Contratos (regras) das entidades
  - `LivroRegras`
  - `UsuarioRegras`
  - `EmprestimoRegras`

- **exceptions** → Tratamento de erros específicos
  - `EmprestimoException`
  - `UsuarioNaoEncontradoException`
  - `LivroNaoEncontradoException`

- **Main.java** → Classe principal para execução do programa

---

## 📂 Estrutura de Pastas

```bash
src/br/c14lab/biblioteca/
 ├── exceptions/
 │   ├── EmprestimoException.java
 │   ├── LivroNaoEncontradoException.java
 │   └── UsuarioNaoEncontradoException.java
 │
 ├── implementacao/
 │   ├── LivroIMPL.java
 │   ├── UsuarioIMPL.java
 │   └── interfaces/
 │       ├── LivroRegras.java
 │       ├── UsuarioRegras.java
 │       └── EmprestimoRegras.java
 │
 ├── model/
 │   ├── Livro.java
 │   ├── Usuario.java
 │   └── Emprestimo.java
 │
 └── Main.java
````
---

## ⚙️ Recursos  Utilizadas

- ☕ **Java 17+**
- 📦 **Coleções Java (List, HashMap, etc.)**
- ⚡ **Exceções personalizadas** para melhor legibilidade, clareza e controle de erros.
- 🔧 **Maven para gerenciamento de dependências e build do projeto**
---

## ✅ Status do Projeto

- ✔️ Sistema de **usuários** implementado
- ⏳ Busca de livros por autor ainda em desenvolvimento
- ⏳ **Empréstimos** definidos via interface (aguardando implementação completa)
- ⚠️ Exceções personalizadas já estruturadas

---
