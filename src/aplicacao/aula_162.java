package aplicacao;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import xadrez.aula_154_PosicionamentoXadrez;
import xadrez.aula_156_ExcecaoXadrez;
import xadrez.aula_162_PartidaXadrez;
import xadrez.aula_159_PecaXadrez;

public class aula_162
{
	public static void main(String[] args)
	{
//		aula_149_Posicao pos = new aula_149_Posicao(3,5); 
//		
//		System.out.println("Vai filhão!! - " + pos);
//		
//		aula_150_Tabuleiro tabuleiro = new aula_150_Tabuleiro( 8, 8);
		
		Locale.setDefault(new Locale("pt","BR") );
		
		Scanner sc = new Scanner(System.in);
		aula_162_PartidaXadrez partidaXadrez =  new aula_162_PartidaXadrez();
		
		while (true)
		{
			try
			{				
				aula_162_IU.limpaTela();
				aula_162_IU.imprimirPartida(partidaXadrez);
				
				System.out.println();
				System.out.print("Origem: ");
				aula_154_PosicionamentoXadrez origem = aula_162_IU.verificarPosicionamentoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				aula_162_IU.limpaTela();
				aula_162_IU.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();			
				System.out.print("Destino: ");
				aula_154_PosicionamentoXadrez destino = aula_162_IU.verificarPosicionamentoXadrez(sc);
				
				aula_159_PecaXadrez pecaCapturada = partidaXadrez.realizaMovimentoPeca(origem, destino);
			}
			catch(aula_156_ExcecaoXadrez e)
			{
				System.out.println(e.getMessage());				
				sc.nextLine();
			}
			catch(InputMismatchException e)
			{
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}
}
