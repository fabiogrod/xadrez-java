package xadrez;

import tabuleiro.aula_151_Tabuleiro;

public class aula_151_PartidaXadrez
{
	private aula_151_Tabuleiro tabuleiro;
	
	public aula_151_PartidaXadrez()
	{
		tabuleiro = new aula_151_Tabuleiro( 8, 8); 
	}
	
	public aula_151_PecaXadrez[][] getPecas()
	{
		aula_151_PecaXadrez[][] matriz = new aula_151_PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (aula_151_PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		return matriz;
	}
}
