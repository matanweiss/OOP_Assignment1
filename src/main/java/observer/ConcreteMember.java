package observer;

public class ConcreteMember implements Member{

    private UndoableStringBuilder data;


    @Override
    public void update(UndoableStringBuilder usb) {
        data = usb;
    }

    public String getCurrentData(){
        return data.toString();
    }


}