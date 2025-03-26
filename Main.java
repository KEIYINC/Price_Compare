package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // WebDriver'ı tanımla
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\umutk\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Ürün adı ve URL'leri tanımla
        String productName = "iPhone";
        String[] urls = {
                "https://www.n11.com/arama?q=iphone+15",
                "https://www.akakce.com/arama/?q=iphone+15",
                "https://www.hepsiburada.com/ara?q=iphone+15"
        };

        // Web driver'ı başlat
        WebDriver driver = new ChromeDriver();

        List<Double> prices = new ArrayList<>();

        for (String url : urls) {
            driver.get(url);
            WebElement priceElement;
            if (url.equals("https://www.n11.com/arama?q=iphone+15")) {
                priceElement = driver.findElement(By.xpath("//span[@class='newPrice cPoint priceEventClick']/ins"));
            } else if (url.equals("https://www.akakce.com/arama/?q=iphone+15")) {
                priceElement = driver.findElement(By.xpath("//span[@class='pt_v8']"));
            } else if (url.equals("https://www.hepsiburada.com/ara?q=iphone+15")) {
                priceElement = driver.findElement(By.xpath("//div[@data-test-id='price-current-price']"));
                String priceText = priceElement.getText();
            } else {
                priceElement = null;
            }
            String priceText = priceElement.getText();
            double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));
            prices.add(price);
        }

        driver.quit();

        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        double totalPrice = 0;


        for (double price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (price > maxPrice) {
                maxPrice = price;
            }
            totalPrice += price;
        }

        double averagePrice = totalPrice / prices.size();

        System.out.println("En düşük fiyat: " + minPrice+"TL");
        System.out.println("En yüksek fiyat: " + maxPrice+"TL");
        System.out.println("Ortalama fiyat: " + averagePrice+"TL");
    }
}
