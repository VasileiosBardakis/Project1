import java.util.List;

public class Donator extends User {
    private Offers offersList;
    //Constructor
    Donator(Offers offersList,String name, String phone){
        super(name,phone);
        this.offersList=offersList;
    }

    public void printName(){
        System.out.println(getName());
    }
    public Offers getOffersList(){return offersList;}

    //Wrapper methods
    public void add(RequestDonation obj){
        offersList.add(obj);
    }
    public void remove(RequestDonation obj){
        offersList.remove(obj);
    }
    public void modify(RequestDonation obj,double quantity){
        offersList.modify(obj,quantity);
    }
    public void reset(){
        offersList.reset();
    }
    public void monitor(){
        offersList.monitor();
    }
    public void commit(){
        offersList.commit();
    }
}

