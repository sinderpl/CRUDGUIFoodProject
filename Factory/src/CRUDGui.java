
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.rmi.Naming;

public class CRUDGui extends JFrame implements ActionListener{

	public void actionPerformed(ActionEvent e){
			//delete
			if(e.getActionCommand().equals("delete")){
				factory.delete(currentName);
				statusLabel.setText(" Status: Object deleted");
				updateFoods();
				
			}
			//create
			else if(e.getActionCommand().equals("create")){
				if(foodName != null && proteinContent != null &&
						carbContent !=null && fatContent !=null &&
							calorieContent != null){
					factory.create(foodName, proteinContent, carbContent, fatContent, calorieContent);
					statusLabel.setText(" Status: Object created.");
					updateFoods();
				}
				else{
					statusLabel.setText(" Status: The object was not created" +
							" because some of the fields are empty");
					
				}		
			}
			//update
			else if(e.getActionCommand().equals("update")){
				if(foodName != null && proteinContent != null &&
						carbContent !=null && fatContent !=null &&
							calorieContent != null){
					factory.update(currentName,foodName, proteinContent, carbContent, fatContent, calorieContent);
					updateFoods();
					statusLabel.setText(" Status: Object updated.");
				}
				else{
					statusLabel.setText(" Status: The object was not updated" +
							" because some of the fields are empty");
				}
				
			}
			//read
			else if(e.getActionCommand().equals("read")){
				//currentName = null;
				//currentName = foodName;
				Food tempFood = factory.read(currentName);
				statusLabel.setText(" Status: Object details read in.;");
				if(tempFood == null)
					statusLabel.setText(" Status:  There is no object with that name.");
				else{
					foodName = tempFood.getFoodName();
					proteinContent= tempFood.getProteinPer100G();
					carbContent= tempFood.getCarbsPer100G();
					fatContent= tempFood.getFatPer100G();
					calorieContent= tempFood.getCalories();
					currentName = foodName;

				foodNameTextArea.setText(foodName);
				proteinTextArea.setText(proteinContent);
				carbsTextArea.setText(carbContent);
				fatTextArea.setText(fatContent);
				caloriesTextArea.setText(calorieContent);

				statusLabel.setText(" Status: 	Object succesfully read.");
				}
				
			}
			else if(e.getActionCommand().equals("foodName")){
				foodName = ((JTextField) e.getSource()).getText();
				statusLabel.setText(" Status: 	Food name changed.");
			}
			else if(e.getActionCommand().equals("protein")){
				proteinContent  = ((JTextField) e.getSource()).getText();
				statusLabel.setText(" Status: 	Protein changed.");
			}
			else if(e.getActionCommand().equals("carbs")){
				carbContent = ((JTextField) e.getSource()).getText();
				statusLabel.setText(" Status: 	Carbs changed.");
			}
			else if(e.getActionCommand().equals("fat")){
				fatContent = ((JTextField) e.getSource()).getText();
				statusLabel.setText(" Status: 	Fat changed.");
			}
			else if(e.getActionCommand().equals("calories")){
				calorieContent = ((JTextField) e.getSource()).getText();
				statusLabel.setText(" Status: 	Calories changed.");
			}
			else if(e.getActionCommand().equals("object")){
				JRadioButton temp = (JRadioButton)e.getSource();
				//currentName = (String) (e.getSource());
				currentName = temp.getText();
				statusLabel.setText(" Status: 	Food object changed.");
			}
		}
	
		
	//Variables
	String foodName = null;
	String proteinContent= null;
	String carbContent= null;
	String fatContent= null;
	String calorieContent= null;
	String currentName = null;
	
	//JPanels
	//middle panels
	JPanel buttonPanel;
	JPanel readPanel;
	JPanel inputPanel;
	
	//outer panels
	JPanel middlePanel;
	JPanel statusPanel;
	
	//JButtons
	JButton createButton;
	JButton readButton;
	JButton updateButton;
	JButton deleteButton;
	
	
	
	//JLabels
	JLabel foodLabel;
	JLabel proteinLabel;
	JLabel carbsLabel;
	JLabel fatLabel;
	JLabel calorieLabel;
	JLabel statusLabel;
	
	//JtextArea
	JTextField foodNameTextArea;
	JTextField proteinTextArea;
	JTextField carbsTextArea;
	JTextField fatTextArea;
	JTextField caloriesTextArea;
	
	//Vectors
	Vector <JRadioButton> dataCheckBoxes;
	Vector <Food> foodVector;
	//Factory
	private String theURL = "rmi:///";
	private Factory factory;
	//current object
	Food currentFood = null;
	//groups
	ButtonGroup radioGroup = new ButtonGroup();
	
