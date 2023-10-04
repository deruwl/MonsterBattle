package main;

import java.sql.SQLException;

import connection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.Monster;



public class ManageMonsterPageAdmin {
	
	public ManageMonsterPageAdmin() {
		initialize();
		set();
		build();
		refreshTable();
		
		Main.gotoPage(scene);
	}
	
	 Scene scene;
	 
	 BorderPane borderContainer;
	 GridPane gridContainer;
	 FlowPane flowContainer;
	 
	 Label idLbl, idShowLbl, nameLbl, typeLbl, minDamageLbl, maxDamageLbl, healthLbl;
	 Spinner<Integer> minDamageSpn, maxDamageSpn, healthSpn;
	 TableView<Monster> monsterInfoTable;
	 ObservableList<Monster> monsterData;
	 Button insertBtn, updateBtn, deleteBtn, backToMenuBtn;
	 ComboBox<Object> typeCB;
	 TextField nameTF;
	 
	 Connect connect = Connect.getInstance();
	 
	 @SuppressWarnings("unchecked")
	void initialize() {
		 
		  borderContainer = new BorderPane();
		  gridContainer = new GridPane();
		  flowContainer = new FlowPane();
		  
		  
		  idLbl = new Label("ID:");
		  idShowLbl = new Label();
		  nameLbl = new Label("Monster Name:");
		  typeLbl = new Label("Monster Type:");
		  minDamageLbl = new Label("Minimal Damage:");
		  maxDamageLbl = new Label("Maximum Damage:");
		  healthLbl = new Label("Health:");
		  
		  minDamageSpn = new Spinner<>(5, 10, 5);
		  maxDamageSpn = new Spinner<>(15, 40, 15);
		  
		  healthSpn = new Spinner<>(30, 80, 30);
		  
		  typeCB = new ComboBox<>();
		  typeCB.getItems().addAll("normal", "ghost");
		  
		  nameTF = new TextField();
		  
		  insertBtn = new Button("Insert");
		  updateBtn = new Button("Update");
		  deleteBtn = new Button("Delete");
		  backToMenuBtn = new Button("Back To Menu");
		  
		  monsterInfoTable = new TableView<Monster>();
		  
		  monsterInfoTable.setEditable(true);
		  
		  TableColumn<Monster, Integer> idCol = new TableColumn<>("Monster ID");
		  idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		  idCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  TableColumn<Monster, String> nameCol = new TableColumn<>("Name");
		  nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		  nameCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  TableColumn<Monster, String> typeCol = new TableColumn<>("Type");
		  typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		  typeCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  TableColumn<Monster, Integer> minDamageCol = new TableColumn<>("Min Damage");
		  minDamageCol.setCellValueFactory(new PropertyValueFactory<>("minDamage"));
		  minDamageCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  TableColumn<Monster, Integer> maxDamageCol = new TableColumn<>("Max Damage");
		  maxDamageCol.setCellValueFactory(new PropertyValueFactory<>("maxDamage"));
		  maxDamageCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  TableColumn<Monster, Integer> healthCol = new TableColumn<>("Health");
		  healthCol.setCellValueFactory(new PropertyValueFactory<>("health"));
		  healthCol.setMinWidth(borderContainer.getWidth() / 5);
		  
		  monsterInfoTable.getColumns().addAll(idCol, nameCol, typeCol, minDamageCol, maxDamageCol, healthCol);
		 
		  monsterData = FXCollections.observableArrayList();
		  monsterInfoTable.setItems(monsterData);
		  monsterInfoTable.setMinHeight(120);
		  monsterInfoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		  
		  scene = new Scene(borderContainer,800,800);
		  
	 } 
	 
