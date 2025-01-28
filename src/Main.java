import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Tarefa> tarefas = TarefaDAO.carregarTarefas(); // Agora carrega do MySQL
        Collections.sort(tarefas);
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome o enter extra

            switch (opcao) {
                case 1 -> adicionarTarefa(scanner, tarefas);
                case 2 -> listarTarefas(tarefas);
                case 3 -> removerTarefa(scanner, tarefas);
                case 4 -> marcarComoConcluida(scanner, tarefas);
                case 5 -> filtrarTarefas(scanner, tarefas);
                case 0 -> System.out.println("--- Saindo do Gerenciador de Tarefas ---");
                default -> System.out.println("Opção inválida!!");
            }
        }
        scanner.close();
    }

    public static void exibirMenu() {
        System.out.println("\n === Gerenciador de Tarefas ===");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Tarefas");
        System.out.println("3. Remover Tarefa");
        System.out.println("4. Marcar como Concluída");
        System.out.println("5. Filtrar Tarefas por Status");
        System.out.println("0. Sair");
        System.out.println("Escolha uma opção: ");
    }

    public static void adicionarTarefa(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.println("--- Digite o Título da tarefa: ---");
        String titulo = scanner.nextLine();

        System.out.println("Escolha a prioridade da tarefa (baixa, média, alta): ");
        String prioridade = scanner.nextLine().toLowerCase();
        if (!prioridade.equals("baixa") && !prioridade.equals("média") && !prioridade.equals("alta")) {
            System.out.println("Prioridade inválida. Tarefa não foi criada.");
            return;
        }

        System.out.println("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine();

        System.out.println("Digite a data de vencimento (dd/MM/yyyy): ");
        String dataEntrada = scanner.nextLine();
        LocalDate dataVencimento;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataVencimento = LocalDate.parse(dataEntrada, formatter);
            Tarefa novaTarefa = new Tarefa(0, titulo, descricao, dataVencimento, prioridade);
            TarefaDAO.salvarTarefa(novaTarefa); // Agora salva no banco
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Data inválida! A tarefa não foi salva.");
        }
    }

    public static void listarTarefas(ArrayList<Tarefa> tarefas) {
        System.out.println("\n--- Listando Tarefas! ---");
        tarefas = TarefaDAO.carregarTarefas(); // Atualiza a lista com os dados do banco
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa registrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println("ID: " + tarefa.getId() + " | " + tarefa);
            }
        }
    }


    public static void removerTarefa(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.println("\n--- Remover Tarefa! ---");
        listarTarefas(tarefas);
        System.out.println("Digite o ID da tarefa que deseja remover: ");
        int id = scanner.nextInt();

        TarefaDAO.excluirTarefa(id); // Remove do banco
        System.out.println("Tarefa removida!");
    }

    public static void marcarComoConcluida(Scanner scanner, ArrayList<Tarefa> tarefas) throws SQLException {
        System.out.println("--- Marcar Tarefa como Concluída ---");
        listarTarefas(tarefas);
        System.out.println("Digite o ID da tarefa que deseja marcar como concluída: ");
        int id = scanner.nextInt();

        Tarefa tarefa = TarefaDAO.buscarTarefaPorId(id); // Buscando diretamente no banco

        if (tarefa != null) {
            tarefa.marcarConcluido();
            TarefaDAO.atualizarTarefa(tarefa); // Atualiza no banco
            System.out.println("✅ Tarefa marcada como concluída!");
        } else {
            System.out.println("❌ ID inválido. Nenhuma tarefa foi encontrada.");
        }
    }


    public static void filtrarTarefas(Scanner scanner, ArrayList<Tarefa> tarefas) {
        System.out.println("\n--- Filtrar Tarefas ---");
        System.out.println("1. Mostrar apenas tarefas pendentes");
        System.out.println("2. Mostrar apenas tarefas concluídas");
        System.out.print("Escolha uma opção: ");

        int filtro = scanner.nextInt();
        scanner.nextLine();

        tarefas = TarefaDAO.carregarTarefas();

        if (filtro == 1) {
            System.out.println("\n--- Tarefas Pendentes ---");
            for (Tarefa tarefa : tarefas) {
                if (!tarefa.isConcluido()) {
                    System.out.println(tarefa);
                }
            }
        } else if (filtro == 2) {
            System.out.println("\n--- Tarefas Concluídas ---");
            for (Tarefa tarefa : tarefas) {
                if (tarefa.isConcluido()) {
                    System.out.println(tarefa);
                }
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }
}
