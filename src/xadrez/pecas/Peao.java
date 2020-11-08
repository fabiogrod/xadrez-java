package xadrez.pecas;

import tabuleiro.Tabuleiro;
import tabuleiro.Posicao;
import xadrez.Cor;
import xadrez.PecaXadrez;
import xadrez.PartidaXadrez;

public class Peao extends PecaXadrez
{	
	private PartidaXadrez partidaXadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez)
	{
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
	}
	
	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao peca = new Posicao( 0, 0);
		
		if(getCor() == Cor.BRANCA)
		{
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao peca2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca) && getTabuleiro().posicaoExistente(peca2) && !getTabuleiro().pecaExistente(peca2) && getContagemMovimentos() == 0)
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			//jogada especial en passant
			if(posicao.getLinha() == 3)
			{
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && verificaPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant())	
				{
					matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && verificaPecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant())	
				{
					matriz[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		}
		else 
		{
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao peca2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca) && getTabuleiro().posicaoExistente(peca2) && !getTabuleiro().pecaExistente(peca2) && getContagemMovimentos() == 0)
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			//jogada especial en passant
			if(posicao.getLinha() == 4)
			{
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExistente(esquerda) && verificaPecaOponente(esquerda) && getTabuleiro().peca(esquerda) == partidaXadrez.getEnPassant())	
				{
					matriz[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExistente(direita) && verificaPecaOponente(direita) && getTabuleiro().peca(direita) == partidaXadrez.getEnPassant())	
				{
					matriz[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		
		return matriz;
	}

	@Override
	public String toString()
	{
		return "P";
	}
}
