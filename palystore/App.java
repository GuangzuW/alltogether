package palystore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App {
    
    public static void main(String[] args) throws IOException {

        String dirPath="data";
        String fileName="googleplaystore.csv";

        File newDirectory=new File(dirPath);
        File file=new File(newDirectory+File.separator+fileName);

        if(!newDirectory.exists()){
            newDirectory.mkdirs();
            System.out.println("New data folder created");
        }
        if(!file.exists()){
            file.createNewFile();
            System.out.println("New csv file created");


            BufferedReader br=new BufferedReader(new FileReader("/Users/gz/Downloads/googleplaystore.csv"));
            BufferedWriter bw=new BufferedWriter(new FileWriter(file));
            String readLine="";
            while((readLine=br.readLine())!=null){
                bw.write(readLine);
                bw.newLine();
            }
            bw.flush();

            br.close();
            bw.close();   
        }

        Map<String, playStoreData> playstore = new HashMap<>();
        BufferedReader br=new BufferedReader(new FileReader(file));
        //discard first line
        String str=br.readLine();
        String[] cols;

        while((str=br.readLine())!=null){
            str=str.trim();
            //line is empty, discard
            if(str.length()<=0){
                continue;
            }
            cols=str.split(",");
            //col number <3, discard
            if(cols.length<3){
                continue;
            }

            String name=cols[0].trim();
            String category =cols[1].trim();
            String strRating=cols[2].trim();
            Float rating=0f;
            //if the rating is not a number NaN, discard
            try {
                rating = Float.parseFloat(strRating);
            } catch (NumberFormatException e) {
                continue;
            }
            //if rating is NaN discard
            if (Float.isNaN(rating)){
                continue;
            }
            //get the data of specific category if the data is null, means the category id a new category ,add new category to the map
            playStoreData data=playstore.get(category);
            if(data==null){
                data=new playStoreData(category);
                playstore.put(category, data);
            }
            data.add(rating);
            data.evaluate(name, rating);

        }
        br.close();

        System.out.println("--".repeat(10));
        for(String cat: playstore.keySet()){
            playStoreData data=playstore.get(cat);
            
            System.out.printf("Category: %s\n", cat);
            System.out.printf("\taverage rating: %.2f\n", data.getAverageRating());
            System.out.printf("\tHighest rated game: %s (%.2f)\n"
                    , data.getHighestRateGame(), data.getHighestRating());
            System.out.printf("\tLowest rated game: %s (%.2f)\n"
                    , data.getLowestRateGame(), data.getLowestRating());
            


        }
        
    }
}
