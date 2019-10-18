public class MyObject {
    private MyObject() {}

    private static class MyPrivateObject{
        private static final MyObject OBJECT = new MyObject();
    }

    public static MyObject getObject() {
        return MyPrivateObject.OBJECT;
    }
}

//    public static void main(String[] args) {
//        MyObject myObject = MyObject.getInstance();
//    }