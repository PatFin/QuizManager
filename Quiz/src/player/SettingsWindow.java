package player;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.ConfigWriter;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private JButton cancelButton, saveButton;
	private JLabel learningGoodAnswers, learningWrongAnswers, learningTitle;
	private JPanel buttonPanel;
	private JTextField lGoodAnswerField, lWrongAnswerField;	
	
	/**
	 * Constructor
	 * Creates the window and initialises to zero the fields.
	 * Use method refreshValues to display the current values in the different fields.
	 * @see #refreshValues() 
	 */
	public SettingsWindow () {
		super("Settings");
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		//row 0 and 1
		learningTitle = new JLabel("LEARNING MODE SETTINGS");
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.PAGE_START;
		c.weighty = 1.0;
		c.gridheight = 2;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		this.add(learningTitle, c);
		
		c.weightx = 1.0;
		c.weighty = 0.8;
		
		//row 2
		learningGoodAnswers = new JLabel("Good answers to pass:");
		lGoodAnswerField = new JTextField(String.valueOf(LearningQuizPanel.successNeededForMearningMode));
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 2;
		this.add(learningGoodAnswers, c);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 2;
		this.add(lGoodAnswerField, c);
		
		//row 3
		learningWrongAnswers = new JLabel("Wrong answers to fail a question:");
		lWrongAnswerField = new JTextField(String.valueOf(LearningQuizPanel.failuresNeededForLearningMode));
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 3;
		this.add(learningWrongAnswers, c);
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 3;
		this.add(lWrongAnswerField, c);
		
		//Row 4 JSeperator
		c.gridy = 4;
		c.gridx = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.gridwidth = 4;	
		c.gridheight = 1;
		this.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		
		//row 5
		//Creating buttons for validation and stuff
		buttonPanel = new JPanel(new GridLayout(1, 2));
		
		cancelButton = new JButton("Cancel");
		saveButton = new JButton("Save");
		this.setActionListeners();
		buttonPanel.add(cancelButton, c);
		buttonPanel.add(saveButton, c);
		
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridheight = 2;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 4;
		this.add(buttonPanel, c);
		
		this.pack();
		this.setVisible(false);	//We do not show the window when it is created. Only when the window is requested by the MainFrame will it display.
	}
	
	public void refreshValues () {
		this.lGoodAnswerField.setText(String.valueOf(LearningQuizPanel.successNeededForMearningMode));
		this.lWrongAnswerField.setText(String.valueOf(LearningQuizPanel.failuresNeededForLearningMode));
		
	}
	
	
	/**
	 * Allows the Settings Window to save the new values for the learning mode. 
	 */
	private void saveValuesToMainFrame () {
		boolean noError = true;
		try {
			LearningQuizPanel.successNeededForMearningMode = Integer.parseInt(lGoodAnswerField.getText());
		} catch (Exception e) {
			System.out.println("Unable to get the values from the TextField for Good answer number.");
			noError = false;
		}
		
		try {
			LearningQuizPanel.failuresNeededForLearningMode = Integer.parseInt(lWrongAnswerField.getText());
		} catch (Exception a) {
			System.out.println("Unable to get the values from the TextField for Wrong answer number.");
			noError = false;
		}
		if (noError) {
			JOptionPane.showMessageDialog(null,
					"Settings saved successfully.",
					"Settings", JOptionPane.INFORMATION_MESSAGE);
					
		} else {
			JOptionPane.showMessageDialog(null,
					"Settings were not saved: \n"
					+ "Make sure you enter a number in the text fields!",
					"Error", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	/**
	 * Sets what each button needs to do.
	 */
	private void setActionListeners () {
		cancelButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				//TODO hide the window
				setVisible(false);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//save the values of the fields to static fields of mainFrame
				//Save the settings in configuration file
				//Close the window
				setVisible(false);
				saveValuesToMainFrame();
				ConfigWriter.savePreferences();
				
			}
		});
	}
	
	public static void main(String [] args) {
		new SettingsWindow().setVisible(true);
	}
}