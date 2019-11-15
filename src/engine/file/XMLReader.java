/**
 * XMLReader.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Alex
 *
 */
public class XMLReader {
	String string_parse = null;
	File file_parse = null;
	
	public XMLReader(String r) {
		string_parse = r;
	}
	
	public XMLReader(File f) {
		file_parse = f;
	}
	
	private Scanner getScanner() {
		if (string_parse != null) return new Scanner(string_parse);
		if (file_parse != null)
			try {
				return new Scanner(file_parse);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	public String getTag(String tagName) {
		String contained = "";
		boolean inTag = false;
		try {
			Scanner sc = getScanner();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!inTag) {
					if (line.contains("<" + tagName + ">")) {
						inTag = true;
						line = line.substring(line.indexOf("<" + tagName + ">") + tagName.length() + 2);
					}
				} if (inTag) {
					if (line.contains("</" + tagName + ">")) {
						line = line.substring(0, line.indexOf("</" + tagName + ">"));
						inTag = false;
					} 
					contained += line;
					if (!inTag) {
						return contained;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public Set<String> getAllOfTag(String tagName) {
		Set<String> tag_insides = new HashSet<String>();
		boolean inTag = false;
		String contained = "";
		try {
			Scanner sc = getScanner();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!inTag) {
					if (line.contains("<" + tagName + ">")) {
						inTag = true;
						line = line.substring(line.indexOf("<" + tagName + ">") + tagName.length() + 2);
					}
				} if (inTag) {
					if (line.contains("</" + tagName + ">")) {
						line = line.substring(0, line.indexOf("</" + tagName + ">"));
						inTag = false;
					} 
					contained += line;
					if (!inTag) {
						tag_insides.add(contained);
						contained = "";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tag_insides;
	}

	public static void main(String args[]) {
		XMLReader reader = new XMLReader(new File("assets/structures/gate.xml"));
		Set<String> struct = reader.getAllOfTag("block");
		for (String s : struct) {
			XMLReader s_reader = new XMLReader(s);
			System.out.println(s_reader.getTag("img"));
		}
	}
}