	 void set() {
		 
		 borderContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		 
		  gridContainer.setHgap(10);
		  gridContainer.setVgap(30);
		  gridContainer.setPadding(new Insets(25, 25, 25, 25));
		  
		  insertBtn.setPrefWidth(100);
		  updateBtn.setPrefWidth(100);
		  deleteBtn.setPrefWidth(100);
		  backToMenuBtn.setPrefWidth(100);
		  
		  monsterInfoTable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Monster m = monsterInfoTable.getSelectionModel().getSelectedItem();
				
				String idString = Integer.toString(m.getId());
		
				idShowLbl.setText(idString);
				nameTF.setText(m.getName());
				typeCB.setValue(m.getType());
				minDamageSpn.getValueFactory().setValue(m.getMinDamage());
				maxDamageSpn.getValueFactory().setValue(m.getMaxDamage());
				healthSpn.getValueFactory().setValue(m.getHealth());
			}
		});
		  
		  backToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				new MainMenuPageAdmin();
				
			}
			
			
		});
		  
		  insertBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String name1 = (String) nameTF.getText();
				String type1 =  (String) typeCB.getValue();
				int minDamage1 = (Integer) minDamageSpn.getValueFactory().getValue();
				int maxDamage1 = (Integer) maxDamageSpn.getValueFactory().getValue();
				int health1 = (Integer) healthSpn.getValueFactory().getValue();
				
				if(nameTF.getText().length() < 5 || nameTF.getText().length() > 20) {
					Alert sizeError = new Alert(AlertType.ERROR);
					sizeError.setHeaderText("Error");
					sizeError.setContentText("The Monsters Name's Length Must be 5 to 20");
					sizeError.showAndWait();
				}
				
				else if (typeCB.getValue().equals(null)) {
					Alert sizeError = new Alert(AlertType.ERROR);
					sizeError.setHeaderText("Error");
					sizeError.setContentText("Please Choose a Monster Type");
					sizeError.showAndWait();
					
				} else {
					addData(name1, type1, minDamage1, maxDamage1, health1);
					monsterData.clear();
					refreshTable();
					Alert insertSuccess = new Alert(AlertType.INFORMATION);
					insertSuccess.setHeaderText("Message");
					insertSuccess.setContentText("Monster Successfully Inserted");
				}
				
				
			}
		});
		  
		  deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if (idShowLbl.getText().equals(null)) {
					Alert emptyError = new Alert(AlertType.ERROR);
					emptyError.setHeaderText("Error");
					emptyError.setContentText("Please Choose a column to delete!");
					emptyError.showAndWait();
					
				} else {
					deleteData();
					monsterData.clear();
					refreshTable();
					Alert insertSuccess = new Alert(AlertType.INFORMATION);
					insertSuccess.setHeaderText("Message");
					insertSuccess.setContentText("Monster Successfully Deleted");
					
				}
				
			}
		});
		  
		 updateBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String name1 = (String) nameTF.getText();
				String type1 =  (String) typeCB.getValue();
				int minDamage1 = (Integer) minDamageSpn.getValueFactory().getValue();
				int maxDamage1 = (Integer) maxDamageSpn.getValueFactory().getValue();
				int health1 = (Integer) healthSpn.getValueFactory().getValue();
				
				updateData(name1, type1, minDamage1, maxDamage1, health1);;
				monsterData.clear();
				refreshTable();
				Alert insertSuccess = new Alert(AlertType.INFORMATION);
				insertSuccess.setHeaderText("Message");
				insertSuccess.setContentText("Monster Successfully Updated");
				
			}
		});

	 }
	 
	 void build() {
		 
		 borderContainer.setTop(monsterInfoTable);
		 borderContainer.setCenter(gridContainer);

		  gridContainer.add(idLbl, 0, 0);
		  gridContainer.add(idShowLbl, 1, 0);
		  
		  gridContainer.add(nameLbl, 0, 1);
		  gridContainer.add(nameTF, 1, 1);

		  gridContainer.add(typeLbl, 0, 2);
		  gridContainer.add(typeCB, 1, 2);
		  
		  gridContainer.add(minDamageLbl, 0, 3);
		  gridContainer.add(minDamageSpn, 1, 3);

		  gridContainer.add(maxDamageLbl, 0, 4);
		  gridContainer.add(maxDamageSpn, 1, 4);
		  
		  gridContainer.add(healthLbl, 0, 5);
		  gridContainer.add(healthSpn, 1, 5);
		  
		  gridContainer.add(insertBtn, 3, 1);
		  gridContainer.add(updateBtn, 3, 2);
		  gridContainer.add(deleteBtn, 3, 3);
		  gridContainer.add(backToMenuBtn, 3, 5);

		 
	 }
	 
	 void refreshTable() {
		  String query = "SELECT * FROM monster";
		  connect.rs = connect.execQuery(query);
		  
		  try {
		   while (connect.rs.next()) {
		    int id = connect.rs.getInt("id");
		    String name = connect.rs.getString("name");
		    String type = connect.rs.getString("type");
		    int minDamage = connect.rs.getInt("minDamage");
		    int maxDamage = connect.rs.getInt("maxDamage");
		    int health = connect.rs.getInt("health");
		    monsterData.add(new Monster(id, name, type, minDamage, maxDamage, health));
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		 }
	 
		private void addData(String name, String type, int minDamage, int maxDamage, int health) {
			String query = String.format("INSERT INTO monster VALUES ('0', '%s', '%s', '%d',' %d', '%d')", name, type, minDamage, maxDamage, health);
			
			connect.execUpdate(query);
			
		}
		
		private void deleteData() {
			int id = Integer.parseInt(idShowLbl.getText());
			String query = String.format("DELETE FROM monster WHERE id = %d", id);
			
			connect.execUpdate(query);
			
		}
		
		@SuppressWarnings("unused")
		private void updateData(String name, String type, int minDamage, int maxDamage, int health) {
			int id = Integer.parseInt(idShowLbl.getText());
			String query = String.format("UPDATE monster SET name = '%s', type = '%s', minDamage = %d, maxDamage = %d, health = %d WHERE id = %d", name, type, minDamage, maxDamage, health, id);
			
			connect.execUpdate(query);
		}
}
