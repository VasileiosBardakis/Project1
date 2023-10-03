
public class Don_Exception
{


    public static void CheckName(Donator ob) throws CustomExceptionClass{
        for(var x:Organization.getDonatorList()){
            if (ob.getName().equals(x.getName())){
                throw new CustomExceptionClass("A donator with the same name already exists!");}
        }
    }
}
