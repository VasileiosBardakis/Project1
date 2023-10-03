
public class RequestDonation
{
    private double quantity;
    private Entity entity;
    public RequestDonation(Entity entity, double quantity){
        this.quantity=quantity;
        this.entity=entity;
    }
    public double getQuantity(){return quantity;}
    public Entity getEntity(){return entity;}
    public void setEntity(Entity entity){this.entity=entity;}
    public void setQuantity(double quantity){this.quantity=quantity;}
}
