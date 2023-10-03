abstract public class Entity {
    private String name;          //onoma
    private String description;     //perigrafi
    private int id;                 //id

    public Entity(String name,String description,int id){
        this.name=name;
        this.description=description;
        this.id=id;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    //Setters


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }


    //Methods
    public String getEntityInfo(){
        return getName()+getDescription() +String.valueOf(getId());
    }

    public abstract String getDetails();

    public String toString(){
        return getEntityInfo()+getDetails();
    }


}
