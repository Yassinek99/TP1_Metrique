import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

public class Tls {

    private String path;

    private ArrayList<TlsData> results;


    public Tls(String path) {
        this.path = path;
        results = new ArrayList<>();
    }

    public Tls() {
        results = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ArrayList<TlsData> getResults() {
        return results;
    }

    public void setResults(ArrayList<TlsData> results) {
        this.results = results;
    }

    public void readDir() {
        File[] dir = new File(path).listFiles();
        for (File file : dir) {
            calculateTls(file);
        }
    }

    public void readDir(String dirPath) {
        if (new File(dirPath).isDirectory()) {
            File[] dir = new File(dirPath).listFiles();

            for (File file : dir) {
                if (file.isDirectory()) {
                    readDir(file.getPath());
                } else {
                    calculateTls(file);
                }
            }
        } else {
            calculateTls(new File(dirPath));
        }
    }

    private void calculateTls(File file) {
        if (FilenameUtils.getExtension(file.getPath()).equals("java")) {

            Tassert tassert;
            TlsData currentTls;
            String className;

            currentTls = new TlsData();

            tassert = new Tassert(file);
            tassert.countAssert();


            //Chemin du fichier
            currentTls.setFilePath(Paths.get(file.getName()).toAbsolutePath().toString());

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

            //On exclu les classes sans test
            if (currentTls.getTasssert() != 0) {
                results.add(currentTls);
            }
        }
    }

    public void printResults() {
        for (TlsData t : results) {
            System.out.println(t);
        }
    }
}
