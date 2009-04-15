import org.apache.xmlbeans.*;

import com.mydomain.myApp.E1Document;
public class OutputXml {
	/** 
	 * 其中e11=11 e121=”e121”
	 * @param args
	 */

	public static void main(String args[]) {

		E1Document e1Document;
		E1Document.E1  E1Element;
		E1Document.E1.E12  E1e2Element;
		
		XmlOptions xmlOptions;
		e1Document = E1Document.Factory.newInstance();
		E1Element = e1Document.addNewE1();
		E1e2Element=E1Element.addNewE12();
		E1Element.setE11(11);
		E1e2Element.setE121("e121");
		/** 
		 * 如果需要设置setE122的值请打开,由于xsd文件定义的元素不是必须的.
		 */
		//E1e2Element.setE122();
		xmlOptions = new XmlOptions();
		xmlOptions.setSavePrettyPrint();
		xmlOptions.setSavePrettyPrintIndent(4);
		String xmlStr = e1Document.xmlText(xmlOptions);
		System.out.println("XML Instance Document is : \n" + xmlStr );
	}

}
