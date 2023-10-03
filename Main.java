import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]){
        System.out.println("--------Start of Main--------");
        Menu menu = new Menu();

        //Ftiaxnoume to organization
        Organization.setName("To Xamogelo Tou Ceida :)");

        List<Entity> entityList=new ArrayList<Entity>();
        Organization.setEntityList(entityList);

        List<Donator> donatorList = new ArrayList<Donator>();
        Organization.setDonatorList(donatorList);

        List<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
        Organization.setBeneficiaryList(beneficiaryList);

        RequestDonationList currentDonations = new RequestDonationList();
        Organization.setCurrentDonations(currentDonations);


        //listes poy pairnoyn oi beneficiary
        List<RequestDonation> rdEntities = new ArrayList<RequestDonation>();
        Offers offersList = new Offers();

        RequestDonationList receivedList1 = new RequestDonationList();
        Requests requestsList1 = new Requests();

        RequestDonationList receivedList2 = new RequestDonationList();
        Requests requestsList2 = new Requests();


        currentDonations.setRdEntities(rdEntities);



        //Eisagwgh xrhstwn
        //Oi beneficiary pairnoyn (onoma,thlefwno,plhthos,tis lists me ta offer poy yparxoyn,request poy exei o beneficiary)
        Admin admin1= new Admin("Miltos","00");
        Organization.setAdmin(admin1);

        Donator kostas = new Donator(offersList,"Kostas","11");
        Organization.insertDonator(kostas);

        Beneficiary giannis = new Beneficiary("Giannis","21",4,receivedList1,requestsList1);
        Organization.insertBeneficiary(giannis);

        Beneficiary mitsos = new Beneficiary("Mitsos","22",4,receivedList2,requestsList2);
        Organization.insertBeneficiary(mitsos);





        //Eisagwgh materials-services
        Material sugar= new Material("Sugar","1kg bags",9991,7,8,9);
        Organization.addEntity(sugar);
        RequestDonation rdSugar = new RequestDonation((Entity)sugar,0);
        Organization.getCurrentDonations().add(rdSugar);

        Material milk= new Material("Milk","1lt. box",9990,7,8,9);
        Organization.addEntity(milk);
        RequestDonation rdMilk = new RequestDonation((Entity)milk,0);
        Organization.getCurrentDonations().add(rdMilk);

        Material rice= new Material("Rice","5 servings/package", 9992,2,5,9);
        Organization.addEntity(rice);
        RequestDonation rdRice = new RequestDonation((Entity)rice,0);
        Organization.getCurrentDonations().add(rdRice);


        Service medicalSupport = new Service("Medical Support","getting checked up by a doctor (1 visit).", 8880);
        Organization.addEntity(medicalSupport);
        RequestDonation rdMedicalSupport = new RequestDonation((Entity)medicalSupport, 0);
        Organization.getCurrentDonations().add(rdMedicalSupport );

        Service nurserySupport = new Service("Nursery Support","watching over sick people(per hour).", 8881);
        Organization.addEntity(nurserySupport);
        RequestDonation rdNurserySupport = new RequestDonation((Entity)nurserySupport,0);
        Organization.getCurrentDonations().add(rdNurserySupport);

        Service babySitting = new Service("Baby Sitting","watching over your kids(per hour).",8882);
        Organization.addEntity(babySitting);
        RequestDonation rdBabySitting = new RequestDonation((Entity)babySitting,0);
        Organization.getCurrentDonations().add(rdBabySitting);



        //Merika donations gia na einai pio eukolo to testing
        RequestDonation kostasDon1 = new RequestDonation(milk,20);
        kostas.add(kostasDon1);
        RequestDonation kostasDon2 = new RequestDonation(sugar,10);
        kostas.add(kostasDon2);
        RequestDonation kostasDon3 = new RequestDonation(rice,2);
        kostas.add(kostasDon3);
        RequestDonation kostasDon4 = new RequestDonation(babySitting,20);
        kostas.add(kostasDon4);
        kostas.commit();

        
        //Merika requests epishs gia testing
        RequestDonation giannisReq5 = new RequestDonation(milk,5);
        giannis.addReq(giannis,giannisReq5);
        RequestDonation giannisReq2 = new RequestDonation(sugar,5);
        giannis.addReq(giannis,giannisReq2);

        RequestDonation mitsosReq2 = new RequestDonation(sugar,2);
        mitsos.addReq(mitsos,mitsosReq2);
        
        //commit kai ena akoma request pou den ginetai commit
        giannis.commit(giannis);
        RequestDonation giannisReq1 = new RequestDonation(milk,1);
        giannis.addReq(giannis,giannisReq1);
        System.out.println("--------End of main--------");

        menu.RunMenu();

    }
}