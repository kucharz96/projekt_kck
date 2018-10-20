import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.WindowListener;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;

public class Interfejs {
	private Centrala C;
	private Terminal terminal;
	private Screen screen;
	private MultiWindowTextGUI gui;
	public Interfejs() throws IOException {
		C = new Centrala();
		this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);
        gui = new MultiWindowTextGUI(screen);
        screen.startScreen();
        
	}
	//////////////LOGOWANIE//////////////////////////////////
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
    	//window.setSize(new TerminalSize(50, 10));
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
	public class KeyStrokeListener implements WindowListener {
	    public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
	    	if(keyStroke.getKeyType() == KeyType.F6)
	    	{
	    		///Dla F6
	    	new MessageDialogBuilder()
			.setTitle("Wcisniêty F6!")

			.addButton(MessageDialogButton.Close)
			.build()
			.showDialog(gui);
	    	}
	    	if(keyStroke.getKeyType() == KeyType.F7)
	    	{
	    		///Dla F7
	    	new MessageDialogBuilder()
			.setTitle("Wcisniêty F7!")

			.addButton(MessageDialogButton.Close)
			.build()
			.showDialog(gui);
	    	}
	    	if(keyStroke.getKeyType() == KeyType.F8)
	    	{
	    		///Dla F8
	    	new MessageDialogBuilder()
			.setTitle("Wcisniêty F8!")

			.addButton(MessageDialogButton.Close)
			.build()
			.showDialog(gui);
	    	}
	    }

	    public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
	        // TODO Auto-generated method stub
	    }

	    public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
	        // TODO Auto-generated method stub
	    }

	    public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
	        // TODO Auto-generated method stub
	    }
	}
	public void Okno_glowne() throws IOException
	{
		BasicWindow window = new BasicWindow();
		Panel mainPanel = new Panel();
		final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
		//KeyStroke keyStroke = terminal.pollInput();
        KeyStrokeListener listener = new KeyStrokeListener();
        //keyStroke = terminal.pollInput();
        window.addWindowListener(listener);
		//System.out.println("keyPressed: " + keyStroke.getKeyType());
		window.setHints(Arrays.asList(Window.Hint.NO_DECORATIONS,Window.Hint.FIT_TERMINAL_WINDOW));
		StringBuilder abc = new StringBuilder();
		for(int a = 0;a<200;a++)
		abc.append(" ");
		
		mainPanel.addComponent(new Label(abc.toString()).setBackgroundColor(TextColor.ANSI.BLUE)
				.setForegroundColor(TextColor.ANSI.WHITE));
		mainPanel.addComponent(new EmptySpace());

		Panel menu = new Panel().setPreferredSize(new TerminalSize(175,1));
		menu.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		mainPanel.addComponent(menu.withBorder(Borders.singleLine("Katalogi")));
		ActionListBox actionListBox = new ActionListBox();
	
		
		Button Pacjent = new Button("Pacjenci");
		Button Wizyty = new Button("Wizyty");
		Button Skierowania = new Button("Skierowania");
		Button Recepty = new Button("Recepty");
		Pacjent.setRenderer(null);

		menu.addComponent(Pacjent);
		menu.addComponent(Wizyty);
		menu.addComponent(Skierowania);
		menu.addComponent(Recepty);
		
		Panel info = new Panel().setPreferredSize(new TerminalSize(175,1));
		mainPanel.addComponent(info);
		info.setLayoutManager(new GridLayout(2));
		
		TextBox wyszukiwarka = new TextBox().setPreferredSize(new TerminalSize(12,1));
        info.addComponent(new Label("Podaj pesel: "));
		info.addComponent(wyszukiwarka);
		Panel cointainer = new Panel().setPreferredSize(new TerminalSize(175,12));
		mainPanel.addComponent(cointainer.withBorder(Borders.singleLine("Katalogi")));

		Panel base1Panel = new Panel().setPreferredSize(new TerminalSize(175,3));
		base1Panel.setLayoutManager(new GridLayout(6));

		mainPanel.addComponent(base1Panel.withBorder(Borders.singleLine("Skróty")));
		
		base1Panel.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel.addComponent(new Label("F6: Dodaj"));
		base1Panel.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel.addComponent(new Label("F7: Usuñ"));
		base1Panel.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel.addComponent(new Label("F8: Edytuj"));
		base1Panel.addComponent(new EmptySpace(new TerminalSize(3,0)));
		window.setComponent(mainPanel);
		window.setHints(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.NO_DECORATIONS));
		window.setComponent(mainPanel);
		terminal.flush();
        gui.addWindowAndWait(window);

		
        
        
        
        

		/*
		if(keyStroke.getKeyType() == KeyType.F6)
		{
			
			
		}
		if(keyStroke.getKeyType() == KeyType.F7)
		{
			
			
		}
		*/
		
		
		
		



        



		
		

		
	}
	public void Okno_pacjenta() {
		screen.clear();
		gui = new MultiWindowTextGUI(screen);
		BasicWindow window = new BasicWindow();
       

        Panel mainPanel = new Panel().setPreferredSize(new TerminalSize(170,45));
        		
        
		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));
		
		Panel upPanel = new Panel().setPreferredSize(new TerminalSize(170,2));
		mainPanel.addComponent(upPanel.withBorder(Borders.singleLine("Katalogi")));
		
		Panel cointainerPanel = new Panel().setPreferredSize(new TerminalSize(170,5));
		mainPanel.addComponent(cointainerPanel.withBorder(Borders.singleLine("Cointainer")));
		cointainerPanel.addComponent(new EmptySpace());
		Panel basePanel = new Panel().setPreferredSize(new TerminalSize(170,30));
		mainPanel.addComponent(basePanel.withBorder(Borders.singleLine("Cointainer")));
		
		Table<String> table = new Table<String>("Pesel","Imie","Nazwisko","Wiek");
		for(Pacjent a : C.getPacjenci()) {
			table.getTableModel().addRow(a.getPesel(), a.getImie(), a.getNazwisko(), Integer.toString(a.getWiek()));

			
		}
		basePanel.addComponent(new EmptySpace());
		basePanel.addComponent(table);
		
		Panel base1Panel = new Panel().setPreferredSize(new TerminalSize(170,3));
		mainPanel.addComponent(base1Panel.withBorder(Borders.singleLine("Cointainer")));
		window.setComponent(mainPanel);
		gui.addWindowAndWait(window);
		
		
		
		
		
	}
	
}
