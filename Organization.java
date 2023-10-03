import java.util.List;

public class Organization
{
    private static String name;
    private static Admin admin;
    private static List<Entity> entityList;
    private static List<Donator> donatorList;
    private static List<Beneficiary> beneficiaryList;
    private static RequestDonationList currentDonations;



    public static void setAdmin(boolean value){
        admin.setIsAdmin(value);
    }
    public static Admin getAdmin(){
        return admin;
    }



    //add & insert - remove
    public static void addEntity(Entity enti){
        try{
            ID_Exception.CheckID(enti);
            entityList.add(enti);
        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
    }

    public static void removeEntity(Entity enti){
        entityList.remove(enti);
    }

    public static boolean insertDonator(Donator dona){
        try{
            Don_Exception.CheckName(dona);
            donatorList.add(dona);
            return true;
        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
        return false;
    }
    public static void removeDonator(Donator dona){
        donatorList.remove(dona);
    }

    public static boolean insertBeneficiary(Beneficiary bene){
        try{
            Ben_Exception.CheckName(bene);
            beneficiaryList.add(bene);
            return true;
        }catch(CustomExceptionClass e){System.out.println(e.getMessage());}
        return false;
    }
    public static void removeBeneficiary(Beneficiary bene){
        beneficiaryList.remove(bene);
    }


    //Getters

    public static String getName(){return name;}

    public static List<Entity> getEntityList() {
        return entityList;
    }

    public static List<Donator> getDonatorList() {
        return donatorList;
    }

    public static List<Beneficiary> getBeneficiaryList() {
        return beneficiaryList;
    }

    public static RequestDonationList getCurrentDonations() {
        return currentDonations;
    }


    //Setters

    public static void setName(String namee){
        name=namee;
    }

    public static void setAdmin(Admin adminn) {
        admin = adminn;
    }

    public static void setEntityList(List<Entity> entityListt) {
        entityList = entityListt;
    }

    public static void setDonatorList(List<Donator> donatorListt) {
        donatorList = donatorListt;
    }

    public static void setBeneficiaryList(List<Beneficiary> beneficiaryListt) {
        beneficiaryList = beneficiaryListt;
    }

    public static void setCurrentDonations(RequestDonationList currentDonationss) {
        currentDonations = currentDonationss;
    }




    public static void listEntities(){
        System.out.println("Materials:\n");
        for (var ent:entityList){
            if (ent instanceof Material)
                System.out.println(ent.getName());
        }
        System.out.println("Services:\n");
        for (var ent:entityList){
            if (ent instanceof Service)
                System.out.println(ent.getName());
        }
    }

    public static void listBeneficiaries(){
        System.out.println("\nList of beneficiaries:\n");
        for (var ben:beneficiaryList){
            ben.getReceivedList().monitor();
        }
    }

    public static void listDonators(){
        System.out.println("List of donators:\n");
        for (var don:donatorList){
            System.out.print("\n");
            don.printName();
        }
    }
}
