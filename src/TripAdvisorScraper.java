import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TripAdvisorScraper {

    public static void main(String[] args) throws IOException {
        try{
            String url = "https://www.makemytrip.com/hotels/madurai-hotels.html";

            // Connect to the hotels page using Jsoup
            Document doc = Jsoup.connect(url).get();

            // Extract the details of each hotel
            Elements elements = doc.select("div.listingRowOuter hotelTileDt makeRelative");
            System.out.println(doc.outerHtml());
            List<String> names = new ArrayList<>();
            List<Double> ratings = new ArrayList<>();
            List<String> ratingTexts = new ArrayList<>();
            List<Integer> reviewCounts = new ArrayList<>();
            for (Element element : elements) {
                String name = element.select("p.latoBlack.font22").text();
                String ratingValueString = element.select("span[itemprop=ratingValue]").attr("content");
                Double ratingValue = ratingValueString.isEmpty() ? 0.0 : Double.parseDouble(ratingValueString);
                String ratingText = element.select("span[class^=wordBreak]").text();
                String reviewCountString = element.select("div[class^=pc__html]").text().replaceAll("[^0-9]+", "");
                Integer reviewCount = reviewCountString.isEmpty() ? 0 : Integer.parseInt(reviewCountString);
                names.add(name);
                ratings.add(ratingValue);
                ratingTexts.add(ratingText);
                reviewCounts.add(reviewCount);
            }

            // Print the details of each hotel
            for (int i = 0; i < names.size(); i++) {
                System.out.println("Name: " + names.get(i));
                System.out.println("Rating Value: " + ratings.get(i));
                System.out.println("Rating Text: " + ratingTexts.get(i));
                System.out.println("Review Count: " + reviewCounts.get(i));
                System.out.println();
            }


        }catch (Exception e) {
        e.printStackTrace();
    }
    }
}
