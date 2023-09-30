package org.example.msg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class UrlHandler {
    public String getHoroscopeFromUrl(String url) {
        Document doc = getDocument(url);
        String className = "contain mt-3";
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

    public String getCharacteristicFromUrl(String url) {
        return getDocument(url).getElementsByClass("container").stream()
                .filter(element -> !element.getElementsByTag("h4").isEmpty())
                .findFirst()
                .map(element -> element.getElementsByClass("p-3").stream()
                        .map(subElement -> subElement.text())
                        .findFirst()
                        .orElse("элемент не найден")
                )
                .orElse("элемент не найден")
                ;
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
