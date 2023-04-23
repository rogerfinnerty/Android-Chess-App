/**
   * 
   * @param num A number of type T
   * @return true iff num is greater than 0
   * @param <T> A type that extends Number
   */

public class unit_test{
    public static <T extends Number> boolean amIPositive(T num) {
        return num.doubleValue() > 0;
        // assume 0 not pos
    }

    /**
   * 
   * @param num A number of type T
   * @param isPos user-input boolean statement if the value num is positive or not
   * @return true iff the output of amIPositive matches the user-entered truth value
   */
    public static <T extends Number> boolean testAmIPositive(T num, boolean isPos){
        return (isPos == amIPositive(num));
    }

    public static void main(String[] args) {
        if(testAmIPositive(3, true)){
            System.out.println("Test passed");
        }
        else{
            System.out.println("Test NOT passed");
        }
        
        
        

    }
}

