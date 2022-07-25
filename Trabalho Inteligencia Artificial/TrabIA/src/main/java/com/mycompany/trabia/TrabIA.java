package com.mycompany.trabia;
import java.util.Scanner;

/**
 * @author Gabriel Portari
 */
public class TrabIA {

    public static void main(String[] args) {
        int tabuleiro[][] = new int[2][2];
        Operacoes operacao = new Operacoes();
        Scanner scan = new Scanner(System.in);
        
        tabuleiro = operacao.gerarTabuleiro();
   
        
        operacao.mostrarMenu();
        int opmenu = scan.nextInt();
        
        while(opmenu != 5){
            switch(opmenu){
                case 1:
                    operacao.ordenar1(tabuleiro);
                break;
                case 2:
                    operacao.ordenar2(tabuleiro);
                break;
                case 3:
                    tabuleiro = operacao.gerarTabuleiro();
                break;
                case 4:
                    operacao.mostrarTabuleiro(tabuleiro);
                break;
                case 5:
                    System.out.println("Voce escolheu sair.");
                    System.exit(0);
                break;
                default:
                    System.out.println("Operação invalida, tente de novo.");
                break;  
            }
            operacao.mostrarMenu();
            opmenu = scan.nextInt();
        }   
    }
}
