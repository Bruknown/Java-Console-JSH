package br.unifil.dc.sisop;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

/**
 * Write a description of class ComandoPrompt here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public class ComandoPrompt 
{
	String parametro;
    public ComandoPrompt(String comando) 
    {
    	this.argumentos = new String[2];
    	if (comando.contains(" ") == true)
    	{
    		this.nome = comando.substring(0, comando.indexOf(" "));;
    		this.parametro = comando.substring(comando.indexOf(" ")+1, comando.length());
    	}
    	else 
    	{
    		this.nome = comando;
    	}
		argumentos[0] = parametro;
		argumentos[1] = nome;
    }
    
    /**
     * Método acessor get para o nome do comando.
     * 
     * @return o nome do comando, exatamente como foi entrado.
     */
    public String getNome() 
    {  
        return nome;
    }
    
    public String getParametro()
    {
    	return parametro;
    }
    
    /**
     * Método acessor get para os argumentos que seguram ao nome do comando.
     * 
     * @return Lista de argumentos do comando, protegida contra modificações externas.
     */
    public List<String> getArgumentos() 
    {
        return Collections.unmodifiableList(Arrays.asList(argumentos));
    }
    
    private String nome;
    private String[] argumentos;
}
