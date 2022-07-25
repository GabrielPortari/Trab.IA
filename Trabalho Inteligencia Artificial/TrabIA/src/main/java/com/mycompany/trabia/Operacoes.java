package com.mycompany.trabia;
import java.util.Random;

/**
 *
 * @author Gabriel Portari
 */
public class Operacoes {
   Random rand = new Random();
   
   
   public void mostrarMenu(){
       //Método basica para mostrar as opções do menu
       System.out.println("1 - Ordenar utilzando busca cega");
       System.out.println("2 - Ordenar utilzando busca informada");
       System.out.println("3 - Gerar novo tabuleiro");
       System.out.println("4 - Mostrar tabuleiro");
       System.out.println("5 - Sair");
       System.out.print("Selecione a opção: ");
   }
   
   
   public void mostrarTabuleiro(int[][] tabuleiro){
       //Apenas mostra o tabuleiro na tela
       System.out.println("");
       for(int i=0; i<3; i++){
           for(int j=0; j<3; j++){
               System.out.print(tabuleiro[i][j]);
           }
           System.out.println("");
       }
       System.out.println("");
   }
   
   
   public int[][] gerarTabuleiro(){
       //Esté método gera o tabuleiro aleatóriamente
       int tabuleiro[][] = new int[3][3];
       System.out.println("");
       /*
       Gera o tabuleiro inicial
       {123
        456
        780}
       */
       tabuleiro[0][0] = 1;
       tabuleiro[0][1] = 2;
       tabuleiro[0][2] = 3;
       tabuleiro[1][0] = 4;
       tabuleiro[1][1] = 5;
       tabuleiro[1][2] = 6;
       tabuleiro[2][0] = 7;
       tabuleiro[2][1] = 8;
       tabuleiro[2][2] = 0;
       
       //Posição inicial do 0
       int x = 2;
       int y = 2;
       
       //Quantidade de movimentos para embaralhar
       int embaralhar = rand.nextInt(100, 10000);
       int iteracao = 0;
       
       //Ao gerar um novo tabuleiro, o algoritmo irá embaralhar uma quantidade aleatória de vezes entre 100 - 10000
       while(iteracao < embaralhar){
           int dir = rand.nextInt(1, 5); //Supomos que 1 - cima, 2 - baixo, 3 - direita, 4 - esquerda
           /*
           Este while seleciona uma direção aleatória para mover o número zero, e o move aleatóriamente dado o valor dir, que é gerado entre 1-4
           deste modo o zero se move aleatóriamente pelo tabuleiro pelo valor dado entre 100 e 10000
           cada case representa uma direção, e a operação realizada dentro é apenas inverter o valor de 0 com o valor da posição selecionada
           */
           switch(dir){
               case 1: // cima
                   if(x-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x-1][y];
                       tabuleiro[x-1][y] = 0;
                       x--;
                       iteracao++;
                   }
               break;
               
               case 2: // baixo
                   if(x+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x+1][y];
                       tabuleiro[x+1][y] = 0;
                       x++;
                       iteracao++;
                   }
               break;
               
               case 3: // direita
                   if(y+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x][y+1];
                       tabuleiro[x][y+1] = 0;
                       y++;
                       iteracao++;
                   }
               break;
               
