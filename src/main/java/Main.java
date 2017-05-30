import java.util.List;

import br.com.integracaobsus.impl.BSus;
import br.com.integracaosigtap.model.BaseProcedimento;

/**
 * Created by astr1x on 16/04/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
    	
    	BSus bsus = new BSus();
    	
    	bsus.setProperties(null);
    	
    	List<BaseProcedimento> pesquisarProcedimentos = bsus.pesquisarProcedimentos();
    	
        for (BaseProcedimento baseProcedimento : pesquisarProcedimentos) {
			System.out.println(baseProcedimento.getCodigo() + "-" + baseProcedimento.getNome());
		}
        
        
        
//        System.out.println(connection.getDetalharProcedimentos(ConnectionSUS.URL_PROCEDIMENTOS));

//        System.out.println(connection.getListarCompatibilidadesPossiveis(ConnectionSUS.URL_COMPATIBILIDADE_POSSIVEIS));

//        System.out.println(connection.getPesquisarCompatibilidades(ConnectionSUS.URL_COMPATIBILIDADE));

//        System.out.println(connection.getListarGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarSubGrupos(ConnectionSUS.URL_GRUPO));

//        System.out.println(connection.getListarFormaOrganizacao(ConnectionSUS.URL_GRUPO));

    }
}
