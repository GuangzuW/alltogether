package day01workshop;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        System.out.println("Welcome to your shopping cart.");
        Console con=System.console();
        String input = "";
        List<String> cart=new ArrayList<String>();
        while(true){
           input=con.readLine(">");
           input=input.replace(",", "");
           String[] split=input.trim().toLowerCase().split(" ");
           switch(split[0]){
            case "list":
                list(cart);
                break;
            case "add":
                add(split,cart);
                break;
            case "delete":
                delete(cart, split);
            default:
                
           }

        }
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