               case 4: // esquerda
                   if(y-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x][y-1];
                       tabuleiro[x][y-1] = 0;
                       y--;
                       iteracao++;
                   }
               break;
           }
       }
       System.out.println("Um novo tabuleiro foi gerado\n");
       return tabuleiro;
   }
   
   public boolean testarTabuleiro(int[][] tabuleiro){
       //Este método testa se o tabuleiro está resolvido, retorna false até que o tabuleiro esteja corretamente resolvido.
       return(tabuleiro[0][0] == 1 && tabuleiro[0][1] == 2 &&  tabuleiro[0][2] == 3 && tabuleiro[1][0] == 4 && tabuleiro[1][1] == 5 && tabuleiro[1][2] == 6 && tabuleiro[2][0] == 7 && tabuleiro[2][1] == 8 && tabuleiro[2][2] == 0);
   }
   
   
   public int[] buscaZero(int[][] tabuleiro){
       //Este método retorna a posição do zero, para que possamos alterar os valores do tabuleiro a partir do zero
       int pos[] = new int[2];
       for(int i=0;i<3;i++){
          for(int j=0;j<3;j++){
              if(tabuleiro[i][j] == 0){
                  pos[0] = i;
                  pos[1] = j;
              }
          } 
       }
       return pos;
   }
   
   
   public int[][] ordenar1(int[][] tabuleiro){
       System.out.println("");
        /*
        Primeira função de ordenação, segue o mesmo padrão do embaralhamento.
        Gera uma direção aleatória até que o tabuleiro esteja resolvido, enquanto não estiver, fica movendo o zero aleatóriamente até resolver.
        */   
        long iteracao = 0;
        int pos[] = new int[2];
        
        //x e y recebem a posição do zero
        pos = buscaZero(tabuleiro);
        int x = pos[0];
        int y = pos[1];
        
        
        Boolean ordenado = testarTabuleiro(tabuleiro);
        int dirant = 10;
        while(!ordenado){
            int dir = rand.nextInt(1, 5); // Gera um número aleatório baseado nas direções: 1 - cima, 2 - baixo, 3 - direita, 4 - esquerda
            
            /*
            Este while permite que o valor do zero não fique indo na direção oposta a anterior
            as vezes pode acontecer do número aleatório ficar indo cima baixo cima baixo cima baixo
            ou esquerda direita esquerda direita, esse loop evita que isso aconteça,
            pois se cima = 1 e baixo = 2; se a direção selecionada + a anterior = 3, significa que ele foi pra cima e está tentando ir pra baixo,
            deste modo geramos outro valor para que isso não aconteça
            */
            while(dirant+dir == 3 || dirant+dir == 7){
                dir = rand.nextInt(1, 5); 
            }
            switch(dir){ //Switch para selecionar a direção
                case 1: // cima
                   if(x-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x-1][y];
                       tabuleiro[x-1][y] = 0;
                       x--;
                       iteracao++;
                   }
               break;
               
               case 2: // baixo
                   if(x+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x+1][y];
                       tabuleiro[x+1][y] = 0;
                       x++;
                       iteracao++;
                   }
               break;
               
               case 3: // direita
                   if(y+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x][y+1];
                       tabuleiro[x][y+1] = 0;
                       y++;
                       iteracao++;
                   }
               break;
               
               case 4: // esquerda
                   if(y-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x][y-1];
                       tabuleiro[x][y-1] = 0;
                       y--;
                       iteracao++;
                   }
               break;
            }
            //Apos o movimento, testa se o tabuleiro está ordenado
            ordenado = testarTabuleiro(tabuleiro);
            
        }
        //Mostra o número de iterações, e retorna o tabuleiro resolvido
        System.out.println("O algoritmo levou " + iteracao + " passos para ordenar\n");
        return tabuleiro;
   }
   
   public int contaPecas(int[][] tabuleiro){
       /*
       Esté método usa a heuristica h2, contando a distancia de cada peça
       até a sua posição correta, utilizando a distancia manhattam (|x1 - x2| + |y1 - y2|)
       */
       int pecasErradas = 0;
       for(int i=0; i<3; i++){
           for(int j=0; j<3; j++){
               if(tabuleiro[i][j] == 1){
                   pecasErradas = pecasErradas + Math.abs(0-i) + Math.abs(0-j);
               }
               if(tabuleiro[i][j] == 2){
                   pecasErradas = pecasErradas + Math.abs(0-i) + Math.abs(1-j);
               }
               if(tabuleiro[i][j] == 3){
                   pecasErradas = pecasErradas + Math.abs(0-i) + Math.abs(2-j);
               }
               if(tabuleiro[i][j] == 4){
                   pecasErradas = pecasErradas + Math.abs(1-i) + Math.abs(0-j);
               }
               if(tabuleiro[i][j] == 5){
                   pecasErradas = pecasErradas + Math.abs(1-i) + Math.abs(1-j);
               }
               if(tabuleiro[i][j] == 6){
                   pecasErradas = pecasErradas + Math.abs(1-i) + Math.abs(2-j);
               }
               if(tabuleiro[i][j] == 7){
                   pecasErradas = pecasErradas + Math.abs(2-i) + Math.abs(0-j);
               }
               if(tabuleiro[i][j] == 8){
                   pecasErradas = pecasErradas + Math.abs(2-i) + Math.abs(1-j);
               }
               if(tabuleiro[i][j] == 0){
                   pecasErradas = pecasErradas + Math.abs(2-i) + Math.abs(2-j);
               }
           }
       }
       return pecasErradas;
   }
   
   public int melhorOpcao(int[][] tabuleiro){
       /*
       Este método utiliza a função acima, testa cada direção e salva seu valor heuristico
       ao fim de todos os testes, retorna a direção com o menor valor para que o algoritmo siga
       */
       int pos[] = new int[2];
       int cima=50, baixo=50, esquerda=50, direita=50;
       
       pos = buscaZero(tabuleiro);
       int x = pos[0];
       int y = pos[1];
       
       /*
       Esses ifs realizam a troca do valor do 0 em todos os movimentos possiveis
       apos a troca checa a função heuristica de cada movimento,
       e ao fim das trocas retorna o menor valor heuristico
       caso algo dê errado, retorna 0 para gerar outra direção aleatoria
       */
       if(x-1 >= 0){ // cima
           tabuleiro[x][y] = tabuleiro[x-1][y];
           tabuleiro[x-1][y] = 0;
           cima = contaPecas(tabuleiro);
           tabuleiro[x-1][y] = tabuleiro[x][y];
           tabuleiro[x][y] = 0;
       }
       if(x+1 <= 2){ // baixo
           tabuleiro[x][y] = tabuleiro[x+1][y];
           tabuleiro[x+1][y] = 0;
           baixo = contaPecas(tabuleiro);
           tabuleiro[x+1][y] = tabuleiro[x][y];
           tabuleiro[x][y] = 0;
       }
       if(y+1 <= 2){ // direita
           tabuleiro[x][y] = tabuleiro[x][y+1];
           tabuleiro[x][y+1] = 0;
           direita = contaPecas(tabuleiro);
           tabuleiro[x][y+1] = tabuleiro[x][y];
           tabuleiro[x][y] = 0;
       }
       if(y-1 >= 0){ //esquerda
           tabuleiro[x][y] = tabuleiro[x][y-1];
           tabuleiro[x][y-1] = 0;
           esquerda = contaPecas(tabuleiro);
           tabuleiro[x][y-1] = tabuleiro[x][y];
           tabuleiro[x][y] = 0;
       }
       /*
       Se cima for a menor heuristica retorna 1
       Se baixo for a menor heuristica retorna 2
       Se direita for a menor heuristica retorna 3
       Se esquerda for a menor heuristica retorna 4
       Caso algo dê errado retorna 0
       */
       if(cima <= baixo && cima <= direita && cima <= esquerda){
           return 1;
       }
       if(baixo <= cima && baixo <= direita && baixo <= esquerda){
           return 2;
       }
       if(direita <= cima && direita <= baixo && direita <= esquerda){
           return 3;
       }
       if(esquerda <= cima && esquerda <= baixo && esquerda <= direita){
           return 4;
       }
       return 0;
   }
   
   public int[][] ordenar2(int[][] tabuleiro){
       System.out.println("");
        /*
       Segundo algoritmo de ordenação, diferente do primeiro, esse utiliza a heuristica descrita acima.
       a direção selecionada se dá a partir do menor valor heuristico retornado nos métodos acima
       */
        int iteracao = 0;
        int pos[] = new int[2];
        int dirant = 10;
        
        //Salvar posição inicial do zero
        pos = buscaZero(tabuleiro);
        int x = pos[0];
        int y = pos[1];
        
        //Teste para ver se o tabuleiro está ordenado
        Boolean ordenado = testarTabuleiro(tabuleiro);
        
        while(!ordenado){
            int dir = melhorOpcao(tabuleiro); // Semelhante as outras vezes que apareceu, nada de novo: 1 - cima, 2 - baixo, 3 - direita, 4 - esquerda*/
            while(dirant+dir == 3 || dirant+dir == 7 || dir == 0){
                /*
                Semelhante ao primeiro algoritmo de ordenação, este faz a mesma coisa, pois as vezes o algoritmo trava seguindo a melhor heuristica,
                e fica se repetindo no mesmo lugar infinitamente, esse loop evita que isso aconteça.
                */
                dir = rand.nextInt(1, 5); 
            }
            switch(dir){ // Switch para a direção escolhida, nada de novo
                case 1: // cima
                   if(x-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x-1][y];
                       tabuleiro[x-1][y] = 0;
                       x--;
                       iteracao++;
                   }
               break;
               
               case 2: // baixo
                   if(x+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x+1][y];
                       tabuleiro[x+1][y] = 0;
                       x++;
                       iteracao++;
                   }
               break;
               
               case 3: // direita
                   if(y+1 <= 2){
                       tabuleiro[x][y] = tabuleiro[x][y+1];
                       tabuleiro[x][y+1] = 0;
                       y++;
                       iteracao++;
                   }
               break;
               
               case 4: // esquerda
                   if(y-1 >= 0){
                       tabuleiro[x][y] = tabuleiro[x][y-1];
                       tabuleiro[x][y-1] = 0;
                       y--;
                       iteracao++;
                   }
               break;
            }
            //direção anterior = direção atual para o teste no inicio do loop, para não travar no mesmo lugar
            //Seguido do teste de ordenação do tabuleiro
            dirant = dir;
            ordenado = testarTabuleiro(tabuleiro);
        }
        System.out.println("O algoritmo levou " + iteracao + " passos para ordenar\n");
        return tabuleiro;
   }
}
