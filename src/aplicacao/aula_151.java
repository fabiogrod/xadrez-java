package aplicacao;

import xadrez.aula_152_PartidaXadrez;

public class aula_151
{
	public static void main(String[] args)
	{
//		aula_149_Posicao pos = new aula_149_Posicao(3,5); 
//		
//		System.out.println("Vai filh�o!! - " + pos);
//		
//		aula_150_Tabuleiro tabuleiro = new aula_150_Tabuleiro( 8, 8);
		
		aula_152_PartidaXadrez partidaXadrez =  new aula_152_PartidaXadrez();
		
		aula_151_IU.imprimirTabuleiro(partidaXadrez.getPecas());
	}
}
