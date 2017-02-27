package gmfilewriter;

import java.io.FileWriter;
import java.io.IOException;

public class GMFileWriter {
	
	public FileWriter fileWriter;
	
	public GMFileWriter() {
		
	}
	
	public void writeToFileWithDoubles(double[] doubles) throws IOException {
		try {
			fileWriter = new FileWriter(System.getProperty("user.home") + "\\Desktop\\2017Autonomous\\CSV\\myFile.csv");
			for (int i = 0; i < doubles.length; i++) {
				fileWriter.write(String.valueOf(doubles[i]));
			}
		} catch (IOException e) {
			System.out.print(e);
		}
	}
	
}
