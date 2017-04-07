import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class InfofileJUnitTest {

	@Test
	public void testIsImg() {
		InfoFile f = new InfoFile (new File("c:\\Users\\Andrea Felline\\Desktop\\testing software.docx"));
		
		String risAtteso = "false";
		String risOttenuto;
		
		if(f.isImg())
			risOttenuto = "true";
		else
			risOttenuto = "false";
		
		assertEquals (risAtteso, risOttenuto);
	}

	@Test
	public void testIsDoc() {
		InfoFile f = new InfoFile (new File("c:\\Users\\Andrea Felline\\Desktop\\testing software.docx"));
		
		String risAtteso = "true";
		String risOttenuto;
		
		if(f.isDoc())
			risOttenuto = "true";
		else
			risOttenuto = "false";
		
		assertEquals (risAtteso, risOttenuto);
	}

}
