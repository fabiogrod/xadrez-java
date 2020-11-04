package tabuleiro;

public class aula_156_Tabuleiro
{
	private int linhas;
	private int colunas;
	private aula_156_Peca[][] pecas;
	
	public aula_156_Tabuleiro(int linhas, int colunas) 
	{
		if(linhas < 1 || colunas < 1)
		{
			throw new aula_153_ExcecaoTabuleiro("Erro ao criar tabuleiro: é necessário pelo menos 1 linha e 1 coluna");
		}
		
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new aula_156_Peca[linhas][colunas]; 
	}
	
	public int getLinhas() {
		return linhas;
	}	

	public int getColunas() {
		return colunas;
	}
	
	public aula_156_Peca peca(int linha, int coluna)
	{
		if (!posicaoExistente(linha, coluna))
		{
			throw new aula_153_ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public aula_156_Peca peca(aula_149_Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new aula_153_ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return pecas[posicao.getLinha() ][posicao.getColuna() ];
	}
	
	public void posicionarPeca(aula_156_Peca peca, aula_149_Posicao posicao)
	{
		if (pecaExistente(posicao))
		{
			throw new aula_153_ExcecaoTabuleiro("Já existe uma peça na posição " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public aula_156_Peca removerPeca(aula_149_Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new aula_153_ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		
		if (peca(posicao) == null)
		{
			return null;
		}
		aula_156_Peca aux = peca(posicao);
		aux.posicao = null;
		
		pecas[posicao.getLinha()] [posicao.getColuna()] = null;
		
		return aux;
	}
	
	private boolean posicaoExistente(int linha, int coluna)
	{
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posicaoExistente(aula_149_Posicao posicao)
	{
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean pecaExistente(aula_149_Posicao posicao)
	{
		if (!posicaoExistente(posicao))
		{
			throw new aula_153_ExcecaoTabuleiro("Posicão não existe no tabuleiro");
		}
		return peca(posicao) != null;
	}
}
