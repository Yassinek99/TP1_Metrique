import java.util.*;
import java.util.stream.Collectors;

public class TropComp {

    private String path, testPath;
    private double threshold;
    private Tls tls;

    private ArrayList<Double> tcmpSorted;
    private ArrayList<Integer> tlocSorted;
    private ArrayList<TlsData> results;

    public TropComp(String path, double threshold) {
        this.path = path;
        this.threshold = threshold;
        tls = new Tls();
        results =new ArrayList<>();
        tcmpSorted = new ArrayList<>();
        tlocSorted = new ArrayList<>();
        setTestPath(path+"/src/test");

        tls.readDir(testPath);
    }

    public TropComp() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTestPath() {
        return testPath;
    }

    public void setTestPath(String testPath) {
        this.testPath = testPath;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public Tls getTls() {
        return tls;
    }

    public void setTls(Tls tls) {
        this.tls = tls;
    }

    public ArrayList<TlsData> getResults() {
        return results;
    }

    public void setResults(ArrayList<TlsData> results) {
        this.results = results;
    }

    private void sortData(){

        final int limit = (int)Math.floor(tls.getResults().size()*threshold);

        for(TlsData t: tls.getResults()){
            tcmpSorted.add(t.getTcmp());
            tlocSorted.add(t.getTloc());
        }

        Collections.sort(tcmpSorted, Collections.reverseOrder());
        Collections.sort(tlocSorted, Collections.reverseOrder());

        tcmpSorted = (ArrayList<Double>) tcmpSorted.stream().limit(limit).collect(Collectors.toList());
        tlocSorted = (ArrayList<Integer>) tlocSorted.stream().limit(limit).collect(Collectors.toList());
    }

    public void findAboveThreshold(){
        sortData();

        for(TlsData t: tls.getResults()){
            if(tcmpSorted.contains(t.getTcmp()) && tlocSorted.contains(t.getTloc())){
                results.add(t);
            }
        }
    }

    public void printResult(){
        results.stream().forEach(System.out::println);
    }


}
