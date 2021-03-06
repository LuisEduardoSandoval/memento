/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventureSimulator;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import java.io.FileInputStream; 
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class AdventureSimulator extends Application
{
    //Working Directory = C:\Users\Luis Sandoval\Documents\NetBeansProjects\Directory
    static Scanner scan = new Scanner(System.in);
    Label messageLbl = new Label();
    String name = null;
    
    Label Start = new Label("Start");
    BackgroundFill bacground_fill = new BackgroundFill(Color.SLATEGRAY,CornerRadii.EMPTY,Insets.EMPTY);
    Background background = new Background(bacground_fill);
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException
    {
        //System.out.println(System.getProperty("user.dir"));
        
        Image image = new Image(new FileInputStream("Title.gif"));
        Image bkg = new Image(new FileInputStream("BKG.png"));
        
        BackgroundImage Mountains = new BackgroundImage(bkg, BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background Mountainsbkg = new Background(Mountains);
        
        //System.out.println(new File(".").getAbsolutePath());
        
        ImageView imageView = new ImageView(image);
  
        imageView.setX(50);
        imageView.setY(25);
        

        
        imageView.setFitHeight(200);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
        

        
        
        
        //create new Button
        Button startBtn = new Button("Start");
        startBtn.setDefaultButton(true);
        //startBtn.setAlignment(Pos.CENTER);
        startBtn.setMinSize(200, 100);
        //event handler 
        
        startBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e)
            {
                stage.setScene(NameScene(stage));
            }
        });
        
        Button OptionsBtn = new Button("Options");
        OptionsBtn.setMinSize(200,100);
        OptionsBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override public void handle(ActionEvent e)
            {
                printMessage("ok");
            }
        });
        VBox buttonBox = new VBox();
        buttonBox.getChildren().addAll(imageView,startBtn,OptionsBtn);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);

        
        VBox root = new VBox();
        
        root.getChildren().addAll(messageLbl,buttonBox);
        
        root.setSpacing(20);
        root.setMinSize(1280,720);
        root.setBackground(Mountainsbkg);
        
        
        root.setStyle(STYLESHEET_MODENA);
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        stage.setTitle("Adventure Game");
       
        
        stage.show();
        
    
    }
    
    
    protected Scene NameScene(Stage stage)
    {
        Label namechk = new Label();
        VBox nameBox = new VBox();
        Label nameLabel = new Label("Enter Name:");
        TextField nameField = new TextField();
        nameField.setMaxWidth(300);
        
        Button nameCreate = new Button("Confirm");
        nameCreate.setOnAction(new EventHandler<ActionEvent>()
        {
           public void handle(ActionEvent t)
           {
               
               if(nameField.getText()== null || nameField.getText().trim().isEmpty())
               {
                   namechk.setText("No Name Selected");
                  
               }
               else
               {
                System.out.println("Name:"+ nameField.getText()+"was Created");
                name = nameField.getText();
                stage.setScene(ClassSelectionScene(stage));
               }
           }
        });
       
        nameBox.getChildren().addAll(nameLabel,nameField,nameCreate,namechk);
        
        nameBox.setSpacing(20);
        nameBox.setMinSize(1280, 720);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setBackground(background);
        nameBox.setStyle(STYLESHEET_MODENA);
        
        
        return new Scene(nameBox);
    }
    
    
    protected Scene ClassSelectionScene(Stage stage)
    {
        ClassFactory player = new ClassFactory();

        Label selectionMsg = new Label ("Choose your Class");
        Label checkSelection = new Label();
        VBox classBox = new VBox();
        
        RadioButton BerserkerBtn = new RadioButton("Berserker");
        
        RadioButton KnightBtn = new RadioButton("Knight   ");
        
        RadioButton RogueBtn = new RadioButton("Rogue   ");
        
        RadioButton WizardBtn = new RadioButton("Wizard   ");
        
        Button Confirmbtn = new Button("Confirm");
        
        
        Confirmbtn.setOnAction(new EventHandler<ActionEvent>()
        {
           public void handle(ActionEvent t)
           {
               if (BerserkerBtn.isSelected())
               {
                   Classes playerClass = player.getClass("Berserker");
                   playerClass.setStats();
                   System.out.println("BERSERKER!");
                   stage.setScene(GameMenu(playerClass));
               }
               if(KnightBtn.isSelected())
               {
                   Classes playerClass = player.getClass("Knight");
                   playerClass.setStats();
                   stage.setScene(GameMenu(playerClass));
               }
               if(RogueBtn.isSelected())
               {
                   Classes playerClass = player.getClass("Rogue");
                   playerClass.setStats();
                   stage.setScene(GameMenu(playerClass));
               }
               if(WizardBtn.isSelected())
               {
                   Classes playerClass = player.getClass("Wizard");
                   playerClass.setStats();
                   stage.setScene(GameMenu(playerClass));
               }

           }
        });
        
        
        ToggleGroup group = new ToggleGroup();
        
        group.getToggles().addAll(BerserkerBtn,KnightBtn,RogueBtn,WizardBtn);
        
        classBox.getChildren().addAll(selectionMsg,BerserkerBtn,KnightBtn,RogueBtn,WizardBtn,Confirmbtn,checkSelection);
        classBox.setSpacing(20);
        classBox.setMinSize(1280,720);
        classBox.setAlignment(Pos.CENTER);
        classBox.setBackground(background);
        
        return new Scene(classBox);
        
        
    }
    protected Scene GameMenu(Classes player)
    {
        player.setName(name);
        Game_Actions game = new Game_Actions();
        Button Statsbtn = new Button("stats");
        Button adventurebtn = new Button("Start Adventure");
        Button fightbtn = new Button("fight!");
        Button atkbtn = new Button("attack");
	Button savebtn = new Button("Save");
	Button restorebtn = new Button("Restore");
        Button enemystats = new Button("enemystats");
        Button continuebtn = new Button("Continue forward!");
        
        Enemies enemy = new Enemies(player);

	Originator originator = new Originator();

	CareTaker caretaker = new CareTaker(); 
        
        Label StatsLbl = new Label();
        Label enemyLbl = new Label();
        Label gameLbl = new Label();
        Label enemyatkLbl = new Label();
        Label playeratkLbl = new Label();
        Label lvlLbl = new Label();
        VBox status_menu = new VBox();
        
        
        
        fightbtn.setVisible(false);
        atkbtn.setVisible(false);
	savebtn.setVisible(false);
	restorebtn.setVisible(false);
        enemystats.setVisible(false);
        Statsbtn.setVisible(false);
        continuebtn.setVisible(false);
        //lvlLbl.setVisible(false);
        
        Statsbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            //int counter = 0;
            public void handle(ActionEvent t)
            {
                
                
                StatsLbl.setText(game.Display_status(player));
                
            }
            
        });
        adventurebtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                adventurebtn.setVisible(false);
                fightbtn.setVisible(true);
                Statsbtn.setVisible(true);
                gameLbl.setText("an enemy approches!");
                
                
            }
        });

	savebtn.setOnAction(new EventHandler<ActionEvent>()
	{
		public void handle(ActionEvent t)
		{
			savebtn.setVisible(false);		
				
	
			originator.setState(player.getPlayerhp(),player.getXp());

			caretaker.addMemento(originator.save());			
				

			gameLbl.setText("You have saved your player's stats");
		}



	});
	
	restorebtn.setOnAction(new EventHanlder<ActionEvent>()
	{
		public void handle(ActionEvent t)	
		{

			restorebtn.setVisible(false);

			player.setPlayerhp(originator.restoreHP());

			player.setXp(originator.restoreXP());

                	StatsLbl.setText(game.Display_status(player));

			gameLbl.setText("You have restored your player's stats");
		}

		

	});

        fightbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                atkbtn.setVisible(true);
                enemystats.setVisible(true);
                fightbtn.setVisible(false);
            }
        });
        atkbtn.setOnAction(new EventHandler<ActionEvent>()
        {
            
            public void handle(ActionEvent t)
            {
                
                gameLbl.setVisible(false);
                game.fight(player,enemy);
                //game.saveEnemyStats(enemy);
                if(enemy.enemyhp <= 0)
                {
                    enemy.enemyhp = 0;
                   
                    
                    continuebtn.setVisible(true);

                    atkbtn.setVisible(false);
                    
                    lvlLbl.setText(game.lvlTxt);
                    
                    gameLbl.setVisible(true);
                    gameLbl.setText("A new enemy approches!");
                    
                    enemyatkLbl.setVisible(false);
		    savebtn.setVisible(true);
		    restorebtn.setVisible(false);
                    enemyLbl.setVisible(false);
                    playeratkLbl.setVisible(false);
                    
                    
                }
                
                StatsLbl.setText(game.Display_status(player));
                enemyLbl.setText(enemy.printEnemyStats());
                enemyatkLbl.setText(enemy.EnemyTxt);
                
                playeratkLbl.setText(game.PlayerTxt);
                
                    
                
            }
        });
        
        enemystats.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                
                //enemyLbl.setText("Enemy "+"\nhp: " + enemy.enemyhp + "\ndmg: " + enemy.enemy_melee_dmg + "\n");
                enemyLbl.setText(enemy.printEnemyStats());
                
            }
        });
        continuebtn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t )
            {
                    gameLbl.setVisible(false);
                    Enemies enemy1 = new Enemies(player);
                    enemy.enemyhp = enemy1.enemyhp;
                    enemy.enemy_melee_dmg = enemy1.enemy_melee_dmg;
                    atkbtn.setVisible(true);
		    savebtn.setVisible(true);
		    restorebtn.setVisible(true);
                    continuebtn.setVisible(false);
                    StatsLbl.setText(game.Display_status(player));
                    enemyLbl.setText(enemy.printEnemyStats());
                    
                    enemyatkLbl.setVisible(true);
                    enemyLbl.setVisible(true);
                    playeratkLbl.setVisible(true);
                
                
            }
        });
        
        
        
        status_menu.getChildren().addAll(adventurebtn,Statsbtn,StatsLbl,fightbtn,enemystats,enemyLbl,savebtn,restorebtn,atkbtn,continuebtn,gameLbl,playeratkLbl,lvlLbl,enemyatkLbl);
        status_menu.setSpacing(20);
        status_menu.setMinSize(1280,720);
        status_menu.setAlignment(Pos.CENTER);
        status_menu.setBackground(background);
        
        return new Scene(status_menu);
        
        

    }
    
    
    //create second button

    
    //create boundaries

    
    
    
    public void printMessage(String message)
    {
        messageLbl.setText(message);
    }

}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*String charclass;
        String Name;
        
        Game_Actions game = new Game_Actions();
        int num = 2;
        
        while(num > 1)
        {
            System.out.println("Enter Name");
            Name = scan.nextLine();
            Classes player = new Classes(Name);
            System.out.println("Choose your class");
            System.out.println("'k' for knight");
            System.out.println("'r' for rogue");
            System.out.println("'w' for wizard");
            System.out.println("'b' for berserker");
            charclass = scan.nextLine();
            while(charclass.charAt(0) != 'k' && charclass.charAt(0) != 'r' && charclass.charAt(0) != 'w' && charclass.charAt(0) != 'b' && charclass.charAt(0) != 'a'){
                System.out.println("'k' for knight");
                System.out.println("'r' for rogue");
                System.out.println("'w' for wizard");
                System.out.println("'b' for berserker");
                charclass = scan.nextLine();
            }
            if(charclass.charAt(0) == 'k')
            {
                player.Knight();
            }
            else if(charclass.charAt(0) == 'r')
            {
                player.Rogue();
            }
            else if(charclass.charAt(0) == 'w')
            {
                player.Wizard();
            }
            else if(charclass.charAt(0) == 'b'){
                player.Berserker();
            }
            for(int currentLevel = 1; currentLevel <= 5; currentLevel ++)
            {
                while(player.level == currentLevel)
                {
                    Enemies enemy = new Enemies(player);
                    game.fight(player, enemy);
                }
                game.Display_status(player); 
            }
            */
            /*Game_Actions game = new Game_Actions();
            player.Rogue();//initializes class ENIMIES MUST BE INITIATED AFTER CLASS CREATION!
            Enemies enemy = new Enemies(player);
            game.Display_status(player); 
            game.fight(player, enemy);*/
        
        
        
        
        
        
        
        
    

