
interface Expression{
	String avaliar(String expr);
	String imprimirArvore(String expr);
	
}

class ExprAritmetica implements Expression{
	String expr;
	
	ExprAritmetica(String expr){
		this.expr = expr;
	}
	
	public String getExpr() {
		return expr;
	}
	public void setExpr(String expr) {
		this.expr = expr;
	}
	private int findOp(String expr, char op) {
		for(int i = expr.length() -1; i>= 0; i--){
			if(expr.charAt(i) == op) {
				return i;
			}
		}
		return -1;
	}

	private int positionToSplit(String expr) {
		int posOp = findOp(expr,'+');
		if(posOp == -1) {
			posOp = findOp(expr,'-');
			if(posOp == -1) {
				posOp = findOp(expr, '*');
				if(posOp == -1) {
					posOp = findOp(expr,'/');
				}
			}
		}
		return posOp;
	}
	private String evaluate(String expr1, char op, String expr2) {
		int posOp1 = positionToSplit(expr1), posOp2 = positionToSplit(expr2);
		if(posOp1 == -1 && posOp2 == -1) {
			double n1 = Double.parseDouble(expr1), n2 = Double.parseDouble(expr2);
			if(op == '+') {
				return Double.toString(n1 + n2);
			}
			else if(op == '-') {
				return Double.toString(n1 - n2);
			}
			else if(op == '*'){
				return Double.toString(n1 * n2);
			}
			else if(op == '/'){
				return Double.toString(n1/n2);
			}
		}
		String newExpr1 = expr1,newExpr2 = expr2;
		
		if(posOp1 != -1){ 
			newExpr1 = evaluate(expr1.substring(0,posOp1),expr1.charAt(posOp1),expr1.substring(posOp1+1,expr1.length()));
        }
		if(posOp2 != -1) {
            newExpr2 = evaluate(expr2.substring(0,posOp2),expr2.charAt(posOp2),expr2.substring(posOp2+1,expr2.length()));

		}
		return evaluate(newExpr1,op,newExpr2) ;
	}		
	@Override
	public String avaliar(String expr) {
		int posOp = positionToSplit(expr);
		if(posOp == -1) {
			return expr;
		}
		char op = expr.charAt(posOp);
		String expr1 = expr.substring(0,posOp);
		String expr2 = expr.substring(posOp+1,expr.length());
		String tree = evaluate(expr1,op,expr2);
		return tree;
	}
	
	
	
	private String printTree(String expr1,char op, String expr2) {
		int posOp1 = positionToSplit(expr1), posOp2 = positionToSplit(expr2);
		if(posOp1 == -1 && posOp2 == -1) {
			if(op == '+')
				return "(" + expr1 + ")" + "P" + "(" + expr2 +")";//PLUS
			else if(op == '-') 
					return "(" + expr1 + ")" + "M" + "(" + expr2 +")" ;	//MINUS
			else if(op == '/') 
				return "(" + expr1 + ")" + "D" + "(" + expr2 +")" ;	//DIVIDE
			else if(op == '*') 
				return "(" + expr1 + ")" + "T" + "(" + expr2 +")"; //TIMES
			
		}
		String newExpr1 = expr1,newExpr2 = expr2;
		if(posOp1 != -1){ 
			newExpr1 = printTree(expr1.substring(0,posOp1),expr1.charAt(posOp1),expr1.substring(posOp1+1,expr1.length()));
        }
		if(posOp2 != -1) {
            newExpr2 = printTree(expr2.substring(0,posOp2),expr2.charAt(posOp2),expr2.substring(posOp2+1,expr2.length()));
		}
		return printTree(newExpr1,op,newExpr2);
		
	}
	@Override
	public String imprimirArvore(String expr) {
		int posOp = positionToSplit(expr);
		char op = 'F';
		if(posOp!=-1)
			op = expr.charAt(posOp);
		else if(posOp == -1) {
			return "(" + expr + ")";
		}
		String expr1 = expr.substring(0,posOp);
		String expr2 = expr.substring(posOp+1,expr.length());
		
		String tree = printTree(expr1,op,expr2);
		tree = tree.replaceAll("P", "+");
		tree = tree.replaceAll("M", "-");
		tree = tree.replaceAll("D", "/");
		tree = tree.replaceAll("T", "*");
		if(positionToSplit(expr1) == -1 && positionToSplit(expr2) == -1)
			return "(" + tree + ")";
		return "(" + tree +")";
				
				
				
	}
	public String toString() {
		return this.expr;
	}
}

