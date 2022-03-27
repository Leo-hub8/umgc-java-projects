import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class file {
	
	static String baseDir = "c:/tmp-umuc";
	static String directory = baseDir;
	PrintWriter path;
	{
	
	 try {
         // create pet files in directory baseDir
         path = new PrintWriter(new File(baseDir));
         path.println("This is my file");
         path.flush();
         path.close();
     } catch (IOException e) {
         e.printStackTrace();  // just print trace if there are issues
     }
}
}
