

public class Ben_Exception
{

    public static void CheckName(Beneficiary ob) throws CustomExceptionClass{
        for(var x:Organization.getBeneficiaryList()){
            if (ob.getName().equals(x.getName())){
                throw new CustomExceptionClass("A Beneficiary with the same name already exists!");}
        }
    }
}
