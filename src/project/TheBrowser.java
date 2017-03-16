
package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class TheBrowser extends JFrame
{
  // this is where the web page url will be entered
  private JTextField addressBar;
  
  //on this the web page will be displayed
  private JEditorPane display;
  
  //Label to display 
  private JLabel statusLabel = new JLabel();
  
  public TheBrowser() {
	super("My Browser");
	
	addressBar = new JTextField("Enter URL here and press enter");
	//this listener will be called when enter is pressed after entering url
	addressBar.addActionListener(
	  new ActionListener() {
		public void actionPerformed(ActionEvent event) {
		  loadPage(event.getActionCommand());
		}
	  }
	);
	
	add(addressBar, BorderLayout.NORTH);
	statusLabel.setText("Java Browser");
	add(statusLabel, BorderLayout.SOUTH);
	
	display = new JEditorPane();
	display.setEditable(false);
	
	//this will make all hyperlinks in the web page active
	//and when clicked the page in connection with that url will open
	display.addHyperlinkListener(
	  new HyperlinkListener() {
		public void hyperlinkUpdate(HyperlinkEvent event) {
		  if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			loadPage(event.getURL().toString());
		  }
		}
	   }
	);
	add(new JScrollPane(display), BorderLayout.CENTER);
	setSize(500, 300);
	setVisible(true);
  }
  
  private void loadPage(String userUrl) {
	try {
	  userUrl = "https://" + userUrl; 
	  display.setPage(userUrl);
	  addressBar.setText(userUrl);
	  statusLabel.setText("Java Browser Running...");
	} catch (Exception e) {
	  statusLabel.setText("An error has occured.");
	}
  }
}

