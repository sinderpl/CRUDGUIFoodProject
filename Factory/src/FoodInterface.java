
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FoodInterface extends Remote
{
	public void Food(String foodName, String proteinPer100g,String carbsPer100g,String fatPer100g,String calories)  throws RemoteException;
}

