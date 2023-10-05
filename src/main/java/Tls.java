import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;

public class Tls {

    private String path, output;

    private HashMap<String, TlsData> results;


    public Tls(String path) {
        this.path = path;
        results = new HashMap<>();
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
        Tassert tassert;
        TlsData currentTls;
        for (File file : dir) {
            currentTls = new TlsData();

            tassert = new Tassert(file);
            tassert.countAssert();


            //Chemin du fichier
            currentTls.setFilePath(Paths.get(file.getName()).toAbsolutePath().getFileName().toString());

            //nom du paquet
            currentTls.setPackageName(tassert.getTloc().getPackageName());

            //nom de la classe
            currentTls.setClassName(FilenameUtils.removeExtension(file.getName()));

            //tloc de la classe
            currentTls.setTloc(tassert.getTloc().getNblines());

            //tassert de la classe
            currentTls.setTasssert(tassert.getNbAsserts());

            //tcmp de la classe
            currentTls.calculateTCMP();

            System.out.println(currentTls);
        }
    }
}
