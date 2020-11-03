package xadrez;

import tabuleiro.aula_153_Tabuleiro;
import xadrez.pecas.aula_152_Rei;
import xadrez.pecas.aula_152_Torre;

public class aula_155_PartidaXadrez
{
	private aula_153_Tabuleiro tabuleiro;
	
	public aula_155_PartidaXadrez()
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
	
	private void posicionaNovaPeca(char coluna, int linha, aula_151_PecaXadrez peca)
	{
		tabuleiro.posicionarPeca(peca, new aula_154_PosicionamentoXadrez(coluna, linha).convertePosicao() );
	}
	
	private void configInicial()
	{				
//		posicionaNovaPeca('B', 6, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA) );
//		posicionaNovaPeca('E', 8, new aula_152_Rei(tabuleiro, aula_151_Cor.PRETA) );
//		posicionaNovaPeca('E', 1, new aula_152_Rei(tabuleiro, aula_151_Cor.BRANCA) );
//		
		posicionaNovaPeca('c', 1, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('c', 2, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 2, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 2, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 1, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 1, new aula_152_Rei(tabuleiro, aula_151_Cor.BRANCA));

        posicionaNovaPeca('c', 7, new aula_152_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('c', 8, new aula_152_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 7, new aula_152_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 7, new aula_152_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 8, new aula_152_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 8, new aula_152_Rei(tabuleiro, aula_151_Cor.PRETA));
	}
}
