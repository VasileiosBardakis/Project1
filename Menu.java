import java.util.Scanner;
import java.lang.String;

public class Menu{
    private String username;
    private String phoneN;
    private User user;
    private int status;
    private String input1=null;
    private int input2;
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner input3 = new Scanner(System.in);
    private int counter;
    private int counter2;
    private int id;
    private RequestDonation rd;
    private Donator donator;
    private Beneficiary beneficiary;
    private double amount;
    private boolean choice;


    public void RunMenu(){
        while (1==1){
            printHeader();
            printAuthentication();


            //periptwsh pou den einai registered
            while(status==0){
                register();
            }

            //donator menu
            while(status==1){
                printDonMenu();
                performDonAction(chooseAction(1,5));
            }

            //admin menu
            while(status==2){
                printAdminMenu();
                int AdminChoice=chooseAction(1,4);
                performAdminAction(AdminChoice);
            }

            //beneficiary menu
            while(status==3){
                printBenMenu();
                int BenChoice=chooseAction(1,5);
                performBenAction(BenChoice);
            }
        }
    }

    private void printHeader(){
        System.out.println("+---------------------------------------+");
        System.out.println("|            Welcome to our             |");
        System.out.println("|           Menu Application            |");
        System.out.println("+---------------------------------------+");
    }

    private void printAuthentication(){

        System.out.println("  Authentication required...");
        System.out.println("Please type your phone number: ");
        phoneN=input.nextLine();
        status=0;

        //Anagnwrhsh tupou tou xrhsth (Donator, Beneficiary h Admin)
        for(var usCheck : Organization.getDonatorList()){
            if (usCheck.getPhone().equals(phoneN)){
                user=(User)usCheck;
                donator=usCheck;
                status=1;
                break;
            }
        }

        //Kanoume if an status==0 wste na mhn ama einai Donator (h meta admin) na mhn kanoume extra anazhthseis.
        if (status==0){
            if (Organization.getAdmin().getPhone().equals(phoneN)){
                user=(User)Organization.getAdmin();
                status=2;
            }
        }

        if (status==0){
            for(var usCheck : Organization.getBeneficiaryList()){
                if (usCheck.getPhone().equals(phoneN)){
                    user=(User)usCheck;
                    beneficiary=usCheck;
                    status=3;
                    break;
                }
            }
        }
    }

    private void register(){
        boolean choice0;
        System.out.println("You are not a registered user, would you like to register? (y/n)");
        input2=readAnswer();
        if (input2 =='n'){
            System.exit(0);
        }

        else if(input2 == 'y'){
            System.out.println("Would you like to register as a donator or as a beneficiary? \nPress 1 for donator and 2 for beneficiary. ");
            input2 = chooseAction(1,2);
            if (input2 == 1){
                choice0=false;
                while(!choice0) {
                    System.out.println("What is your name? ");
                    username = input3.nextLine();

                    //Dhmiourgeia neou donator
                    Offers offersList = new Offers();
                    Donator newDon = new Donator(offersList, username, phoneN);
                    if (Organization.insertDonator(newDon)) {
                        donator = newDon;
                        status = 1;
                        choice0 = true;
                    }
                }

            }
            else if(input2 == 2){
                choice0=false;
                while(!choice0) {
                    System.out.println("What is your name? ");
                    username = input3.nextLine();

                    //Dhmiourgeia neou beneficiary
                    RequestDonationList receivedListTemp = new RequestDonationList();
                    Requests requestsListTemp = new Requests();

                    System.out.println("How many persons are in your family group? ");
                    counter = chooseAction(1,20);

                    Beneficiary newBen = new Beneficiary(username, phoneN, counter, receivedListTemp, requestsListTemp);
                    if (Organization.insertBeneficiary(newBen)) {
                        beneficiary = newBen;
                        status = 3;
                        choice0=true;
                    }
                }
            }
        }
    }




