package br.ufg.inf.integracao;

public class App {

	public static void main(String[] args) {
		SigTap sigTap = new SigTap();
		try {
			sigTap.getListaProcedimentos();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
