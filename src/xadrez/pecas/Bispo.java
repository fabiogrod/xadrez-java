package xadrez.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez
{
	public Bispo ( Tabuleiro tabuleiro, Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "B";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{	
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao peca = new Posicao( 0, 0);
		
		//esquerda superior 
		peca.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;			
			peca.setValores(peca.getLinha() - 1, peca.getColuna() - 1);
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//direita superior
		peca.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setValores(peca.getLinha() - 1, peca.getColuna() + 1);

		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//direta inferior
		peca.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setValores(peca.getLinha() + 1, peca.getColuna() + 1);
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//esquerda inferior
		peca.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setValores(peca.getLinha() + 1, peca.getColuna() - 1);

		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		return matriz;
	}	
}
