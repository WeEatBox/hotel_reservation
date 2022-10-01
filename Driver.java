import model.*;

public class Driver{
    public static void main(String[] args) throws Exception{
        try{
            Customer customer = new Customer("first", "second", "email");
            System.out.println(customer);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage()); 
        }
    }
}