	//document listener

	
	public void createFoods(){
		
		factory.create("Chicken","20","10","5","231");
		factory.create("Beef","15","8","20","180");
		factory.create("Pasta","10","40","3","300");
		factory.create("Cereal","9","15","5","200");
		factory.create("Peanut Butter","34","4","20","400");
		factory.saveArray();
		updateFoods();
	}
	public void updateFoods(){
		
		//readPanel = new JPanel();
		//readPanel.removeAll();
		//readPanel.setBackground(Color.black);
		//readPanel.setVisible(false);
		readPanel.removeAll();
		readPanel.revalidate();

		foodVector = factory.getFoods();
		if(dataCheckBoxes.size()>1)
		dataCheckBoxes.clear();
		//dataCheckBoxes = new Vector<JRadioButton>();
	
		radioGroup = new ButtonGroup();
		//dataCheckBoxes = new Vector <JRadioButton>();
		readPanel.setLayout(new GridLayout(foodVector.size(),1));
		for(int x = 0;x<foodVector.size();x++){
			String name = foodVector.get(x).getFoodName();
			JRadioButton button = new JRadioButton(name);
			button.addActionListener(this);
			button.setActionCommand("object");
			dataCheckBoxes.add(button);
			readPanel.add(button);
			radioGroup.add(button);
			//button.setBackground(Color.white);
		}
		//readPanel.validate();
		readPanel.revalidate();
		readPanel.repaint();
		repaint();
		this.add(readPanel,BorderLayout.WEST);
		readPanel.setVisible(true);
		this.setVisible(true);
		factory.saveArray();
		
	}

	
	public CRUDGui(){
		setSize(800,800);
		setTitle("Java Assignent 2");
		setLayout(new BorderLayout());
		currentName = "Chicken";
		
		dataCheckBoxes = new Vector<JRadioButton>();
		readPanel = new JPanel();
		//readPanel.setSize(600,200);
			//factory = (Factory) Naming.lookup(theURL + "factory");
		factory = factory.getInstance();
		createFoods();
		Border areaBorder = BorderFactory.createLineBorder(Color.BLACK);
		//JLabel
		foodLabel = new JLabel(" Food Name: ");
		foodLabel.setBorder(areaBorder);
		proteinLabel = new JLabel(" Protein per 100g: ");
		proteinLabel.setBorder(areaBorder);
		carbsLabel = new JLabel(" Carbs per 100g: ");
		carbsLabel.setBorder(areaBorder);
		fatLabel = new JLabel(" Fat per 100g: ");
		fatLabel.setBorder(areaBorder);
		calorieLabel = new JLabel(" Calories: ");
		calorieLabel.setBorder(areaBorder);
		statusLabel = new JLabel(" Status:");
		statusLabel.setBorder(areaBorder);
		
		//JTextArea
		foodNameTextArea =new JTextField() ;
		foodNameTextArea.setBorder(areaBorder);
		foodNameTextArea.addActionListener(this);
		foodNameTextArea.setActionCommand("foodName");
		
		proteinTextArea =new JTextField();
		proteinTextArea.setBorder(areaBorder);
		proteinTextArea.setActionCommand("protein");
		proteinTextArea.addActionListener(this);
	
		carbsTextArea =new JTextField();
		carbsTextArea.setBorder(areaBorder);
		carbsTextArea.setActionCommand("carbs");
		carbsTextArea.addActionListener(this);
		
		fatTextArea =new JTextField();
		fatTextArea.setBorder(areaBorder);
		fatTextArea.setActionCommand("fat");
		fatTextArea.addActionListener(this);
		
		caloriesTextArea =new JTextField();
		caloriesTextArea.setBorder(areaBorder);
		caloriesTextArea.setActionCommand("calories");
		caloriesTextArea.addActionListener(this);
		
		
		//JButtons
			//create Button
		createButton = new JButton("Create");
		createButton.setActionCommand("create");
		createButton.addActionListener(this);
			//readButton
		readButton = new JButton("Read");
		readButton.setActionCommand("read");
		readButton.addActionListener(this);
			//update button
		updateButton = new JButton("Update");
		updateButton.setActionCommand("update");
		updateButton.addActionListener(this);
			//deleteButton
		deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("delete");
		deleteButton.addActionListener(this);
		
		//JPanels
			//button panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,1));
		buttonPanel.add(createButton);
		buttonPanel.add(readButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
		
			//read panel
		//readPanel = new JPanel();
		readPanel.setBorder(new EmptyBorder(10,10,10,10));
		
			//input panel
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(5,2));
		inputPanel.add(foodLabel);
		inputPanel.add(foodNameTextArea);
		inputPanel.add(proteinLabel);
		inputPanel.add(proteinTextArea);
		inputPanel.add(carbsLabel);
		inputPanel.add(carbsTextArea);
		inputPanel.add(fatLabel);
		inputPanel.add(fatTextArea);
		inputPanel.add(calorieLabel);
		inputPanel.add(caloriesTextArea);
		inputPanel.setBorder(new EmptyBorder(10,10,10,10));
		
			//middle panel
		middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(1,3));
		middlePanel.add(readPanel);
		middlePanel.add(inputPanel);
		middlePanel.add(buttonPanel);
		
			//status panel
		statusPanel = new JPanel();
		statusPanel.add(statusLabel);
		
		add(middlePanel, BorderLayout.CENTER);
		add(statusLabel, BorderLayout.SOUTH);
		
		
		
		setVisible(true);
	}

}
