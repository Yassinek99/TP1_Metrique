
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        runTls(args);


    }

    public static void runTloc(String[] args){
        Tloc tloc = new Tloc();
        tloc.readFile(new File(args[0]));
        System.out.println(tloc.getNblines());
    }

    public static void runTassert(String[] args){
        Tassert tassert = new Tassert(new File(args[0]));
        tassert.countAssert();
        System.out.println(tassert.getNbAsserts());
    }

    public static void runTls(String[] args) throws IOException {
        Tls tls = new Tls();

        if(args.length>1){
            FileWriter fileWriter = new FileWriter(new File(args[0]));

            tls.readDir(args[1]);

            for(TlsData t: tls.getResults()){
                fileWriter.write(t.toString()+"\n");
            }
            fileWriter.close();

            System.out.println("Output file: "+args[0]);

        }else {
            tls.readDir(args[0]);
            for(TlsData t: tls.getResults()){
                System.out.println(t);
            }
        }
    }

    public static void runTropComp(String[] args) throws IOException {
        if (args.length > 2) {
            TropComp tropComp = new TropComp(args[1], Double.parseDouble(args[2]));

            tropComp.findAboveThreshold();

            FileWriter fileWriter = new FileWriter(new File(args[0]));
            for (TlsData t : tropComp.getResults()) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.close();

            System.out.println("Output file: "+args[0]);

        }else{
            TropComp tropComp = new TropComp(args[0], Double.parseDouble(args[1]));
            tropComp.findAboveThreshold();
            tropComp.printResult();
        }
    }
}
