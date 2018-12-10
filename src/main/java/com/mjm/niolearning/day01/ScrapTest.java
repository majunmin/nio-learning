package com.mjm.niolearning.day01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by majunmin on 2018/12/1.
 */
public class ScrapTest {

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://en.wikipedia.org/").get();
            Elements newsHeadlines = doc.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                System.out.println(headline.attr("title") + " : " + headline.absUrl("href"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
