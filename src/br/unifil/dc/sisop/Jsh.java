package br.unifil.dc.sisop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh 
{
    static Scanner key = new Scanner(System.in);
    
    static int rodando = 1;
    
    static String userspace = (System.getProperty("user.dir") + "\\home\\shared");
    
    public static void promptTerminal() {

        while (rodando == 1) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
    		executarComando(comandoEntrado);
    	}
    }

    /**
    * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
    * terminal está pronto para receber o próximo comando como entrada.
    */
    public static void exibirPrompt() 
    {
    	System.out.print(System.getProperty("user.name") + "#1488 :" + userspace.substring(userspace.indexOf("home"), userspace.length()) + "\\");
    }

    /**
    * Preenche as strings comando e parametros com a entrada do usuario do terminal.
    * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
    * outras palavras subsequentes sao parametros para o comando. A palavras sao
    * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
    * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
    * EOL (End Of Line).
    *
    * @return 
    */
    public static ComandoPrompt lerComando() 
    {
    	return new ComandoPrompt(key.nextLine());
    }

    /**
    * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
    * se for, o executa.
    * 
    * Se nao for, verifica se é o nome de um programa terceiro localizado no atual 
    * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
    * esse processo executa, o processo do uniterm deve permanecer em espera.
    *
    * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
    * programa desconhecido.
     * @throws FileNotFoundException 
    */
    public static void executarComando(ComandoPrompt comando) {
        switch(comando.getNome()){
        	case "encerrar":
        		rodando = 0;
        		System.out.println("SISTEMA ENCERRADO");
        		break;
        	
        	case "relogio":
        		rodando = ComandosInternos.exibirRelogio();
        		break;
        		
        	case "la":
        		rodando = ComandosInternos.escreverListaArquivos(null);
        		break;
        		
        	case "cd":
        		rodando = ComandosInternos.criarNovoDiretorio(comando.getArgumentos().get(0));
        		break;
        		
        	case "ad":
        		rodando = ComandosInternos.apagarDiretorio(comando.getArgumentos().get(0));
        		break;
        		
        	case "mdt":
        		rodando = ComandosInternos.mudarDiretorioTrabalho(comando.getArgumentos().get(0));
        		break;
        		
        	default:
			try {
		    	System.out.print("por favor, ensira o comando mais uma vez: ");
				rodando = executarPrograma(lerComando());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        		break;
        }
    }
    
    public static int executarPrograma(ComandoPrompt comando) throws FileNotFoundException 
    {
    	File f = new File(ComandosInternos.currentPath + "/" + comando.getArgumentos().get(1));
    	String fileName = comando.getArgumentos().get(1);
    	String line = null;
    	if (f.exists())
    	{
    		try {
    			Process p = Runtime.getRuntime().exec((ComandosInternos.currentPath + "/" + comando.getArgumentos().get(1)), null, new File(ComandosInternos.currentPath));
    			
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                
                while ((line = stdInput.readLine()) != null) 
                {                 
                	System.out.println(line);             
                }
                
            }
            catch(FileNotFoundException ex) {
                System.out.println("n�o � possivel abrir o file: " + fileName + "");                
            }
            catch(IOException ex) {
                System.out.println("erro ao ler o file: " + fileName + "");                  

            }
    		return 1;
    	}
    	System.out.println("comando ou file n�o existente");
    	return 1;
    }
    
    
    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) 
    {

        promptTerminal();
    }
    
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}
}
