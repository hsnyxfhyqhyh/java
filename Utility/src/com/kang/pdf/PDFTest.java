package com.kang.pdf;

import java.io.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class PDFTest {

	public static void main(String arg[]) throws Exception {
//		Document document = new Document();
		Document document = new Document(PageSize.A4.rotate());
		
		PdfWriter writer=PdfWriter.getInstance(document,
				  new FileOutputStream("landscape1.pdf"));
		  
		
		document.open();
		Image image1 = Image.getInstance("1.jpg");
		
		Image image2 = Image.getInstance("2.jpg");
		
		Image image3 = Image.getInstance("2.jpg");

//		Paragraph para = new Paragraph(
//				"RoseIndia.net is a global services company that ensures maximum returns by providing quality software solutions and services. The Indian based company provides services to several reputed institutional clients, in the domain of IT and IT enabled Technologies. We help in understanding the client requirements and offer customized solutions in various specialized areas like Web based Technologies , Database Systems , Client Server Architecture , E-commerce Solutions and Consultancy Services . Other services offered include Online Technical Tutorials , Content Development and Generation , Web solutions for B2B, B2C, C2C and Travel portals , Web Development, Promotion and Hosting, and Applications Service Provider Solutions . With expertise and skilled human resource, we also provide assistance for offshore development of projects. ");
//		document.add(para);
		document.add(image1);
		
		document.newPage();
        writer.setPageEmpty(false);
        
		document.add(image2);
		
		document.newPage();
        writer.setPageEmpty(false);
        
        document.add(image3);
        
        document.close();
	}
}
