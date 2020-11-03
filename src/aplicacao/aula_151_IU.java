package aplicacao;

import xadrez.aula_151_PecaXadrez;

public class aula_151_IU
{
	public static void imprimirTabuleiro(aula_151_PecaXadrez[][] pecas)
	{
		for (int i =0; i< pecas.length; i++)
		{
			System.out.print( (8-i) + " ");
			for (int j =0; j< pecas.length; j++)
			{
				imprimirPeca(pecas[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A B C D E F G H");
	}
	
	private static void imprimirPeca(aula_151_PecaXadrez peca)
	{
		if (peca == null)
		{
			System.out.print("-");
		}
		else
		{
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
