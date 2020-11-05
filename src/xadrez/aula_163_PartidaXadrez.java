package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;
import xadrez.pecas.aula_159_Torre;
import xadrez.pecas.aula_161_Rei;

public class aula_163_PartidaXadrez
{
	private int turno;
	private aula_151_Cor jogadorAtual;
	private aula_156_Tabuleiro tabuleiro;
	
	private List<aula_157_Peca> pecasTabuleiro  = new ArrayList<>();
	private List<aula_157_Peca> pecasCapturadas  = new ArrayList<>();
	
	public aula_163_PartidaXadrez()
	{
		tabuleiro = new aula_156_Tabuleiro( 8, 8);
		turno = 1;
		jogadorAtual = aula_151_Cor.BRANCA;
		configInicial();
	}
	
	public aula_159_PecaXadrez[][] getPecas()
	{
		aula_159_PecaXadrez[][] matriz = new aula_159_PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (aula_159_PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		return matriz;
	}
	
	public int getTurno()
	{
		return turno;
	}
	
	public aula_151_Cor getJogadorAtual()
	{
		return jogadorAtual;
	}
	
	public boolean[][] movimentosPossiveis(aula_154_PosicionamentoXadrez posicaoOrigem)
	{
		aula_159_Posicao posicao = posicaoOrigem.convertePosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public aula_159_PecaXadrez realizaMovimentoPeca(aula_154_PosicionamentoXadrez posicaoOrigem, aula_154_PosicionamentoXadrez posicaoDestino)
	{
		aula_159_Posicao origem = posicaoOrigem.convertePosicao();
		aula_159_Posicao destino =  posicaoDestino.convertePosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		aula_157_Peca pecaCapturada = realizarMovimento(origem, destino);
		proximoTurno();
		return (aula_159_PecaXadrez) pecaCapturada;		
	}
	
	private aula_157_Peca realizarMovimento(aula_159_Posicao origem, aula_159_Posicao destino)
	{
		aula_157_Peca peca = tabuleiro.removerPeca(origem);
		aula_157_Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(peca, destino);
		
		if (pecaCapturada != null)
		{
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(aula_159_Posicao posicao)
	{
		if (!tabuleiro.pecaExistente(posicao))
		{
			throw new aula_156_ExcecaoXadrez("Não existe peça na posição de origem");
		}
		
		if (jogadorAtual != ((aula_159_PecaXadrez)tabuleiro.peca(posicao)).getCor() )
		{
			throw new aula_156_ExcecaoXadrez("A peça escolhida não é sua");
		}
		
		if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel())
		{
			throw new aula_156_ExcecaoXadrez("Não existe movimento possível para a peça selecionada");
		}
	}
	
	private void validarPosicaoDestino(aula_159_Posicao origem, aula_159_Posicao destino)
	{
		if(!tabuleiro.peca(origem).movimentoPossivel(destino))
		{
			throw new aula_156_ExcecaoXadrez("A peça escolhida não poder movimentada para a posição escolhida");
		}
	}
	
	private void posicionaNovaPeca(char coluna, int linha, aula_159_PecaXadrez peca)
	{
		tabuleiro.posicionarPeca(peca, new aula_154_PosicionamentoXadrez(coluna, linha).convertePosicao() );
		pecasTabuleiro.add(peca);
	}
	
	private void proximoTurno()
	{
		turno++;
		jogadorAtual = (jogadorAtual == aula_151_Cor.BRANCA) ? aula_151_Cor.PRETA : aula_151_Cor.BRANCA;
	}
	
	private void configInicial()
	{				
//		posicionaNovaPeca('B', 6, new aula_152_Torre(tabuleiro, aula_151_Cor.BRANCA) );
//		posicionaNovaPeca('E', 8, new aula_152_Rei(tabuleiro, aula_151_Cor.PRETA) );
//		posicionaNovaPeca('E', 1, new aula_152_Rei(tabuleiro, aula_151_Cor.BRANCA) );
//		
		posicionaNovaPeca('c', 1, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('c', 2, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 2, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 2, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('e', 1, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 1, new aula_161_Rei(tabuleiro, aula_151_Cor.BRANCA));

        posicionaNovaPeca('c', 7, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('c', 8, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 7, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 7, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 8, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 8, new aula_161_Rei(tabuleiro, aula_151_Cor.PRETA));
	}
}
