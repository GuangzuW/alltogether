package day03workshop;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public final class App extends ShoppingCartDB{
    public static void main(String[] args) throws IOException {
        String dirPath="";
        if (args.length==0){
            dirPath="bd";
        }else {
            dirPath=args[0];
        }

        File newDirectory=new File(dirPath);
        if(!newDirectory.exists()){
            newDirectory.mkdirs();
            System.out.println("New folder created for "+dirPath);
        }else{
            System.out.println("Shopping cart folder "+dirPath+"already exist.");
        }

        System.out.println("Welcome to your shopping cart.");

        Console con=System.console();
        String input = "";
        List<String> cart=new ArrayList<String>();
        
        
        boolean stop=false;
        while(!stop){
            input=con.readLine(">");
            input=input.replace(",", "");
            String[] split=input.trim().toLowerCase().split(" ");
            if (split[0].equalsIgnoreCase("login")){
                String cartName=split[1];
                login(split[1], dirPath, cart);
                
                while(!stop){
                    input=con.readLine(">");
                    input=input.replace(",", "");
                    split=input.trim().toLowerCase().split(" ");
                    switch(split[0]){
                        case "users":
                            users(dirPath);
                            break;
                        case "save":
                            save(cart,dirPath,cartName);
                            break;
                        case "list":
                            list(cart);
                            break;
                        case "add":
                            add(split,cart);
                            break;
                        case "delete":
                            delete(cart, split);
                            break;
                        case "exit":
                            stop=true;
                            break;
                        default:    
                    }
                }
           }else {
            System.out.println("Please log in ");
           }
        }  
        System.out.println("exit shopping cart ");
    }

    public static void list(List <String> cart){
        if(cart.isEmpty()){
            System.out.println("Your cart is empty");
        }else{
            for (int i=0;i<cart.size();i++){
                System.out.println((i+1)+". "+cart.get(i));
            }
        }
    }

    public static void add (String[] split,List<String> cart){
        for (int i=1;i<split.length;i++){
            boolean isExist=false;
            for(int j=0;j<cart.size();j++){
                if(cart.get(j).equals(split[i])){
                    isExist=true;
                    System.out.println("You have "+ split[i] +" in your cart");
                    break;
                }
            }
            if(!isExist){
                cart.add(split[i]);
                isExist=false;
                System.out.println(split[i] +" added in your cart");
            }
        }
        
    }
    public static void delete(List<String> cart, String[] split){
        int index= Integer.parseInt(split[1]);
        if(index-1<cart.size()){
            String removeItem=cart.get(index-1);
            cart.remove(index-1);
            System.out.println(removeItem+" removed from cart");
        }else {
            System.out.println("Incorrect item index");
        }
        
    }
}
