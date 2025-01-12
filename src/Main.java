import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Ler as entradas do usuário
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n === Gerenciador de Tarefas ===");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Remover Tarefa");
            System.out.println("4. Marcar como Concluída");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.println("--- Digite o Titulo da tarefa: ---");
                String titulo = scanner.nextLine();
                System.out.println("Digite a descrição da tarefa: ");
                String descricao = scanner.nextLine();
                Tarefa novaTarefa = new Tarefa(titulo, descricao);
                tarefas.add(novaTarefa);
                System.out.println("Tarefa Adicionada!!");
            } else if (opcao == 2) {
                System.out.println("\n--- Listando Tarefas! ---");
                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa registrada.");
                } else {
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.println((i + 1) + ". " + tarefas.get(i));
                    }
                }
            } else if (opcao == 3) {
                System.out.println("\n--- Remover Tarefa! ---");
                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa para remover.");
                } else {  // Exibe as tarefas
                    System.out.println("Tarefas disponiveis: ");
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.println((i + 1));
                    }

                    // Solicita o numero de tarefas
                    System.out.println("Digite o numero da tarefa que deseja remover: ");
                    int numero = scanner.nextInt();

                    // valida a entrada do usuario
                    if (numero > 0 && numero <= tarefas.size()) {
                        tarefas.remove(numero - 1); // Remove a tarefa
                        System.out.println("Tarefa removida!!");

                    } else {
                        System.out.println("Numero Invalido !!");
                    }
                }


            } else if (opcao == 4) {
                System.out.println("--- Marcar Tarefa como Concluida ---");
                if (tarefas.isEmpty()) {
                    System.out.println("Tarefa não encontrada");
                } else {
                    System.out.println("Tarefas disponiveis:");
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.println((i +1) + ". " + tarefas.get(i));

                    }

                    System.out.println("Escolha a tarefa para marcar como conclida: ");
                    int numero = scanner.nextInt();

                    if (numero > 0 && numero <= tarefas.size()) {
                        tarefas.get(numero - 1).marcarConcluido();
                        System.out.println("Tarefa Concluída!");
                    } else {
                        System.out.println("Numero inválido. Por favor, tente novamente.");
                    }
                }
            } else if (opcao == 0) {
                System.out.println("--- Saindo do Gerenciador de Tarefas ---");
            } else {
                System.out.println("Opção invalida !!!");
            }

        }

        scanner.close();
    }
}















