import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Tloc {

    private String strippedFile;
    private int nblines;

    private String packageName;

    public Tloc() {
        nblines=0;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getNblines() {
        return nblines;
    }

    public void setNblines(int nblines) {
        this.nblines = nblines;
    }

    public String getStrippedFile() {
        return strippedFile;
    }

    public void setStrippedFile(String strippedFile) {
        this.strippedFile = strippedFile;
    }

    public void readFile(File file){
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

                if(line.startsWith("package")){
                    packageName = line.split(" ")[line.split(" ").length-1];
                    packageName = packageName.substring(0,packageName.length()-1);
                }
                strippedFile+=line+"\n";
                nblines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
