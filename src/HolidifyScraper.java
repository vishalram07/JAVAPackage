import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HolidifyScraper {
    public List<String[]> getAttractionsData(String place) {
        List<String[]> attractions = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/sightseeing-and-things-to-do.html").get();
            Elements attractionList = doc.select("#attractionList .content-card");
            for (Element attraction : attractionList) {
                String dataHref = attraction.select("a").attr("data-href");
                String[] cardHeading = attraction.select("h3.card-heading").text().split("\\.");
                String ratingBadge = attraction.select("span.rating-badge").text();
                String objective = attraction.select("p.objective").text();
                String cardText = attraction.select("p.card-text").text();
                String imgSrc = attraction.select("img").attr("data-original");

                String[] attractionData = new String[6];
                attractionData[0] = cardHeading[1].trim();
                attractionData[1] = dataHref;
                attractionData[2] = ratingBadge;
                attractionData[3] = objective;
                attractionData[4] = cardText;
                attractionData[5] = imgSrc;

                attractions.add(attractionData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attractions;
    }

    public List<String[]> getRestaurantsData(String place) {
        List<String[]> restaurants = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/restaurants-places-to-eat-local-cuisine.html").get();
            Elements cards = doc.select(".card.content-card");
            for (Element card : cards) {
                String title = card.select("h3.card-heading").text();
                title = title.replaceAll("^\\d+\\.\\s+", "");
                String imageUrl = card.select("div[data-hotel-position] img").attr("data-original");
                String location = card.select("div.restaurantItems > div:nth-child(1)").text();
                String cuisine = card.select("div.restaurantItems > div:nth-child(2)").text();
                String timings = card.select("div.restaurantItems > div:nth-child(3)").text();
                String price = card.select("div.restaurantItems > div:nth-child(4)").text();
                String detailsUrl = "https://www.holidify.com/" + card.select("div.content-card-footer").attr("data-href");

                String[] RestData = new String[7];
                RestData[0] = title;
                RestData[1] = imageUrl;
                RestData[2] = location;
                RestData[3] = cuisine;
                RestData[4] = timings;
                RestData[5] = price;
                RestData[6] = detailsUrl;

                restaurants.add(RestData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurants;
    }

    public List<String[]> getHotelsData(String place) {
        List<String[]> hotels = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.holidify.com/places/" + place.toLowerCase() + "/hotels-where-to-stay.html").get();
            Elements cards = doc.select("div.card.content-card");
            for (Element card : cards) {
                String hotelName = card.select("h3.card-heading").text();
                hotelName = hotelName.replaceAll("^\\d+\\.\\s+", "");
                String imageLink = card.select("div.collection-scrollable img.card-img-top").attr("data-original");
                String dataHref1 = "https://www.holidify.com/" + card.select("a").attr("href");
                String rating = card.select("span.rating-badge b").text();
                String price = card.select("p.hotel-price span.price").text();
                String neighborhood = card.select("p.hotel-neighbourhood").text();

                String[] HotelData = new String[6];
                HotelData[0] = hotelName;
                HotelData[1] = imageLink;
                HotelData[2] = dataHref1;
                HotelData[3] = rating;
                HotelData[4] = price;
                HotelData[5] = neighborhood;

                hotels.add(HotelData);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    public static void main(String[] args) {

    }
}
