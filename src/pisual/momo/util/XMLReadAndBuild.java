package pisual.momo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import pisual.bank.model.SourceLocation;
import pisual.bank.model.Unit;

public class XMLReadAndBuild {
public List<Unit> XMLReadAndBuild(String file) throws DocumentException {
    System.out.println("Start!"); 
	List<Unit> unitList = new ArrayList<Unit>();
	 SAXReader saxReader = new SAXReader();
     Document document = saxReader.read(new File(file));
	 Element root = document.getRootElement();
	 List<Element> Results = root.elements("Results");
	 for (Iterator iter =Results.iterator();iter.hasNext();)
     {
		 Element e = (Element) iter.next();
		 List<Element> Result = e.elements("Result");
		 System.out.println(e.getName());
		 for (Iterator iterr =Result.iterator();iterr.hasNext();)
	     {
		 Unit unit = new Unit();
		 Element r = (Element) iterr.next();
		unit.setSourceFile(new File(r.elementText("SourceFile")));
		System.out.println(r.elementText("SourceFile"));
		unit.setTargetLocationX(Integer.parseInt(r.elementText("TargetLocationX")));
		System.out.println(r.elementText("TargetLocationX"));
		unit.setTargetLocationY(Integer.parseInt(r.elementText("TargetLocationY")));
		System.out.println(r.elementText("TargetLocationY"));
		
		List<Element> SourceLocations = r.elements("SourceLocations");
		List<SourceLocation> sourceLocationList = new ArrayList<SourceLocation>();
		 for (Iterator iterSourceLocations =SourceLocations.iterator();iterSourceLocations.hasNext();)
	     {
			 Element SourceLocationElement = (Element) iterSourceLocations.next();
			 List<Element> SourceLocation = SourceLocationElement.elements("SourceLocation");
			 for (Iterator iterSourceLocation =SourceLocation.iterator();iterSourceLocation.hasNext();)
		     {
				 SourceLocation sourceLocation = new SourceLocation();
				 Element s = (Element) iterSourceLocation.next();
				 sourceLocation.setFile(new File(s.elementText("File")));
				 System.out.println(s.elementText("File"));
				 sourceLocation.setLocationX(Integer.parseInt(s.elementText("locationX")));
				 System.out.println(s.elementText("locationX"));
				 sourceLocation.setLocationY(Integer.parseInt(s.elementText("locationY")));
				 System.out.println(s.elementText("locationY"));
				 sourceLocationList.add(sourceLocation);
	     }
	     }
		 unit.setSourceLocation(sourceLocationList);
		 unitList.add(unit);
     }
     }
	 return unitList;
}
}
