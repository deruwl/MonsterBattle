package main;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenuPageAdmin {

	static Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label titleLbl;
	Button updateMonsterBtn;
	Button logoutBtn;
	
	public MainMenuPageAdmin() {
		initialize();
		set();
		build();
		
		Main.gotoPage(scene);
	}
	
	void initialize() {
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		
		titleLbl = new Label("Monster Battle Admin!");
		updateMonsterBtn = new Button("Manage Monsters");
		logoutBtn = new Button("Logout");
		
		scene = new Scene(borderContainer, 600, 400);
		
	}
	
	void set() {
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(updateMonsterBtn, HPos.CENTER);
		GridPane.setHalignment(logoutBtn, HPos.CENTER);
		  
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
		Font font = Font.font("Arial", FontWeight.BOLD, 25);
		titleLbl.setFont(font);
		  
		updateMonsterBtn.setStyle("-fx-background-color: lightseagreen;");
		updateMonsterBtn.setTextFill(Color.WHITE);
		Font playFont = Font.font("Arial", FontWeight.BOLD, 15);
		updateMonsterBtn.setFont(playFont);
		
		updateMonsterBtn.setPrefSize(200, 60);
		
		
		updateMonsterBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				new ManageMonsterPageAdmin();
				
			}
		});
		
		logoutBtn.setStyle("-fx-background-color: lightblue;");
		
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				new LoginPage();
				
			}
		});
		
		gridContainer.setStyle("-fx-background-color: lightgreen;");

		

	}
	
	void build() {
		
		gridContainer.add(titleLbl, 1, 0);
		gridContainer.add(updateMonsterBtn, 1, 1);
		gridContainer.add(logoutBtn, 1, 3);
		
		borderContainer.setCenter(gridContainer);
		  
	    gridContainer.setAlignment(Pos.CENTER);
		  
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
	}
	
	

	
}