    //Menu gia kathe eidos xrhsth!!!
    private void printDonMenu(){
        System.out.println("Welcome "+donator.getName()+", Phone : "+user.getPhone()+"     Donator to ||"+Organization.getName()+"||");
        System.out.println("Please choose your action:");
        System.out.println("1)Add Offer");
        System.out.println("2)Show Offers");
        System.out.println("3)Commit on your donations");
        System.out.println("4)Log out");
        System.out.println("5)Exit application");
    }


    private void printAdminMenu(){
        System.out.println("Welcome "+user.getName()+", Phone : "+user.getPhone()+"    Admin to ||"+Organization.getName()+"||");
        System.out.println("Please choose your action:");
        System.out.println("1)View");
        System.out.println("2)Monitor Organization");
        System.out.println("3)Log out");
        System.out.println("4)Exit application");
    }
    private void printBenMenu(){
        System.out.println("Welcome "+beneficiary.getName()+", Phone : "+user.getPhone()+"    Beneficiary to ||"+Organization.getName()+"||");
        System.out.println("Please choose your action:");
        System.out.println("1)Add Request");
        System.out.println("2)Show Requests");
        System.out.println("3)Commit on your requests");
        System.out.println("4)Log out");
        System.out.println("5)Exit application");
    }


    //Actions gia kathe eidos xrhsth
    private void performAdminAction(int AdminChoice){
        boolean choice0=false;
        boolean choice1=false;
        boolean choice2=false;
        boolean choice3=false;
        while(!choice0){
            switch(AdminChoice){
                case 1://View
                    System.out.println("Choose the type of entity:\n1) Materials\n2) Services\n3) Back");
                    switch(chooseAction(1,3)){
                        case 1://Materials
                            //tupwnei kai diavazei material
                            printMatSer("Material");
                            choice2=getMatSer("Material",counter);
                            System.out.println(choice2);
                            if(choice2){choice0=true;}//periptwsh pou epelekse na paei pisw
                            if(!choice2) {
                                System.out.println(rd.getEntity().getName() + " - " + rd.getEntity().getDetails() + "\nCurrent quantity - " + rd.getQuantity());
                            }
                            break;
                        case 2://Services
                            //tupwnei kai diavazei service
                            printMatSer("Service");
                            choice2=getMatSer("Service",counter);
                            if(choice2){choice0=true;}//periptwsh pou epelekse na paei pisw
                            if(!choice2) {
                                System.out.println(rd.getEntity().getName() + " - " + rd.getEntity().getDetails() + "\nCurrent quantity - " + rd.getQuantity());
                            }
                            choice2=true;
                            break;
                        case 3://Back
                            choice0=true;
                            break;
                        default:
                            System.out.println("An unknown error has occured.");
                            System.exit(0);
                            break;
                    }
                    break;
                case 2://Monitor Organization

                    System.out.println("Please choose an action\n1)List Beneficiaries\n2)List Donators\n3)Reset Beneficiaries\n4)Back");
                    switch(chooseAction(1,4)){
                        case 1://List Beneficiaries
                            //afou thelei arithmimenh lista kai meta thelei na epileksoume beneficiary, h listBeneficiaries() ths Organization den mas arkei.
                            counter=0;
                            for(var x:Organization.getBeneficiaryList()){
                                counter+=1;
                                System.out.println(counter+")"+x.getName());
                            }

                            //dialegei beneficiary
                            System.out.println("Please choose a beneficiary (Press "+(counter+1)+" to go back.)");
                            input2=chooseAction(1,counter+1);
                            if(input2==counter+1){choice0=true;}
                            if(input2!=counter+1) {
                                counter = 0;
                                for (var x : Organization.getBeneficiaryList()) {
                                    counter += 1;
                                    if (counter == input2) {
                                        beneficiary = x;
                                        break;
                                    }
                                }
                                choice3 = false;
                                while (choice3 == false) {
                                    System.out.println("\nPlease choose an action\n1)Print received list\n2)Clear received list\n3)Delete Beneficiary\n4)Back");
                                    switch (chooseAction(1, 4)) {
                                        case 1://Print received
                                            beneficiary.getReceivedList().monitor();
                                            break;
                                        case 2://Clear received
                                            beneficiary.getReceivedList().reset();
                                            System.out.println("Beneficiary's received list was reset!");
                                            break;
                                        case 3://Delete
                                            Organization.removeBeneficiary(beneficiary);
                                            System.out.println("Beneficiary was removed!");
                                            break;
                                        case 4://Back
                                            choice3 = true;
                                            break;

                                    }
                                }
                            }

                            break;

                        case 2://List Donators
                            counter=0;
                            for(var x:Organization.getDonatorList()){
                                counter+=1;
                                System.out.println(counter+")"+x.getName());
                            }

                            System.out.println("Please choose a donator (Press "+(counter+1)+" to go back.)");
                            input2=chooseAction(1,counter+1);
                            if(input2==counter+1){choice0=true;}
                            if(input2!=counter+1) {
                                counter = 0;
                                for (var x : Organization.getDonatorList()) {
                                    counter += 1;
                                    if (counter == input2) {
                                        donator = x;
                                        break;
                                    }
                                }
                                choice3 = false;
                                while (choice3 == false) {
                                    System.out.println("Please choose an action\n1)Print offers\n2)Delete donator\n3)Back");
                                    switch (chooseAction(1, 4)) {
                                        case 1://Print offers
                                            donator.getOffersList().monitor();
                                            break;
                                        case 2://Delete donator
                                            Organization.removeDonator(donator);
                                            System.out.println("Donator was removed!");
                                            break;
                                        case 3://Back
                                            choice3 = true;
                                            break;

                                    }
                                }
                            }

                            break;
                        case 3://Reset Beneficiaries
                            for(var x:Organization.getBeneficiaryList()){
                                x.getReceivedList().reset();
                            }
                            System.out.println("Beneficiary received lists were all reset!");
                            break;
                        case 4://Back
                            choice0=true;
                            break;
                        default:
                            System.out.println("An unknown error has occured.");
                            break;
                    }
                    break;
                case 3://Log out
                    status=0;
                    choice0=true;
                    break;
                case 4://Exit
                    input.close();
                    System.out.println("Thank you for using our application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("An unknown error has occured.");
                    System.exit(0);
                    break;

            }
        }
    }


    private void performBenAction(int BenChoice) {
        boolean checker=false;
        boolean choice0=false;
        boolean choice1;
        boolean choice2;
        choice = false;


        while(choice0==false){
            switch(BenChoice){

                case 1://Add Request
                    counter=0;
                    input1=null;
                    input2=0;
                    System.out.println("What do you want to request?\n1)Material\n2)Service\n3)Back");
                    //Periptwsh epiloghs 1)Material
                    switch(chooseAction(1,3)){
                        case 1://1) Material
                            choice1=false;
                            while(!choice1){

                                //Tupwsh twn materials
                                printMatSer("Material");
                                //Epilogh material
                                choice2=getMatSer("Material",counter);
                                if(choice2==true){break;}

                                //Epilogh prosforas
                                input1=null;
                                System.out.println(rd.getEntity().getName()+"\n"+"Description: "+rd.getEntity().getDescription()+"\nCurrent quantity:"+rd.getQuantity()+"\n"+rd.getEntity().getDetails());
                                System.out.println("\nDo you want to request this Material? (y/n)");
                                input2=readAnswer();
                                if (input2=='y')
                                {
                                    System.out.println("Please insert the amount you want to request");
                                    amount=readDouble();

                                    RequestDonation rd2 = new RequestDonation(rd.getEntity(),amount);
                                    beneficiary.addReq(beneficiary,rd2);
                                    break;
                                }
                                else if (input2=='n'){choice1=true;}
                            }
                            break;

                        case 2://1) Service
                            choice1=false;

                            while(!choice1){

                                //Tupwsh twn Services
                                printMatSer("Service");
                                //Epilogh Service
                                choice2=getMatSer("Service",counter);
                                if(choice2==true){break;}

                                //Epilogh prosforas
                                System.out.println(rd.getEntity().getName()+"\n"+"Description: "+rd.getEntity().getDescription()+"\nCurrent quantity:"+rd.getQuantity()+rd.getEntity().getDetails());
                                System.out.println("\nDo you want to request this service? (y/n)");
                                input2=readAnswer();
                                if (input2=='y')
                                {
                                    System.out.println("Please insert the amount you want to request:");
                                    amount=readDouble();
                                    RequestDonation rd2 = new RequestDonation(rd.getEntity(),amount);
                                    beneficiary.addReq(beneficiary,rd2);
                                    break;
                                }
                                else if (input2=='n'){choice1=true;}
                            }
                            break;
                        case 3://3)Back
                            choice1=true;
                            choice0=true;
                    }
                    break;
                case 2: //2)Show Requests
                    choice1=false;
                    counter2 = 0;

                    while(!choice1) {
                        counter2 = 0;
                        int tempcounter=0;
                        for (var y : beneficiary.getRequestsList().getlist()) {//tupwnei thn lista requestsList
                            counter2=counter2+1;
                            if (counter2 > 0) {
                                System.out.println(counter2 + ")" + y.getEntity().getName() + " - Quantity: " + y.getQuantity());
                                tempcounter=counter2;
                            }
                        }
                        if (tempcounter == 0) {//periptwsh pou den uparxoun requests
                            System.out.println("You have no active requests.");
                            choice1=false;
                            choice0=false;
                        }

                        System.out.print("\n");

                        //Epilogh action
                        System.out.println("What would you like to do?\n1)Choose a request.\n2)Clear requests.\n3)Commit requests.\n4)Back");
                        switch (chooseAction(1, 4)) {
                            case 1://1) Choose a request
                                counter2 = 0;
                                System.out.println("Which request do you want to choose?(Press "+(tempcounter+1)+" to go back)");
                                input2 = chooseAction(1, tempcounter + 1);

                                for (var x : beneficiary.getRequestsList().getlist()) { //Anazhthsh sthn requestList gia to request epiloghs
                                    counter2 += 1;
                                    if (counter2 == input2) {
                                        System.out.println("Which action do you want to choose?\n1)Delete request\n2)Modify request\n3)Back");
                                        switch (chooseAction(1, 3)) {
                                            case 1://Delete request
                                                beneficiary.getRequestsList().remove(x);
                                                System.out.println("Succesfully removed!");
                                                break;
                                            case 2://Modify request
                                                System.out.println("Please insert new amount");
                                                amount=readDouble();
                                                beneficiary.modifyReq(beneficiary,x,amount);
                                                break;
                                            case 3://Back
                                                break;
                                        }

                                        break; //kanei break thn for
                                    }
                                }
                                if (input2 == tempcounter + 1) {
                                    choice1 = true;
                                }

                                break;

                            case 2://2) Clear Requests
                                beneficiary.getRequestsList().reset();
                                System.out.println("Requests cleared successfully");
                                choice1=true;
                                choice0=true;
                                break;
                            case 3://3) Commit Requests
                                beneficiary.getRequestsList().commit(beneficiary);
                                System.out.println("Requests commited successfully");
                                choice1=true;
                                choice0=true;
                                break;
                            case 4://4) Back
                                choice1 = true;
                                choice0=true;
                                break;
                        }
                    }


                    break;

                case 3://Commit requests
                    beneficiary.commit(beneficiary);   //ta munimata ektupwnontai mesw tis commit() ths Requests.
                    choice0=true;
                    break;
                case 4://Logout
                    status = 0;
                    choice0=true;
                    break;
                case 5://Exit
                    input.close();
                    System.out.println("Thank you for using our application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("An unknown error has occured.");
                    System.exit(0);
                    break;

            }
        }
    }
    private void performDonAction(int DonChoice){
        boolean choice0=false;
        boolean choice1;
        boolean choice2;
        while (!choice0){
            switch(DonChoice){


                case 1://Add Offer
                    choice1=false;
                    while(!choice1) {
                        counter = 0;
                        input1 = null;
                        input2 = 0;
                        System.out.println("What do you want to donate?\n1)Material\n2)Service\n3)Back");
                        //Periptwsh epiloghs 1)Material
                        switch (chooseAction(1, 3)) {
                            case 1://1) Material

                                //Tupwsh twn materials
                                printMatSer("Material");
                                //Epilogh material
                                choice2 = getMatSer("Material", counter);
                                if (choice2) {
                                    break;
                                }

                                //Epilogh prosforas

                                input1 = null;
                                System.out.println(rd.getEntity().getName() + "\n" + "Description: " + rd.getEntity().getDescription() + "\nCurrent quantity:" + rd.getQuantity() + "\n" + rd.getEntity().getDetails());
                                System.out.println("\nDo you want to donate this Material?(y/n)");
                                input2 = readAnswer();
                                if (input2 == 'y') {
                                    System.out.println("Please insert the amount you want to donate");
                                    amount=readDouble();
                                    RequestDonation rd2 = new RequestDonation(rd.getEntity(),amount);
                                    donator.getOffersList().add(rd2);
                                    choice1 =true;
                                    choice0 = true;
                                    break;
                                }


                                break;


                            case 2://2)Service
                                //Tupwsh twn Services
                                printMatSer("Service");
                                //Epilogh Service
                                choice2 = getMatSer("Service", counter);
                                if (choice2) {
                                    break;
                                }

                                //Epilogh prosforas
                                input1 = null;
                                System.out.println(rd.getEntity().getName() + "\n" + "Description: " + rd.getEntity().getDescription() + "\nCurrent quantity:" + rd.getQuantity());
                                System.out.println("\nDo you want to donate this Service?(y/n)");
                                input2 = readAnswer();
                                if (input2 == 'y') {
                                    System.out.println("Please insert the amount of hours you want to donate");
                                    amount=readDouble();
                                    RequestDonation rd2 = new RequestDonation(rd.getEntity(),amount);
                                    donator.getOffersList().add(rd2);
                                    donator.getOffersList().add(rd2);
                                    choice1 =true;
                                    choice0 = true;
                                    break;
                                }
                                break;

                            case 3://3)Back
                                choice1 =true;
                                choice0 = true;
                                break;
                        }
                    }
                    break;
                case 2: //2)Show Offers
                    choice1=false;
                    while(!choice1){
                        counter2=0;
                        for(var y:donator.getOffersList().getlist()){
                            counter2+=1;
                            if(counter2>0){System.out.println(counter2+")"+y.getEntity().getName()+" - Quantity: "+y.getQuantity());}
                        }
                        if(counter2==0){
                            System.out.println("You have no active offers.");
                            choice1=true;
                            choice0=true;
                            break;
                        }
                        if(counter2>0) {
                            //Epilogh action
                            System.out.println("What would you like to do?\n1)Choose an offer.\n2)Clear Offers.\n3)Commit Offers.\n4)Back");
                            choice2=false;
                            while (!choice2) {
                                switch (chooseAction(1, 4)) {
                                    case 1://1) Choose an offer
                                        counter2 = 0;
                                        System.out.println("Which offer do you want to choose?(Press "+(counter2+1)+" to go back)");
                                        input2 = chooseAction(1, counter2 + 1);

                                        for (var x : donator.getOffersList().getlist()) { //Anazhthsh sthn OffersList gia to offer epiloghs
                                            counter2 += 1;
                                            if (counter2 == input2) {
                                                System.out.println("Which action do you want to choose?\n1)Delete offer\n2)Modify offer\n3)Back");
                                                switch (chooseAction(1, 3)) {
                                                    case 1://Delete offer
                                                        donator.getOffersList().remove(x);
                                                        choice2=true;
                                                        break;
                                                    case 2://Modify offer
                                                        System.out.println("Please insert new amount");
                                                        amount=readDouble();
                                                        donator.getOffersList().modify(x, amount);
                                                        choice2=true;
                                                        break;
                                                    case 3://Back
                                                        choice2=true;
                                                        break;
                                                }

                                                break; //kanei break thn for
                                            }
                                        }

                                        break;
                                    case 2://2) Clear Offers
                                        donator.getOffersList().reset();
                                        System.out.println("Offers cleared successfully");
                                        choice1=true;
                                        break;
                                    case 3://3) Commit Offers
                                        donator.getOffersList().commit();
                                        System.out.println("Offers commited successfully");
                                        choice1=true;
                                        break;
                                    case 4://4) Back
                                        choice1=true;
                                        choice2 = true;
                                        break;
                                }
                            }

                        }
                        choice2 = true;
                        choice1=true;
                        choice0=true;
                    }
                    break;

                case 3://3) Commit offers
                    donator.getOffersList().commit();
                    System.out.println("Offers commited successfully");
                    choice0=true;
                    break;


                case 4://4) Logout
                    status=0;
                    choice0=true;
                    break;

                case 5://5) Exit
                    input.close();
                    System.out.println("Thank you for using our application.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("An unknown error has occured.");
                    System.exit(0);
                    break;
            }
        }
    }



    //VOHTHITIKES METHODOI



    //kanei print lista services h materials tou organismou analoga me to String str
    private void printMatSer(String str){
        counter=0;
        for(var ent:Organization.getEntityList()) {
            if (ent.getDetails().indexOf(str)!=-1){
                counter+=1;
                if(Organization.getCurrentDonations().get(ent.getId())==null){
                    System.out.println(counter+") "+str+" - "+ent.getName()+"  (Amount: "+"0)");}
                else{System.out.println(counter+") "+str+" - "+ent.getName()+"  (Amount: "+Organization.getCurrentDonations().get(ent.getId()).getQuantity()+")");}
            }
        }
    }

    //Pairnei ena sugkekrimeno material h service apthn lista analoga me to String str kai me auto kanoume initialize to rd
    private boolean getMatSer(String str,int counter){
        boolean check=false;
        System.out.println("Which "+str+" do you want to select?(Press "+(counter+1)+" to go back.)");
        input2=chooseAction(1,counter+1);
        if(counter+1==input2){check=true;}
        //Anazhthsh sthn lista gia to item epiloghs
        counter=0;
        if(!check) {
            for (var ent : Organization.getEntityList()) {
                if (ent.getDetails().indexOf(str) != -1) {
                    counter += 1;
                    if (counter == input2) {
                        id = ent.getId();
                        rd = Organization.getCurrentDonations().get(id);
                        break;
                    }
                }
            }
        }
        return check;
    }

    //diavazei y/n
    private int readAnswer(){
        int answer=0;
        while(answer!='y' && answer!='n'){
            answer = input.next().charAt(0);
            input.nextLine();
            if(answer!='y' && answer!='n'){System.out.println("Wrong input, please try again");}
        }
        return answer;
    }

    //diavazei arithmo apo to min mexri to max
    private int chooseAction(int min, int max){
        int Action=-1;
        while(Action<min || Action>max){
            try{
                Action= Integer.parseInt(input.nextLine());
                if (Action<min || Action>max){System.out.println("Wrong input, please try again");}
            }catch(NumberFormatException ea){System.out.println("Invalid selection");}
        }
        return Action;
    }

    private double readDouble(){
        double amount=0;
        while(amount<=0){
            try {
                amount = Double.parseDouble(input.nextLine());
                if (amount <= 0) { System.out.println("Wrong input, please try again"); }
            }catch(NumberFormatException ea){System.out.println("Invalid selection");}
        }
        return amount;
    }
}