package tabuleiro;

public abstract class aula_157_Peca
{
	protected aula_149_Posicao posicao;
	private aula_156_Tabuleiro tabuleiro;
	
	public aula_157_Peca() {};
	
	public aula_157_Peca(aula_156_Tabuleiro tabuleiro)
	{
		this.tabuleiro = tabuleiro;
		posicao  = null;
	}

	protected aula_156_Tabuleiro getTabuleiro() {
		return tabuleiro;
	}	
	
	public abstract boolean[][] movimentosPossiveis();
	
	public boolean movimentoPossivel(aula_149_Posicao posicao)
	{
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean haAlgumMovimentoPossivel()
	{
		boolean[][] matriz = movimentosPossiveis(); 
		
		for (int i = 0; i < matriz.length; i++)
		{
			for (int j = 0; j < matriz.length; j++)
			{
				if (matriz[i][j])
				{
					return true;
				}
			}			
		}
		return false;
	}
}
