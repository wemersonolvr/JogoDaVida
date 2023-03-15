import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        boolean jogarNovamente = true;// Define uma variável booleana como verdadeira para permitir que o loop while seja executado pelo menos uma vez

        while (jogarNovamente) {// Inicia um loop while para permitir que o usuário jogue o jogo da vida várias vezes.
            System.out.println("Digite o tamanho do tabuleiro:");
            int tamanho = scanner.nextInt();
            JogoDaVida jogo = new JogoDaVida(tamanho);// Cria uma nova instância da classe JogoDaVida com o tamanho do tabuleiro fornecido pelo usuário.
            jogo.iniciarJogo(); // Chama o método iniciarJogo() para iniciar o jogo da vida
            String opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("Q")) { // Verifica se a entrada do usuário é "Q" (ignorando maiúsculas e minúsculas) para determinar se o usuário deseja jogar novamente ou sair do jogo.
               jogarNovamente = false;
            }
          break;
        }
        System.out.println("Obrigado por jogar!");
    }
}