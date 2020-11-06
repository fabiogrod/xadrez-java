package xadrez.pecas;

import tabuleiro.aula_159_Posicao;
import tabuleiro.aula_156_Tabuleiro;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;

public class aula_159_Torre extends aula_167_PecaXadrez
{
	public aula_159_Torre ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{	
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		aula_159_Posicao p = new aula_159_Posicao( 0, 0);
		
		//acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() );
		
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p))
		{
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1 );
		}
		
		if( getTabuleiro().posicaoExistente(p) && verificaPecaOponente(p) ) 
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p))
		{
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1 );
		}
		
		if( getTabuleiro().posicaoExistente(p) && verificaPecaOponente(p) ) 
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//direta
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p))
		{
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1  );
		}
		
		if( getTabuleiro().posicaoExistente(p) && verificaPecaOponente(p) ) 
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() );
		
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().pecaExistente(p))
		{
			matriz[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1 );
		}
		
		if( getTabuleiro().posicaoExistente(p) && verificaPecaOponente(p) ) 
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}	
}
