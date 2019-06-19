import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import swing2swt.layout.BoxLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Jedit {

	protected Shell shell;
	private Text text;
	private Jedit self = this;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Jedit window = new Jedit();
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

	public void receiveOpenedFile(String path) {
		text.setText(fileManager.openFile(path));
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(518, 360);
		shell.setText("Jedit");
		shell.setLayout(new GridLayout(1, false));
		
		Group group = new Group(shell, SWT.NONE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		group.setForeground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		GridData gd_group = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_group.widthHint = 246;
		group.setLayoutData(gd_group);
		
		Button openBtn = new Button(group, SWT.NONE);
		openBtn.setBounds(10, 0, 63, 28);
		openBtn.setText("Open...");
		
		Button saveBtn = new Button(group, SWT.NONE);
		saveBtn.setBounds(79, 0, 45, 28);
		saveBtn.setText("Save");
		
		text = new Text(shell, SWT.BORDER | SWT.MULTI);
		text.setEditable(false);
		text.setEnabled(false);
		GridData gd_text = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_text.widthHint = 412;
		gd_text.heightHint = 261;
		text.setLayoutData(gd_text);

		// Open Button implementation
		openBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// System.out.println(fileManager.openFile("/home/vm/Documents/test.txt"));
				// text.setText(fileManager.openFile("/home/vm/Documents/test.txt"));
				try {
					openFile openWindow = new openFile();
					openWindow.setMain(self);
					openWindow.open();
				} catch (Exception err) {
					err.printStackTrace();
				}
				text.setEnabled(true);
				text.setEditable(true);
				shell.setText("File Opened - Jedit");
			}
		});
		
		// Save Button implementation
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				System.out.println("You clicked Save!");
				fileManager.saveFile(text.getText());
			}
		});
	}
}
