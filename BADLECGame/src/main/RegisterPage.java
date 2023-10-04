package main;



import connection.Connect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;



public class RegisterPage {
	
	Scene scene;
	
	BorderPane borderContainer;
	GridPane gridContainer;
	HBox genderHBox;
	
	Label titleLbl, usernameLbl,
	emailLbl, passwordLbl, confPasswordLbl, genderLbl, regionLbl; 
	
	TextField usernameTF, emailTF;

	PasswordField passwordTF;
	PasswordField confPasswordTF;
	
	RadioButton maleRB, femaleRB;
	
	ToggleGroup genderTG;
	
	ComboBox<String> regionCB;
	
	Button registerBtn;
	
	Hyperlink loginHL;
	
	String genderChoice;
	
	private Connect connect = Connect.getInstance();
	
	public RegisterPage() {
		initialize();
		set();
		build();
	
		Main.gotoPage(scene);

	}
	

	void initialize() {
		
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		genderHBox = new HBox();
		
		titleLbl = new Label("Register Here");
		usernameLbl = new Label("Username:");
		emailLbl = new Label("Email:");
		passwordLbl = new Label("Password:");
		confPasswordLbl = new Label("Confirm Password:");
		genderLbl = new Label("Gender:");
		regionLbl = new Label("Region:");
		
		usernameTF = new TextField();
		emailTF = new TextField();
		passwordTF = new PasswordField();
		confPasswordTF = new PasswordField();
		usernameTF.setPrefWidth(350);
		
		maleRB = new RadioButton("Male");
		femaleRB = new RadioButton("Female");
		
		genderTG = new ToggleGroup();
		maleRB.setToggleGroup(genderTG);
		femaleRB.setToggleGroup(genderTG);
		
		regionCB = new ComboBox<>();
		regionCB.getItems().addAll("Alam Sutera", "Kemanggisan", "Bekasi", "Bandung", "Semarang", "Malang");
		regionCB.setPrefWidth(150);
		
		registerBtn = new Button("Register");
		
		loginHL = new Hyperlink("Already Have an Account? Login!");
		
		scene = new Scene(borderContainer, 800, 600);
		
		
	}
	
	void set() {
		  GridPane.setHalignment(registerBtn, HPos.CENTER);
		  GridPane.setHalignment(loginHL, HPos.CENTER);
		  GridPane.setHalignment(titleLbl, HPos.CENTER);
		  
		  gridContainer.setVgap(20);
		  gridContainer.setHgap(20);
		  
		  Font font = Font.font("Arial", FontWeight.BOLD, 20);
		  titleLbl.setFont(font);

		  gridContainer.setStyle("-fx-background-color: lightgreen;");
		  
		  registerBtn.setStyle("-fx-background-color: blue;");
		  registerBtn.setTextFill(Color.WHITE);
		  
		  maleRB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				genderChoice = maleRB.getText();
					
			}
		});
			
		  femaleRB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				genderChoice = femaleRB.getText();
					
			}
		});	
		  
		  loginHL.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new LoginPage();
				
			}
		});
		  
		  registerBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				String username = usernameTF.getText();
				String email = emailTF.getText();
				String password = passwordTF.getText();
				String gender = genderChoice;
				String region = regionCB.getValue();
				String user = "User";
				
				  if ((usernameTF.getText().length() < 5 || usernameTF.getText().length() > 20)) {
					   Alert usernameError = new Alert(AlertType.ERROR);
					   usernameError.setHeaderText("Invalid Username!");
					   usernameError.setContentText("Length must be 5 - 20");
					   usernameError.showAndWait();
					 } 
				  
				  else if (!(emailTF.getText()).contains("@") || (emailTF.getText()).endsWith(".")) {
					   Alert emailError = new Alert(AlertType.ERROR);
					   emailError.setHeaderText("Invalid Email!");
					   emailError.setContentText("Must include @email.com");
					   emailError.showAndWait();
					  }
				  
				  else if (passwordTF.getText().length() < 5 || passwordTF.getText().length() > 20) {
					   Alert passwordError = new Alert(AlertType.ERROR);
					   passwordError.setHeaderText("Invalid Password!");
					   passwordError.setContentText("Length must be 5 - 20");
					   passwordError.showAndWait();
					 } 
					  
				  else if (!(passwordTF.getText().toString().equals(confPasswordTF.getText().toString()))) {
					   Alert confirmPasswordError = new Alert(AlertType.ERROR);
					   confirmPasswordError.setHeaderText("Invalid Confirm Password!");
					   confirmPasswordError.setContentText("Password and Confirm Password is Different!");
					   confirmPasswordError.showAndWait();
					 } 
				  
				  else if (genderTG.getSelectedToggle() == null) {
					   Alert genderError = new Alert(AlertType.ERROR);
					   genderError.setHeaderText("Invalid Gender");
					   genderError.setContentText("Please select a gender!");
					   genderError.showAndWait();
					 } 
				  
				  else if (regionCB.getSelectionModel().isEmpty()) {
					   Alert nationalityError = new Alert(AlertType.ERROR);
					   nationalityError.setHeaderText("Invalid Region");
					   nationalityError.setContentText("Please Select a Region");
					   nationalityError.showAndWait();
				  	}
				  
				  else {
					  
					  
					  addData(0, username, email, password, gender, region, user);
					  Alert registerSucess = new Alert(AlertType.INFORMATION);
					  registerSucess.setHeaderText("Message");
					  registerSucess.setContentText("Register Sucess");
						  
					  registerSucess.showAndWait();
					  new LoginPage();
					  
					  
				  }
				  
				  
				
			}
		});
	}
	
	void build() {
		
		genderHBox.getChildren().addAll(maleRB, femaleRB);
		
		gridContainer.add(titleLbl, 1, 0);
		
		gridContainer.add(usernameLbl, 0, 1);
		gridContainer.add(usernameTF, 1, 1);
		
		gridContainer.add(emailLbl, 0, 2);
		gridContainer.add(emailTF, 1, 2);
		
		gridContainer.add(passwordLbl, 0, 3);
		gridContainer.add(passwordTF, 1, 3);
		
		gridContainer.add(confPasswordLbl, 0, 4);
		gridContainer.add(confPasswordTF, 1, 4);
		
		gridContainer.add(genderLbl, 0, 5);
		gridContainer.add(genderHBox, 1, 5);
		
		gridContainer.add(regionLbl, 0, 6);
		gridContainer.add(regionCB, 1, 6);
		
		gridContainer.add(registerBtn, 1, 7);
		
		gridContainer.add(loginHL, 1, 8);
		
		borderContainer.setCenter(gridContainer);
		
		gridContainer.setAlignment(Pos.CENTER);
		  
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
	}
	
	private void addData(int id, String username, String email, String password, String gender, String region, String role) {
		String query = String.format("INSERT INTO user VALUES ('0', '%s', '%s', '%s', '%s', '%s', '%s')", username, email, password, gender, region, role);
		
		connect.execUpdate(query);
		
	}
	
}
