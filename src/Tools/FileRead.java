package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileRead {
	public static String toWord(String s) {
		StringBuffer res = new StringBuffer();
		int size = s.length();
		for (int i = 0; i < size; i++) {
			char c = s.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				res.append(c);
			}
		}
		return res.toString();
	}
	/*Retourne la liste des mots présents dans le fichier fileName*/
	public static String[] readWord(File fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			return everything.split(System.lineSeparator());
		} finally {
			br.close();
		}
	}

	/*Retourne la liste des mots dans tous les fichiers dans le dossier nomDirectory*/
	public static String[] readDirectory(String dirName) throws IOException {
		File dir = new File(dirName);
		String[] buffer = new String[0];
		if (!dir.isDirectory()) {
			return buffer;
		} else {
			String[] files = dir.list();
			for (int i = 0; i < files.length; i++) { 
				File temp = new File(dirName+"/"+files[i]);
				buffer = concat(buffer, readWord(temp));

			}
			return buffer;
		}

	}

	private static String[] concat(String[] first, String[] second) {
		List<String> both = new ArrayList<String>(first.length + second.length);
		Collections.addAll(both, first);
		Collections.addAll(both, second);
		return both.toArray(new String[both.size()]);
	}
}
