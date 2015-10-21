import java.awt.Color;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.html.HTML;


public class PicWatch extends JPanel{

	private static String FILENAME="\\read.html";
	private String[] PIC_EXT={"png","jpg"};
	
	private String _fileStr="E:\\mytest\\picFile\\files";
	private ArrayList<File> _fileList;
	
	private JLabel alarmText;
	
	public PicWatch()
	{
		super();
		
		initBoard();
		
//		readDic();
		
	}
	
	WindowListener wl = new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			closeAll();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private void closeAll()
	{
		try {
			System.exit(ABORT);
			
		} catch (Exception e) {
			// TODO: handle exception
		} catch (Throwable t)
		{
			
		}
	}
	
	private void initBoard()
	{
		JFrame jf = new JFrame("");
		jf.setSize(500, 400);
		jf.setVisible(true);
		jf.addWindowListener(wl);
		Container container = jf.getContentPane();
		
		final JTextField tf = new JTextField();
		tf.setSize(200, 20);
		tf.setText(_fileStr);
		container.add(tf);
		tf.setLocation(20, 20);
		
		JButton conformBtn = new JButton("Conform");
		conformBtn.setSize(100, 20);
		container.add(conformBtn);
		conformBtn.setEnabled(true);
		conformBtn.setLocation(20, 50);
		conformBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fileStr = tf.getText().trim();
				if(readDic())
				{
					String str = createHtmlStr();
					System.out.println(str);
					createFile(str);
				}
			}
		});
		
		alarmText = new JLabel("");
		alarmText.setSize(100, 20);
		container.add(alarmText);
		alarmText.setLocation(20, 70);
		alarmText.setForeground(new Color(255,0,0));
	}
	
	private void createFile(String str)
	{
		File targetFile = new File(_fileStr+FILENAME);
		
		if(targetFile.exists())
		{
			targetFile.delete();
		}
		try {
			targetFile.createNewFile();
			FileWriter writer = new FileWriter(targetFile);
			writer.write(str);
			writer.close();
			alarmText.setText(alarmText.getText()+"files built!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String createHtmlStr()
	{
		StringBuffer result = new StringBuffer();
		result.append("<!DOCTYPE html>\n");
		result.append("<html>\n");
		result.append("<body overflow=\"auto\">\n");
		for (File pic : _fileList) {
			result.append(pic.getName()+"<br/>\n");
			result.append("<img src=\""+pic.getName()+"\" /><br/>\n");
		}
		result.append("</body>\n");
		result.append("</html>\n");
		
		return result.toString();
	}
	
	private boolean readDic()
	{
		File root = new File(_fileStr);
		if(root.exists() && root.isDirectory())
		{
			alarmText.setText("");
			_fileList = new ArrayList<File>();
			File[] list = root.listFiles();
			for (int i = 0; i < list.length; i++) {
				File tmp=list[i];
				if(isPic(tmp))
				{
					_fileList.add(tmp);
//					System.out.println(tmp);
				}
			}
			if(!_fileList.isEmpty())
			{
				alarmText.setText("files read!\n");
				return true;
			}
			else
				alarmText.setText("files are empty!");
			
		}
		else
			alarmText.setText("wrong link!");
			
		return false;
	}
	
	private boolean isPic(File file)
	{
		if(file.isDirectory())return false;
		String[] list = file.getName().toLowerCase().split("\\.");
		String ext;
		if(list!=null&&list.length>0)
			ext = list[list.length-1];
		else return false;
		for (int i = 0; i < PIC_EXT.length; i++) {
			if(ext.equals(PIC_EXT[i]))return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		PicWatch instance = new PicWatch();
		
		
		
	}
	
	
}