class ExprLogica implements Expression{
	
	String expr;
	
	ExprLogica(String expr){
		this.expr = expr;
	}
	public String getExpr() {
		return this.expr;
	}
	public void setExpr(String expr){
		this.expr = expr;
	}
	private int findOp(String expr) {
		int pos = -1;
		for(int i = 0; i<expr.length(); i++) {
			if(expr.charAt(i) == '=' || expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '!' ) {
				return i;
			}				
		}
		return pos;
	}
	
	private String returnOp(String expr) {
		int pos = findOp(expr);
		char op = expr.charAt(pos);
		char op1 = expr.charAt(pos+1);
		if(op == '=' && op1 == '=') {
			return "==";
		}
		else if(op == '!'&& op1 == '=') {
			return "!=";
		}
		else if(op == '>') {
			if(op1 == '=') {
				return ">=";
			}
			return ">";
		}
		else if(op == '<') {
			if(op1 == '=') {
				return ">=";
			}
			return "<";
		}
		return "";
	}
	@Override
	public String avaliar(String expr) {
		int posOp = findOp(expr);
		String op = returnOp(expr);
		int opLen = op.length();
		ExprAritmetica expr1 = new ExprAritmetica(expr.substring(0,posOp));
		ExprAritmetica expr2 = new ExprAritmetica(expr.substring(posOp + opLen, expr.length()));
		Double result1 = Double.parseDouble(expr1.avaliar(expr1.getExpr()).toString().toString());
		Double result2 = Double.parseDouble(expr2.avaliar(expr2.getExpr()).toString());
		boolean finalResult = true;
		if(op == "==") {
			finalResult = Double.compare(result1, result2) == 0? true : false;
		}
		else if(op == "!=")
			finalResult = Double.compare(result1, result2) == 0 ? false : true;
		else if(op == ">=")
			finalResult = result1 >= result2;
		else if(op == "<=")
			finalResult = result1 <= result2;
		else if(op == "<")
			finalResult = result1 < result2;
		else if(op == ">")
			finalResult = result1 > result2;
		return finalResult? "True":"False";
	}

	@Override
	public String imprimirArvore(String expr) {
		int posOp = findOp(expr);
		String op = returnOp(expr);
		int opLen = op.length();
		ExprAritmetica expr1 = new ExprAritmetica(expr.substring(0,posOp));
		ExprAritmetica expr2 = new ExprAritmetica(expr.substring(posOp + opLen, expr.length()));
		String result1 = expr1.imprimirArvore(expr1.getExpr());
		String result2 = expr2.imprimirArvore(expr2.getExpr());
		return "("+result1+op+result2+")";
	}
	
	public String toString() {
		return this.expr;
	}
	

}


public class Q6 {
	public static void main(String[] args){
		String expr = "2*3+4/6/6*4";
		char type = descobreTipoDeExpr(expr);
		if(type == 'L') {
			ExprLogica expression = new ExprLogica(expr) ;
			System.out.println(expression.avaliar(expression.getExpr()));
			System.out.println(expression.imprimirArvore(expression.getExpr()));
		}
		else if(type == 'A') {
			ExprAritmetica expression = new ExprAritmetica(expr) ;
			System.out.println(expression.avaliar(expression.getExpr()));
			System.out.println(expression.imprimirArvore(expression.getExpr()));			
		}
	}
	
	public static char descobreTipoDeExpr(String expr) {
		for(int i = 0; i < expr.length(); i++) {
			if(expr.charAt(i) == '=' || expr.charAt(i) == '>' || expr.charAt(i) == '<' || expr.charAt(i) == '!' ) {
				return 'L';
			}	
		}
		return 'A';
	}
}
