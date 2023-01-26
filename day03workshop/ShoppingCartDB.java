package day03workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDB {


    public static void login(String fileName,String dirPath,List<String> cart) throws IOException{
        File file =new File(dirPath+File.separator+fileName+".db");
        if (!file.exists()){
            file.createNewFile();
            System.out.println("New shopping cart created. ");
        }else{
            BufferedReader br=new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                cart.add(line);
            }
            br.close();
            System.out.println("shopping cart lodaed");
        }
    }
    

    public static void save(List<String> cart,String dirPath,String cartName) throws IOException{
        if(!cart.isEmpty()){
            FileWriter fw=new FileWriter(dirPath+File.separator+cartName+".db");
            for (int i=0;i<cart.size();i++){
                fw.write(cart.get(i));
                fw.write("\n");
            }
            fw.flush();
            System.out.println("Shopping cart list saved");
            fw.close();
        }
    }

    public static void users(String dirPath){
        File newDir=new File(dirPath);
        File[]listOfFiles=newDir.listFiles();
        if (listOfFiles.length==0){
            for (File file: listOfFiles){
                System.out.println(file.getName());
            }
        }else {
            System.out.println("no users in the shopping cart database.");
        }
        

    }
}
