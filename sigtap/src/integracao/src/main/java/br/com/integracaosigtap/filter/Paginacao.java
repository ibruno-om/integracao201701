package br.com.integracaosigtap.filter;

public class Paginacao {
	int registoInicial, registoFinal, total;

	public Paginacao() {}

	public Paginacao(int registoInicial, int registoFinal, int total) {
		super();
		this.registoInicial = registoInicial;
		this.registoFinal = registoFinal;
		this.total = total;
	}

	public int getRegistoInicial() {
		return registoInicial;
	}

	public void setRegistoInicial(int registoInicial) {
		this.registoInicial = registoInicial;
	}

	public int getRegistoFinal() {
		return registoFinal;
	}

	public void setRegistoFinal(int registoFinal) {
		this.registoFinal = registoFinal;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
