package com.wipro.webcrawler;


import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PageLinksTest {

    @Test
    public void shouldLoadPageLinks() throws MalformedURLException {

        //Given
//        URL url = new File("src/main/resources/Google.html").toURI().toURL();
        URL url = new URL("http://www.wipro.com");

        //When
        PageLinks wiproPageLinks = new PageLinks(url);
        List<Element> result = wiproPageLinks.scanLinks();

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    public void shouldExtractFullLinks() throws MalformedURLException {

        //Given
        URL url = new URL("http://www.wipro.com");

        //When
        PageLinks wiproPageLinks = new PageLinks(url);
        List<Element> results = wiproPageLinks.internalLinksOnly();

        //Then
        assertThat(results).isNotNull();

    }

    @Test
    public void shouldGetExternalLinksOnly() throws MalformedURLException {

        //Given
        URL url = new URL("http://www.wipro.com");

        //When
        PageLinks wiproPageLinks = new PageLinks(url);
        List<Element> results = wiproPageLinks.externalLinksOnly();

        //Then
        assertThat(results).isNotNull();
    }
}