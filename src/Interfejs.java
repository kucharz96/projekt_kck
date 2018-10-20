import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Button.Listener;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.InputFilter;
import com.googlecode.lanterna.gui2.Interactable.FocusChangeDirection;
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

import javafx.scene.shape.Sphere;

public class Interfejs {
	private Panel container;
	private Table<String> table;
	private Centrala C;
	private TextBox wyszukiwarka1;
	private Button Pacjent;
	private Button Skierowania;
	private Button Wizyty;
	private Button Recepty;
	private Terminal terminal;
	private Screen screen;
	private BasicWindow window;
	private MultiWindowTextGUI gui;
	private boolean przyciskPacjent = false;
	private boolean przyciskSkierowania = false;
	private boolean przyciskWizyty = false;
	private boolean przyciskRecepty = false;
	
	private ButtonListener list = new ButtonListener();
	private KeyStrokeListener listener0 = new KeyStrokeListener();
	private KeyStrokeListener listener1 = new KeyStrokeListener();
	private KeyStrokeListener listener2 = new KeyStrokeListener();
	private KeyStrokeListener listener3 = new KeyStrokeListener();
	private KeyStrokeListener listener4 = new KeyStrokeListener();

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
	public class ButtonListener implements Button.Listener
	{

		@Override
		public void onTriggered(Button button) {
			// TODO Auto-generated method stub
			if(button.equals(Pacjent))
			{
				przyciskPacjent = true;
				//window.addWindowListener(windowListener);
				//window.removeWindowListener(listener0);
	    		//window.removeWindowListener(listener1);
	    		//window.removeWindowListener(listener2);
	    		//window.removeWindowListener(listener3);
	    		//window.removeWindowListener(listener4);
				System.out.println("XDD");
			}
			if(button.equals(Wizyty))
			{
				System.out.println("Heh");
			}
			if(button.equals(Recepty))
			{
				
				
			}
			if(button.equals(Skierowania))
			{
				
			}
		}
		
	}
	public class KeyStrokeListener implements WindowListener {
		
		private boolean focus1,focus2,focus3,focus4;
		
		public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {
	    	if(keyStroke.getKeyType() == KeyType.F6 && przyciskPacjent == true)
	    	{
	    		///Dla F6
	    		Dodaj_pacjenta();
	    		/*
	    	new MessageDialogBuilder()
			.setTitle("Wcisniêty F6!")

			.addButton(MessageDialogButton.Close)
			.build()
			.showDialog(gui);
			*/
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
	    	if(basePane.getFocusedInteractable() == wyszukiwarka1 && (keyStroke.getKeyType() == KeyType.ArrowUp || keyStroke.getKeyType() == KeyType.Escape) ) {
	    		
	    		przyciskPacjent = false;
	    		Pacjent.onLeaveFocus(null, null);
	    		Skierowania.onLeaveFocus(null, null);
	    		Wizyty.onLeaveFocus(null, null);
	    		Recepty.onLeaveFocus(null, null);
	    		container.removeAllComponents();
	    		wyszukiwarka1.setEnabled(false);
	    		basePane.setFocusedInteractable(Pacjent);
	    		window.removeWindowListener(listener0);
	    		//window.removeWindowListener(listener1);
	    		//window.removeWindowListener(listener2);
	    		//window.removeWindowListener(listener3);
	    		//window.removeWindowListener(listener4);
	    	}
	    }

	    public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
	        // TODO Auto-generated method stub
	    }

	    public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
	        // TODO Auto-generated method stub
	    }

