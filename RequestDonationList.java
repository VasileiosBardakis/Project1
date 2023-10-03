import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class RequestDonationList
{
    private int counter;
    private RequestDonation don;
    private List<RequestDonation> rdEntities = new ArrayList();
    private boolean check;

    //Methods
    public void setRdEntities(List<RequestDonation> rdEntities){this.rdEntities=rdEntities;}
    public List<RequestDonation> getlist(){return rdEntities;}

    public RequestDonation get(int id){
        for (var ent:rdEntities)
        {
            if (ent.getEntity().getId()==id)
                return ent;
        }
        return null;
    }
    public void add(RequestDonation obj){
        try{
            check=false;
            RequestDonation_ExcStructure.RequestDonationAdd(obj.getEntity());
            for (var ent:rdEntities)
            {
                if (ent.getEntity().getId()==obj.getEntity().getId()){
                    ent.setQuantity(ent.getQuantity()+obj.getQuantity());
                    check=true;
                }
            }
            if(check==false){rdEntities.add(obj);}
        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
    }
    public void remove(RequestDonation obj){
        check=false;
        //Kanei iterate thn rdEntities, vriskei to antikeimeno kai to diagrafei.
        for (Iterator<RequestDonation> iterator = rdEntities.iterator(); iterator.hasNext(); ) {
            RequestDonation don = iterator.next();
            if (don.getEntity().getId() == obj.getEntity().getId()) {
                iterator.remove();
                check = true;
                break;
            }
        }
        //Se periptwsh pou to antikeimeno pou valame san orisma den uparxei
        if (check==false){System.out.println("This entity does not exist in this list");}
    }
    public void modify(RequestDonation obj,double quantity){
        for (var ent:rdEntities)
        {
            if (ent.getEntity().getId()==obj.getEntity().getId())
                obj.setQuantity(quantity);
        }
    }
    public void monitor(){
        counter=0;
        for (var ent:rdEntities)
        {
            counter+=1;
            System.out.println("Name: "+ent.getEntity().getName()+"||"+"  Quantity:  "+ent.getQuantity());
        }
        if(counter==0){System.out.println("This list is empty!\n");}
    }
    public void reset(){

        for (Iterator<RequestDonation> iterator = rdEntities.iterator(); iterator.hasNext(); ) {
            RequestDonation don = iterator.next();
            iterator.remove();
        }
    }
}