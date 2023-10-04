package main;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


import connection.Connect;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class PlayPageUser {
	
	static Scene scene;
	
	Random rand = new Random();

	VBox vBoxTop1, vBoxTop2, vBoxAll;
	
	HBox hBoxTop, hBoxBottom;
	
	GridPane gridPlayer, gridMonster;
	
	BorderPane borderContainer;
	
	Image monsterImage, playerImage;
	
	ImageView monsterImageView, playerImageView;
	
	Label playerLbl, monsterLbl, playerHpLbl, monsterHpLbl, attackForLbl, 
	
	MonsterAttackForLbl, playerDmgLbl, monsterDmgLbl, attackTypeLbl, monsterTypeLbl;
	
	Button attack1Btn, attack2Btn, attack3Btn, healBtn, endTurnBtn;
	
	int attackTableLen;
	int monsterTableLen;
	
	int attack1Id, attack2Id, attack3Id;
	
	String attack1Name, attack2Name, attack3Name;
	
	int attack1dmg, attack2dmg, attack3dmg;
	
	String attack1dmgDisp, attack2dmgDisp, attack3dmgDisp;
	
	String attack1type, attack2type, attack3type;
	
	String attacktype;
	
	int attackDmg;
	
	int monsterHealth, monsterMinDamage, monsterMaxDamage;
	
	String monsterName;
	
	int monsterId;
	
	int maxHealth = 50, maxMonsterHealth;
	
	int currentHealth = 50;
	
	int monsterDamage;
	
	int healFor;
	
	int addHealth;
	
	String monsterType;
	
	private Connect connect = Connect.getInstance();
	
	public PlayPageUser() throws SQLException {
		initialize();
		set();
		build();
		setButtons();
		setMonster();
		randomizing();
		
		Main.gotoPage(scene);
	}
	
	void initialize() {
		
		vBoxTop1 = new VBox();
		vBoxTop2 = new VBox();
		hBoxTop = new HBox();
		hBoxBottom = new HBox();
		vBoxAll = new VBox();
		
		gridPlayer = new GridPane(); 
		gridMonster = new GridPane();
		
		borderContainer = new BorderPane();
		
		playerLbl = new Label("Player");
		monsterLbl = new Label("Monster");
		
		monsterImage = new Image("monster-icon.png");
		monsterImageView = new ImageView(monsterImage);
		
		playerImage = new Image("player-icon.png");
		playerImageView = new ImageView(playerImage);
		
		playerHpLbl = new Label("HP " + currentHealth + "/" + maxHealth);
		monsterHpLbl = new Label("HP " + monsterHealth + "/" + maxMonsterHealth);
		
		attackForLbl = new Label("Attack or heal for");
		MonsterAttackForLbl = new Label("Monster Intends to Attack for");
		
		playerDmgLbl = new Label();
		monsterDmgLbl = new Label();
		
		attackTypeLbl = new Label();
		monsterTypeLbl = new Label();
		
		attack1Btn = new Button();
		attack2Btn = new Button();
		attack3Btn = new Button();
		
		healBtn = new Button("Heal");
		
		endTurnBtn = new Button("End Turn");
		
		scene = new Scene(borderContainer, 600, 500);
		
	}
	
	void set() {
		
		attack1Btn.setPrefSize(150, 30);
		attack1Btn.setStyle("-fx-background-color: lightgreen;");
		
		attack2Btn.setPrefSize(150, 30);
		attack2Btn.setStyle("-fx-background-color: lightgreen;");
		
		attack3Btn.setPrefSize(150, 30);
		attack3Btn.setStyle("-fx-background-color: lightgreen;");
		
		healBtn.setPrefSize(150, 30);
		healBtn.setStyle("-fx-background-color: lightgreen;");
		
		endTurnBtn.setPrefSize(100, 30);
		endTurnBtn.setStyle("-fx-background-color: cyan;");
		
		Font nameFont = Font.font("Arial", FontWeight.BOLD, 15);
		playerLbl.setFont(nameFont);
		monsterLbl.setFont(nameFont);
		
		
		Font damageFont = Font.font("Arial", FontWeight.BOLD, 12);
		playerDmgLbl.setFont(damageFont);
		monsterDmgLbl.setFont(damageFont);
		
		Font hpFont = Font.font("Arial", 17);
		playerHpLbl.setFont(hpFont);
		monsterHpLbl.setFont(hpFont);
		
		hBoxTop.setSpacing(320);
		hBoxBottom.setSpacing(100);
		
		vBoxTop1.setSpacing(20);
		vBoxTop1.setAlignment(Pos.TOP_LEFT);
		
		
		vBoxTop2.setSpacing(20);
		vBoxTop2.setAlignment(Pos.TOP_RIGHT);
		
		vBoxAll.setSpacing(100);
		
		vBoxAll.setAlignment(Pos.CENTER);
		
		gridPlayer.setHgap(20);
		gridPlayer.setVgap(20);
		gridPlayer.setAlignment(Pos.CENTER_LEFT);
		
		
		gridMonster.setHgap(20);
		gridMonster.setVgap(20);
		gridMonster.setAlignment(Pos.CENTER_RIGHT);
		
		
		BorderPane.setMargin(vBoxAll, new Insets(12,12,12,12));
		
		BorderPane.setAlignment(vBoxAll, Pos.CENTER);
		
		borderContainer.setStyle("-fx-background-color: lightblue;");
	}
	
	void build() {
		vBoxTop1.getChildren().addAll(playerLbl, playerImageView, playerHpLbl);
		vBoxTop2.getChildren().addAll(monsterLbl, monsterImageView, monsterHpLbl);
		hBoxTop.getChildren().addAll(vBoxTop1, vBoxTop2);
		
		gridPlayer.add(attack1Btn, 0, 0);
		gridPlayer.add(attackForLbl, 1, 0);
		
		gridPlayer.add(attack2Btn, 0, 1);
		gridPlayer.add(playerDmgLbl, 1, 1);
		
		gridPlayer.add(attack3Btn, 0, 2);
		gridPlayer.add(attackTypeLbl, 1, 2);
		
		gridPlayer.add(healBtn, 0, 3);
		gridPlayer.add(endTurnBtn, 1, 3);
		
		gridMonster.add(MonsterAttackForLbl, 0, 0);
		gridMonster.add(monsterDmgLbl, 0, 1);
		gridMonster.add(monsterTypeLbl, 0, 2);
		
		hBoxBottom.getChildren().addAll(gridPlayer, gridMonster);
		
		vBoxAll.getChildren().addAll(hBoxTop, hBoxBottom);
		
		borderContainer.setCenter(vBoxAll);
	
	}
	
	void setButtons() throws SQLException {
		
		getAttackTableLen();
		attack1Id = rand.nextInt(attackTableLen) + 1;
		
		do {
			try {
				getAttackTableLen();
				attack2Id = rand.nextInt(attackTableLen) + 1;
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (attack1Id == attack2Id);
		
		do {
			try {
				getAttackTableLen();
				attack3Id = rand.nextInt(attackTableLen) + 1;
			} catch (Exception e) {
				// TODO: handle exception
			}
		} while (attack2Id == attack3Id || attack3Id == attack1Id);
		
		
		getAttack1();
		getAttack2();
		getAttack3();
		
		attack1Btn.setText(attack1Name);
		attack2Btn.setText(attack2Name);
		attack3Btn.setText(attack3Name);
		
		attack1dmgDisp = Integer.toString(attack1dmg);
		attack2dmgDisp = Integer.toString(attack2dmg);
		attack3dmgDisp = Integer.toString(attack3dmg);
		
		attack1Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				playerDmgLbl.setText(attack1dmgDisp + " Damage");
				attackDmg = attack1dmg;
				attacktype = attack1type;
				attackTypeLbl.setText(attacktype);
				addHealth = 0;
				
			}
		});
		
		attack2Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				playerDmgLbl.setText(attack2dmgDisp + " Damage");
				attackDmg = attack2dmg;
				attacktype = attack2type;
				attackTypeLbl.setText(attacktype);
				addHealth = 0;
				
			}
		});
		
		attack3Btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				playerDmgLbl.setText(attack3dmgDisp + " Damage");
				attackDmg = attack3dmg;
				attacktype = attack3type;
				attackTypeLbl.setText(attacktype);
				addHealth = 0;
				
			}
		});
		
		healBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				
				addHealth = healFor;
				playerDmgLbl.setText(addHealth + " Healed");
				attacktype = "";
				attackTypeLbl.setText("");
				
			}
		});
		
		endTurnBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				
				if (monsterHealth <= 0) {
					
					Alert WinSuccess = new Alert(AlertType.INFORMATION);
					WinSuccess.setHeaderText("Congratulations!");
					WinSuccess.setContentText("You Have Won!");
					WinSuccess.showAndWait();
					
					new MainMenuPageUser();
					
				} else if (currentHealth <= 0) {
					
					Alert loseFailed = new Alert(AlertType.ERROR);
					loseFailed.setHeaderText("You Have Lost!");
				    loseFailed.setContentText("Try Again");
					loseFailed.showAndWait();
					
					new MainMenuPageUser();
					
					
				}else if (addHealth == 0 && attackDmg == 0) {
					Alert notClickedError = new Alert(AlertType.ERROR);
					notClickedError.setHeaderText("PLEASE ATTACK/HEAL");
				    notClickedError.setContentText("You have not attacked or healed!");
					notClickedError.showAndWait();

				} else if (addHealth >= 1) {
					currentHealth += addHealth;
					currentHealth -= monsterDamage;
					try {
						setButtons();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					randomizing();
					refreshPage();
				} else if (attackDmg >= 1) {
					
					if (monsterType.equals("ghost") && attacktype.equals("physical")) {
						Alert noDamage = new Alert(AlertType.ERROR);
						noDamage.setHeaderText("No Damage!");
					    noDamage.setContentText("You Cannot Damage Ghost Monsters with Physical");
						noDamage.showAndWait();
					} else if (monsterType.equals("normal") && attacktype.equals("magical")) {
						Alert noDamage = new Alert(AlertType.ERROR);
						noDamage.setHeaderText("No Damage!");
					    noDamage.setContentText("You Cannot Damage Normal Monsters with Magical");
						noDamage.showAndWait();
					} else {
						currentHealth -= monsterDamage;
						monsterHealth -= attackDmg;
						try {
							setButtons();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						randomizing();
						refreshPage();
					}
					
				}
				
			}
		});
		
	}
	
	void setMonster() throws SQLException {
		getMonsterTableLen();
		
		monsterId = rand.nextInt(monsterTableLen) + 1;
		getMonster();
		
		monsterLbl.setText(monsterName);
		maxMonsterHealth = monsterHealth;
		
		monsterHpLbl.setText("HP " + monsterHealth + "/" + maxMonsterHealth);
		monsterTypeLbl.setText("This Monster is a " + monsterType + " type");
	}
	
	void randomizing() {
		
		monsterDamage = rand.nextInt((monsterMaxDamage - monsterMinDamage) + 1) + monsterMinDamage;
		monsterDmgLbl.setText(monsterDamage + "");
		healFor = rand.nextInt(20);
		
	}
	
	void refreshPage() {
		
		if (monsterHealth <= 0) {
			
			monsterHealth = 0;
			
			Alert WinSuccess = new Alert(AlertType.INFORMATION);
			WinSuccess.setHeaderText("Congratulations!");
			WinSuccess.setContentText("You Have Won!");
			WinSuccess.showAndWait();
			
			new MainMenuPageUser();
			
			
		} else if (currentHealth <= 0) {
			
			currentHealth = 0;
			
			Alert loseFailed = new Alert(AlertType.ERROR);
			loseFailed.setHeaderText("You Have Lost!");
		    loseFailed.setContentText("Try Again");
			loseFailed.showAndWait();
			
			new MainMenuPageUser();
			
		}
		
		monsterHpLbl.setText("HP " + monsterHealth + "/" + maxMonsterHealth);
		playerHpLbl.setText("HP " + currentHealth + "/" + maxHealth);
		
		attackDmg = 0;
		
		playerDmgLbl.setText("");
		
	}
	
	
	void getAttackTableLen() throws SQLException {
		
		String query =  " SELECT COUNT(*) AS attacktablelen FROM attack";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				attackTableLen = connect.rs.getInt("attacktablelen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void getMonsterTableLen() throws SQLException {
		
		String query =  " SELECT COUNT(*) AS monstertablelen FROM monster";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				monsterTableLen = connect.rs.getInt("monstertablelen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	void getAttack1() throws SQLException {
		
		String query = "SELECT * FROM attack WHERE id = ?";
		
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setInt(1, attack1Id);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			attack1Name = rs.getString("name");
			attack1dmg = rs.getInt("damage");
			attack1type = rs.getString("type");
		}
	}
	
	void getAttack2() throws SQLException {
		
		String query = "SELECT * FROM attack WHERE id = ?";
		
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setInt(1, attack2Id);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			attack2Name = rs.getString("name");
			attack2dmg = rs.getInt("damage");
			attack2type = rs.getString("type");
		}
	}
	
	void getAttack3() throws SQLException {
		
		String query = "SELECT * FROM attack WHERE id = ?";
		
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setInt(1, attack3Id);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			attack3Name = rs.getString("name");
			attack3dmg = rs.getInt("damage");
			attack3type = rs.getString("type");
		}
	}
	
	void getMonster() throws SQLException {
		
		String query = "SELECT * FROM monster WHERE id = ?";
		
		PreparedStatement ps = connect.prepareStatement(query);
		
		try {
			ps.setInt(1, monsterId);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			monsterName = rs.getString("name");
			monsterMinDamage = rs.getInt("minDamage");
			monsterMaxDamage = rs.getInt("maxDamage");
			monsterHealth = rs.getInt("health");
			monsterType = rs.getString("type");
			
		}
		
	}
	
}
