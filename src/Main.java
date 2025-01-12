import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Ler as entradas do usuário
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        carregarTarefas(tarefas);
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


                System.out.println("Digite a data de vencimento (dd/MM/yyyy): ");
                String dataEntrada = scanner.nextLine();
                LocalDate dataVencimento;

                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataVencimento = LocalDate.parse(dataEntrada, formatter);
                } catch (Exception e) {
                    System.out.println("Data invalida! A Tarefa não foi criada.");
                    continue; // Volta para o menu principal
                }
                Tarefa novaTarefa = new Tarefa(titulo, descricao, dataVencimento);
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
                salvarTarefas(tarefas);
                System.out.println("--- Saindo do Gerenciador de Tarefas ---");
            } else {
                System.out.println("Opção invalida !!!");
            }

        }

        scanner.close();
    }

    public static void salvarTarefas(ArrayList<Tarefa> tarefas) {
        try (FileWriter writer = new FileWriter("tarefas.txt")) {
            for (Tarefa tarefa : tarefas) {
                writer.write(tarefa.getTitulo() + ";" + tarefa.getDescricao() + ";" + tarefa.isConcluido() + "\n");
            }
            System.out.println("Tarefas salvas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public static void carregarTarefas(ArrayList<Tarefa> tarefas) {
        try (BufferedReader reader = new BufferedReader(new FileReader("tarefas.txt"))){
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length < 4) {
                    System.out.println("Linha inválida no arquivo: " + linha);
                    continue;
                }

                String titulo = partes[0];
                String descricao = partes[1];
                boolean concluida = Boolean.parseBoolean(partes[2]);
                LocalDate dataVencimento;

                try {
                    // Converte a parte 3 para LocalDate

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    dataVencimento = LocalDate.parse(partes[3], formatter);
                } catch (Exception e) {
                    System.out.println("Erro ao ler a data de Vencimento: " + partes[3]);
                    continue; // Ignora esta linha e continua para a proxima
                }
                Tarefa tarefa = new Tarefa(titulo,  descricao, dataVencimento);
                if (concluida) {
                    tarefa.marcarConcluido();
                }
                tarefas.add(tarefa);
            }
            System.out.println("Tarefa carregadas com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
    }
}















