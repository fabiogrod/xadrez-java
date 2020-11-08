package tabuleiro;

public class Tabuleiro
{
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) 
	{
		if(linhas < 1 || colunas < 1)
		{
			throw new ExcecaoTabuleiro("Erro ao criar tabuleiro: é necessário pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas]; 
	}
	
	public int getLinhas() {
		return linhas;
	}	

	public int getColunas() {
		return colunas;
	}
	
	public Peca peca(int linha, int coluna)
	{
		if (!posicaoExistente(linha, coluna))
		{
			throw new ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return pecas[posicao.getLinha() ][posicao.getColuna() ];
	}
	
	public void posicionarPeca(Peca peca, Posicao posicao)
	{
		if (pecaExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca removerPeca(Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		
		if (peca(posicao) == null)
		{
			return null;
		}
		Peca aux = peca(posicao);
		aux.posicao = null;
		
		pecas[posicao.getLinha()] [posicao.getColuna()] = null;
		
		return aux;
	}
	
	private boolean posicaoExistente(int linha, int coluna)
	{
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(Posicao posicao)
	{
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean pecaExistente(Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return peca(posicao) != null;
	}
}
