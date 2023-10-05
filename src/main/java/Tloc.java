import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tloc {

    private String strippedFile;

    public String getStrippedFile() {
        return strippedFile;
    }

    public void setStrippedFile(String strippedFile) {
        this.strippedFile = strippedFile;
    }

    public int readFile(File file){
        int nbLines =1;
        boolean insideComment =false;
        try{
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

                if(line.isBlank()) {
                    continue;
                }

                //check for comments
                if(line.startsWith("//")){
                    continue;
                }

                if (line.startsWith("/*")){
                    insideComment=true;
                    continue;
                }
                strippedFile+=line+"\n";
                nbLines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return nbLines;
    }
}
