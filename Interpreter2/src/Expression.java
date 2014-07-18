import java.util.ArrayList;

/**
 * Derived class that represents an expression in the SILLY language.
 *   @author Dave Reed
 *   @author Steven Warner
 *   @version 2/8/14
 */
public class Expression {
    private ArrayList<Token> expr;
    
    /**
     * Creates an expression from the specified TokenStream.
     *   @param input the TokenStream from which the program is read
     */
    public Expression(TokenStream input) throws Exception {
        this.expr = new ArrayList<Token>();
        
        Token token1 = input.next();
        if (!token1.toString().equals("(")) {
            this.expr.add(token1);
        }
        else {
            Token token2 = input.next();
            while (!token2.toString().equals(")")) {
                this.expr.add(token2);
                token2 = input.next();
            }
        }
        
        if (this.expr.size() % 2 == 0) {
            throw new Exception("SYNTAX ERROR: Malformed expression");
        }
        else {
            for (int i = 0; i < this.expr.size(); i++) {
                if ((i % 2 == 0 && this.expr.get(i).getType() != Token.Type.INTEGER && 
                                   this.expr.get(i).getType() != Token.Type.STRING &&
                                   this.expr.get(i).getType() != Token.Type.IDENTIFIER) ||
                    (i % 2 == 1 && this.expr.get(i).getType() != Token.Type.OPERATOR)) {
                      throw new Exception("SYNTAX ERROR: Malformed expression");  
                }
            }
        }
    }
    
    /**
     * Private helper method, called by evaluate.
     */
    private DataValue getValue(Token t) throws Exception {        
    	 if (t.getType() == Token.Type.IDENTIFIER) {
             return Interpreter.STACK.lookup(t);
         }
         else if (t.getType() == Token.Type.INTEGER) {
            return new IntegerValue(Integer.parseInt(t.toString()));
        }
        else {
            return new StringValue(t.toString());
        }
    }
    
    /**
     * Evaluates the current expression involving integers and/or variables.
     *   @param bindings the current variable and subroutine bindings
     *   @return the value represented by the expression
     */
    public DataValue evaluate() throws Exception {
        DataValue value = this.getValue(this.expr.get(0));
    
        for (int i = 1; i < this.expr.size(); i+=2) {
            Token operator = this.expr.get(i);
            DataValue operandVal = this.getValue(this.expr.get(i+1));

            if (operator.toString().equals("+")) {
                if (value instanceof IntegerValue && operandVal instanceof IntegerValue) {
                    value = new IntegerValue((Integer)value.getValue() +
                                      (Integer)operandVal.getValue());
                }
                
                //concatenates an int onto a string
                else if (value instanceof IntegerValue && operandVal instanceof StringValue) {
                	DataValue numberSide = new IntegerValue((Integer)value.getValue()); 
                	String str1 = String.valueOf(numberSide);
                	String str2 = (String)operandVal.getValue();
                    value = new StringValue('"' + str1.substring(0, str1.length()) +
                                                  str2.substring(1, str2.length()));
                }
                //concatenates a string onto an int
                else if (value instanceof StringValue && operandVal instanceof IntegerValue) {
                	DataValue numberSide = new IntegerValue((Integer)operandVal.getValue()); 
                	String str1 = (String)value.getValue();
                	String str2 = String.valueOf(numberSide);
                    value = new StringValue(str1.substring(0, str1.length()-1) +
                                            str2.substring(0, str2.length()) + '"');
                }
                //concatenates two strings 
                else if (value instanceof StringValue && operandVal instanceof StringValue) {
                    String str1 = (String)value.getValue();
                    String str2 = (String)operandVal.getValue();
                    value = new StringValue(str1.substring(0, str1.length()-1) +
                                                  str2.substring(1, str2.length()));
                }
                else {
                    throw new Exception("RUNTIME ERROR: illegal operands for +");
                }
            }
            if (operator.toString().equals("-")) {
                if (value instanceof IntegerValue && operandVal instanceof IntegerValue) {
                    value = new IntegerValue((Integer)value.getValue() -
                                      (Integer)operandVal.getValue());
                }
                else {
                	throw new Exception("RUNTIME ERROR: illegal operands for -");
                }
            }
            if (operator.toString().equals("*")) {
                if (value instanceof IntegerValue && operandVal instanceof IntegerValue) {
                    value = new IntegerValue((Integer)value.getValue() *
                                      (Integer)operandVal.getValue());
                }
                else {
                	throw new Exception("RUNTIME ERROR: illegal operands for *");
                }
            }
            if (operator.toString().equals("/")) {
                if (value instanceof IntegerValue && operandVal instanceof IntegerValue) {
                    value = new IntegerValue((Integer)value.getValue() /
                                      (Integer)operandVal.getValue());
                }
                else {
                	throw new Exception("RUNTIME ERROR: illegal operands for /");
                }
            }
        }
        return value;
    }
      
	/**
	 * Converts the current expression into a String.
	 *   @return the String representation of this expression
	 */
	public String toString() {
        if (this.expr.size() == 0) {
            return "";
        }
        else if (expr.size() == 1) {
            return this.expr.get(0).toString();
        }
        else {
            String str = "(";
            for (Token t : this.expr) {
                str += " " + t;
            }
            return str + " )";
        }
    }
}
