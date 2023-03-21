import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikitravelScraper {
    public static void main(String[] args) throws IOException {
        try {
            String url = "https://wikitravel.org/en/Madurai";


            // Fetch the HTML of the page
            Document doc = Jsoup.connect(url).get();

            // Create empty arrays for each section
            ArrayList<String> eatSection = new ArrayList<>();
            ArrayList<String> sleepSection = new ArrayList<>();
            ArrayList<String> seeSection = new ArrayList<>();
            ArrayList<String> otherSitesSection = new ArrayList<>();

            // Find the "Eat", "Sleep", "See" and "Other sites" sections by their IDs
            Element eatHeading = doc.getElementById("eat");
            Element sleepHeading = doc.getElementById("sleep");
            Element seeHeading = doc.getElementById("see");
            Element otherSitesHeading = doc.getElementById("other sites");

            // Extract the content from each section and store it in the corresponding array
            if (eatHeading != null) {
                Elements eatContent = eatHeading.nextElementSibling().select("li");
                for (Element e : eatContent) {
                    eatSection.add(e.text());
                }
            }

            if (sleepHeading != null) {
                Elements sleepContent = sleepHeading.nextElementSibling().select("li");
                for (Element e : sleepContent) {
                    sleepSection.add(e.text());
                }
            }

            if (seeHeading != null) {
                Elements seeContent = seeHeading.nextElementSibling().select("li");
                for (Element e : seeContent) {
                    seeSection.add(e.text());
                }
            }

            if (otherSitesHeading != null) {
                Elements otherSitesContent = otherSitesHeading.nextElementSibling().select("li");
                for (Element e : otherSitesContent) {
                    otherSitesSection.add(e.text());
                }
            }

            // Print the results
            System.out.println("Eat section: " + eatSection);
            System.out.println("Sleep section: " + sleepSection);
            System.out.println("See section: " + seeSection);
            System.out.println("Other sites section: " + otherSitesSection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


