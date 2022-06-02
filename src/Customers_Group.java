public class Customers_Group {

    // ID of customers
    private int group_id;

    // Number of people in this group of customers
    private int group_size;


    // This is an entity class that presents a group of customers.
    public Customers_Group(int group_id, int group_size){
        this.group_id = group_id;
        this.group_size = group_size;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getGroup_size() {
        return group_size;
    }

    public void setGroup_size(int group_size) {
        this.group_size = group_size;
    }
}