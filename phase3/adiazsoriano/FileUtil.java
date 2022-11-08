import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public abstract class FileUtil {
    
    public static LinkedList<String> readFile(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        LinkedList<String> fileContent = new LinkedList<>();
        
        BufferedReader bufferedRead = null;
        try {
            
           bufferedRead = new BufferedReader(new FileReader(f));
                
            String line;
            while((line = bufferedRead.readLine()) != null) {
                fileContent.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File does not exist.\n");
        } catch(IOException e) {
            System.err.println("Input/Output Issues or Interruption:\n");
            e.printStackTrace();
        } finally {
           try {
                if(bufferedRead != null) {
                    bufferedRead.close();
                }
           } catch (IOException e) {
                System.err.println("Exception when attempting to close buffered reader:\n");
                e.printStackTrace();
           }
        }

        return fileContent;
    }
}
