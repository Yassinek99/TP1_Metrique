import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Tloc {
    public void readFile(String path){
        int nbLines =0;
        boolean insideComment =false;
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNextLine()){
                line = scanner.nextLine().trim();

                if(insideComment){
                    if(line.endsWith("*/")){
                        insideComment = false;
                    }
                    continue;
                }
                if(line.startsWith("//")){
                    System.out.println(line);
                    continue;
                }
                if (line.startsWith("/*")){
                    insideComment=true;
                    continue;
                }
                nbLines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}