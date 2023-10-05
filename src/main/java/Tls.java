import java.io.File;

public class Tls {

    private String path;

    public Tls(String path) {
        this.path = path;
    }

    public void readDir(){
        File[] dir = new File(path).listFiles();
        for(File file: dir){
            System.out.println(file.getName());
            System.out.println(new Tassert(file).countAssert());
        }
    }
}
