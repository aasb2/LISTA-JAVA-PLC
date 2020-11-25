import java.util.Scanner;

 abstract class Livro{
    protected String autor;
    protected String isbn;
    protected String genero;
    protected int edicao;
    protected String editora;
    protected int ano;
    
}

class LivroLivraria{
    private int preco;
    private String estado; // novo, usado, etc...
}

class LivroBiblioteca{
    private int tempo_de_emprestimo;
    private boolean isEmprestado;
}


public class Q1 {

	public static void main(String[] args){
        System.out.println("Hello World");
	}

}
