package jsonjava;

import jdk.nashorn.internal.parser.JSONParser;

public class ObjectGPNWZT{
    public static void main(String[] args){
        JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("/mnt/33B43DC17BD1F746/Work/5.halfyear/XML/GPNWZT_1203/PD_munkafajlok/XMLGPNWZT1.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
 
			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray companyList = (JSONArray) jsonObject.get("Company List");
 
			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			Iterator<JSONObject> iterator = companyList.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}     
    }
}