import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;

public class Frame extends JFrame implements ActionListener {
	private JMenuItem open, exit, info, about, save, nuovo;
	private JTextArea i, t;// info e testo
	private JLabel img;
	private InfoFile iFile;
	private boolean doc = false;

	public Frame() {
		super("Test");

		menu();
		Container c = this.getContentPane();
		JPanel p = new JPanel(new GridLayout(1, 3, 10, 10));
		JPanel ico = new JPanel();
		i = new JTextArea("INFO", 13, 13);
		t = new JTextArea("TESTO", 13, 13);
		img = new JLabel(new ImageIcon("default.png"));

		ico.add(img);

		p.add(i);
		p.add(t);
		p.add(ico);
		c.add(p);

		// this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 350);
		setLocation(400, 200);
		setVisible(true);
	}

	public void menu() {
		JMenu file, help;
		JMenuBar mb = new JMenuBar();

		file = new JMenu("File");
		help = new JMenu("Help");

		nuovo = new JMenuItem("Nuovo file", 'N');
		open = new JMenuItem("Apri file", 'A');
		info = new JMenuItem("?", '?');
		save = new JMenuItem("Salva", 'S');
		about = new JMenuItem("About", 'A');
		exit = new JMenuItem("Esci", 'E');

		nuovo.addActionListener(this);
		save.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		info.addActionListener(this);
		about.addActionListener(this);

		help.add(info);
		help.add(about);
		file.add(nuovo);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(exit);

		mb.add(file);
		mb.add(help);

		this.setJMenuBar(mb);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o == open) {
			t.setText("");
			img.setIcon(new ImageIcon("default.png"));

			JFileChooser jf = new JFileChooser("c:\\Users\\Andrea Felline\\Desktop");// scelgo il file
			int i = jf.showDialog(null, "Seleziona un file");

			if (i == JFileChooser.APPROVE_OPTION) {// se ne sceglie uno

				iFile = new InfoFile(jf.getSelectedFile());// istanzio infoFile
				this.i.setText(iFile.getGenericInfo() + "\n" + iFile.getTechnicalInfo());

				if (iFile.isDoc()) {// se è un documento

					doc = true;
					try {// leggo il contenuto
						FileReader fr = new FileReader(iFile.getFile().getAbsolutePath());
						BufferedReader in = new BufferedReader(fr);
						String s = "";
						while ((s = in.readLine()) != null)
							t.append(s + "\n");

					} catch (IOException error) {
						System.out.println(error);
					}
					img.setIcon(new ImageIcon("immagine.png"));
				}

				if (iFile.isImg()) {// se è un'immagine
					doc = false;
					img.setIcon(new ImageIcon(iFile.getFile().getAbsolutePath()));
				}
			}
		} else if (o == nuovo) {
			File newf = null;
			JFileChooser jf = new JFileChooser("C:\\Users\\Andrea Felline\\Desktop\\");
			int i = jf.showSaveDialog(null);
			if (i == JFileChooser.APPROVE_OPTION) {
				String s = t.getText();
				newf = jf.getSelectedFile();
				try {
					newf.createNewFile();
					FileWriter fout = new FileWriter(newf.getAbsolutePath());
					PrintWriter out = new PrintWriter(fout);
					StringTokenizer st = new StringTokenizer(s,"\n");
					while(st.hasMoreTokens())
						out.println(st.nextToken());
					out.close();
					fout.close();

					//////////// apro il nuovo file
					doc = true;
					iFile = new InfoFile(newf);
					this.i.setText(iFile.getGenericInfo() + "\n" + iFile.getTechnicalInfo());
					img.setIcon(new ImageIcon("immagine.png"));

				} catch (IOException err) {
					System.out.println(err);
				}
			}

		} else if (o == save && iFile != null && doc) {
			salva();
		} else if (o == save) {
			System.out.println("Nessun file da salvare");
		} else if (o == exit) {
			System.exit(0);
		} else if (o == info) {
			JOptionPane.showMessageDialog(null, "Informazioni", "Informazioni", JOptionPane.INFORMATION_MESSAGE, null);
		} else if (o == about) {// about
			JOptionPane.showMessageDialog(null, "Fatto da Andrea Felline", "Informazioni",
					JOptionPane.INFORMATION_MESSAGE, null);
		}
	}

	private void salva() {
		String s = t.getText();
		try {// scrivo il contenuto della textarea sul file
			FileWriter fout = new FileWriter(iFile.getFile().getAbsolutePath());
			PrintWriter out = new PrintWriter(fout);
			out.println(s);
			out.close();
			fout.close();
		} catch (IOException error) {
			System.out.println(error);
		}
	}

	public static void main(String[] args) {
		new Frame();
	}

}
