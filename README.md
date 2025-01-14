# Gerenciador de Tarefas

Este projeto é um aplicativo de linha de comando desenvolvido em Java para gerenciar tarefas de forma eficiente. Permite adicionar, listar, editar e remover tarefas, além de marcar seu status como pendente ou concluída.

## Funcionalidades

- **Adicionar Tarefas:** Permite criar novas tarefas com descrição e status inicial.
- **Listar Tarefas:** Exibe todas as tarefas cadastradas com suas descrições e status.
- **Editar Tarefas:** Possibilita alterar a descrição ou status de uma tarefa existente.
- **Remover Tarefas:** Permite excluir tarefas que não são mais necessárias.
- **Marcar Status:** Facilita a atualização do status das tarefas entre pendente e concluída.

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Ambiente de Desenvolvimento:** Qualquer IDE compatível com Java (Eclipse, IntelliJ IDEA, etc.)

## Como Executar o Projeto

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/GabrielDutraLima/GerenciadorDeTarefas.git
   ```

2. **Importe o projeto em sua IDE de preferência.**

3. **Compile o projeto:** Certifique-se de que todas as dependências estão configuradas corretamente.

4. **Execute a aplicação:** Inicie a execução a partir da classe principal `Main.java`.

## Estrutura do Projeto

- `Main.java`: Classe principal que contém o método `main` e gerencia a interação com o usuário.
- `Tarefa.java`: Classe que representa o modelo de uma tarefa, com atributos como descrição e status.
- `GerenciadorDeTarefas.java`: Classe responsável por gerenciar a lista de tarefas e as operações relacionadas.
