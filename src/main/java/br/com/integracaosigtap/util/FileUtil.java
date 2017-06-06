package br.com.integracaosigtap.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static String fileToString(File arquivo) throws IOException{
		FileReader fr = null;
		BufferedReader br = null;
		String linha = "";
		
		try{
			fr = new FileReader(arquivo);
			br = new BufferedReader(fr);
			 
			
			while (br.ready()) {
				linha.concat(br.readLine());
				System.out.println(linha);
			}
		}finally{
			if(br != null)
				br.close();
			if(fr != null)
				fr.close();
		}
			
			return linha;

	}
}
