package xadrez;

import tabuleiro.aula_149_Posicao;
import tabuleiro.aula_156_Peca;
import tabuleiro.aula_156_Tabuleiro;
import xadrez.pecas.aula_156_Rei;
import xadrez.pecas.aula_156_Torre;

public class aula_156_PartidaXadrez
{
	private aula_156_Tabuleiro tabuleiro;
	
	public aula_156_PartidaXadrez()
	{
		tabuleiro = new aula_156_Tabuleiro( 8, 8);
		configInicial();
	}
	
	public aula_156_PecaXadrez[][] getPecas()
	{
		aula_156_PecaXadrez[][] matriz = new aula_156_PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (aula_156_PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		return matriz;
	}
	
	public aula_156_PecaXadrez verificarMovimentoPeca(aula_154_PosicionamentoXadrez posicaoOrigem, aula_154_PosicionamentoXadrez posicaoDestino)
	{
		aula_149_Posicao origem = posicaoOrigem.convertePosicao();
		aula_149_Posicao destino =  posicaoDestino.convertePosicao();
		validarPosicaoOrigem(origem);
		aula_156_Peca pecaCapturada = realizarMovimento(origem, destino);
		return (aula_156_PecaXadrez) pecaCapturada;		
	}
	
	private aula_156_Peca realizarMovimento(aula_149_Posicao origem, aula_149_Posicao destino)
	{
		aula_156_Peca p = tabuleiro.removerPeca(origem);
		aula_156_Peca pc = tabuleiro.removerPeca(destino);
		
		tabuleiro.posicionarPeca(p, destino);
		return pc;
	}
	
	private void validarPosicaoOrigem(aula_149_Posicao posicao)
	{
		if (!tabuleiro.pecaExistente(posicao))
		{
			throw new aula_156_ExcecaoXadrez("Não existe peça na posição de origem");
		}
	}
	
	private void posicionaNovaPeca(char coluna, int linha, aula_156_PecaXadrez peca)
	{
		tabuleiro.posicionarPeca(peca, new aula_154_PosicionamentoXadrez(coluna, linha).convertePosicao() );
	}
	
	private void configInicial()
	{				
//		posicionaNovaPeca('B', 6, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA) );
//		posicionaNovaPeca('E', 8, new aula_152_Rei(tabuleiro, aula_151_Cor.PRETA) );
//		posicionaNovaPeca('E', 1, new aula_152_Rei(tabuleiro, aula_151_Cor.BRANCA) );
//		
		posicionaNovaPeca('c', 1, new aula_156_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('c', 2, new aula_156_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 2, new aula_156_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 2, new aula_156_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 1, new aula_156_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 1, new aula_156_Rei(tabuleiro, aula_151_Cor.BRANCA));

        posicionaNovaPeca('c', 7, new aula_156_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('c', 8, new aula_156_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 7, new aula_156_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 7, new aula_156_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 8, new aula_156_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 8, new aula_156_Rei(tabuleiro, aula_151_Cor.PRETA));
	}
}
