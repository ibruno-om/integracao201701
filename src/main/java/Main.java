import br.com.integracaobsus.impl.BSus;
import br.com.integracaosigtap.model.Compatibilidade;
import br.com.integracaosigtap.model.Grupo;
import br.com.integracaosigtap.model.SubGrupo;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

	public static void main(String[] args) throws Exception {

		BSus bsus = new BSus();
		bsus.setProperties(null);

		for (SubGrupo emp : bsus.listarSubGrupos()) {
			System.out.println(emp);
		}

		for (Grupo grupo : bsus.listarGrupos()) {
			System.out.println(grupo);
		}

		for (Compatibilidade compatibilidade : bsus.pesquisarCompatibilidades()) {
			System.out.println(compatibilidade);
		}

	}
}
