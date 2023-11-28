public abstract class Person {


    private Hand hand;
    private String name;
    

    public Person(){

        this.hand = new Hand();
        this.name = ""; // empty string
    }

    //Setter and Getter
    public void setHand(Hand hand){
        this.hand = hand;
    }

    public Hand getHand(){
        return hand;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    
}
