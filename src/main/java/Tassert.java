import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Tassert {

    private File file;
    private ArrayList<String> assertMethods;
    private Tloc tloc;
    private int nbAsserts;

    public Tassert(File file) {
        this.file = file;
        assertMethods = new ArrayList<>();
        tloc = new Tloc();
        findMethodNames();
        nbAsserts =0;
    }

    public int getNbAsserts() {
        return nbAsserts;
    }

    public void setNbAsserts(int nbAsserts) {
        this.nbAsserts = nbAsserts;
    }

    public Tloc getTloc() {
        return tloc;
    }

    public void setTloc(Tloc tloc) {
        this.tloc = tloc;
    }

    private void findMethodNames() {
        ArrayList<String> methodsToRemove = new ArrayList<>();

        for (Method m : Main.class.getMethods()) {
            methodsToRemove.add(m.getName());
        }

        for (Method m : org.junit.Assert.class.getMethods()) {

            if (!methodsToRemove.contains(m.getName())) {
                if (!assertMethods.contains(m.getName())) {
                    assertMethods.add(m.getName());
                }
            }
        }
    }

    public void countAssert() {
        tloc.readFile(file);
        String code = tloc.getStrippedFile();

        Scanner scanner = new Scanner(code);
        String line;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (String assertMethod : assertMethods) {
                if (line.contains(assertMethod)) {
                  //  System.out.println(line);
                    nbAsserts++;
                    break;
                }
            }
        }
    }


}
