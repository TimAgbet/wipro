package com.wipro.webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PageLinks {

    private URL url;

    public PageLinks(URL url) {
        this.url = url;
    }

    public PageLinks(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public List<Element> scanLinks() {
        return getLInks();
    }

    private List<Element> getLInks() {
        List<Element> allLinks = new ArrayList<Element>();
        try {
            Document pageDocument = Jsoup.connect(url.toString()).get();
            Elements pageLinks = pageDocument.select("a[href]");
            for (Element pageLink : pageLinks) {
                allLinks.add(pageLink);
                System.out.println(pageLink);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allLinks;
    }

    public List<Element> internalLinksOnly() {
        List<Element> allLinks = getLInks();

        List<Element> internalLinks = allLinks.stream()
                .filter(p -> p.attr("href")
                        .startsWith("/"))
                .collect(toList());
        return internalLinks;
    }

    public List<Element> externalLinksOnly() {
        List<Element> externalLinks = new ArrayList<>();
        try {
            Document pageDocument = Jsoup.connect(url.toString()).get();
            List<Element> pageLinks = pageDocument.select("a[href]").stream()
                    .filter(p -> p.attr("href")
                            .startsWith("http"))
                    .collect(Collectors.toList());
            for (Element pageLink : pageLinks) {
                externalLinks.add(pageLink);
                System.out.println(pageLink);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return externalLinks;
    }

    public void process(String fileName) throws IOException {
        List<Element> externalLinks = externalLinksOnly();
        printOutput(fileName, externalLinks);

        List<Element> subPages = new ArrayList<>();

        List<Element> internalLinks = internalLinksOnly();
        for (Element internalLink : internalLinks) {
            Document document = Jsoup.connect(internalLink.attr("abs:href")).get();
            Elements links = document.select("a[href]");
            subPages.addAll(links);
            printOutput(fileName, links);
        }
    }

    private void printOutput(String fileName, List<Element> dataLinks) throws IOException {

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName, true);
            for (Element dataLink : dataLinks) {
                byte[] linkString = dataLink.attr("abs:href").getBytes();
                fileOutputStream.write(linkString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }
    }
}
