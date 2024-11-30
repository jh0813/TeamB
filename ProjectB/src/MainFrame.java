import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		setTitle("Ang 김호지~");
		setSize(1500, 900);
		add(new Screen());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
