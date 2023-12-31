package org.example.msg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CharacteristicUrlHandler {
    public String getCharacteristicFromUrl(String url) {
        Document doc = null;
        try {
            //Получаем документ нужной нам страницы
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String className = "container";
        // Получаем группу объектов, обращаясь методом из Jsoup к определенному блоку
        Elements elQuote = doc.getElementsByClass(className);
        // Достаем текст из каждого объекта поочереди и добавляем в наше хранилище
        Element element = elQuote.get(0);
        elQuote = element.getElementsByClass("border border-light rounded shadow");
        element = elQuote.get(0);
        elQuote = element.getElementsByClass("p-3");
        element = elQuote.get(0);
        return element.text();

    }
}
