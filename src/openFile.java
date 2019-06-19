import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class openFile {

	protected Shell shell;
	private Text text;
	private Jedit mainWindow;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			openFile window = new openFile();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void setMain(Jedit mainW) {
		mainWindow = mainW;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Open File...");
		shell.setLayout(new GridLayout(2, false));
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Drag and Drop your file here...");
		new Label(shell, SWT.NONE);
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				mainWindow.receiveOpenedFile(text.getText().replace("file:///", "/").trim());
				shell.close();
			}
		});
		btnNewButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		btnNewButton.setText("Open");

	}
}
