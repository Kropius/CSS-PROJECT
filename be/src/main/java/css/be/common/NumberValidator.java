package css.be.common;

import css.be.exceptions.InvalidNumberException;

public class NumberValidator {

    private NumberValidator(){

    }

    public static void validate(String number){
        validatePresenceOfNumber(number);
        validateFirstDigit(number);
        validateNumberCharacters(number);
    }

    private static void validatePresenceOfNumber(String number) {
        assert number !=null : "Number is null!";
        if (number.length() == 0) {
            throw new InvalidNumberException("Looks like one of the operands is missing!");
        }
    }

    private static void validateFirstDigit(String number){
        assert number !=null : "Number is null!";
        if(number.charAt(0) == '0' && number.length() != 1){
            throw new InvalidNumberException("Numbers do not start with 0!");
        }
    }

    private static void validateNumberCharacters(String number){
        assert number !=null : "Number is null!";
        if(!number.matches("[0-9]+")){
            throw new InvalidNumberException("Invalid number!");
        }
    }

    public static void validateDivisionBy0(String number) {
        assert number !=null : "Number is null!";
        if(number.charAt(0) == '0' && number.length() == 1){
            throw new InvalidNumberException("Division by 0!");
        }
    }
}
