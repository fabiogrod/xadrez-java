package aplicacao;

import java.util.Scanner;

import xadrez.aula_154_PosicionamentoXadrez;
import xadrez.aula_156_PartidaXadrez;
import xadrez.aula_156_PecaXadrez;

public class aula_156
{
	public static void main(String[] args)
	{
//		aula_149_Posicao pos = new aula_149_Posicao(3,5); 
//		
//		System.out.println("Vai filhão!! - " + pos);
//		
//		aula_150_Tabuleiro tabuleiro = new aula_150_Tabuleiro( 8, 8);
		
		Scanner sc = new Scanner(System.in);
		aula_156_PartidaXadrez partidaXadrez =  new aula_156_PartidaXadrez();
		
		while (true)
		{
			aula_156_IU.imprimirTabuleiro(partidaXadrez.getPecas());
			
			System.out.println();
			System.out.print("Origem: ");
			aula_154_PosicionamentoXadrez origem = aula_156_IU.verificarPosicionamentoXadrez(sc);
			
			System.out.println();			
			System.out.print("Destino: ");
			aula_154_PosicionamentoXadrez destino = aula_156_IU.verificarPosicionamentoXadrez(sc);
			
			aula_156_PecaXadrez pecaCapturada = partidaXadrez.verificarMovimentoPeca(origem, destino);
		}
	}
}
