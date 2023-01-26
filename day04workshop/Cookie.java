package day04workshop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Cookie {
    private String dirPath;
    private String fileName;

    public Cookie(){}
    public Cookie(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
    }

    public String getDirPath() {
        return dirPath;
    }
    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void readCookie(List<String> cookies) throws IOException{
        
        String dirPath=this.getDirPath();
        File newDirectory =new File(dirPath);
        if(!newDirectory.exists()){
            newDirectory.mkdirs();
        }
        String fileName=this.getFileName();
        File file =new File(dirPath+File.separator+fileName);
        if (file.createNewFile()){
            System.out.println("cookie file not exist");
        }else{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String line="";

            while((line=bufferedReader.readLine())!=null){
                cookies.add(line);
            }
            System.out.println(line);
            bufferedReader.close();
        }
    }

    public String randCookie(List<String> cookie){
        String str="";
        if(cookie.isEmpty()){
            str="cookie is empty....";
        }else {
            Random rand= new Random();
            str=cookie.get(rand.nextInt(cookie.size()));
        } 
        return str;
    }
    

    public void printCookie(List<String> cookie){
        if(cookie.isEmpty()){
            System.out.println("cookie is empty....");
        }else {
            for(String str:cookie){
                System.out.println(str);
            }
        } 
    }
    
}
