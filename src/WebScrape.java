import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class WebScrape {

    public static void main(String args[])
    {

        class StockData{
            String tick;
            String sname;
            double price1;
        }
        final String url = "https://sharestobeclosed.telegraph.co.uk/indices/financials/index/MCX";
        ArrayList<StockData> a1 = new ArrayList<StockData>();
        try{
            final Document doc = Jsoup.connect(url).get();

            for(Element row: doc.select("table.tablesorter.full tr")){
                if(row.select("td:nth-of-type(1)").text().equals("") || row.select("td:nth-of-type(3)").text().equals("n/a")){
                    continue;
                }
                else{
                    final String ticker = row.select("td:nth-of-type(1)").text();
                    final String name = row.select("td:nth-of-type(2)").text();
                    final String tempPrice = row.select("td:nth-of-type(3)").text();
                    final String tempPrice1 = tempPrice.replace(",","");
                    final double price = Double.parseDouble(tempPrice1);
                    StockData s1 = new StockData();
                    s1.tick = ticker;
                    s1.sname = name;
                    s1.price1 = price;

                    a1.add(s1);

                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        for (StockData s2 : a1) {
            System.out.println(s2.tick+" "+ s2.sname+" "+s2.price1 );
        }
    }
}
