import java.util.Scanner;


class Texto{
	private String texto;
	Texto(String texto){
		this.texto = texto;
	}
	public String getTexto() {
		return this.texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public void imprimir() {
		System.out.println(texto);
	}
	
	public int palavras() {
		String[] words = texto.split(" |\n|\\.|\\,|\\;|\\:|\\?|\\!|\t",-1);
		int length = 0;
		for(int i = 0; i< words.length; i++) {
			if(words[i].length() > 0) {
				if(((words[i].charAt(0) <= 'Z' && words[i].charAt(0) >= 'A') || (words[i].charAt(0) <= 'z' && words[i].charAt(0) >= 'a') || (words[i].charAt(0) <= '9' && words[i].charAt(0) >= '0') )) {
					length ++;
				}
			}  
		}
		return length ;
	}
	
	public void substituir(String palavra,String substituto) {
		texto = texto.replaceAll("\\b"+palavra+"\\b", substituto);
	}
	
	public int substrings(String substr) {
		int counter = 0;
		int index = 0;
		while (index != -1) {
			index = texto.indexOf(substr,index);
			
			if(index != -1) {
				counter ++;
				index += substr.length();			
			}
				
		}
		return counter;
	}
}

public class Q2 {
	
	public static void main(String[] args){
        Texto texto = new Texto("I reject your reality and substitute my own!!!");
        texto.imprimir();
        System.out.println(texto.palavras());
        System.out.println(texto.substrings("own"));
        texto.substituir("your","my");
        texto.imprimir();
	}
}