package converter;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        String result = "";
        
        int sourceBase = sc.nextInt();
        int number = sc.nextInt();
        int newBase = sc.nextInt();
        
        int decimalNumber = number;
        //String result = selectOperation(number,base);
        //System.out.println(result); 
        if(sourceBase == 1) {
            decimalNumber = baseOneToDecimal(number);
        }
        else if(sourceBase != 10) {
            decimalNumber = (int)Integer.parseInt(""+number, sourceBase);
        }
        if(newBase == 1) {
            result = decimalToBaseOne(decimalNumber);
        }
        else {
            result = Integer.toString(decimalNumber, newBase);
        }
        
        System.out.println(result);
    }
    
    private static int baseOneToDecimal(int number) {
        int decimalNumber = 0;
        int length = String.valueOf(number).length();
        for(int i = 0; i < length; i++) {
            ++decimalNumber;
        }
        return decimalNumber;
    }
    
    private static String decimalToBaseOne(int number) {
        String baseOneNumber = "";
        for(int i = 0; i < number; i++) {
            baseOneNumber += "1";
        }
        return baseOneNumber;
    }
    
    private static String selectOperation(int decimal, int base) {
        String result = "";
        switch(base){
            case 2:
                result = "0b" + orderNumber(decimalToBinary(decimal));
                break;
            case 8:
                result = "0" + orderNumber(decimalToOctal(decimal));
                break;
            case 16:
                result = "0x" + orderNumber(decimalToHexadecimal(decimal));
                break;     
        }
        
        return result;
    }
    
    private static String decimalToBinary(int decimalNumber) {
        String result = "";
        if(decimalNumber == 0) {
            return "0";
        }
        while(decimalNumber >= 1) {
            result += decimalNumber % 2;
            decimalNumber =  decimalNumber / 2;
        }
        
        return result;
    }
    
    private static String decimalToOctal(int decimalNumber) {
        if(decimalNumber == 0) {
            return "0";
        }
        int quotient = decimalNumber;
        int  remainder;
        String result = "";
        
        while(quotient >= 8){
            remainder = quotient % 8;
            result += remainder;
            quotient = quotient / 8;
        }
        result += quotient;
        
        return result;
    }
    
    private static String decimalToHexadecimal(int decimalNumber) {
        if(decimalNumber == 0) {
            return "0";
        }
        char hexchars[]={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};  
        String result = "";
        while(decimalNumber >= 1) {
            int remainder = decimalNumber%16;
            result += hexchars[remainder];
            decimalNumber = decimalNumber / 16;
        }
        
        return result;
    }
    
    private static String orderNumber(String number) {
        String result = "";
        for(int i = number.length() - 1; i >= 0 ; i--) {
            result += number.charAt(i) ;
        }
        return result;
    }
}
