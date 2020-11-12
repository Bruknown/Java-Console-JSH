package br.unifil.dc.sisop;

import java.util.Optional;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;  


/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
	
	static String currentPath = (System.getProperty("user.dir") + "\\home\\shared");
    
    public static int exibirRelogio() {

    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
    	LocalDateTime now = LocalDateTime.now();  
    	System.out.println(dtf.format(now));  
    	return 1;
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) {
    	try {
			Process process = Runtime.getRuntime().exec("cmd /c dir", null, new File(currentPath));
			System.out.println(currentPath);
			printResults(process);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return 1;
    }
    
    public static int criarNovoDiretorio(String nomeDir) {
    	File f = new File(currentPath + "/" + nomeDir);
    	f.mkdir();
    	return 1;
    }
    
    public static int apagarDiretorio(String nomeDir) {
    	File f = new File(currentPath + "/" + nomeDir);
    	if(f.exists())
    	{
    		f.delete();
    		System.out.println("File removido");
    		return 1;
    	}
    	System.out.println("File n„o encontrado");
        return 1;
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){
    	if (nomeDir.equals(".."))
    	{
    		currentPath = currentPath.substring(0, currentPath.lastIndexOf("\\"));
    	}
        currentPath += ("/" + nomeDir);
        return 1;
    }
    
    
    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
    
    /**
     * Essa classe n√£o deve ser instanciada.
     */
    private ComandosInternos() {}
}
