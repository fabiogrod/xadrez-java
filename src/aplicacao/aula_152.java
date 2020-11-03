package aplicacao;

import xadrez.aula_155_PartidaXadrez;

public class aula_152
{
	public static void main(String[] args)
	{
//		aula_149_Posicao pos = new aula_149_Posicao(3,5); 
//		
//		System.out.println("Vai filhão!! - " + pos);
//		
//		aula_150_Tabuleiro tabuleiro = new aula_150_Tabuleiro( 8, 8);
		
		aula_155_PartidaXadrez partidaXadrez =  new aula_155_PartidaXadrez();
		
		aula_155_IU.imprimirTabuleiro(partidaXadrez.getPecas());
	}
}
