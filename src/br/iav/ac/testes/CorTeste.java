package br.iav.ac.testes;

import br.iav.ac.negocio.Cor;

public class CorTeste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Cor cor = new Cor();
		cor.setCodigo(1);
		cor.setNome("Azul");
		cor.insert();

	}

}
