package xadrez;

import tabuleiro.aula_149_Posicao;
import tabuleiro.aula_153_Tabuleiro;
import xadrez.pecas.aula_152_Rei;
import xadrez.pecas.aula_152_Torre;

public class aula_153_PartidaXadrez
{
	private aula_153_Tabuleiro tabuleiro;
	
	public aula_153_PartidaXadrez()
	{
		tabuleiro = new aula_153_Tabuleiro( 8, 8);
		configInicial();
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
	
	private void configInicial()
	{				
		tabuleiro.posicionarPeca(new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA), new aula_149_Posicao( 2, 1 ) );
		tabuleiro.posicionarPeca(new aula_152_Rei(tabuleiro, aula_151_Cor.PRETA), new aula_149_Posicao( 0, 4 ) );
		tabuleiro.posicionarPeca(new aula_152_Rei(tabuleiro, aula_151_Cor.BRANCA), new aula_149_Posicao( 7, 4 ) );
	}
}
