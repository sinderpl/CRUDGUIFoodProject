import java.io.*;
import java.util.*;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
public class Factory implements FoodInterface, Serializable{
private static Factory theSingleInstance=null;
//Array list
//private ArrayList<Food> objectArrayList = new ArrayList<Food>();
private static HashMap<String,Food> foodList = new HashMap();
Vector<Food> foodVector = new Vector<Food>();
private Factory(){
  System.out.println("Constructor for singleton has been called");
}

public static Factory getInstance(){
	  if(theSingleInstance == null){
	    System.out.println("The Singleton does not exist so it will be created");
	    theSingleInstance = new Factory();
	    return theSingleInstance;
  		}
	  else{
			System.out.println("The Singleton does already exist");
			return theSingleInstance;
		}
	}
public static void saveArray(){
	try {
		FileOutputStream out = new FileOutputStream ("foodData.ser");
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(foodList);
		oos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void readArray(){
	try {
		FileInputStream in = new FileInputStream("foodData.ser");
		ObjectInputStream ois= new ObjectInputStream(in);
		foodList = (HashMap<String,Food>) ois.readObject();
		ois.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
public void update(String objectKey,String objectName,String protein,String carbs,
		String fat,String calories){
	foodList.get(objectKey).setFoodName(objectName);
	foodList.get(objectName).setProteinPer100G(protein);
	foodList.get(objectName).setCarbsPer100G(carbs);
	foodList.get(objectName).setFatPer100G(fat);
	foodList.get(objectName).setCalories(calories);
}
public void create(String objectName,String protein,String carbs,
		String fat,String calories){
	Food newFood = new Food(objectName,protein,carbs,fat,calories);
	foodList.put(objectName, newFood);
}
public Food read(String key){
	Food returnFood = null;
	returnFood = foodList.get(key);
	return returnFood;
}
public void delete(String key){
	foodList.remove(key);
	
}
public Food getFood(String key){
	Food tempFood = null;
	tempFood = foodList.get(key);
	return tempFood;
	
}
public Vector<Food> getFoods(){
	if(foodVector.size()>1)
		foodVector.clear();
	for(Object value: foodList.values())
		foodVector.add((Food) value);
	
	return foodVector;
}

public void Food(String foodName, String proteinPer100g, String carbsPer100g,
		String fatPer100g, String calories) throws RemoteException {
	// TODO Auto-generated method stub
	
}
}
