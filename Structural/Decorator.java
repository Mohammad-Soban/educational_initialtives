package Structural;

interface Dress{
    public void assemble();
}

class BasicDress implements Dress {

    @Overrider
    public void assemble() {
        
        System.out.println("Basic dress features.");
    }

}

class DressDecorator implements Dress {
    protected Dress dress;

    DressDecorator(Dress c) {
        this.dress = c;
    }

    @Override
    public void assemble() {
        // TODO Auto-generated method stub
        this.dress.assemble();
    }

}

class CasualDress extends DressDecorator {
    public CasualDress(Dress c) {
        super(c);
    }

    @Override
    public void assemble() {
        // TODO Auto-generated method stub
        super.assemble();
        System.out.println("Adding casual dress features.");
    }

}

class FancyDress extends DressDecorator {
    public FancyDress(Dress c) {
        super(c);
    }

    @Override
    public void assemble() {
        // TODO Auto-generated method stub
        super.assemble();
        System.out.println("Adding fancy dress features.");
    }

}

class SportyDress extends DressDecorator {
    SportyDress(Dress c) {
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding Sporty dress features.");
    }

}

public class Decorator{
    public static void main(String[] args) {
        Dress sportyDress = new SportyDress(new BasicDress());
        sportyDress.assemble();
        System.out.println();

        Dress fancydress = new FancyDress(new BasicDress());
        fancydress.assemble();
        System.out.println();

        Dress sportyfencydress = new SportyDress(new FancyDress(new BasicDress()));
        sportyfencydress.assemble();

    }
}
