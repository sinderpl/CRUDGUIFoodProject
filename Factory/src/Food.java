import java.io.Serializable;


public class Food implements Serializable{
	String foodName = "";
	String proteinPer100G = "";
	String carbsPer100G = "";
	String fatPer100G = "" ;
	String calories = "";
	public Food(String foodName, String proteinPer100g,String carbsPer100g,String fatPer100g,String calories){
		this.foodName = foodName;
		this.proteinPer100G = proteinPer100g;
		this.carbsPer100G = carbsPer100g;
		this.fatPer100G = fatPer100g;
		this.calories = calories;
	}
	public String prdouble(){
		return("Food name:"+ foodName+" Protein per 100g: "+proteinPer100G+" Carbohydrates per 100g: "+carbsPer100G+" Fat per 100g: "+fatPer100G+" Calories: "+calories);
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getProteinPer100G() {
		return proteinPer100G;
	}
	public void setProteinPer100G(String proteinPer100G) {
		this.proteinPer100G = proteinPer100G;
	}
	public String getCarbsPer100G() {
		return carbsPer100G;
	}
	public void setCarbsPer100G(String carbsPer100G) {
		this.carbsPer100G = carbsPer100G;
	}
	public String getFatPer100G() {
		return fatPer100G;
	}
	public void setFatPer100G(String fatPer100G) {
		this.fatPer100G = fatPer100G;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
}
