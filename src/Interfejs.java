import java.io.IOException;
import java.util.Arrays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Interfejs {
	private Terminal terminal;
	private Screen screen;
	public Interfejs() throws IOException {
		this.terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);
		//sassaassaksadksk
	}
	public void Logowanie()  throws IOException { 
		// Setup terminal and screen layers
        
        screen.startScreen();
        
        // Create panel to hold components
        Panel panel = new Panel();
        panel.setLayoutManager(new GridLayout(2));
        
        
        panel.addComponent(new Label("Login"));
        panel.addComponent(new TextBox());

        panel.addComponent(new Label("Has³o"));
        panel.addComponent(new TextBox());

        panel.addComponent(new EmptySpace(new TerminalSize(0,0))); // Empty space underneath labels
        Button button = new Button("Submit", new Runnable() {
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
        // Create window to hold the panel
    	BasicWindow window = new BasicWindow();
    	window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);

        // Create gui and start gui
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        gui.addWindowAndWait(window);

		
		
		
	}
	
	public void Menu_rejestracja() throws IOException {
		

		// Setup WindowBasedTextGUI for dialogs
		
		
		
		
		
	}
	
}
