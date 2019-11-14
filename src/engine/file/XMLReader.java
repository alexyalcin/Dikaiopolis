/**
 * XMLReader.java 1.0 Nov 13, 2019
 *
 * Copyright (c) 2019 Alexander Yalcin. All rights reserved.
 */
package engine.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Alex
 *
 */
public class XMLReader {
	File file;
	public XMLReader(String file_name) {
		file = new File(file_name);
	}
	
	public String getTag(String tag_name) {
		return getTag(file, tag_name);
	}
	
	public static String getTag(String file_name, String tagName) {
		return getTag(new File(file_name), tagName);
	}
	
	public static String getTag(File file, String tagName) {
		String contained = "";
		boolean inTag = false;
		try {
			Scanner sc = new Scanner(file);
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static void main(String args[]) {
	}
}
