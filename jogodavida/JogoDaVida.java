import java.util.Scanner;

public class JogoDaVida {
    private int[][] tabuleiro;
    private int tamanho;

    // Construtor para inicializar o tabuleiro com o tamanho especificado
    public JogoDaVida(int tamanho) {
        this.tamanho = tamanho;
        this.tabuleiro = new int[tamanho][tamanho];
    }
      // Método para iniciar o jogo
    public void iniciarJogo() {
         // Gerar um tabuleiro aleatório e exibi-lo
        gerarTabuleiroAleatorio();
        exibirTabuleiro();
         // Criar um objeto Scanner para obter entrada do usuário
        Scanner scanner = new Scanner(System.in);
        int contageracao = 1;
        // Continuar a gerar novas gerações até que o usuário digite "sair"
        while (true) {
            System.out.println("Voce esta na " + contageracao + " Geracao. Pressione ENTER para gerar a proxima geracao OU digite 'Q' para encerrar o jogo.");
            String entrada = scanner.nextLine();
            if (entrada.equalsIgnoreCase("q")) {
                break;
            }
             // Gerar a próxima geração e exibi-la
            gerarProximaGeracao();
            exibirTabuleiro();
            contageracao++;
            
            // Verificar se todas as células estão mortas e perguntar se o usuário deseja jogar novamente
            if (todasCelulasMortas()) {
                System.out.println("Todas as celulas morreram. Deseja jogar novamente? (s/n)");
                entrada = scanner.nextLine();
                if (entrada.equalsIgnoreCase("s")) {
                    // Gerar um novo tabuleiro aleatório e exibi-lo
                    gerarTabuleiroAleatorio();
                    exibirTabuleiro();
                    contageracao=1;
                } else {
                    break;
                }
            }
        }
    }
      // Método para gerar um tabuleiro aleatório
    private void gerarTabuleiroAleatorio() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                  // Atribuir um valor aleatório de 0 ou 1 para cada célula do tabuleiro
                tabuleiro[i][j] = (int) (Math.random()*2);
            }
        }
    }

    private void gerarProximaGeracao() {
    // cria um novo tabuleiro para armazenar a próxima geração
        int[][] novoTabuleiro = new int[tamanho][tamanho];
        // percorre todas as células do tabuleiro atual
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
            // conta quantos vizinhos vivos cada célula tem
                int vizinhosVivos = contarVizinhosVivos(i, j);
                // verifica o estado atual da célula
                if (tabuleiro[i][j] == 1) {
                    if (vizinhosVivos < 2 || vizinhosVivos > 3) {
                        novoTabuleiro[i][j] = 0;
                    } else {
                        novoTabuleiro[i][j] = 1;
                    }
                } else {
                    if (vizinhosVivos == 3) {
                        novoTabuleiro[i][j] = 1;
                    }
                }
            }
        }
        // atualiza o tabuleiro atual com o novo tabuleiro gerado
        tabuleiro = novoTabuleiro;
    }

    private int contarVizinhosVivos(int linha, int coluna) {
    int vizinhosVivos = 0;
    
    // Verificar vizinhos laterais
    if (coluna > 0 && tabuleiro[linha][coluna - 1] == 1) {
        vizinhosVivos++;
    }
    if (coluna < tamanho - 1 && tabuleiro[linha][coluna + 1] == 1) {
        vizinhosVivos++;
    }
    
    // Verificar vizinhos acima e abaixo
    if (linha > 0 && tabuleiro[linha - 1][coluna] == 1) {
        vizinhosVivos++;
    }
    if (linha < tamanho - 1 && tabuleiro[linha + 1][coluna] == 1) {
        vizinhosVivos++;
    }
      // Verificar vizinhos das bordas opostas
    if (coluna == 0 && tabuleiro[linha][tamanho - 1] == 1) {
        vizinhosVivos++;
    }
    if (coluna == tamanho - 1 && tabuleiro[linha][0] == 1) {
        vizinhosVivos++;
    }
    if (linha == 0 && tabuleiro[tamanho - 1][coluna] == 1) {
        vizinhosVivos++;
    }
    if (linha == tamanho - 1 && tabuleiro[0][coluna] == 1) {
        vizinhosVivos++;
    }
    
    return vizinhosVivos;
}
   
    private boolean todasCelulasMortas() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
            if (tabuleiro[i][j] == 1) { // se encontrar uma célula viva, retorna falso (nem todas as células estão mortas)
                return false;
            }
        }
    }
    return true; // se nenhuma célula viva for encontrada, retorna verdadeiro (todas as células estão mortas)
}

private void exibirTabuleiro() {
    System.out.println();
    for (int i = 0; i < tamanho; i++) {
        for (int j = 0; j < tamanho; j++) {
            System.out.print(tabuleiro[i][j] + " "); // imprime o valor da célula no tabuleiro
        }
        System.out.println();
    }
    System.out.println();
  }
}
