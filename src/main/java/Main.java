import java.io.File;

public class Main {

    public static void main(String[] args){
        Tloc tloc = new Tloc();

        System.out.println(tloc.readFile(new File("src/main/resources/test.java")));
    }
}
