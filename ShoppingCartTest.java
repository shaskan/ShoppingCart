package JDK8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingCartTest {
    public static void main(String[] args) {

        // items amount
        int appleAmtEach = 35;
        int bananaAmtEach = 20;
        int melonAmtBuy1Get1 = 50;
        int limeAmtBuy2Get3 = 15;
        long totalAmt = 0;

        List<String> itemsCart = Arrays.asList("apple","apple","banana","banana","lime","lime","lime","melon","melon","melon");

        //find the count for each item
        Map<String,Long> countEach = itemsCart.stream().collect(Collectors.groupingBy
                (Function.identity(),Collectors.counting()));

        //calculate the amount for each item
        for(Map.Entry<String,Long> entry: countEach.entrySet()){
            if("apple".equalsIgnoreCase(entry.getKey())){
                totalAmt += appleAmtEach * entry.getValue();
            }else if("banana".equalsIgnoreCase(entry.getKey())){
                totalAmt += bananaAmtEach * entry.getValue();
            }else if("melon".equalsIgnoreCase(entry.getKey())){
                long totalMelon = entry.getValue();
                if(totalMelon == 1){
                    totalAmt += melonAmtBuy1Get1;
                }else if(totalMelon %2 == 0){
                    totalAmt += (melonAmtBuy1Get1 * entry.getValue())/2;
                }else if(totalMelon %2 != 0){
                    totalAmt += (melonAmtBuy1Get1 * (entry.getValue()-1))/2;
                    totalAmt += melonAmtBuy1Get1;
                }
            }else if("lime".equalsIgnoreCase(entry.getKey())){
                long totalLime = entry.getValue();
                if(totalLime == 1){
                    totalAmt += limeAmtBuy2Get3;
                }else if(totalLime %2 !=0){
                    totalAmt += limeAmtBuy2Get3 * (entry.getValue()-1);
                }else if(totalLime %2==0){
                    totalAmt += limeAmtBuy2Get3 * entry.getValue();
                }
            }
        }
        System.out.println("total Amt for cart items-> "+ totalAmt);
    }
}