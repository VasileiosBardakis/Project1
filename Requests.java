import java.util.List;
import java.util.Iterator;

public class Requests extends RequestDonationList
{
    private double Quantity;
    private double Quantity2;
    private RequestDonation don;
    private boolean check;


    public boolean validRequestDonation(Beneficiary ben,RequestDonation don)
    {
        Quantity2=0;

        if(don.getEntity().getDetails().indexOf("Material")!=-1)
        {
            //Periptwsh pou einai sthn requestsList kai receivedList
            if(ben.getRequestsList().get(don.getEntity().getId())!=null && ben.getReceivedList().get(don.getEntity().getId())!=null)
            {
                //Periptwsh pou to antikeimeno pou vrhkame sthn requestsList den einai to idio me to don ara to prosthetoume
                if(ben.getRequestsList().get(don.getEntity().getId())!=don) {
                    Quantity2 = ben.getRequestsList().get(don.getEntity().getId()).getQuantity();
                }
                Quantity=Quantity2 + ben.getReceivedList().get(don.getEntity().getId()).getQuantity() + don.getQuantity();
            }//Periptwsh pou einai sthn requestsList alla oxi sthn receivedList
            else if(ben.getRequestsList().get(don.getEntity().getId())!=null)
            {
                //Periptwsh pou to antikeimeno pou vrhkame sthn requestsList den einai to idio me to don ara to prosthetoume
                if(ben.getRequestsList().get(don.getEntity().getId())!=don) {
                    Quantity2 = ben.getRequestsList().get(don.getEntity().getId()).getQuantity();
                }
                Quantity= Quantity2+ don.getQuantity();
            }//Periptwsh pou den einai se kamia
            else
                Quantity=don.getQuantity();


            if(Quantity>((Material)don.getEntity()).returnLevel(ben.getPersons()))
                return false;
            else
                return true;



        }
        else
            return true;
    }

    public void add(Beneficiary ben,RequestDonation obj){
        try{
            //xrhsh exception

            RequestException.RequestException(ben, obj);
            ben.getRequestsList().add(obj);
            System.out.println("Request added successfully");
        }
        catch(CustomExceptionClass e){System.out.println(e.getMessage());}
    }



    public void modify(Beneficiary ben,RequestDonation obj,double quantity){
        try {
            check = false;
            Quantity2 = obj.getQuantity();

            if (obj == null) {
                System.out.println("You do not have an active request for this Entity.");
            } else {
                //xrhsh exception
                RequestException.RequestException(ben, obj);
                check = true;
                ben.getRequestsList().modify(obj, quantity);
                System.out.println("Successful modification!");

                if (!check) {
                    obj.setQuantity(Quantity2);
                }
            }
        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
    }

    public void commit(Beneficiary ben){
        Iterator<RequestDonation> iterator = ben.getRequestsList().getlist().iterator();
        try {
                while(iterator.hasNext()) {
                    RequestDonation don = iterator.next();
                    RequestException.RequestException(ben,don);
                    Organization.getCurrentDonations().modify(Organization.getCurrentDonations().get(don.getEntity().getId()), Organization.getCurrentDonations().get(don.getEntity().getId()).getQuantity() - don.getQuantity());
                    ben.add(don);
                    System.out.println("Your request for " + don.getEntity().getName() + " and its quantity of " + don.getQuantity() + " has been fulfilled!");
                    iterator.remove();
            }


        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
    }
}