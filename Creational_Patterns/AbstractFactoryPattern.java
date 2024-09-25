interface Toy{
    public void getCompanyName();
    public void getPrice();
    public void getDesc();
}

interface Remote{
    public void On();
    public void Off();
    public void operate();
}

interface AbsFactory{
    public abstract Remote remote();
    public abstract Toy toy();
}

class EducationalToy implements Toy{
    @Override
    public void getCompanyName(){
        System.out.println("EDToy company");
    }

    @Override
    public void getPrice(){
        System.out.println("500 Rs");
    }

    @Override
    public void getDesc(){
        System.out.println("Educational Toy");
    }
}

class SoftToy implements Toy{
    @Override
    public void getCompanyName(){
        System.out.println("SoftToy company");
    }

    @Override
    public void getPrice(){
        System.out.println("1000 Rs");
    }

    @Override
    public void getDesc(){
        System.out.println("Soft Toy");
    }
}

class EducationalRemote implements Remote{
    @Override
    public void On(){
        System.out.println("Starting Educational Toy");
    }

    @Override
    public void Off(){
        System.out.println("Stopping Educational Toy");
    }

    @Override
    public void operate(){
        System.out.println("Using Educational Toy");
    }
}

class SoftRemote implements Remote{
    @Override
    public void On(){
        System.out.println("Starting Soft Toy");
    }

    @Override
    public void Off(){
        System.out.println("Stopping Soft Toy");
    }

    @Override
    public void operate(){
        System.out.println("Using Soft Toy");
    }
}

class EducationalFactory implements AbsFactory{
    @Override
    public EducationalRemote remote() {
        return new EducationalRemote();
    }

    @Override
    public EducationalToy toy() {
        return new EducationalToy();
    }
}

class SoftFactory implements AbsFactory{
    @Override
    public SoftRemote remote() {
        return new SoftRemote();
    }

    @Override
    public SoftToy toy() {
        return new SoftToy();
    }
}


public class AbstractFactoryPattern {
    public static void main(String[] args) {
        EducationalFactory e1 = new EducationalFactory();
        e1.toy();
        e1.toy().getCompanyName();
        e1.toy().getDesc();
        e1.toy().getPrice();
        
        e1.remote();
        e1.remote().Off();
        e1.remote().On();
        e1.remote().operate();
    }
}
