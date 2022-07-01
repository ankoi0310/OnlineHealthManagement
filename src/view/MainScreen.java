package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.BoxLayout;

public class MainScreen extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Header header;
	private ListView view;

	/**
	 * Create the panel.
	 */
	public MainScreen() {
		setBorder(new EmptyBorder(8, 8, 8, 8));
		setLayout(new BorderLayout(0, 0));
		
		header = new Header();
		header.setBorder(new EmptyBorder(4, 4, 4, 4));
		add(header, BorderLayout.NORTH);
		header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
		
		view = new ListView();
		view.setBounds(0, 0, getWidth(), getHeight());
		view.setBorder(new EmptyBorder(4, 4, 4, 4));
		GridBagLayout gbl_view = new GridBagLayout();
		gbl_view.columnWidths = new int[]{0};
		gbl_view.rowHeights = new int[]{0};
		gbl_view.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_view.rowWeights = new double[]{Double.MIN_VALUE};
		view.setLayout(gbl_view);
		add(view);
	}

}
