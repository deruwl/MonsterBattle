package main;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import connection.Connect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPage {
	
	Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label emailLbl, passLbl, loginLbl, titleLbl;
	TextField usernameTF;
	PasswordField passPF;
	Stage window;
	Hyperlink registHyperlink;
	
	String role;
	
	Button loginBtn;
	
	private Connect connect = Connect.getInstance();

	public LoginPage() {
		intialize();
		set();
		build();
		
		Main.gotoPage(scene);
		
	}

	void intialize() {
		
		registHyperlink = new Hyperlink();
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		scene = new Scene(borderContainer, 450, 250);
		
		
		titleLbl = new Label("Welcome to Monster Battle!");
		loginLbl = new Label ("Please Login to Continue!");
		emailLbl = new Label ("Username");
		passLbl = new Label ("Password");
		
		usernameTF = new TextField ();
		passPF = new PasswordField ();
		
		loginBtn = new Button("Login");
		
		registHyperlink.setText("Dont have an account? Register here!");
				
	}
	
	void set() {
		
		GridPane.setHalignment(loginBtn, HPos.CENTER);
		GridPane.setHalignment(loginLbl, HPos.CENTER);
		GridPane.setHalignment(registHyperlink, HPos.CENTER);
		gridContainer.setAlignment(Pos.CENTER);
		
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
		Font font = Font.font("Arial", 15);
		loginLbl.setFont(font);
		
		Font titleFont = Font.font("Arial", FontWeight.BOLD, 25);
		titleLbl.setFont(titleFont);
		
		gridContainer.setStyle("-fx-background-color: lightgreen;");
		
		loginBtn.setStyle("-fx-background-color: blue;");
		loginBtn.setTextFill(Color.WHITE);
		
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			
			public void handle(ActionEvent stage) {
				
			try {
				selectID();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			role = role.toLowerCase();
			
			if (role.equals("user")) {
				Alert loginSuccess = new Alert(AlertType.INFORMATION);
				loginSuccess.setHeaderText("Message");
				loginSuccess.setContentText("User Sign Up Success");
				loginSuccess.showAndWait();
				role = "";
				new MainMenuPageUser();
				
			} else if (role.equals("admin")) {
				Alert loginSuccess = new Alert(AlertType.INFORMATION);
				loginSuccess.setHeaderText("Message");
				loginSuccess.setContentText("Admin Sign Up Success");
				loginSuccess.showAndWait();
				role = "";
				new MainMenuPageAdmin();
				
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("Invalid Email/Password!");
				
				Optional<ButtonType> choice = alert.showAndWait();
				if (choice.get() == ButtonType.CANCEL) {
					stage.consume();
				}
			}	
			
			}
		});	
		
		registHyperlink.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new RegisterPage();
				
			}
		});
			
	}
	
	void build() {
		gridContainer.add(titleLbl, 1, 0, 2, 2);
		gridContainer.add(loginLbl, 1, 1, 2, 2); 
		
		gridContainer.add(emailLbl, 1, 3);
		gridContainer.add(usernameTF, 2, 3);
		
		gridContainer.add(passLbl, 1, 4);
		gridContainer.add(passPF, 2, 4);
		
		gridContainer.add(loginBtn, 1, 5, 3, 2);
		gridContainer.add(registHyperlink, 1, 7, 3, 2);
		
		borderContainer.setCenter(gridContainer);
	}
	
	void selectID () throws SQLException  {
		String query = "SELECT * FROM user WHERE username = ? AND password = ?";
		
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setString(1, usernameTF.getText());
			ps.setString(2, passPF.getText());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			role = rs.getString("role");
		}
		
		role.toLowerCase();	

	}

}
