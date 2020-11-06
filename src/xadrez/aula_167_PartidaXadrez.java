package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;
import xadrez.pecas.aula_159_Torre;
import xadrez.pecas.aula_161_Rei;
import xadrez.aula_167_PartidaXadrez;

public class aula_167_PartidaXadrez
{
	private int turno;
	private aula_151_Cor jogadorAtual;
	private aula_156_Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequemate;
	
	private List<aula_157_Peca> pecasTabuleiro  = new ArrayList<>();
	private List<aula_157_Peca> pecasCapturadas  = new ArrayList<>();
	
	public aula_167_PartidaXadrez()
	{
		tabuleiro = new aula_156_Tabuleiro( 8, 8);
		turno = 1;
		jogadorAtual = aula_151_Cor.BRANCA;
		xeque = false;
		configInicial();
	}
	
	public aula_167_PecaXadrez[][] getPecas()
	{
		aula_167_PecaXadrez[][] matriz = new aula_167_PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (aula_167_PecaXadrez)tabuleiro.peca(i,j);
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
	
	public boolean getXeque()
	{
		return xeque;
	}
	
	public boolean getXequemate()
	{
		return xequemate;
	}
	
	public boolean[][] movimentosPossiveis(aula_154_PosicionamentoXadrez posicaoOrigem)
	{
		aula_159_Posicao posicao = posicaoOrigem.convertePosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public aula_167_PecaXadrez realizarMovimentoXadrez(aula_154_PosicionamentoXadrez posicaoOrigem, aula_154_PosicionamentoXadrez posicaoDestino)
	{
		aula_159_Posicao origem = posicaoOrigem.convertePosicao();
		aula_159_Posicao destino =  posicaoDestino.convertePosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		aula_157_Peca pecaCapturada = realizarMovimento(origem, destino);
		
		if (testeXeque(jogadorAtual))
		{
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new aula_156_ExcecaoXadrez("Não é permitido que o jogador se coloque em xeque");
		}
		
		xeque = (testeXeque(oponente(jogadorAtual) ) ) ? true : false;
		
		if (testeXequemate(oponente(jogadorAtual)))
		{
			xequemate = true;
		}
		else
		{
			proximoTurno();
		}		
		
		return (aula_167_PecaXadrez) pecaCapturada;		
	}
	
	private aula_157_Peca realizarMovimento(aula_159_Posicao origem, aula_159_Posicao destino)
	{
		aula_167_PecaXadrez peca = (aula_167_PecaXadrez)tabuleiro.removerPeca(origem);
		peca.somarContagemMovimentos();
		aula_157_Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(peca, destino);
		
		if (pecaCapturada != null)
		{
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}		
		return pecaCapturada;
	}
	
	private void desfazerMovimento(aula_159_Posicao origem, aula_159_Posicao destino, aula_157_Peca pecaCapturada)
	{
		aula_167_PecaXadrez peca = (aula_167_PecaXadrez)tabuleiro.removerPeca(destino);
		
		tabuleiro.posicionarPeca(peca, origem);
		
		if(pecaCapturada != null)
		{
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(aula_159_Posicao posicao)
	{
		if (!tabuleiro.pecaExistente(posicao))
		{
			throw new aula_156_ExcecaoXadrez("Não existe peça na posição de origem");
		}
		
		if (jogadorAtual != ((aula_167_PecaXadrez)tabuleiro.peca(posicao)).getCor() )
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
	
	private aula_151_Cor oponente(aula_151_Cor cor)
	{
		return (cor == aula_151_Cor.BRANCA) ? aula_151_Cor.PRETA : aula_151_Cor.BRANCA;
	}
	
	private aula_167_PecaXadrez rei(aula_151_Cor cor)
	{
		List<aula_157_Peca> lista = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == cor ).collect(Collectors.toList());
		
		for (aula_157_Peca p : lista)
		{
			if (p instanceof aula_161_Rei)
			{
				return (aula_167_PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor"+ cor +" no tabuleiro");
	}
	
	private boolean testeXeque(aula_151_Cor cor)
	{
		aula_159_Posicao posicaoRei = rei(cor).getPosicionamentoXadrez().convertePosicao();
		List<aula_157_Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == oponente(cor) ).collect(Collectors.toList());
		
		for (aula_157_Peca p : pecasOponente)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()])
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequemate(aula_151_Cor cor)
	{
		if(!testeXeque(cor))
		{
			return false;
		}
		List<aula_157_Peca> lista = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == cor) .collect(Collectors.toList());
		
		for(aula_157_Peca p : lista)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++)
			{
				for(int j = 0; j < tabuleiro.getColunas(); j++)
				{
					if(matriz[i][j])
					{
						aula_159_Posicao origem = ((aula_167_PecaXadrez)p).getPosicionamentoXadrez().convertePosicao();
						aula_159_Posicao destino = new aula_159_Posicao( i, j);
						aula_157_Peca pecaCapturada = realizarMovimento(origem, destino);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeXeque)
						{
							return false;
						}
					}
				}
			}				
		}
		return true;
	}
	
	private void posicionaNovaPeca(char coluna, int linha, aula_167_PecaXadrez peca)
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
		posicionaNovaPeca('h', 7, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('d', 1, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));        
        posicionaNovaPeca('e', 1, new aula_161_Rei(tabuleiro, aula_151_Cor.BRANCA));

        posicionaNovaPeca('b', 8, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));      
        posicionaNovaPeca('a', 8, new aula_161_Rei(tabuleiro, aula_151_Cor.PRETA));
	}
}
