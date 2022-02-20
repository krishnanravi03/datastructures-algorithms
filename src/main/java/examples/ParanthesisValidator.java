package examples;

import datastructures.MyStack;

public final class ParanthesisValidator {
    public static void run(){
        checkValidity("(");
        checkValidity("[({}{})]");
        checkValidity("((())[");
        checkValidity("()");
    }

    public static void checkValidity (String input){
        MyStack<Character> symStack = new MyStack<>();
        int invalidflag =0;
        try{

            for (int i = 0; i< input.length(); i++){
                Character c = input.charAt(i);
                switch (c){
                    case '(':
                    case '{':
                    case '[':
                        symStack.push(c);
                        break;
                    case ')':
                        if(!symStack.pop().equals('(')){
                            invalidflag = 1;
                        }
                        break;
                    case ']':
                        if(!symStack.pop().equals('[')){
                            invalidflag = 1;
                        }
                        break;
                    case '}':
                        if(!symStack.pop().equals('{')){
                            invalidflag = 1;
                        }
                        break;
                    default:
                    break;            
                }
                if(invalidflag == 1){
                    break;
                }
            }
            if(symStack.size() == 0) System.out.println("The given expression: '" + input +"' has balanced symbols and is valid");
            else System.out.println("The expression: '"+ input +"' is invalid!");
        }
        catch(IndexOutOfBoundsException ex){
            System.out.println("The expression: '"+ input +"' is invalid!");
        }
        symStack.clear();

    }
}
