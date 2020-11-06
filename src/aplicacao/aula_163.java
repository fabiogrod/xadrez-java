package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import xadrez.aula_154_PosicionamentoXadrez;
import xadrez.aula_156_ExcecaoXadrez;
import xadrez.aula_164_PecaXadrez;
import xadrez.aula_165_PartidaXadrez;

public class aula_163
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
		aula_165_PartidaXadrez partidaXadrez =  new aula_165_PartidaXadrez();
		List<aula_164_PecaXadrez> capturas = new ArrayList<>();		
		
		while (true)
		{
			try
			{				
				aula_165_IU.limpaTela();
				aula_165_IU.imprimirPartida(partidaXadrez, capturas);
				
				System.out.println();
				System.out.print("Origem: ");
				aula_154_PosicionamentoXadrez origem = aula_165_IU.verificarPosicionamentoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				aula_165_IU.limpaTela();
				aula_165_IU.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();			
				System.out.print("Destino: ");
				aula_154_PosicionamentoXadrez destino = aula_165_IU.verificarPosicionamentoXadrez(sc);
				
				aula_164_PecaXadrez pecaCapturada = partidaXadrez.realizarMovimentoXadrez(origem, destino);
				if (pecaCapturada != null)
				{
					capturas.add(pecaCapturada);
				}
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
