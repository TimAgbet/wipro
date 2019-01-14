import com.wipro.webcrawler.PageLinks;

public class RunApp {

    public static void main(String args[]) {
        try {
            String filename = args[0];
            String url = args[1];

            PageLinks pageLinks = new PageLinks(url);
            pageLinks.scanLinks();
            pageLinks.process(filename);
        } catch (Exception e) {
            System.out.println("Incorrect parameters!");
            e.printStackTrace();
        }
    }
}
