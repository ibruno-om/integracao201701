import java.util.List;

import br.com.integracaobsus.impl.BSus;
import br.com.integracaosigtap.connect.ConnectionSUS;
import br.com.integracaosigtap.model.Procedimento;


/**
 * Created by astr1x on 16/04/17.
 */
public class Main {


	public static void main(String[] args) throws Exception {

		BSus bsus = new BSus();
		bsus.setProperties(null);
		
		List<Procedimento> procedimentos = bsus.getDetalharProcedimentos();
		for(Procedimento procedimento : procedimentos){
			System.out.println(procedimento.toString());
		}
		
		//ConnectionSUS connection = new ConnectionSUS();
		//System.out.println(connection.getDetalharProcedimentos("https://servicoshm.saude.gov.br/sigtap/ProcedimentoService/v1"));


	}
}
