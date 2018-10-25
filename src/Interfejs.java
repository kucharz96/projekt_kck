import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import com.googlecode.lanterna.gui2.Interactable;
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
import com.googlecode.lanterna.screen.AbstractScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.Screen.RefreshType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.screen.VirtualScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.MouseCaptureMode;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalResizeListener;

import javafx.scene.shape.Sphere;

public class Interfejs {
	private int check;
	private Panel container;

	static private boolean spr = true;
	private Table<String> table;
	private Centrala C;
	private TextBox wyszukiwarka1;
	private Button Pacjent;
	private Button Skierowania;
	private Button Wizyty;
	private Button Recepty;
	private Button Lekarz;
	private VirtualScreen Vscreen;
	private Terminal terminal;
	private Screen screen;
	private BasicWindow window;
	private MultiWindowTextGUI gui;
	private Label nazwaWyszukiwarki;
	//Panel dla skrótów
	private Panel mainpanel;
	private Panel base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175, 1));
	//Dla lekarza po ID
	Panel info = new Panel().setPreferredSize(new TerminalSize(175, 1));
//<<<<<<< HEAD
	private boolean przyciskPacjent = false;
	private boolean przyciskSkierowania = false;
	private boolean przyciskWizyty = false;
	private boolean przyciskRecepty = false;
	private boolean przyciskLekarz = false;
	
	private ButtonListener list = new ButtonListener();
	private KeyStrokeListener listener0 = new KeyStrokeListener();
	private KeyStrokeListener listener1 = new KeyStrokeListener();
	private KeyStrokeListener listener2 = new KeyStrokeListener();
	private KeyStrokeListener listener3 = new KeyStrokeListener();
	private KeyStrokeListener listener4 = new KeyStrokeListener();
