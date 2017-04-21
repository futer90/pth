/**
 * Created by admin on 14.04.2017.
 */
public class Item {
    int id;
    String message;
    Item(int id,String message){
        this.id=id;
        this.message=message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
