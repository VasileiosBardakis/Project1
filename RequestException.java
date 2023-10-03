import java.util.List;

public class RequestException {

    public static void RequestException(Beneficiary ben,RequestDonation ob) throws CustomExceptionClass {
        if(ob==null){throw new CustomExceptionClass("There are no donations for this object");}

        else{
            //Elegxos gia to a)
            if (ob.getQuantity()>Organization.getCurrentDonations().get(ob.getEntity().getId()).getQuantity()){
                throw new CustomExceptionClass("Organization doesn't have enough of the resource to complete.");
            }

                //Elegxos gia to b)
                if (!ben.getRequestsList().validRequestDonation(ben,ob)) {
                    throw new CustomExceptionClass("The beneficiary's request for " + ob.getEntity().getName() + ": " + ob.getQuantity() + " is not in valid bounds");
                }

        }
    }
}
