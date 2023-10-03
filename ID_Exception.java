import java.util.List;

public class ID_Exception{

    public static void CheckID(Entity ob) throws CustomExceptionClass{
        for(var x:Organization.getEntityList()){
            if (ob.getId()==x.getId()){
                throw new CustomExceptionClass("An entity with the same ID already exists!");}
        }
    }
}
