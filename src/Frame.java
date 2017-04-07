import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	private JMenuItem open, exit, info, about;
	private JTextArea i,t;//info e testo
	private JLabel img;
	private InfoFile iFile;
	
	public Frame() {
		super("Test");
		
		menu();
		Container c = this.getContentPane();
		JPanel p = new JPanel(new GridLayout(1,3));
		JPanel info =new JPanel();
		JPanel txt =new JPanel();
		JPanel ico =new JPanel();
		i=new JTextArea("info",13,13);
		t=new JTextArea("testo",13,13);
		img=new JLabel(new ImageIcon("green32.png"));
		
		info.add(i);
		txt.add(t);
		ico.add(img);
		
		p.add(info);
		p.add(txt);
		p.add(ico);
		c.add(p);
		
		// this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 350);
		setLocation(400, 200);
		setVisible(true);
	}
	
	public void menu() {
		JMenu file,help;
		JMenuBar mb = new JMenuBar();

		file = new JMenu("File");
		help = new JMenu("Help");
		
		open = new JMenuItem("Apri file", 'A');
		info = new JMenuItem("?", '?');
		about=new JMenuItem("About",'A');
		exit = new JMenuItem("Esci", 'E');

		open.addActionListener(this);
		exit.addActionListener(this);
		info.addActionListener(this);
		about.addActionListener(this);

		help.add(info);
		help.add(about);
		file.add(open);
		file.addSeparator();
		file.add(exit);

		mb.add(file);
		mb.add(help);

		this.setJMenuBar(mb);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o==open){
			JFileChooser jf = new JFileChooser("c:\\Users\\Andrea Felline\\Desktop");
			int i = jf.showDialog(null, "Seleziona un file");
			if (i == JFileChooser.APPROVE_OPTION){
				iFile= new InfoFile(jf.getSelectedFile());
				this.i.setText(iFile.getGenericInfo()+"\n"+iFile.getTechnicalInfo());
				if(iFile.isDoc()){
					//testo
					img.setIcon(new ImageIcon("immagine.png"));
				}
				if(iFile.isImg()){
					img.setIcon(new ImageIcon(iFile.getFile().getAbsolutePath()));
				}
				
			}
		}else if(o==exit){
			System.exit(0);
		}else if(o==info){
			//joptionpane
		}else{//about
			//joptionpane
		}

	}

	public static void main(String[] args) {
		new Frame();
	}

}
