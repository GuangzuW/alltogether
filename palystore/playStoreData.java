package palystore;

public class playStoreData {

    private String category;
    private String highestRateGame;
    private float highestRating=-100f;
    private String lowestRateGame;
    private float lowestRating=100f;
    private float totalRating=0f;
    private int count=0;

    public playStoreData(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    public float getAverageRating(){return this.totalRating/this.count;}

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHighestRateGame() {
        return highestRateGame;
    }

    public void setHighestRateGame(String highestRateGame) {
        this.highestRateGame = highestRateGame;
    }

    public float getHighestRating() {
        return highestRating;
    }

    public void setHighestRating(float highestRating) {
        this.highestRating = highestRating;
    }

    public String getLowestRateGame() {
        return lowestRateGame;
    }

    public void setLowestRateGame(String lowestRateGame) {
        this.lowestRateGame = lowestRateGame;
    }

    public float getLowestRating() {
        return lowestRating;
    }

    public void setLowestRating(float lowestRating) {
        this.lowestRating = lowestRating;
    }

    public int getCount() {
        return count;
    }

    public void add(float rating){
        totalRating+=rating;
        count++;
    }
    public void evaluate(String name,float rating){
        if(rating>this.highestRating){
            this.highestRating=rating;
            this.highestRateGame=name;
        }else if(rating <this.lowestRating){
            this.lowestRating=rating;
            this.lowestRateGame=name;
        }
    }
    

    
    
}
