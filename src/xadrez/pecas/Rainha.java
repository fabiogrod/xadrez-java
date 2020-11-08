package xadrez.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rainha extends PecaXadrez
{
	public Rainha ( Tabuleiro tabuleiro, Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "R";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{	
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao peca = new Posicao( 0, 0);
		
		//acima
		peca.setValores(posicao.getLinha() - 1, posicao.getColuna() );
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setLinha(peca.getLinha() - 1 );
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//esquerda
		peca.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setColuna(peca.getColuna() - 1 );
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//direta
		peca.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setColuna(peca.getColuna() + 1  );
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
		//abaixo
		peca.setValores(posicao.getLinha() + 1, posicao.getColuna() );
		
		while(getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setLinha(peca.getLinha() + 1 );
		}
		
		if( getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca) ) 
		{
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}
		
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