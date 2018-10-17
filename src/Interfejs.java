import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;

public class Interfejs {
	private Centrala C;
	private Terminal terminal;
	private Screen screen;
	private MultiWindowTextGUI gui;
	public Interfejs() throws IOException {
		C = new Centrala();
		this.terminal = new DefaultTerminalFactory().setMouseCaptureMode(MouseCaptureMode.CLICK).createTerminal();
        this.screen = new TerminalScreen(terminal);
        gui = new MultiWindowTextGUI(screen);
        screen.startScreen();
	}
	public void Logowanie()  throws IOException { 
		// Setup terminal and screen layers
        
    	BasicWindow window = new BasicWindow();


        // Create panel to hold components
        Panel mainpanel = new Panel();
		

		Panel panel_tmp = new Panel();
		mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine("LOGOWANIE")));
		Panel panel = new Panel();
		panel_tmp.addComponent(new EmptySpace(new TerminalSize(0,1)));

		panel_tmp.addComponent(panel);

		panel.setLayoutManager(new GridLayout(4));
        

        panel.addComponent(new Label("Login"));
		panel.addComponent(new EmptySpace(new TerminalSize(3,0)));
		TextBox login = new TextBox();
        panel.addComponent(login);
		panel.addComponent(new EmptySpace(new TerminalSize(0,2)));
		TextBox haslo = new TextBox().setMask('*');

        panel.addComponent(new Label("Has³o"));
		panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(haslo);
		panel.addComponent(new EmptySpace(new TerminalSize(0,2)));

        Button button = new Button("Zaloguj", new Runnable() {
    		@Override
    		public void run() {
    			try {
					if(C.Logowanie(login.getText(),haslo.getText()).equals("")){
						new MessageDialogBuilder()
					.setTitle("B³¹d logowania")
					.setText("Nieprawid³owe has³o lub login")

					.addButton(MessageDialogButton.Close)
					.build()
					.showDialog(gui);
					}
					if(C.Logowanie(login.getText(),haslo.getText()).equals("admin"))
						Menu_rejestracja();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        Button button1 = new Button("Zamknij", new Runnable() {
    		@Override
    		public void run() {
    			try {
					terminal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        panel.addComponent(button);
		panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(button1);

        // Create window to hold the panel
    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(mainpanel);

        // Create gui and start gui
        gui.addWindowAndWait(window);

		
		
		
	}
	
	public void Menu_rejestracja() throws IOException {
		screen.clear();
		gui = new MultiWindowTextGUI(screen);
		
		new ActionListDialogBuilder()
		.setTitle("Menu rejestracji")
		.setDescription("Wybierz opcje")
		.addAction("Zarz¹dzanie pacjentami", new Runnable() {
		    @Override
		    public void run() {
		        // Do 1st thing...
		    }
		})
		.addAction("Zarz¹dzanie lekarzami", new Runnable() {
		    @Override
		    public void run() {
		        // Do 2nd thing...
		    }
		})
		.addAction("Wyloguj", new Runnable() {
		    @Override
		    public void run() {
		        // Do 3rd thing...
		    }
		})
		.addAction("Zamknij", new Runnable() {
		    @Override
		    public void run() {
		        // Do 3rd thing...
		    }
		})
		.build()
		.showDialog(gui);
	}
	
	public void Okno_pacjenta() {
		screen.clear();
		gui = new MultiWindowTextGUI(screen);
		BasicWindow window = new BasicWindow();
       Window widn = null;;

        Panel mainPanel = new Panel().setPreferredSize(new TerminalSize(75,20));
        		
        
		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Panel upPanel = new Panel().setPreferredSize(new TerminalSize(75,2));
		mainPanel.addComponent(upPanel.withBorder(Borders.singleLine("Katalogi")));
		
		Panel cointainerPanel = new Panel().setPreferredSize(new TerminalSize(75,8));
		mainPanel.addComponent(cointainerPanel.withBorder(Borders.singleLine("Cointainer")));
		cointainerPanel.addComponent(new EmptySpace());
		Panel basePanel = new Panel().setPreferredSize(new TerminalSize(77,3));
		mainPanel.addComponent(basePanel.withBorder(Borders.singleLine("Cointainer")));
		window.setComponent(mainPanel);
		gui.addWindowAndWait(window);
		
		
		
		
		
	}
	
}
