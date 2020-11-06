package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import xadrez.aula_154_PosicionamentoXadrez;
import xadrez.aula_156_ExcecaoXadrez;
import xadrez.aula_168_PartidaXadrez;
import xadrez.aula_167_PecaXadrez;

public class aula_166
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
		aula_168_PartidaXadrez partidaXadrez =  new aula_168_PartidaXadrez();
		List<aula_167_PecaXadrez> capturas = new ArrayList<>();		
		
		while (!partidaXadrez.getXequemate())
		{
			try
			{				
				aula_166_IU.limpaTela();
				aula_166_IU.imprimirPartida(partidaXadrez, capturas);
				
				System.out.println();
				System.out.print("Origem: ");
				aula_154_PosicionamentoXadrez origem = aula_166_IU.verificarPosicionamentoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				aula_166_IU.limpaTela();
				aula_166_IU.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();			
				System.out.print("Destino: ");
				aula_154_PosicionamentoXadrez destino = aula_166_IU.verificarPosicionamentoXadrez(sc);
				
				aula_167_PecaXadrez pecaCapturada = partidaXadrez.realizarMovimentoXadrez(origem, destino);
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
		aula_166_IU.limpaTela();
		aula_166_IU.imprimirPartida(partidaXadrez, capturas);
	}
}
