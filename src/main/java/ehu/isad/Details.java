package ehu.isad;

import java.util.Arrays;

public class Details {
    String[] publishers;
    String number_of_pages;
    String title;
    String subtitle;

    public String getTitle() {
        if(subtitle!=null){ // liburu batzutan azpititulua dago
            return title + ": " + subtitle;
        }
        return title;
    }

    public String getNumber_of_pages() {
        return number_of_pages;
    }

    public String getPublishers() {
        String pubs=publishers[0];
        for (int i = 1; i < publishers.length; i++) {
            pubs = pubs + ", " + publishers[i];
        }
        return pubs;
    }

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }
}