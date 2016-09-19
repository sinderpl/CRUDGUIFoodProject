
import java.rmi.*;
import java.rmi.server.*;

public class FoodServer
{
    public static void main(String args[])
    {
      System.out.println("Food details Server Starting");
        try
        {
            Factory aFactory = Factory.getInstance();
            Naming.rebind("factory", (Remote) aFactory);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
