package observer;

public class ConcreteMember implements Member{

    private UndoableStringBuilder data;


    @Override
    public void update(UndoableStringBuilder usb) {
        data = usb;
    }

    public String getCurrentData(){
        if(data != null){
            return data.toString();
        }else{
            return null;
        }
    }


}