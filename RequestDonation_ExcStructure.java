import java.util.List;

public class RequestDonation_ExcStructure {
    private static boolean check=false;


    public static void RequestDonationAdd(Entity ob) throws CustomExceptionClass{
        for(var x:Organization.getEntityList()){
            if (ob.getId()==x.getId()){
                check=true;}
        }
        if (check==false){
            throw new CustomExceptionClass("This entity doesn't exist in the organization's archives");
        }
    }
}

