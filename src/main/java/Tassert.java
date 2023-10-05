import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class Tassert {

    private File file;
    private ArrayList<String> assertMethods;
    private Tloc tloc;

    public Tassert(File file) {
        this.file = file;
        assertMethods = new ArrayList<>();
        tloc = new Tloc();
        findMethodNames();
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

    public int countAssert() {
        tloc.readFile(file);
        String code = tloc.getStrippedFile();

        Scanner scanner = new Scanner(code);
        String line;
        int count = 0;

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            for (String assertMethod : assertMethods) {
                if (line.contains(assertMethod)) {
                  //  System.out.println(line);
                    count++;
                    break;
                }
            }
        }
        return count;
    }


}
