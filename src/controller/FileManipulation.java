package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManipulation implements IFile {

	@Override
	public boolean writeFile(String filename, String content) {
		// TODO Auto-generated method stub
		File file = new File("D:/junior/expressTrace/WebRoot/json/" + filename);
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(content);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