		public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
	    
		
		}		
	}
	public void Dodaj_pacjenta()
	{
		window = new BasicWindow("Dodaj pacjenta");
		//window.addWindowListener(listener);
		//window1.addWindowListener(listener1);
		window.setCloseWindowWithEscape(true);
		Panel mainpanel = new Panel(new GridLayout(2));
		//Do PESELU
		mainpanel.addComponent(new Label("Numer PESEL"));
		//mainpanel.addComponent(new EmptySpace());
		TextBox TextPesel = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("([0-9]){0,11}"));
		mainpanel.addComponent(TextPesel);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());

		//Imiê
		mainpanel.addComponent(new Label("Imie"));
		TextBox TextImie = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextImie);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());

		//Nazwisko
		mainpanel.addComponent(new Label("Nazwisko"));
		TextBox TextNazwisko = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextNazwisko);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//Wiek
		mainpanel.addComponent(new Label("Wiek"));
		TextBox TextWiek = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextWiek);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//Ulica
		mainpanel.addComponent(new Label("Ulica"));
		TextBox TextUlica = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextUlica);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//Numer domu
		mainpanel.addComponent(new Label("Numer domu"));
		TextBox TextNumerDomu = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextNumerDomu);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//Numer mieszkania
		mainpanel.addComponent(new Label("Numer mieszkania"));
		TextBox TextNumerMieszkania = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextNumerMieszkania);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//Miejscowoœæ
		mainpanel.addComponent(new Label("Miejscowoœæ"));
		TextBox TextMiejscowosc = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextMiejscowosc);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		Button button = new Button("Dodaj", new Runnable() {
    		@Override
    		public void run() {
    			if(TextImie.getText().isEmpty() || TextNazwisko.getText().isEmpty() || TextWiek.getText().isEmpty() || TextUlica.getText().isEmpty()
    					|| TextNumerDomu.getText().isEmpty() || TextNumerMieszkania.getText().isEmpty() || TextMiejscowosc.getText().isEmpty()) 
				{
					new MessageDialogBuilder()
					.setTitle("Error")
					.setText("Uzupe³nij puste pola.")
					.addButton(MessageDialogButton.Close.valueOf("OK"))
					.build()
					.showDialog(gui);
				}
				for(Pacjent P: C.getPacjenci()) {
					
					if(TextPesel.getText().equals(P.getPesel()))
					{
						new MessageDialogBuilder()
						.setTitle("Error")
						.setText("Pesel ju¿ taki wystêpuje.")
						.addButton(MessageDialogButton.Close)
						.build()
						.showDialog(gui);
					}
				}
				
    		}
    	});
        Button button1 = new Button("Zamknij", new Runnable() {
    		@Override
    		public void run() {
    	       
    			window.close();
    		}
    	});
        mainpanel.addComponent(button1);
		//mainpanel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        mainpanel.addComponent(button);
		// Create window to hold the panel
    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(mainpanel);
        //window1.removeWindowListener(listener1);
        //window1.removeWindowListener(listener);
        // Create gui and start gui
        gui.addWindowAndWait(window);
		
	}
	public void Okno_glowne() throws IOException
	{
		window = new BasicWindow();
		Panel mainPanel = new Panel();
		
		
		//window.removeWindowListener(listener0);
		final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
		//KeyStroke keyStroke = terminal.pollInput();
        
        //keyStroke = terminal.pollInput();
        
		//System.out.println("keyPressed: " + keyStroke.getKeyType());
		window.setHints(Arrays.asList(Window.Hint.NO_DECORATIONS));
		StringBuilder abc = new StringBuilder();
		for(int a = 0;a<200;a++)
		abc.append(" ");
		
		mainPanel.addComponent(new Label(abc.toString()).setBackgroundColor(TextColor.ANSI.BLUE)
				.setForegroundColor(TextColor.ANSI.WHITE));
		mainPanel.addComponent(new EmptySpace());

		Panel menu = new Panel().setPreferredSize(new TerminalSize(175,1));
		menu.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		container = new Panel().setPreferredSize(new TerminalSize(175,12));
		mainPanel.addComponent(menu.withBorder(Borders.singleLine("Katalogi")));
		Panel info = new Panel().setPreferredSize(new TerminalSize(175,1));
		Panel base1Panel = new Panel().setPreferredSize(new TerminalSize(175,3));
		ActionListBox actionListBox = new ActionListBox();
		wyszukiwarka1 = new TextBox().setPreferredSize(new TerminalSize(12,1)).setValidationPattern(Pattern.compile("([0-9]){0,11}"));
		wyszukiwarka1.setEnabled(false);
		
		Pacjent = new Button("Pacjenci", new Runnable() {
		
			
			@Override
			public void run() {
				window.addWindowListener(listener0);
				//window.addWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.removeWindowListener(listener3);
				//window.removeWindowListener(listener4);
				table = new Table<String>("Pesel","Imie","Nazwisko","Wiek", "Ulica", "Numer domu", "Numer mieszkania", "Miejscowoœæ");
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				Pacjent.onEnterFocus(null, null);	
				table.setVisibleColumns(30);
				table.setVisibleRows(10);
				for(Pacjent a : C.getPacjenci()) {
					table.getTableModel().addRow(a.getPesel(), a.getImie(), a.getNazwisko(), Integer.toString(a.getWiek()), a.getUlica(), Integer.toString(a.getNr_domu()),
							Integer.toString(a.getNr_mieszkania()), a.getMiejscowosc());
				}
				if(!container.containsComponent(table))
				{
					System.out.println("True");
					
					container.removeAllComponents();
					container.addComponent(new EmptySpace());
					container.addComponent(table);
				}
				
				
			}});
		Pacjent.addListener(list);
		Wizyty = new Button("Wizyty", new Runnable() {

			@Override
			public void run() {
				window.addWindowListener(listener0);
				//window.removeWindowListener(listener1);
				//window.addWindowListener(listener2);
				//window.removeWindowListener(listener3);
				//window.removeWindowListener(listener4);
				//window.addWindowListener(listener);
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				
				Wizyty.onEnterFocus(null, null);
				table = new Table<String>("Pesel","Imie lekarza","Nazwisko lekarza","Data", "Opis");
				
				table.setVisibleColumns(175);
				table.setVisibleRows(10);
				for(Wizyta a : C.getWizyty()) {
					table.getTableModel().addRow(a.getPesel_pacjenta(),C.getLekarze().get(a.getId_lekarza()).getImie() ,
							C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(),a.getOpis());
				}
				if(!container.containsComponent(table))
				{
					System.out.println("True");
					container.removeAllComponents();
					container.addComponent(new EmptySpace());
					container.addComponent(table);
				}
			}});
		Wizyty.addListener(list);
		Skierowania = new Button("Skierowania",new Runnable() {

			
			public void run() {
				window.addWindowListener(listener0);
				//window.removeWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.addWindowListener(listener3);
				//window.removeWindowListener(listener4);
				//window.addWindowListener(listener);
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				
				Skierowania.onEnterFocus(null, null);	
				table = new Table<String>("Pesel","Imie lekarza","Nazwisko lekarza","Data", "Opis","Cel");
				
				table.setVisibleColumns(7);
				table.setVisibleRows(10);
				for(Skierowanie a : C.getSkierowania()) {
					table.getTableModel().addRow(a.getPesel_pacjenta(),C.getLekarze().get(a.getId_lekarza()).getImie() ,
							C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(),a.getOpis(),a.getCel());
				}
				if(!container.containsComponent(table))
				{
					System.out.println("True");
					container.removeAllComponents();
					container.addComponent(new EmptySpace());
					container.addComponent(table);
				}	
			}});
		Skierowania.addListener(list);
		Recepty = new Button("Recepty", new Runnable() {

			@Override
			public void run() {
				//window.addWindowListener(listener0);
				//window.removeWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.removeWindowListener(listener3);
				//window.addWindowListener(listener4);
				//window.addWindowListener(listener);
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				
				Recepty.onEnterFocus(null, null);
				table = new Table<String>("Pesel","Imie lekarza","Nazwisko lekarza","Data", "Opis");
				
				table.setVisibleColumns(7);
				table.setVisibleRows(10);
				for(Recepta a : C.getRecepty()) {
					table.getTableModel().addRow(a.getPesel_pacjenta(),C.getLekarze().get(a.getId_lekarza()).getImie() ,
							C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(),a.getOpis());
				}
				if(!container.containsComponent(table))
				{
					System.out.println("True");
					container.removeAllComponents();
					container.addComponent(new EmptySpace());
					container.addComponent(table);
				}	
			}});
		Recepty.addListener(list);

		menu.addComponent(Pacjent);
		menu.addComponent(Wizyty);
		menu.addComponent(Skierowania);
		menu.addComponent(Recepty);
		

		mainPanel.addComponent(info);
		info.setLayoutManager(new GridLayout(2));

     
		info.addComponent(new Label("Podaj pesel: "));
		info.addComponent(wyszukiwarka1);

		Panel base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175,1));

		
		
		mainPanel.addComponent(container.withBorder(Borders.singleLine("Informacje")));
		

		base1Panel1.setLayoutManager(new GridLayout(6));

		mainPanel.addComponent(base1Panel1.withBorder(Borders.singleLine("Skróty")));
		
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel1.addComponent(new Label("F6: Dodaj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel1.addComponent(new Label("F7: Usuñ"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3,0)));
		base1Panel1.addComponent(new Label("F8: Edytuj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3,0)));
		//window.setComponent(mainPanel);
		window.setHints(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.NO_DECORATIONS));
		window.setComponent(mainPanel);
		terminal.flush();
        gui.addWindowAndWait(window);
		
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