//=======
//>>>>>>> branch 'master' of https://github.com/kucharz96/projekt_kck.git

	public Interfejs() throws IOException {
		C = new Centrala();
		this.terminal = new DefaultTerminalFactory().createTerminal();
		this.screen = new TerminalScreen(terminal);
		this.Vscreen = new VirtualScreen(screen);
		Vscreen.setMinimumSize(new TerminalSize(100, 30));
		gui = new MultiWindowTextGUI(Vscreen);
		Vscreen.startScreen();

	}

	////////////// LOGOWANIE//////////////////////////////////
	public void Logowanie() throws IOException {
		// Setup terminal and screen layers
		BasicWindow window = new BasicWindow();
		// Create panel to hold components
		Panel mainpanel = new Panel();
		Panel panel_tmp = new Panel();
		mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine("LOGOWANIE")));
		Panel panel = new Panel();
		panel_tmp.addComponent(new EmptySpace(new TerminalSize(0, 1)));

		panel_tmp.addComponent(panel);

		panel.setLayoutManager(new GridLayout(4));
		panel.addComponent(new Label("Login"));
		panel.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		TextBox login = new TextBox();
		panel.addComponent(login);
		panel.addComponent(new EmptySpace(new TerminalSize(0, 2)));
		TextBox haslo = new TextBox().setMask('*');

		panel.addComponent(new Label("Has³o"));
		panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));

		panel.addComponent(haslo);
		panel.addComponent(new EmptySpace(new TerminalSize(0, 2)));

		Button button = new Button("Zaloguj", new Runnable() {
			@Override
			public void run() {
				try {
					if (C.Logowanie(login.getText(), haslo.getText()).equals("")) {
						new MessageDialogBuilder().setTitle("Błąd logowania").setText("Nieprawid³owe has³o lub login")

								.addButton(MessageDialogButton.Close).build().showDialog(gui);
					}
					if (C.Logowanie(login.getText(), haslo.getText()).equals("admin"))
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
		panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));

		panel.addComponent(button1);

		// Create window to hold the panel
		window.setHints(Arrays.asList(Window.Hint.CENTERED));
		// window.setSize(new TerminalSize(50, 10));
		window.setComponent(mainpanel);

		// Create gui and start gui
		gui.addWindowAndWait(window);

	}

	public void Menu_rejestracja() throws IOException {
		//Vscreen.clear();
		gui = new MultiWindowTextGUI(Vscreen);

		new ActionListDialogBuilder().setTitle("Menu rejestracji").setDescription("Wybierz opcje")
				.addAction("Zarządzanie pacjentami", new Runnable() {
					@Override
					public void run() {
						try {
							Okno_glowne_centrala();
							//Vscreen.close();
							gui.updateScreen();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).addAction("Zarzadzanie lekarzami", new Runnable() {
					@Override
					public void run() {
						// Do 2nd thing...
					}
				}).addAction("Wyloguj", new Runnable() {
					@Override
					public void run() {
						try {
							Logowanie();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).addAction("Zamknij", new Runnable() {
					@Override
					public void run() {
						try {
							terminal.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).build().showDialog(gui);
	}
	public class ButtonListener implements Button.Listener
	{

		@Override
		public void onTriggered(Button button) {
			// TODO Auto-generated method stub
			if(button.equals(Pacjent))
			{
				przyciskPacjent = true;
			}
			if(button.equals(Wizyty))
			{
				przyciskWizyty = true;
			}
			if(button.equals(Recepty))
			{
				przyciskRecepty = true;
				
			}
			if(button.equals(Skierowania))
			{
				przyciskSkierowania = true;
			}
			if(button.equals(Lekarz))
			{
				przyciskLekarz = true;
			}
		}
		
	}

	public void wyswietl_pacjentow(Window window, String filtr) {
		table = new Table<String>("Pesel", "Imie", "Nazwisko", "Wiek", "Ulica", "Numer domu", "Numer mieszkania",
				"Miejscowość");
		//base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175, 1));

		Pacjent.onEnterFocus(null, null);
		base1Panel1.removeAllComponents();
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F6: Dodaj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F7: Usuñ"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F8: Edytuj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));
		table.setVisibleColumns(30);
		table.setVisibleRows(11);
		int j = 0 ;
		table.setSelectAction(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				return;
				
			}
		});
		if(C.getPacjenci().isEmpty())
		{
			container.addComponent(new Label("Brak pacjentów."));
			return;
		}
	for (Pacjent a : C.getPacjenci()) {
			if(a.getPesel().startsWith(filtr)) {
			table.getTableModel().addRow(a.getPesel(), a.getImie(), a.getNazwisko(), Integer.toString(a.getWiek()),
					a.getUlica(), Integer.toString(a.getNr_domu()), Integer.toString(a.getNr_mieszkania()),
					a.getMiejscowosc());
			j ++;
			}
		}
		if(j != 0) {
			container.addComponent(table);

			container.addComponent(new EmptySpace());
			
			}
		

	}
	void wyswietl_lekarzy(Window window,String filtr) {
		Lekarz.onEnterFocus(null, null);
		//info.removeComponent(nazwaWyszukiwarki);
		//info.addComponent(new Label("Podaj imie i nazwisko:"));
		base1Panel1.removeAllComponents();
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F6: Dodaj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F7: Usuñ"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F8: Edytuj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));
		table = new Table<String>("ID ","Login", "Haslo", "Imie", "Nazwisko", "Wiek", "Numer sali", "Telefon");
		table.setSelectAction(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				return;
				
			}
		});
		int j = 0;
		table.setVisibleColumns(175);
		table.setVisibleRows(10);
		System.out.println("XD");
		if(C.getLekarze().isEmpty())
		{
			container.addComponent(new Label("Brak lekarzy."));
			return;
		}
		for (Lekarz L : C.getLekarze()) {
			if((L.getImie() +" "+ L.getNazwisko()).startsWith(filtr)) {
				table.getTableModel().addRow(new Integer(L.getId()).toString(), L.getLogin(),
					L.getHaslo(), L.getImie(), L.getNazwisko(), new Integer(L.getWiek()).toString(), new Integer(L.getSala()).toString(), L.getTelefon());
				j ++;
			}
		}
		
		
		if(j != 0) {
			container.addComponent(table);

			container.addComponent(new EmptySpace());
			
			}

	}
	
	void wyswietl_wizyty(Window window,String filtr) {
		Wizyty.onEnterFocus(null, null);
		System.out.println("Jstesm w wizycie");
		base1Panel1.removeAllComponents();
		//Panel base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175, 1));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F6: Dodaj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F7: Usuñ"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F8: Edytuj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));
		table = new Table<String>("Pesel", "Imie lekarza", "Nazwisko lekarza", "Data", "Opis");
		table.setSelectAction(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				return;
				
			}
		});
		int j = 0;
		table.setVisibleColumns(175);
		table.setVisibleRows(10);
		if(C.getLekarze().isEmpty())
		{
			container.addComponent(new Label("Brak wizyt."));
			return;
		}
		for (Wizyta a : C.getWizyty()) {
			if(a.getPesel_pacjenta().startsWith(filtr)) {
				table.getTableModel().addRow(a.getPesel_pacjenta(), C.getLekarze().get(a.getId_lekarza()).getImie(),
					C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(), a.getOpis());
				j ++;
			}
		}
		
		if(j != 0) {
			container.addComponent(table);

			container.addComponent(new EmptySpace());
			
			}

	}
	
	void wyswietl_skierowania(Window window,String filtr) {
		base1Panel1.removeAllComponents();
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));
		Skierowania.onEnterFocus(null, null);
		table = new Table<String>("Pesel", "Imie lekarza", "Nazwisko lekarza", "Data", "Opis", "Cel");
		int j = 0;
		table.setVisibleColumns(7);
		table.setVisibleRows(10);
		table.setSelectAction(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				return;
				
			}
		});
		if (C.getLekarze().isEmpty())
		{
			container.addComponent(new Label("Brak skierowań."));
			return;
		}
		for (Skierowanie a : C.getSkierowania()) {
			if(a.getPesel_pacjenta().startsWith(filtr)) {
				table.getTableModel().addRow(a.getPesel_pacjenta(), C.getLekarze().get(a.getId_lekarza()).getImie(),
					C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(), a.getOpis(),
					a.getCel());
				j ++;
			}
		}
		
		if(j != 0) {
			container.addComponent(table);
			container.addComponent(new EmptySpace());	
			}

	}
	
	void wyswietl_recepty(Window window,String filtr) {
		Recepty.onEnterFocus(null, null);
		base1Panel1.removeAllComponents();
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));

		table = new Table<String>("Pesel", "Imie lekarza", "Nazwisko lekarza", "Data", "Opis");

		table.setVisibleColumns(7);
		table.setVisibleRows(10);
		table.setSelectAction(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				return;
				
			}
		});
		if(C.getLekarze().isEmpty())
		{
			container.addComponent(new Label("Brak recept."));
			return;
		}
		int j = 0;
		for (Recepta a : C.getRecepty()) {
			if(a.getPesel_pacjenta().startsWith(filtr)) {
				System.out.println("W wyswietl_recepty(window, filtr) - przed warunkiem");
			table.getTableModel().addRow(a.getPesel_pacjenta(), C.getLekarze().get(a.getId_lekarza()).getImie(),
					C.getLekarze().get(a.getId_lekarza()).getNazwisko(), a.getData().toString(), a.getOpis());
			j ++;
			}
		}
		if(j != 0) {
			System.out.println("W wyswietl_recepty(window, filtr) - j != 0");
			container.addComponent(table);

			//container.addComponent(new EmptySpace());
			
			}
	}

	public class KeyStrokeListener implements WindowListener {

		private boolean focus1, focus2, focus3, focus4;


		public void onInput(Window basePane, KeyStroke keyStroke, AtomicBoolean deliverEvent) {

			if(spr == false) {
				spr = true;
				return;
			}
			//Dla dodawania informacji//
			if(keyStroke.getKeyType() == KeyType.F11)
			{
				BasicWindow window = new BasicWindow();
				// Create panel to hold components
				Panel mainpanel = new Panel();
				Panel panel_tmp = new Panel();
				mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine("Co zrobić?")));
				Panel panel = new Panel();
				panel_tmp.addComponent(new EmptySpace(new TerminalSize(1, 1)));

				panel_tmp.addComponent(panel);

				panel.setLayoutManager(new GridLayout(5));

				Button button = new Button("Wyloguj", new Runnable() {
					@Override
					public void run() {
						try {
							
								Menu_rejestracja();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				Button button1 = new Button("NARA", new Runnable() {
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
				Button button2 = new Button("NIC", new Runnable() {
					@Override
					public void run() {
						window.close();
					}
				});
				//panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
				panel.addComponent(button);
				panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));

				panel.addComponent(button1);
				panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
				panel.addComponent(button2);
				panel.addComponent(new EmptySpace(new TerminalSize(0, 0)));
				
				// Create window to hold the panel
				window.setHints(Arrays.asList(Window.Hint.CENTERED));
				// window.setSize(new TerminalSize(50, 10));
				window.setComponent(mainpanel);

				// Create gui and start gui
				gui.addWindowAndWait(window);
			}
if(keyStroke.getKeyType() == KeyType.F6 && przyciskPacjent == true && spr == true)
	    	{
	    		Dodaj_pacjenta();
	    		
	    	}/*
	    	if(keyStroke.getKeyType() == KeyType.F6 && przyciskRecepty == true && spr == true)
	    	{
					Dodaj_recepte();		
			}
			*/
	    	/*
	    	if(keyStroke.getKeyType() == KeyType.F6 && przyciskSkierowania == true && spr == true)
	    	{
	    		Dodaj_skierowanie();
			
			}
			*/
if(keyStroke.getKeyType() == KeyType.F6 && przyciskWizyty == true && spr == true)
	    	{
	    		Dodaj_wizyte();
			}
if(keyStroke.getKeyType() == KeyType.F6 && przyciskLekarz == true && spr == true)
{
	Dodaj_lekarza();
	
}
if (keyStroke.getKeyType() == KeyType.F7 && przyciskLekarz == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getLekarze().size())
			{
				
				BasicWindow window = new BasicWindow();
				// Create panel to hold components
				Panel mainpanel = new Panel();
				Panel panel_tmp = new Panel();
				mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine()));
				Panel panel = new Panel();
				panel_tmp.addComponent(panel);
				panel.setLayoutManager(new GridLayout(2));
				panel.addComponent(new Label("Czy na pewno chcesz usunąć rekord?"));
				panel.addComponent(new Label(""));
				panel.addComponent(new EmptySpace());
				panel.addComponent(new EmptySpace());
				Button button = new Button("Tak", new Runnable() {
					@Override
					public void run() {
						if(table.getTableModel().getRowCount() == 1) {
							
							try
							{
								C.removeLekarz(table.getSelectedRow());
							} catch (IndexOutOfBoundsException I1)
							{
								System.out.println("XD LUL");
							}
							try {
								Okno_glowne_centrala();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							}
								
							try
							{
								System.out.println("Usunieto");
							new MessageDialogBuilder().setTitle("Usunieto!")
							.addButton(MessageDialogButton.Close).build().showDialog(gui);
							   table.getTableModel().removeRow(table.getSelectedRow());
							   
						   C.removeLekarz(table.getSelectedRow());
						   window.setFocusedInteractable(Pacjent);
						   window.close();
						   
							}
							catch (IndexOutOfBoundsException I2)
							{
								System.out.println("Tu sobie wyskoczył wyjątek, ale cicho XDDDDDDD");
							}
							window.close();
					}
				});
				Button button1 = new Button("Nie", new Runnable() {
					@Override
					public void run() {
					window.close();
					}
				});
				panel.addComponent(button);
				panel.addComponent(button1);

				// Create window to hold the panel
				window.setHints(Arrays.asList(Window.Hint.CENTERED));
				window.setComponent(mainpanel);

				// Create gui and start gui
				gui.addWindowAndWait(window);
					}
			
	    	///////////////////////////////////////////////////////////////////////////USUWANIE PACJENTOW////////////////////////////////////////////////////////////////////////
if (keyStroke.getKeyType() == KeyType.F7 && przyciskPacjent == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getPacjenci().size())
			{
				
				BasicWindow window = new BasicWindow();
				// Create panel to hold components
				Panel mainpanel = new Panel();
				Panel panel_tmp = new Panel();
				mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine()));
				Panel panel = new Panel();
				panel_tmp.addComponent(panel);
				panel.setLayoutManager(new GridLayout(2));
				panel.addComponent(new Label("Czy na pewno chcesz usunąć rekord?"));
				panel.addComponent(new Label(""));
				panel.addComponent(new EmptySpace());
				panel.addComponent(new EmptySpace());
				Button button = new Button("Tak", new Runnable() {
					@Override
					public void run() {
						if(table.getTableModel().getRowCount() == 1) {
							
							try
							{
								C.removePacjent(table.getSelectedRow());
							} catch (IndexOutOfBoundsException I1)
							{
								System.out.println("XD LUL");
							}
							try {
								Okno_glowne_centrala();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							}
								
							try
							{
								System.out.println("Usunieto");
							new MessageDialogBuilder().setTitle("Usunieto!")
							.addButton(MessageDialogButton.Close).build().showDialog(gui);
							   table.getTableModel().removeRow(table.getSelectedRow());
							   
						   C.removePacjent(table.getSelectedRow());
						   window.setFocusedInteractable(Pacjent);
						   window.close();
						   
							}
							catch (IndexOutOfBoundsException I2)
							{
								System.out.println("Tu sobie wyskoczył wyjątek, ale cicho XDDDDDDD");
							}
							finally
							{
								System.out.println("huj");
							}
							window.close();
					}
				});
				Button button1 = new Button("Nie", new Runnable() {
					@Override
					public void run() {
					window.close();
					}
				});
				panel.addComponent(button);
				panel.addComponent(button1);

				// Create window to hold the panel
				window.setHints(Arrays.asList(Window.Hint.CENTERED));
				window.setComponent(mainpanel);

				// Create gui and start gui
				gui.addWindowAndWait(window);
					}
			
		if (keyStroke.getKeyType() == KeyType.F7 && przyciskWizyty == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getWizyty().size()) {
					
					BasicWindow window = new BasicWindow();
					// Create panel to hold components
					Panel mainpanel = new Panel();
					Panel panel_tmp = new Panel();
					mainpanel.addComponent(panel_tmp.withBorder(Borders.singleLine()));
					Panel panel = new Panel();
					panel_tmp.addComponent(panel);
					panel.setLayoutManager(new GridLayout(2));
					panel.addComponent(new Label("Czy na pewno chcesz usunąćrekord?"));
					panel.addComponent(new Label(""));
					panel.addComponent(new EmptySpace());
					panel.addComponent(new EmptySpace());
					Button button = new Button("Tak", new Runnable() {
						@Override
						public void run() {
							if(table.getTableModel().getRowCount() == 1) {
								try
								{
									C.removeWizyta(table.getSelectedRow());
								} catch (IndexOutOfBoundsException I1)
								{
									System.out.println("XD LUL");
								}
								try {
									Okno_glowne_centrala();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								try {
									Vscreen.refresh();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								window.setFocusedInteractable(Pacjent);
								}
									
								try
								{
								new MessageDialogBuilder().setTitle("Usunieto!")
								.addButton(MessageDialogButton.Close).build().showDialog(gui);
								   table.getTableModel().removeRow(table.getSelectedRow());
	
							   C.removeWizyta(table.getSelectedRow());
							   window.close();
								}
								catch (IndexOutOfBoundsException I2)
								{
									System.out.println("Tu sobie wyskoczył wyjątek, ale cicho XDDDDDDD");
								}
						}
					});
					Button button1 = new Button("Nie", new Runnable() {
						@Override
						public void run() {
						window.close();
						}
					});
					panel.addComponent(button);
					panel.addComponent(button1);
	
					// Create window to hold the panel
					window.setHints(Arrays.asList(Window.Hint.CENTERED));
					window.setComponent(mainpanel);
	
					// Create gui and start gui
					gui.addWindowAndWait(window);
						}
				
			//////////////////////EDYCJA PACJENTA///////////////////////////////////////////////////////////////////////////////////////////////////////
			if (keyStroke.getKeyType() == KeyType.F8 && przyciskPacjent == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getPacjenci().size()) {
				/// Dla F8
				List<String> data = table.getTableModel().getRow(table.getSelectedRow());
				for(int i = 0; i < data.size(); i++) {
				    System.out.println(data.get(i));
				}
				BasicWindow window = new BasicWindow("Edytuj pacjenta");
				Panel mainpanel = new Panel(new GridLayout(2));
				// Do PESELU
				mainpanel.addComponent(new Label("Numer PESEL"));

				TextBox TextPesel = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,11}"));
				mainpanel.addComponent(TextPesel);
				TextPesel.setText(data.get(0)).setCaretPosition(data.get(0).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Imie
				mainpanel.addComponent(new Label("Imie"));
				TextBox TextImie = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextImie);
				TextImie.setText(data.get(1)).setCaretPosition(data.get(1).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Nazwisko
				mainpanel.addComponent(new Label("Nazwisko"));
				TextBox TextNazwisko = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextNazwisko);
				TextNazwisko.setText(data.get(2)).setCaretPosition(data.get(2).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				// Wiek
				mainpanel.addComponent(new Label("Wiek"));
				TextBox TextWiek = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
				mainpanel.addComponent(TextWiek);
				TextWiek.setText(data.get(3)).setCaretPosition(data.get(3).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Ulica
				mainpanel.addComponent(new Label("Ulica"));
				TextBox TextUlica = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextUlica);
				TextUlica.setText(data.get(4)).setCaretPosition(data.get(4).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Numer domu
				mainpanel.addComponent(new Label("Numer domu"));
				TextBox TextNumerDomu = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
				mainpanel.addComponent(TextNumerDomu);
				TextNumerDomu.setText(data.get(5)).setCaretPosition(data.get(5).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Numer mieszkania
				mainpanel.addComponent(new Label("Numer mieszkania"));
				TextBox TextNumerMieszkania = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
				mainpanel.addComponent(TextNumerMieszkania);
				TextNumerMieszkania.setText(data.get(6)).setCaretPosition(data.get(6).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Miejscowość
				mainpanel.addComponent(new Label("Miejscowość"));
				TextBox TextMiejscowosc = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextMiejscowosc);
				TextMiejscowosc.setText(data.get(7)).setCaretPosition(data.get(7).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Dodaj", new Runnable() {
					@Override
					public void run() {
						if (TextPesel.getText().isEmpty() ||TextImie.getText().isEmpty() || TextNazwisko.getText().isEmpty() || TextWiek.getText().isEmpty()
								|| TextUlica.getText().isEmpty() || TextNumerDomu.getText().isEmpty()
								|| TextNumerMieszkania.getText().isEmpty() || TextMiejscowosc.getText().isEmpty()) {
							new MessageDialogBuilder().setTitle("Error").setText("Uzupełnij puste pola.")
									.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
						}
						Pacjent P =	C.getPacjenci().get(table.getSelectedRow());
						for (Pacjent P1 : C.getPacjenci()) {

							if (P1.getPesel().equals(TextPesel.getText()) && (P.getPesel() != P1.getPesel())) {
								new MessageDialogBuilder().setTitle("Error").setText("Pesel już taki występuje.")
										.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
							}
							else
							{
								check = 5;
								P.setPesel(TextPesel.getText());
								P.setImie(TextImie.getText());
								P.setNazwisko(TextNazwisko.getText());
								P.setWiek(Integer.parseInt(TextWiek.getText()));
								P.setMiejscowosc(TextMiejscowosc.getText());
								P.setNr_domu(Integer.parseInt(TextNumerDomu.getText()));
								P.setNr_mieszkania(Integer.parseInt(TextNumerMieszkania.getText()));
								P.setUlica(TextUlica.getText());
								window.close();
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
		        mainpanel.addComponent(button);
		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        window.setCloseWindowWithEscape(true);
		        //window1.removeWindowListener(listener1);
		        //window1.removeWindowListener(listener);
		        // Create gui and start gui
		        gui.addWindowAndWait(window);

	    	}
			////////////////////////////////////////////////////EDYCJA LEKARZA//////////////////////////////////////////////////
			if (keyStroke.getKeyType() == KeyType.F8 && przyciskLekarz == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getLekarze().size()) {
				/// Dla F8
				List<String> data = table.getTableModel().getRow(table.getSelectedRow());
				for(int i = 0; i < data.size(); i++) {
				    System.out.println(data.get(i));
				}
				BasicWindow window = new BasicWindow("Edytuj lekarze");
				Panel mainpanel = new Panel(new GridLayout(2));

				//Login//
				mainpanel.addComponent(new Label("Login"));
				TextBox TextLogin = new TextBox().setPreferredSize(new TerminalSize(12, 1));
				mainpanel.addComponent(TextLogin);
				TextLogin.setText(data.get(1)).setCaretPosition(data.get(1).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				//Haslo
				mainpanel.addComponent(new Label("Haslo"));
				TextBox TextHaslo = new TextBox().setPreferredSize(new TerminalSize(12, 1));
				mainpanel.addComponent(TextHaslo);
				TextHaslo.setText(data.get(2)).setCaretPosition(data.get(2).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Imie
				mainpanel.addComponent(new Label("Imie"));
				TextBox TextImie = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextImie);
				TextImie.setText(data.get(3)).setCaretPosition(data.get(3).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Nazwisko
				mainpanel.addComponent(new Label("Nazwisko"));
				TextBox TextNazwisko = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
				mainpanel.addComponent(TextNazwisko);
				TextNazwisko.setText(data.get(4)).setCaretPosition(data.get(4).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				// Wiek
				mainpanel.addComponent(new Label("Wiek"));
				TextBox TextWiek = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
				mainpanel.addComponent(TextWiek);
				TextWiek.setText(data.get(5)).setCaretPosition(data.get(5).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				// Sala
				mainpanel.addComponent(new Label("Sala"));
				TextBox TextSala = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
				mainpanel.addComponent(TextSala);
				TextSala.setText(data.get(6)).setCaretPosition(data.get(6).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				//Telefon
				mainpanel.addComponent(new Label("Telefon"));
				TextBox TextTelefon = new TextBox().setPreferredSize(new TerminalSize(12, 1))
						.setValidationPattern(Pattern.compile("([0-9]){0,9}"));
				mainpanel.addComponent(TextTelefon);
				TextTelefon.setText(data.get(7)).setCaretPosition(data.get(7).length());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				

				Button button = new Button("Dodaj", new Runnable() {
					@Override
					public void run() {
						if (TextLogin.getText().isEmpty() ||TextImie.getText().isEmpty() || TextNazwisko.getText().isEmpty() || TextWiek.getText().isEmpty()
								|| TextSala.getText().isEmpty() || TextTelefon.getText().isEmpty()
								|| TextHaslo.getText().isEmpty()) {
							new MessageDialogBuilder().setTitle("Error").setText("Uzupełnij puste pola.")
									.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
						}
						Lekarz L =	C.getLekarze().get(table.getSelectedRow());
						for (Lekarz L1 : C.getLekarze()) {

							if (L1.getLogin().equals(TextLogin.getText()) && (L.getLogin() != L1.getLogin())) {
								new MessageDialogBuilder().setTitle("Error").setText("Pesel już taki występuje.")
										.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
							}
							else
							{
								check = 7;
								L.setLogin(TextLogin.getText());
								L.setImie(TextImie.getText());
								L.setNazwisko(TextNazwisko.getText());
								L.setWiek(Integer.parseInt(TextWiek.getText()));
								L.setHaslo(TextHaslo.getText());
								L.setSala(Integer.parseInt(TextSala.getText()));
								L.setTelefon(TextTelefon.getText());
								window.close();
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
		        mainpanel.addComponent(button);
		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        window.setCloseWindowWithEscape(true);
		        //window1.removeWindowListener(listener1);
		        //window1.removeWindowListener(listener);
		        // Create gui and start gui
		        gui.addWindowAndWait(window);

	    	}
			////////////////////////////////TYLKO LEKARZ EDYTUJE///////////////////////////////////////
/////////////////////////////////////////////////////////////EDYCJA SKIEROWANIA////////////////////////////////////////////////////////////
			/*
			if(keyStroke.getKeyType() == KeyType.F8 && przyciskSkierowania == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getPacjenci().size())
			{
				final TextBox.Style MULTI_LINE;
				List<String> data = table.getTableModel().getRow(table.getSelectedRow());
				for(int i = 0; i < data.size(); i++) {
				    System.out.println(data.get(i));
				}
				BasicWindow window = new BasicWindow("Edytuj skierowanie");
				Panel mainpanel = new Panel(new GridLayout(2));
				mainpanel.addComponent(new Label("ID lekarza"));
				ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(30, 1));
				for (Lekarz L : C.getLekarze()) {
					listaLekarzy.addItem(L.getId()+" : "+L.getImie() +" "+ L.getNazwisko());
				}
				mainpanel.addComponent(listaLekarzy);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				mainpanel.addComponent(new Label("Pacjent"));
				ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(30, 1));
				for (Pacjent P: C.getPacjenci()) {
					listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
				}
				mainpanel.addComponent(listaPacjentow);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Opis (Data doda się automatycznie)
				mainpanel.addComponent(new Label("Cel"));
				TextBox TextCel = new TextBox().setPreferredSize(new TerminalSize(20, 3));
				mainpanel.addComponent(TextCel);
				TextCel.setText(data.get(4));
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				mainpanel.addComponent(new Label("Opis"));
				TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(20, 5));
				mainpanel.addComponent(TextOpis);
				TextOpis.setText(data.get(5));
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Edytuj", new Runnable() {
					///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
					@Override
					public void run() {
						if(TextOpis.getText().isEmpty() || TextCel.getText().isEmpty())
							new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij puste pola.")
									.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);

						
						else
						{
							Skierowanie S =	C.getSkierowania().get(table.getSelectedRow());
							String wyraz = listaPacjentow.getSelectedItem();
							String wyraz2 = listaLekarzy.getSelectedItem();
							String wyr[] = wyraz.split(" ", 2);
							String wyr2[] = wyraz2.split(" ", 2);
							S.setId_lekarza(Integer.parseInt(wyr2[0]));
							S.setPesel_pacjenta(wyr[0]);
							S.setOpis(TextOpis.getText());
							
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
		        mainpanel.addComponent(button);

		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        //window.setCloseWindowWithEscape(true);
		        gui.addWindowAndWait(window);
				
			}
			*/

	    	//////////////////////////////////////////EDYCJA WIZYTY////////////////////////////////////////////////
	    	if(keyStroke.getKeyType() == KeyType.F8 && przyciskWizyty == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getPacjenci().size())
	    	{
	    		List<String> data = table.getTableModel().getRow(table.getSelectedRow());
				for(int i = 0; i < data.size(); i++) {
				    System.out.println(data.get(i));
				}
				BasicWindow window = new BasicWindow("Edytuj wizyte");
				Panel mainpanel = new Panel(new GridLayout(2));
				//mainpanel.setPreferredSize(new TerminalSize(40, 10));
				//Lekarz
				mainpanel.addComponent(new Label("ID lekarza"));
				ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(false)
						.setPreferredSize(new TerminalSize(21, 1));
				for (Lekarz L : C.getLekarze()) {
					listaLekarzy.addItem(L.getId() + " : "+L.getImie() +" "+ L.getNazwisko());
				}
				mainpanel.addComponent(listaLekarzy);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				//
				//Pacjent
				
				mainpanel.addComponent(new Label("Pacjent"));
				ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(false)
						.setPreferredSize(new TerminalSize(21, 1));
				for (Pacjent P: C.getPacjenci()) {
					listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
				}
				mainpanel.addComponent(listaPacjentow);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				//Data
				mainpanel.addComponent(new Label("Data:(DD-MM-RRRR HH:MM)"));
				TextBox TextData = new TextBox().setPreferredSize(new TerminalSize(21, 3));
				mainpanel.addComponent(TextData);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				//Opis
				mainpanel.addComponent(new Label("Opis"));
				TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(21, 3));
				mainpanel.addComponent(TextOpis);
				TextOpis.setText(data.get(4));
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Dodaj", new Runnable() {
					///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
					@Override
					public void run() {
							if(TextData.getText().isEmpty() || TextOpis.getText().isEmpty())
							{
								new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij dane.")
								.addButton(MessageDialogButton.Close).build().showDialog(gui);
							}
	
							else
							{
								SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
								Date Data = null;
								try {
									Data = formatter.parse(TextData.getText());

									
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								check = 6;
								Wizyta W =	C.getWizyty().get(table.getSelectedRow());
								
								String wyraz = listaPacjentow.getSelectedItem();
								String wyraz2 = listaLekarzy.getSelectedItem();
								String wyr[] = wyraz.split(" ", 2);
								String wyr2[] = wyraz2.split(" ", 2);
								W.setId_lekarza(Integer.parseInt(wyr2[0]));
								W.setPesel_pacjenta(wyr[0]);
								W.setData(TextData.getText());
								W.setOpis(TextOpis.getText());
								
								
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
		        mainpanel.addComponent(button);

		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        window.setCloseWindowWithEscape(true);
		        gui.addWindowAndWait(window);
	    	}
	    	/////////////////////////////////////////////////////////////DLA LEKARZA ZOSTAWIC///////////////////////////////////////////////////////////////
	    	
	    	//////////////////////////////////////////EDYCJA RECEPTY////////////////////////////////////////////////
	    	/*
	    	if(keyStroke.getKeyType() == KeyType.F8 && przyciskRecepty == true && spr == true && table.isFocused()==true && table.getSelectedRow() != C.getPacjenci().size())
	    	{
	    		List<String> data = table.getTableModel().getRow(table.getSelectedRow());
				for(int i = 0; i < data.size(); i++) {
				    System.out.println(data.get(i));
				}
				BasicWindow window = new BasicWindow("Edytuj recepte");
				Panel mainpanel = new Panel(new GridLayout(2));
				mainpanel.setPreferredSize(new TerminalSize(40, 10));
				mainpanel.addComponent(new Label("ID lekarza"));
				// mainpanel.addComponent(new EmptySpace());
				ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(20, 1));
				for (Lekarz L : C.getLekarze()) {
					listaLekarzy.addItem(L.getId()+" : "+L.getImie() +" "+ L.getNazwisko());
				}
				mainpanel.addComponent(listaLekarzy);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new Label("Pacjent"));
				ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(20, 1));
				for (Pacjent P: C.getPacjenci()) {
					listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
				}
				mainpanel.addComponent(listaPacjentow);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Opis (Data doda się automatycznie)
				mainpanel.addComponent(new Label("Opis"));
				TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(20, 3));
				mainpanel.addComponent(TextOpis);
				TextOpis.setText(data.get(4));
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Dodaj", new Runnable() {
					///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
					@Override
					public void run() {
						if(TextOpis.getText().isEmpty())
						{
							new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij dane.")
							.addButton(MessageDialogButton.Close).build().showDialog(gui);
						}
						else
						{
							Recepta R =	C.getRecepty().get(table.getSelectedRow());
							String wyraz = listaPacjentow.getSelectedItem();
							String wyraz2 = listaLekarzy.getSelectedItem();
							String wyr[] = wyraz.split(" ", 2);
							String wyr2[] = wyraz2.split(" ", 2);
							R.setId_lekarza(Integer.parseInt(wyr2[0]));
							R.setPesel_pacjenta(wyr[0]);
							R.setOpis(TextOpis.getText());
							
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
		        mainpanel.addComponent(button);
		        
		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        gui.addWindowAndWait(window);
		
		        
	    	}
	    	*/
			
	    	if(basePane.getFocusedInteractable() == wyszukiwarka1 && ( keyStroke.getKeyType() == KeyType.ArrowUp) ) {
	    		przyciskPacjent = false;
	    		przyciskWizyty = false;
	    		przyciskRecepty = false;
	    		przyciskSkierowania = false;
	    		przyciskLekarz = false;
	    		Lekarz.onLeaveFocus(null,  null);
	    		Pacjent.onLeaveFocus(null, null);
	    		Skierowania.onLeaveFocus(null, null);
	    		Wizyty.onLeaveFocus(null, null);
	    		Recepty.onLeaveFocus(null, null);
	    		container.removeAllComponents();
	    		wyszukiwarka1.setEnabled(false);
	    		basePane.setFocusedInteractable(Pacjent);
	    	}
	    
	    	spr = false;
	    	
		}

		public void onUnhandledInput(Window basePane, KeyStroke keyStroke, AtomicBoolean hasBeenHandled) {
			// TODO Auto-generated method stub
			
		}

		public void onResized(Window window, TerminalSize oldSize, TerminalSize newSize) {
			// TODO Auto-generated method stub
			///Do usuwania///
			//usuwanie recept
			/*
			if(check == 5)
			{
				container.removeAllComponents();
				window.setFocusedInteractable(Recepty);
				System.out.println("onMoved 5");
				wyswietl_recepty(window, "");

			}
			*/
			//usuwanie pacjentow

			//usuwanie
		}

		public void onMoved(Window window, TerminalPosition oldPosition, TerminalPosition newPosition) {
			if(check == 1 && window.getFocusedInteractable() == wyszukiwarka1) {
				container.removeAllComponents();
				wyswietl_pacjentow(window,wyszukiwarka1.getText());
				
			
			}

			if(check == 2 && window.getFocusedInteractable() == wyszukiwarka1) {
				container.removeAllComponents();
				wyswietl_wizyty(window,wyszukiwarka1.getText());
				
			
			}
			
			if(check == 3 && window.getFocusedInteractable() == wyszukiwarka1) {
				container.removeAllComponents();
				wyswietl_skierowania(window,wyszukiwarka1.getText());
				
			
			}
			
			
			if(check == 4 && window.getFocusedInteractable() == wyszukiwarka1) {
				container.removeAllComponents();
				System.out.println("onMoved 4");
				System.out.println(oldPosition);
				System.out.println(newPosition);
				wyswietl_recepty(window,wyszukiwarka1.getText());
				
			
			}
			if(check == 8 && window.getFocusedInteractable() == wyszukiwarka1)
			{
				container.removeAllComponents();
				wyswietl_lekarzy(window,wyszukiwarka1.getText());
			}
			if(check == 5)
			{
				container.removeAllComponents();
				window.setFocusedInteractable(Pacjent);
				System.out.println("onResized 6");
				wyswietl_pacjentow(window, "");
			}
			if(check == 6)
			{
				container.removeAllComponents();
				window.setFocusedInteractable(Wizyty);
				System.out.println("check 6");
				wyswietl_wizyty(window, "");
			}
			
			if(check == 7)
			{
				container.removeAllComponents();
				window.setFocusedInteractable(Wizyty);
				System.out.println("check 7");
				wyswietl_lekarzy(window, "");
			}
			
			

		
	}
	}
	/////////////////////////////////////////////////////EDYCJA PACJENTA///////////////////////////////////////////////////////////
	
	public void Dodaj_pacjenta() {
		
		BasicWindow window = new BasicWindow("Dodaj pacjenta");
		Panel mainpanel = new Panel(new GridLayout(2));
		// Do PESELU
		mainpanel.addComponent(new Label("Numer PESEL"));
		// mainpanel.addComponent(new EmptySpace());
		TextBox TextPesel = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,11}"));
		mainpanel.addComponent(TextPesel);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());

		// Imiê
		mainpanel.addComponent(new Label("Imie"));
		TextBox TextImie = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextImie);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());

		// Nazwisko
		mainpanel.addComponent(new Label("Nazwisko"));
		TextBox TextNazwisko = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextNazwisko);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Wiek
		mainpanel.addComponent(new Label("Wiek"));
		TextBox TextWiek = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextWiek);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Ulica
		mainpanel.addComponent(new Label("Ulica"));
		TextBox TextUlica = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextUlica);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Numer domu
		mainpanel.addComponent(new Label("Numer domu"));
		TextBox TextNumerDomu = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextNumerDomu);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Numer mieszkania
		mainpanel.addComponent(new Label("Numer mieszkania"));
		TextBox TextNumerMieszkania = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextNumerMieszkania);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Miejscowoœæ
		mainpanel.addComponent(new Label("Miejscowość"));
		TextBox TextMiejscowosc = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextMiejscowosc);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		Button button = new Button("Dodaj", new Runnable() {
			@Override
			public void run() {

				if (TextImie.getText().isEmpty() || TextNazwisko.getText().isEmpty() || TextWiek.getText().isEmpty()
						|| TextUlica.getText().isEmpty() || TextNumerDomu.getText().isEmpty()
						|| TextNumerMieszkania.getText().isEmpty() || TextMiejscowosc.getText().isEmpty() || (TextPesel.getText().length() < 11)) {
					new MessageDialogBuilder().setTitle("Error").setText("Uzupełnij prawidłowo pola.")
							.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
					return;
					
				}
				for (Pacjent P : C.getPacjenci()) {

					if (TextPesel.getText().equals(P.getPesel())) {
						new MessageDialogBuilder().setTitle("Error").setText("Pesel już taki wystêeuje.")
								.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
						return;
					}
				}
				check = 5;
				C.addPacjent(new Pacjent(TextPesel.getText(), TextImie.getText(), TextNazwisko.getText(), Integer.parseInt(TextWiek.getText()), TextUlica.getText(),
						Integer.parseInt(TextNumerDomu.getText()), Integer.parseInt(TextNumerMieszkania.getText()), TextMiejscowosc.getText()));
				
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

    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(mainpanel);
        window.setCloseWindowWithEscape(true);
        //window1.removeWindowListener(listener1);
        //window1.removeWindowListener(listener);
        // Create gui and start gui
        gui.addWindowAndWait(window);
	}
	//////////////////////////////////////////////////////////////DODAJ LEKARZA//////////////////////////////////////////////////////////////
	public void Dodaj_lekarza() {
		BasicWindow window = new BasicWindow("Dodaj lekarza");
		Panel mainpanel = new Panel(new GridLayout(2));


		// Login
		mainpanel.addComponent(new Label("Login"));
		TextBox TextLogin = new TextBox().setPreferredSize(new TerminalSize(12, 1));
		mainpanel.addComponent(TextLogin);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());

		// Haslo
		mainpanel.addComponent(new Label("Haslo"));
		TextBox TextHaslo = new TextBox().setPreferredSize(new TerminalSize(12, 1));
		mainpanel.addComponent(TextHaslo);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		
		// imie
		mainpanel.addComponent(new Label("Imie"));
		TextBox TextImie = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextImie);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Nazwisko
		mainpanel.addComponent(new Label("Nazwisko"));
		TextBox TextNazwisko = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("[A-Z][a-z]*"));
		mainpanel.addComponent(TextNazwisko);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		// Wiek
		mainpanel.addComponent(new Label("Wiek"));
		TextBox TextWiek = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextWiek);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		
		// Numer sali
		mainpanel.addComponent(new Label("Numer sali"));
		TextBox TextNumerSali = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,3}"));
		mainpanel.addComponent(TextNumerSali);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		
		// Telefon
		mainpanel.addComponent(new Label("Telefon"));
		TextBox TextTelefon = new TextBox().setPreferredSize(new TerminalSize(12, 1))
				.setValidationPattern(Pattern.compile("([0-9]){0,11}"));
		mainpanel.addComponent(TextTelefon);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		Button button = new Button("Dodaj", new Runnable() {
			@Override
			public void run() {

				if (TextImie.getText().isEmpty() || TextNazwisko.getText().isEmpty() || TextWiek.getText().isEmpty()
						|| TextHaslo.getText().isEmpty() || TextNumerSali.getText().isEmpty()
						|| TextTelefon.getText().isEmpty() || TextLogin.getText().isEmpty()) {
					new MessageDialogBuilder().setTitle("Error").setText("Uzupełnij Puste pola.")
							.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
					return;
					
				}
				if(TextLogin.getText().length() < 6 || TextHaslo.getText().length() < 6)
				{
					new MessageDialogBuilder().setTitle("Error").setText("Login i haslo musza mieć co najmniej 6 znaków.")
					.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
					return;
				}
				for (Lekarz L : C.getLekarze()) {

					if (TextLogin.getText().equals(L.getLogin())) {
						new MessageDialogBuilder().setTitle("Error").setText("Login już istnieje.")
								.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);
						return;
					}
				}
				check = 8;
				C.addLekarz(new Lekarz(TextLogin.getText(), TextHaslo.getText(), TextImie.getText(), TextNazwisko.getText(), Integer.parseInt(TextWiek.getText()),
						Integer.parseInt(TextNumerSali.getText()), TextTelefon.getText()));
				
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

    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(mainpanel);
        window.setCloseWindowWithEscape(true);
        // Create gui and start gui
        gui.addWindowAndWait(window);
	}
	////////////////////////////////////////////Dodaj wizyte//////////////////////////////////////////////////////////
	public void Dodaj_wizyte()
	{

		BasicWindow window = new BasicWindow("Dodaj wizyte");
		Panel mainpanel = new Panel(new GridLayout(2));
		//mainpanel.setPreferredSize(new TerminalSize(40, 10));
		//Lekarz
		mainpanel.addComponent(new Label("ID lekarza"));
		ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(false)
				.setPreferredSize(new TerminalSize(21, 1));
		for (Lekarz L : C.getLekarze()) {
			listaLekarzy.addItem(L.getId() + " : "+L.getImie() +" "+ L.getNazwisko());
		}
		mainpanel.addComponent(listaLekarzy);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		//
		//Pacjent
		mainpanel.addComponent(new Label("Pacjent"));
		ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(false)
				.setPreferredSize(new TerminalSize(21, 1));
		for (Pacjent P: C.getPacjenci()) {
			listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
		}
		mainpanel.addComponent(listaPacjentow);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		
		//Data
		mainpanel.addComponent(new Label("Data:(DD-MM-RRRR HH:MM)"));
		TextBox TextData = new TextBox().setPreferredSize(new TerminalSize(21, 3));
		mainpanel.addComponent(TextData);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		
		//Opis
		mainpanel.addComponent(new Label("Opis"));
		TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(21, 3));
		mainpanel.addComponent(TextOpis);
		mainpanel.addComponent(new EmptySpace());
		mainpanel.addComponent(new EmptySpace());
		Button button = new Button("Dodaj", new Runnable() {
			///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
			@Override
			public void run() {
					if(TextData.getText().isEmpty() || TextOpis.getText().isEmpty())
					{
						new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij dane.")
						.addButton(MessageDialogButton.Close).build().showDialog(gui);
					}
					SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date Data = null;
					
					try {
						Data = formatter.parse(TextData.getText());
						String wyraz = listaPacjentow.getSelectedItem();
						String wyr[] = wyraz.split(" ", 2);
						check = 6;
						C.addWizyta(new Wizyta(listaLekarzy.getSelectedIndex(), wyr[0], TextOpis.getText(), TextData.getText()));
						try {
							screen.refresh(RefreshType.COMPLETE);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
        mainpanel.addComponent(button);

    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(mainpanel);
        window.setCloseWindowWithEscape(true);
        gui.addWindowAndWait(window);
	}
	/////////////////////////////////DODAWANIE RECEPT TYLKO PRZEZ LEKARZA///////////////////////////////////////
	//////////////////////////////////////Dodaj recepte/////////////////////////////////////////////////////
	/*
	public void Dodaj_recepte()
	{		
			
				BasicWindow window = new BasicWindow("Dodaj recepte");
				Panel mainpanel = new Panel(new GridLayout(2));
				mainpanel.setPreferredSize(new TerminalSize(40, 10));
				mainpanel.addComponent(new Label("ID lekarza"));
				// mainpanel.addComponent(new EmptySpace());
				ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(20, 1));
				for (Lekarz L : C.getLekarze()) {
					listaLekarzy.addItem(L.getId()+" : "+L.getImie() +" "+ L.getNazwisko());
				}
				mainpanel.addComponent(listaLekarzy);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new Label("Pacjent"));
				ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(20, 1));
				for (Pacjent P: C.getPacjenci()) {
					listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
				}
				mainpanel.addComponent(listaPacjentow);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Opis (Data doda się automatycznie)
				mainpanel.addComponent(new Label("Opis"));
				TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(20, 3));
				mainpanel.addComponent(TextOpis);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Dodaj", new Runnable() {
					///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
					@Override
					public void run() {
						if(TextOpis.getText().isEmpty())
						{
							new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij dane.")
							.addButton(MessageDialogButton.Close).build().showDialog(gui);
						}
						else{
							check = 5;
							System.out.println("Dodanie recepty");
							String wyraz = listaPacjentow.getSelectedItem();
							String wyr[] = wyraz.split(" ", 2);
							C.addRecepta(new Recepta(listaLekarzy.getSelectedIndex(), wyr[0], TextOpis.getText()));	
							
							
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
		        mainpanel.addComponent(button);
		        
		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        gui.addWindowAndWait(window);
		       // window.setCloseWindowWithEscape(true);
		
		        
	}
	////////////////////////////////////Dodaj skierowanie////////////////////////////////
	public void Dodaj_skierowanie()
	{
				//check = 5;
				BasicWindow window = new BasicWindow("Dodaj skierowanie");
				Panel mainpanel = new Panel(new GridLayout(2));
				mainpanel.addComponent(new Label("ID lekarza"));
				ComboBox<String> listaLekarzy = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(30, 1));
				for (Lekarz L : C.getLekarze()) {
					listaLekarzy.addItem(L.getId()+" : "+L.getImie() +" "+ L.getNazwisko());
				}
				mainpanel.addComponent(listaLekarzy);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				mainpanel.addComponent(new Label("Pacjent"));
				ComboBox<String> listaPacjentow = new ComboBox<String>().setReadOnly(true)
						.setPreferredSize(new TerminalSize(30, 1));
				for (Pacjent P: C.getPacjenci()) {
					listaPacjentow.addItem(P.getPesel()+ " : "+P.getImie() +" "+ P.getNazwisko());
				}
				mainpanel.addComponent(listaPacjentow);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());

				// Opis (Data doda się automatycznie)
				mainpanel.addComponent(new Label("Cel"));
				TextBox TextCel = new TextBox().setPreferredSize(new TerminalSize(20, 3));
				mainpanel.addComponent(TextCel);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				
				mainpanel.addComponent(new Label("Opis"));
				TextBox TextOpis = new TextBox().setPreferredSize(new TerminalSize(20, 3));
				mainpanel.addComponent(TextOpis);
				mainpanel.addComponent(new EmptySpace());
				mainpanel.addComponent(new EmptySpace());
				Button button = new Button("Dodaj", new Runnable() {
					///////////////////DODAC FUNKCJE Z DODAWANIEM///////////////////////
					@Override
					public void run() {
						if(TextOpis.getText().isEmpty() || TextCel.getText().isEmpty())
							new MessageDialogBuilder().setTitle("Error").setText("Uzupelnij puste pola.")
									.addButton(MessageDialogButton.Close.valueOf("OK")).build().showDialog(gui);

						
						else
						{
							String wyraz = listaPacjentow.getSelectedItem();
							String wyr1[] = wyraz.split(" ", 2);
							C.addSkierowanie(new Skierowanie(listaLekarzy.getSelectedIndex(), wyr1[0], TextCel.getText(), TextOpis.getText()));
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
		        mainpanel.addComponent(button);

		    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
		        window.setComponent(mainpanel);
		        //window.setCloseWindowWithEscape(true);
		        gui.addWindowAndWait(window);
	}
	*/
	public void Okno_glowne_centrala() throws IOException {
		System.out.println("okno glowne");
		window = new BasicWindow();
		Panel mainPanel = new Panel();
		final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(Vscreen);
		//KeyStroke keyStroke = terminal.pollInput();
        
        //keyStroke = terminal.pollInput();
        
		//System.out.println("keyPressed: " + keyStroke.getKeyType());
		// KeyStroke keyStroke = terminal.pollInput();
		KeyStrokeListener listener = new KeyStrokeListener();
		// keyStroke = terminal.pollInput();
		window.addWindowListener(listener);
		// System.out.println("keyPressed: " + keyStroke.getKeyType());
		window.setHints(Arrays.asList(Window.Hint.NO_DECORATIONS));
		StringBuilder abc = new StringBuilder();
		for (int a = 0; a < 200; a++)
			abc.append(" ");

		mainPanel.addComponent(new Label(abc.toString()).setBackgroundColor(TextColor.ANSI.BLUE)
				.setForegroundColor(TextColor.ANSI.WHITE));
		mainPanel.addComponent(new EmptySpace());

		Panel menu = new Panel().setPreferredSize(new TerminalSize(175, 1));
		menu.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));
		container = new Panel().setPreferredSize(new TerminalSize(175, 12));
		mainPanel.addComponent(menu.withBorder(Borders.singleLine("Katalogi")));
		
		//Panel base1Panel = new Panel().setPreferredSize(new TerminalSize(175, 3));
		//ActionListBox actionListBox = new ActionListBox();
		wyszukiwarka1 = new TextBox().setPreferredSize(new TerminalSize(12, 1));
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
				check = 1;

				
				
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
				check = 2;

			}});
		Wizyty.addListener(list);
		Skierowania = new Button("Skierowania", new Runnable() {

			public void run() {
				window.addWindowListener(listener0);
				//window.removeWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.addWindowListener(listener3);
				//window.removeWindowListener(listener4);
				//window.addWindowListener(listener);
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				check = 3;
				
			}});
		Skierowania.addListener(list);


		Recepty = new Button("Recepty", new Runnable() {

			@Override
			public void run() {
				window.addWindowListener(listener0);
				//window.removeWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.removeWindowListener(listener3);
				//window.addWindowListener(listener4);
				//window.addWindowListener(listener);
				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				check = 4;
				
				
			}});
		Recepty.addListener(list);
		Lekarz = new Button("Lekarze", new Runnable() {

			@Override
			public void run() {

				window.addWindowListener(listener0);
				//window.addWindowListener(listener1);
				//window.removeWindowListener(listener2);
				//window.removeWindowListener(listener3);
				//window.removeWindowListener(listener4);
				//table = new Table<String>("ID", "Login", "Haslo", "Imie", "Nazwisko", "Wiek", "Numer sali", "Telefon");

				wyszukiwarka1.setEnabled(true);
				window.setFocusedInteractable(wyszukiwarka1);
				check = 8;

				
				
			}});
		Lekarz.addListener(list);
			
		menu.addComponent(Pacjent);
		menu.addComponent(Wizyty);
		menu.addComponent(Skierowania);
		menu.addComponent(Recepty);
		menu.addComponent(Lekarz);

		mainPanel.addComponent(info);
		info.setLayoutManager(new GridLayout(2));
		nazwaWyszukiwarki = new Label("Pesel pacjenta lub dane lekarza:");
		info.addComponent(nazwaWyszukiwarki);
		//nazwaWyszukiwarki = new Label("Podaj pesel:");
		//info.addComponent(nazwaWyszukiwarki);
		//info.addComponent(new Label("Podaj pesel: "));
		info.addComponent(wyszukiwarka1);

		//Panel base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175, 1));

		mainPanel.addComponent(container.withBorder(Borders.singleLine("Informacje")));
		base1Panel1 = new Panel().setPreferredSize(new TerminalSize(175, 1));
		base1Panel1.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		mainPanel.addComponent(base1Panel1.withBorder(Borders.singleLine("Skróty")));
		//
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F6: Dodaj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F7: Usuñ"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F8: Edytuj"));
		base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		base1Panel1.addComponent(new Label("F11: Wyloguj"));
		
		//base1Panel1.addComponent(new EmptySpace(new TerminalSize(3,0)));
		//window.setComponent(mainPanel);

		//base1Panel1.addComponent(new EmptySpace(new TerminalSize(3, 0)));
		//window.setComponent(mainPanel);

		window.setHints(Arrays.asList(Window.Hint.FIT_TERMINAL_WINDOW, Window.Hint.EXPANDED));
		window.setComponent(mainPanel);
		terminal.flush();

        gui.addWindowAndWait(window);



	}

	public void Okno_pacjenta() {
		screen.clear();
		gui = new MultiWindowTextGUI(screen);
		BasicWindow window = new BasicWindow();

		Panel mainPanel = new Panel().setPreferredSize(new TerminalSize(170, 45));

		mainPanel.setLayoutManager(new LinearLayout(Direction.VERTICAL));

		Panel upPanel = new Panel().setPreferredSize(new TerminalSize(170, 2));
		mainPanel.addComponent(upPanel.withBorder(Borders.singleLine("Katalogi")));

		Panel cointainerPanel = new Panel().setPreferredSize(new TerminalSize(170, 5));
		mainPanel.addComponent(cointainerPanel.withBorder(Borders.singleLine("Cointainer")));
		cointainerPanel.addComponent(new EmptySpace());
		Panel basePanel = new Panel().setPreferredSize(new TerminalSize(170, 30));
		mainPanel.addComponent(basePanel.withBorder(Borders.singleLine("Cointainer")));

		Table<String> table = new Table<String>("Pesel", "Imie", "Nazwisko", "Wiek");
		for (Pacjent a : C.getPacjenci()) {
			table.getTableModel().addRow(a.getPesel(), a.getImie(), a.getNazwisko(), Integer.toString(a.getWiek()));

		}
		basePanel.addComponent(new EmptySpace());
		basePanel.addComponent(table);

		Panel base1Panel = new Panel().setPreferredSize(new TerminalSize(170, 3));
		mainPanel.addComponent(base1Panel.withBorder(Borders.singleLine("Cointainer")));
		window.setComponent(mainPanel);
		gui.addWindowAndWait(window);

	}

}
