package lva.docsflow.parsingXML;

import lva.docsflow.catalogs.*;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LoadProductsFromXML {

	private String fileNameProducts;

	public LoadProductsFromXML(String fname) {
		this.fileNameProducts = fname;
	}

	public ArrayList<Products> ReadProductsFromXML() {
		ArrayList<Products> products = new ArrayList<Products>();
		
		File fileXML = new File(fileNameProducts);
		
		if (fileXML.exists()) {
			try {
				products = ParsingProductXML();
			} catch (ParserConfigurationException ex) {
				System.out.println("Parsing XML error!\n" + ex.getMessage());
			} catch (Exception ex) {
				System.out.println("General XML error!\n" + ex.getMessage());
			}		
		}
		return products;
	}

	private ArrayList<Products> ParsingProductXML() throws ParserConfigurationException, Exception {
		ArrayList<Products> products = new ArrayList<Products>();
		
        // create document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder(); 
        
        // get root node
        Document doc = db.parse(new File(this.fileNameProducts));
        NodeList listProducts = doc.getDocumentElement().getElementsByTagName("Product");

        // parsing XML, get products
        for (int i = 0; i < listProducts.getLength(); i++) {
            Node nodeProduct = listProducts.item(i);
            
            NamedNodeMap attrProduct = nodeProduct.getAttributes();
            products.add(new Products(i+1,attrProduct.getNamedItem("ProductCode").getNodeValue(), 
            		attrProduct.getNamedItem("ProductName").getNodeValue(),
                    Float.valueOf(attrProduct.getNamedItem("Package_qty").getNodeValue()).intValue(),
                    Float.parseFloat(attrProduct.getNamedItem("ProductVolume").getNodeValue()),
                    Float.parseFloat(attrProduct.getNamedItem("UnitWeight").getNodeValue())
                )      
            );
        }
	
		return products;
	}
}
