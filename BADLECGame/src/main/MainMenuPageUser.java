package main;

import java.sql.SQLException;

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

public class MainMenuPageUser {

	static Scene scene;
	BorderPane borderContainer;
	GridPane gridContainer;
	
	Label titleLbl;
	Button playBtn;
	Button logoutBtn;
	
	public MainMenuPageUser() {
		initialize();
		set();
		build();
		
		Main.gotoPage(scene);
	}
	
	void initialize() {
		borderContainer = new BorderPane();
		gridContainer = new GridPane();
		
		titleLbl = new Label("LETS PLAY MONSTER BATTLE!");
		playBtn = new Button("Play");
		logoutBtn = new Button("Logout");
		
		scene = new Scene(borderContainer, 600, 400);
		
	}
	
	void set() {
		
		GridPane.setHalignment(titleLbl, HPos.CENTER);
		GridPane.setHalignment(playBtn, HPos.CENTER);
		GridPane.setHalignment(logoutBtn, HPos.CENTER);
		  
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
		Font font = Font.font("Arial", FontWeight.BOLD, 25);
		titleLbl.setFont(font);
		  
		playBtn.setStyle("-fx-background-color: lightseagreen;");
		playBtn.setTextFill(Color.WHITE);
		Font playFont = Font.font("Arial", FontWeight.BOLD, 15);
		playBtn.setFont(playFont);
		
		playBtn.setPrefSize(100, 60);
		
		playBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					new PlayPageUser();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
		gridContainer.add(playBtn, 1, 1);
		gridContainer.add(logoutBtn, 1, 2);
		
		borderContainer.setCenter(gridContainer);
		  
	    gridContainer.setAlignment(Pos.CENTER);
		  
		gridContainer.setVgap(20);
		gridContainer.setHgap(20);
		
	}

	
}
