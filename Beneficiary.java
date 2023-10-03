public class Beneficiary extends User
{
    private int noPersons=1;
    private RequestDonationList receivedList;
    private Requests requestsList;


    //Constructor
    public Beneficiary(String name, String phone,int noPersons, RequestDonationList receivedList,Requests requestsList){
        super(name,phone);
        this.noPersons=noPersons;
        this.receivedList=receivedList;
        this.requestsList=requestsList;

    }

    //Wrapper methodoi
    public void add(RequestDonation obj){ receivedList.add(obj);}
    public void remove(RequestDonation obj){
        receivedList.remove(obj);
    }
    public void modify(RequestDonation obj,double quantity){receivedList .modify(obj,quantity); }
    public void addReq(Beneficiary ben,RequestDonation obj){requestsList.add(ben,obj);}
    public void modifyReq(Beneficiary ben,RequestDonation obj, double quantity){
        requestsList.modify(ben,obj,quantity);
    }

    public void commit(Beneficiary ben){
        requestsList.commit(ben);
    }

    //Setters
    public void setPersons(int persons){this.noPersons=persons;}
    public void setReceivedList(RequestDonationList receivedList){this.receivedList=receivedList;}
    public void setRequestsList(Requests requestsList){this.requestsList=requestsList;}

    //Getters
    public RequestDonationList getReceivedList(){return receivedList;}
    public Requests getRequestsList(){return requestsList;}
    public int getPersons(){return noPersons;}
}
