import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.ActionListBox;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.ActionListDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.gui2.table.Table;
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
		this.terminal = new DefaultTerminalFactory().setMouseCaptureMode(MouseCaptureMode.CLICK).createTerminal();
        this.screen = new TerminalScreen(terminal);
        gui = new MultiWindowTextGUI(screen);
        //sassaassaksadksk,,,,
	}
	public void Logowanie()  throws IOException { 
		// Setup terminal and screen layers
        
        screen.startScreen();
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

        panel.addComponent(new TextBox());
		panel.addComponent(new EmptySpace(new TerminalSize(0,2)));

        panel.addComponent(new Label("Has³o"));
		panel.addComponent(new EmptySpace(new TerminalSize(0,0)));

        panel.addComponent(new TextBox());
		panel.addComponent(new EmptySpace(new TerminalSize(0,2)));

        Button button = new Button("Zaloguj", new Runnable() {
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
        Button button1 = new Button("Zamknij", new Runnable() {
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
		BasicWindow window = new BasicWindow();
        ActionListBox actionListBox = new ActionListBox();
        actionListBox.addItem("jjjj", new Runnable() {
    		@Override
    		public void run() {
    			// Code to run when action activated
    		}
    	});
        actionListBox.addItem("ww", new Runnable() {
    		@Override
    		public void run() {
System.out.println("aaa");    		}
    	});
        actionListBox.setPreferredSize(new TerminalSize(4,4));

        Panel mainPanel = new Panel();
		mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

		Panel leftPanel = new Panel();
		mainPanel.addComponent(leftPanel.withBorder(Borders.singleLine("Left Panel")));
		leftPanel.addComponent(actionListBox);
		Panel rightPanel = new Panel();
		mainPanel.addComponent(rightPanel.withBorder(Borders.singleLine("Right Panel")));
		leftPanel.addComponent(new EmptySpace(new TerminalSize(20,17)));
		rightPanel.addComponent(new EmptySpace(new TerminalSize(47,18)));

		window.setComponent(mainPanel);
		gui.addWindowAndWait(window);
	}
	
}
