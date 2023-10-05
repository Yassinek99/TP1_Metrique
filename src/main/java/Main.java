import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Tloc tloc = new Tloc();

       //int count =tloc.readFile(new File("src/main/resources/jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java"));
     //  System.out.println(count);
//
      //  System.out.println(tloc.getStrippedFile());
        Tassert tassert = new Tassert(new File("src/main/resources/jfreechart/src/test/java/org/jfree/chart/title/TitleTest.java"));
        System.out.println(tassert.countAssert());

       // Tls tls = new Tls("src/main/resources/jfreechart/src/test/java/org/jfree/chart/title");
        //tls.readDir();

    }
}
