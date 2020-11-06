package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_159_Posicao;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;

public class aula_169_Bispo extends aula_167_PecaXadrez
{
	public aula_169_Bispo ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
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
		
		aula_159_Posicao peca = new aula_159_Posicao( 0, 0);
		
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
