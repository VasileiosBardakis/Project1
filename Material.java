public class Material extends Entity
{
    private double level1,level2,level3;

    public Material(String name,String description, int id,double level1,double level2,double level3)
    {
        super(name,description,id);
        this.level1=level1;
        this.level2=level2;
        this.level3=level3;
    }

    public double returnLevel(int person)
    {
        if(person==1)
            return level1;
        else if(person>=2&&person<=4)
            return level2;
        else
            return level3;
    }

    public String getDetails(){
        return ("Material \nLevel 1 = "+level1+"\nLevel 2 = "+level2+"\nLevel 3 = "+level3);
    }
}
