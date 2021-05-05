package com.equestrian.clubs.job;

import com.equestrian.clubs.model.Clubs;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.equestrian.clubs.service.ClubsService;

import java.io.IOException;

@Component
public class ParseTask {

    @Autowired
    ClubsService clubsService;

    @Scheduled(fixedDelay = 300000)
    public void parseNewClubs() {
        clubsService.clearall(); //очищаем бд
        System.out.println("Data Base clear");
        String url = "https://realty.yandex.ru/moskva/kupit/kvartira/?from=main_menu";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();
            Elements clubs = doc.getElementsByClass("OffersSerpItem__title"); //Заголовок
            Elements clubs1 = doc.getElementsByClass("OffersSerpItem__address"); //Адрес
            Elements clubs2 = doc.getElementsByClass("MetroStation__title"); //Метро
            int size = clubs.size();
            for (int i = 0; i < size; i++){
                String title = clubs.get(i).ownText();
                String section = clubs1.get(i).ownText();
                String subway = clubs2.get(i).ownText();
                if (!clubsService.isExist(title)){
                    Clubs obj = new Clubs();
                    obj.setTitle(title);
                    obj.setSection(section);
                    obj.setSubway(subway);

                    clubsService.save(obj);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
