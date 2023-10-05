import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

public class Tls {

    private String path;

    private HashMap<String, TlsData> results;


    public Tls(String path) {
        this.path = path;
        results = new HashMap<>();
    }

    public Tls() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //    public void readDir(){
//        File[] dir = new File(path).listFiles();
//        Tassert tassert;
//        for(File file: dir){
//            tassert=new Tassert(file);
//            tassert.countAssert();
//            output ="./";
//            //Chemin du fichier
//            output+=  Paths.get(file.getName()).toAbsolutePath().getFileName().toString()+", ";
//
//            //nom du paquet
//            output+= tassert.getTloc().getPackageName()+", ";
//
//            //nom de la classe
//            output+= FilenameUtils.removeExtension(file.getName())+", ";
//
//            //tloc de la classe
//            output+= tassert.getTloc().getNblines()+", ";
//
//            //tassert de la classe
//            output+= tassert.getNbAsserts()+", ";
//
//            //tcmp de la classe
//            double result = tassert.getNbAsserts()==0? 0: (double) tassert.getTloc().getNblines() /tassert.getNbAsserts();
//            output+= String.format("%.2f",result);
//
//            System.out.println(output);
//        }

    public void readDir() {
        File[] dir = new File(path).listFiles();

        for (File file : dir) {
            calculateTls(file);
        }
        for (String k : results.keySet()) {
            System.out.println(results.get(k));
        }
    }

    public void readDir(String dirPath) {
        File[] dir = new File(dirPath).listFiles();
        Tassert tassert;
        TlsData currentTls;
        String className;

        for (File file : dir) {
            if (file.isDirectory()) {
                // System.out.println(file.getPath());
                System.out.println(file.getName());
                readDir(file.getPath());
                continue;
            }else{
                calculateTls(file);
            }
        }
    }

    public void calculateTls(File file){
        Tassert tassert;
        TlsData currentTls;
        String className;

        currentTls = new TlsData();

        tassert = new Tassert(file);
        tassert.countAssert();


        //Chemin du fichier
        currentTls.setFilePath(Paths.get(file.getName()).toAbsolutePath().getFileName().toString());

        //nom du paquet
        currentTls.setPackageName(tassert.getTloc().getPackageName());

        //nom de la classe
        className = FilenameUtils.removeExtension(file.getName());
        currentTls.setClassName(className);

        //tloc de la classe
        currentTls.setTloc(tassert.getTloc().getNblines());

        //tassert de la classe
        currentTls.setTasssert(tassert.getNbAsserts());

        //tcmp de la classe
        currentTls.calculateTCMP();

        results.put(className, currentTls);
    }

    public void printResults(){
        for(String k : results.keySet()){
            System.out.println(results.get(k));
        }
    }
}
