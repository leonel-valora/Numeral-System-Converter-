package converter;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    private static char alphaNumChars[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        boolean inputInvalid = false;
        int sourceBase = 0;
        String number = "";
        int newBase = 0;
        if(sc.hasNextInt()){
            sourceBase = sc.nextInt();
        } else {
            inputInvalid = true;
        }
        if(sc.hasNext()) {
            number = sc.next();
        } else {
            inputInvalid = true;
        }
        if(sc.hasNextInt()){
            newBase = sc.nextInt();
        } else {
            inputInvalid = true;
        }
        String result;
        if(isValidOperation(sourceBase, number, newBase) && !inputInvalid) {
            result = makeConvertion(sourceBase, number, newBase);
        } else {
            result = "error";
        }
        System.out.println(result);
    }


    private static boolean isValidOperation(int sourceBase, String number, int newBase) {
        if(number.isEmpty()){
            return false;
        }

        if(sourceBase < 1 || sourceBase > 36 || newBase < 1 || newBase > 36) {
            return false;
        }

        if(number.contains("\\.") && sourceBase == 1) {
            return false;
        }
        if(sourceBase == 1) {
            for(int i = 0; i < number.length(); i++) {
                if(number.charAt(i) != '1'){
                    return false;
                }
            }
        } else{
            String[] numbers = number.split("\\.");
            for(String n : numbers) {
                if(!isInputValid(n, sourceBase)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInputValid(String number, int base) {
        char[] alphanumerics = Arrays.copyOfRange(alphaNumChars, 0, base);
        String validValues = String.valueOf(alphanumerics);
        for(int i = 0; i < number.length(); i++) {
            String value = String.valueOf(number.charAt(i));
            if(validValues.contains(value)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private static String makeConvertion(int sourceBase, String number,int newBase) {
        if(sourceBase == newBase){
            return number;
        }
        //Split number
        String[] numbers = number.split("\\.");
        //Convert integer part to decimal if exist
        int integerPart = baseXToDecimal(numbers[0], sourceBase);
        //Convert fractions to decimal if exist
        double fractionsPart = 0;
        String fractionResult = null;
        if(numbers.length > 1) {
            //System.out.println(numbers[1]);

            //int fractions = baseXToDecimal(numRep, sourceBase, true);
            //System.out.print("BaseX a decimal: "+fractions);
            fractionsPart = fractionsToDecimal(numbers[1], sourceBase);

            //System.out.println("fraccion en decimal: "+fractionsPart);
            fractionResult = fractionsBaseX(fractionsPart, newBase);
        }
        //Convert to base x
        String result = "";
        if(newBase == 1){
            result = decimalToBaseOne(integerPart);
        } else {
            result = Integer.toString(integerPart, newBase);
        }


        if(fractionResult != null) {
            result += "."+fractionResult;
        }

        return result;
    }

    private static int numericRepresentation(char number) {
        String result = "";
        String alphanumerics = String.valueOf(alphaNumChars);
        //for(int i = 0; i < number.length(); i++) {
        // char c = number.charAt(i);
        int index = alphanumerics.indexOf(number);
        //result += index;
        //}
        return index;
    }

    private static double fractionsToDecimal(String number, int base) {
        double result = 0;
        for(int i = 0; i < number.length(); i++){
            char c = number.charAt(i);
            int num =  numericRepresentation(c);
            double n = Math.pow(base, i+1);
            result += num/n;
        }
        return result;
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

    private static String fractionsBaseX(double number, int base) {
        double fractionNumber = number;
        String result = "";
        for(int i = 0; i < 5; i++) {
            int aux = (int)(fractionNumber * base);
            result += alphaNumChars[aux];
            fractionNumber = (fractionNumber * base) - aux;
        }

        return result;
    }

    private static int baseXToDecimal(String number, int base) {
        if(base == 1) {
            return baseOneToDecimal(Integer.parseInt(number));
        }
        int n = number.length() - 1;
        int decimal = 0;
        int result = 0;
        for(int i = 0; i < number.length(); i++) {
            char hexChar = number.charAt(i);
            for(int j = 0; j <= base; j++) {
                if(hexChar == alphaNumChars[j]) {
                    decimal = j;
                    break;
                }
            }
            result += ((int)Math.pow(base,n))  * decimal;
            --n;
        }
        return result;
    }
}
