import java.util.List;
import java.util.Iterator;

public class Offers extends RequestDonationList
{
    public void commit(){
        for (Iterator<RequestDonation> iterator = getlist().iterator(); iterator.hasNext();) {
            RequestDonation off = iterator.next();
            Organization.getCurrentDonations().add(off);
            iterator.remove();
        }
    }
}
