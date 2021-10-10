package design.mode;

public enum SingletonByEnum {
    INSTANCE;
    public void doSometing(){
        System.out.println("Instance is created.");
    }
}
