package br.com.gionni.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressaoRegular {

	public static void main(String[] args) {
		
		String padrao = "JAVA";
		
		/* Modificadores
		 * (?i), ignora maisculas e minusculas
		 * (?x), Comentários
		 * (?m), Multilinhas
		 * (?s), Dottal
		 */
		
		/* Metacaracteres
		 .  qualquer caractere 
		 \d dígitos       [0-9]
		 \D não é digito  [^0-9]
		 \s espaços       [ \t\n\x0B\f\r]
		 \S não é espaço  [^\s]
		 \w letra         [a-zA-Z_0-9]
		 \W não é letra
		 * (?s), Dottal
		 */
		
		boolean b = "Java".matches("(?i)java");
		System.out.println("Deu match: " + b);
		
		boolean s = "Gionni".matches("(?i)gionni");
		
		b = "@".matches(".");
		b = "5".matches("\\d");
		b = "a".matches("\\w"); 
		b = "#".matches("\\w"); //false
		b = " ".matches("\\s"); //espacos
		System.out.println(b);
		
		/* QUANTIFICADORES
		 * X{n}   X, exatamente n vezes
		 * X{n,}  X, pelo menos n vezes
		 * X{n,m} X, pelo menos n vezes não mais que m vezes
		 * X?     X, 0 ou 1 vez
		 * X*     X, 0 ou + vezes
		 * X+     X, 1 ou + vezes*
		 */
		
		b = "21".matches("\\d{2}");
		b = "32323".matches("\\d{2,}"); //pelo menos 2, mas pode ser mais
		b = "3232".matches("\\d{2,4}"); //pelo menos 2, no maximo 4
		b = "".matches(".?"); //0 ou 1
		b = "ab".matches(".*"); //0 ou +
		b = "a".matches(".+"); //1 ou +
		
		System.out.println(b);
		
		String cep = "53150-370";
		String cpf = "123324567-33";
		String dataNasc = "12/03/1965";
		
		boolean validaCEP = cep.matches("\\d{5}-\\d{3}");
		System.out.println(validaCEP);
		
		boolean validaCPF = cpf.matches("\\d{9}-\\d{2}");
		System.out.println("É um cpf valido? ===>" + b);
		
		boolean validaDataNasc = dataNasc.matches("\\d{2}/\\d{2}/\\d{2,4}");
		System.out.println("Data de Nasc válida? ==> " + validaDataNasc);
		
		/* METACARACTER DE FRONTEIRA
		 * ^ inicia a palavra com 
		 * $ finaliza
		 * | ou 
		 */
		b = "Pier21".matches("^Pier21");
		b = "Pier21".matches(".*21$");
		b = "tem java aqui".matches(".*java.*");
		b = "Na verdade, o Java é a linguagem mais usada no mundo.".matches("(?i).*java.*");
		b = "tem java aqui".matches("^tem.*aqui$");
		b = "sim".matches("sim|não");
		System.out.println(b);
		
		/* AGRUPADORES
		 * [...]   Agrupamento
		 * [a-z]   Alcance
		 * [a-e][i-u] União
		 * [a-z&&[aeiou]] Interseção
		 * [^abc]  Exceção
		 * [a-z&&[^m-p]]  Subtração
		 * \x   Fuga Literal 
		 */
	
		b = "v".matches("[a-z]");
		b = "3".matches("[0-9]");
		b = "True".matches("[tT]rue"); //true ou True 
		b = "Gionni".matches("[gG]ionni"); //Gionni ou gionni
		b = "Yes".matches("(?i)[tT]rue|(?i)[yY]es"); //true ou True yes
		b = "Beatriz".matches("[A-Z][a-zA-Z]*"); //validar Primeiro Nome
		b = "olho".matches("[^abc]lho"); // palavras que terminam com lho com excecao abc
		b = "alho".matches("[^abc]lho"); // retorna false
		b = "crau".matches("cr[ae]u"); //crau ou creu
		
		//padrao de validacao de e-mail
		b = "rh@gionni.com".matches("\\w+@\\w+\\.\\w{2,3}");
		
		b = "rh@gionni.com.br".matches("\\w+@\\w+\\.\\w{2,3}+\\.\\w{2}");
		
		System.out.println(b);
		
		String doce = "Qual é o DOce mais doCE que o doce";
		Matcher matcher = Pattern.compile("(?i)doce").matcher(doce);
		
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		
		String r = doce.replaceAll("(?i)doce", "DOCINHO");
		
		//Substituindo
		
		String rato = "O rato roeu a roupa do rei de roma";
		
		r = rato.replaceAll("r[aeiou]", "XX");
		System.out.println(r);
		//ex: substituir espaços por tab
		r = "Tabular este texto".replaceAll("\\s", "\t");
		System.out.println(r);
		
		String url = "www.gionni.com.br/clientes-2011.html";
				//http://www.gionni.com.br/2011/clientes.jsp
		
		String re = "www.gionni.com.br/\\w{2,}-\\d{4}.html";
		
		b = url.matches(re);
		System.out.println("Match: " + b);
		
		re = "(www.gionni.com.br)/(\\w{2,})-(\\d{4}).html";
		System.out.println("URL anterior: " + url);
		
		r = url.replaceAll(re, "http://$1/$3/$2.jsp");
		System.out.println("Nova URL: " + r);
		
	}

}