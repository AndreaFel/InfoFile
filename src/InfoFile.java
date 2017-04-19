import java.io.*;

public class InfoFile {
	private File file;

	public InfoFile(File f) {
		file = f;
	}

	public String getGenericInfo() {
		String s = "";
		s += "Nome: " + file.getName();
		s += "\nPath: " + file.getAbsolutePath();
		s += "\nLength: " + file.length();
		return s;
	}

	public boolean isImg() {
		String[] ext = { "png", "jpg", "ico", "bmp", "jpeg", "gif" };
		for (int i = 0; i < ext.length; i++) {
			if (file.getName().toLowerCase().endsWith(ext[i]))
				return true;
		}
		return false;
	}
	
	public File getFile(){
		return file;
	}

	public boolean isDoc() {
		/*String[] ext = { "pdf", "doc", "docx", "txt" };
		for (int i = 0; i < ext.length; i++) {
			if (file.getName().toLowerCase().endsWith(ext[i]))
				return true;
		}*/
		if(!this.isImg()){
			return true;
		}
		return false;
	}
	/**
	 * ciao <b>bello</b>
	 * */
	public String getTechnicalInfo() {
		String s = "";
		s += "Può essere letto: " + file.canRead();
		s += "\nPuò essere scritto: " + file.canWrite();
		s += "\nPuò essere eseguito: " + file.canExecute();
		s += "\nE' nascosto: " + file.isHidden();
		return s;
	}

}