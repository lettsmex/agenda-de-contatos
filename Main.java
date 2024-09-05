import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Contato> listaDeContatos = new ArrayList<>();
        boolean loop = true;

        while (loop) {
            menu(listaDeContatos);

            int escolha;
            try {
                escolha = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                input.nextLine();
                continue;
            }
            input.nextLine();

            switch (escolha) {
                case 1:
                    adicionar(listaDeContatos, input);
                    break;
                case 2:
                    detalhar(listaDeContatos, input);
                    break;
                case 3:
                    editar(listaDeContatos, input);
                    break;
                case 4:
                    remover(listaDeContatos, input);
                    break;
                case 5:
                    listarFavoritos(listaDeContatos);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    loop = false;
                    break;
                default:
                    System.out.println("Número inválido, por favor digite um número válido.");
                    break;
            }
        }
    }

    public static void menu(List<Contato> listaDeContatos) {
        System.out.println("\n##################");
        System.out.println("##### AGENDA #####");
        System.out.println("##################");
        System.out.println("\n>>>> Contatos <<<<");
        for (int i = 0; i < listaDeContatos.size(); i++) {
            Contato contato = listaDeContatos.get(i);
            System.out.println("Id = " + (i + 1) + "   | Nome: " + contato.getNome() + " | Número: " + contato.getNumero());
        }
        System.out.println("\n>>>> Menu Contato <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Detalhar Contato");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Remover Contato");
        System.out.println("5 - Listar Contatos Favoritos");
        System.out.println("0 - Sair");
        System.out.println("\nEscolha uma opção: ");
    }

    public static void listarFavoritos(List<Contato> listaDeContatos) {
        System.out.println("\n>>>> Contatos Favoritos <<<<");
        boolean temFavoritos = false;
        for (Contato contato : listaDeContatos) {
            if (contato instanceof ContatoFavorito) {
                System.out.println("Nome: " + contato.getNome() + " | Número: " + contato.getNumero());
                temFavoritos = true;
            }
        }
        if (!temFavoritos) {
            System.out.println("Nenhum contato favorito encontrado.");
        }
    }

    public static void adicionar(List<Contato> listaDeContatos, Scanner input) {
        System.out.println("Por favor, digite o nome do contato que deseja adicionar:");
        String nome = input.nextLine();
        if (contatoExistePorNome(listaDeContatos, nome)) {
            System.out.println("Esse nome já é de um contato salvo na agenda.\n");
            return;
        }

        System.out.println("Agora, digite o número do contato em questão:");
        long numero = 0;
        boolean loopNumero = true;
        while (loopNumero) {
            try {
                numero = input.nextLong();
                input.nextLine();
                if (contatoExistePorNumero(listaDeContatos, numero)) {
                    System.out.println("Esse número já é de um contato salvo na agenda.\n");
                    return;
                }
                loopNumero = false;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números.");
                input.nextLine();
            }
        }

        System.out.println("Digite o E-mail do contato em questão:");
        String email = input.nextLine();
        if (contatoExistePorEmail(listaDeContatos, email)) {
            System.out.println("Esse E-mail já é de um contato salvo na agenda.\n");
            return;
        }

        System.out.println("Deseja adicionar um endereço? (sim/não)");
        String endereco = null;
        if (input.nextLine().equalsIgnoreCase("sim")) {
            System.out.println("Digite o endereço:");
            endereco = input.nextLine();
        }

        System.out.println("Deseja adicionar uma data de aniversário? (sim/não)");
        String aniversario = null;
        if (input.nextLine().equalsIgnoreCase("sim")) {
            System.out.println("Digite a data de aniversário (dd/mm):");
            aniversario = input.nextLine();
        }

        System.out.println("Deseja marcar este contato como favorito? (sim/não)");
        boolean favorito = input.nextLine().equalsIgnoreCase("sim");

        Contato contato;
        if (favorito) {
            contato = new ContatoFavorito(nome, numero, email, endereco, aniversario);
        } else {
            contato = new Contato(nome, numero, email, endereco, aniversario);
        }

        listaDeContatos.add(contato);
        System.out.println("Contato adicionado com sucesso.");
    }

    public static void remover(List<Contato> listaDeContatos, Scanner input) {
        System.out.println("Por favor, digite o número do contato que deseja remover:");
        long numeroParaConsulta = 0;
        boolean loopNumero = true;
        while (loopNumero) {
            try {
                numeroParaConsulta = input.nextLong();
                input.nextLine();
                loopNumero = false;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números.");
                input.nextLine();
            }
        }

        Contato contato = buscarContatoPorNumero(listaDeContatos, numeroParaConsulta);
        if (contato != null) {
            listaDeContatos.remove(contato);
            System.out.println("Contato deletado com sucesso.");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public static void detalhar(List<Contato> listaDeContatos, Scanner input) {
        System.out.println("Por favor, digite o número do contato que deseja ver todos os detalhes:");
        long numeroParaConsulta = 0;
        boolean loopNumero = true;
        while (loopNumero) {
            try {
                numeroParaConsulta = input.nextLong();
                input.nextLine();
                loopNumero = false;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números.");
                input.nextLine();
            }
        }

        Contato contato = buscarContatoPorNumero(listaDeContatos, numeroParaConsulta);
        if (contato != null) {
            System.out.println(contato);
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public static void editar(List<Contato> listaDeContatos, Scanner input) {
        System.out.println("Por favor, digite o número do contato que deseja alterar:");
        long numeroParaConsulta = 0;
        boolean loopNumero = true;
        while (loopNumero) {
            try {
                numeroParaConsulta = input.nextLong();
                input.nextLine();
                loopNumero = false;
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite apenas números.");
                input.nextLine();
            }
        }

        Contato contato = buscarContatoPorNumero(listaDeContatos, numeroParaConsulta);
        if (contato != null) {
            System.out.println("O que você deseja alterar?");
            System.out.println("1 - Nome do Contato");
            System.out.println("2 - Número do Contato");
            System.out.println("3 - E-mail do Contato");
            System.out.println("4 - Endereço do Contato");
            System.out.println("5 - Aniversário do Contato");
            System.out.println("6 - Marcar/Desmarcar como Favorito");
            System.out.println("(Digite o número da opção selecionada)");
            int escolherAlterar = input.nextInt();
            input.nextLine();

            switch (escolherAlterar) {
                case 1:
                    System.out.println("Digite o novo nome do contato:");
                    contato.setNome(input.nextLine());
                    break;
                case 2:
                    System.out.println("Digite o novo número do contato:");
                    try {
                        contato.setNumero(input.nextLong());
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite apenas números.");
                        input.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Digite o novo e-mail do contato:");
                    contato.setEmail(input.nextLine());
                    break;
                case 4:
                    System.out.println("Digite o novo endereço:");
                    contato.setEndereco(input.nextLine());
                    break;
                case 5:
                    System.out.println("Digite a nova data de aniversário:");
                    contato.setAniversario(input.nextLine());
                    break;
                case 6:
                    if (contato instanceof ContatoFavorito) {
                        System.out.println("Desmarcando como favorito...");
                        listaDeContatos.remove(contato);
                        Contato contatoAtualizado = new Contato(contato.getNome(), contato.getNumero(), contato.getEmail(), contato.getEndereco(), contato.getAniversario());
                        listaDeContatos.add(contatoAtualizado);
                    } else {
                        System.out.println("Marcando como favorito...");
                        listaDeContatos.remove(contato);
                        Contato contatoAtualizado = new ContatoFavorito(contato.getNome(), contato.getNumero(), contato.getEmail(), contato.getEndereco(), contato.getAniversario());
                        listaDeContatos.add(contatoAtualizado);
                    }
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }

            System.out.println("Contato atualizado com sucesso.");
        } else {
            System.out.println("Número não encontrado, tente novamente.");
        }
    }


    public static boolean contatoExistePorNome(List<Contato> listaDeContatos, String nome) {
        return listaDeContatos.stream().anyMatch(contato -> contato.getNome().equalsIgnoreCase(nome));
    }

    public static boolean contatoExistePorNumero(List<Contato> listaDeContatos, long numero) {
        return listaDeContatos.stream().anyMatch(contato -> contato.getNumero() == numero);
    }

    public static boolean contatoExistePorEmail(List<Contato> listaDeContatos, String email) {
        return listaDeContatos.stream().anyMatch(contato -> contato.getEmail().equalsIgnoreCase(email));
    }

    public static Contato buscarContatoPorNumero(List<Contato> listaDeContatos, long numero) {
        return listaDeContatos.stream().filter(contato -> contato.getNumero() == numero).findFirst().orElse(null);
    }
}



