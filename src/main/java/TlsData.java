public class TlsData {

    private String filePath, packageName, className;
    private int tloc, tasssert;
    private double tcmp;

    public TlsData(String filePath, String packageName, String className, int tloc, int tasssert) {
        this.filePath = filePath;
        this.packageName = packageName;
        this.className = className;
        this.tloc = tloc;
        this.tasssert = tasssert;
    }

    public TlsData() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }

    public int getTasssert() {
        return tasssert;
    }

    public void setTasssert(int tasssert) {
        this.tasssert = tasssert;
    }

    public double getTcmp() {
        return tcmp;
    }

    public void setTcmp(double tcmp) {
        this.tcmp = tcmp;
    }

    public void calculateTCMP(){
        tcmp = getTasssert()== 0 ? 0 : (double)getTloc()/getTasssert();
    }
    @Override
    public String toString() {
        return "./"+filePath+", "+packageName+", "+className+", "+tloc+", "+tasssert+", "+String.format("%.2f",tcmp);
    }
}
