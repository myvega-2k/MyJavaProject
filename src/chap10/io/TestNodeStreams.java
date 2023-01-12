package chap10.io;
import java.io.*;

public class TestNodeStreams {
	public static void main(String[] args) {
		try {
			FileReader input = new FileReader(args[0]);
			try {
				FileWriter output = new FileWriter(args[1]);
				try {
					char[]     buffer = new char[128];
					int        charsRead;

					// read the first buffer
					charsRead = input.read(buffer);
					while ( charsRead != -1 ) {
						// write buffer to the output file
						output.write(buffer, 0, charsRead);

						// read the next buffer
						charsRead = input.read(buffer);
					}

				} finally {
					output.close();}
			} finally {
				input.close();}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}