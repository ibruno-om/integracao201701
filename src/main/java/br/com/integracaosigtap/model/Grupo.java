package br.com.integracaosigtap.model;

/**
 * Created by astr1x on 21/05/17.
 */
public class Grupo extends Model {

	private Grupo grupoParent;

	public Grupo getGrupo() {
		return grupoParent;
	}

	public void setGrupo(Grupo grupo) {
		this.grupoParent = grupo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((grupoParent == null) ? 0 : grupoParent.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (grupoParent == null) {
			if (other.grupoParent != null)
				return false;
		} else if (!grupoParent.equals(other.grupoParent))
			return false;
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grupo [grupoParent=" + grupoParent + ", toString()=" + super.toString() + "]";
	}

}
