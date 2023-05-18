import java.util.ArrayList;
import java.util.List;

// Singleton
class EasterBunny {
    private static EasterBunny instance;
    private Basket basket;

    private EasterBunny() {
        basket = new Basket();
    }

    public static EasterBunny getInstance() {
        if (instance == null) {
            instance = new EasterBunny();
        }
        return instance;
    }

    public void createAndDecorateEgg() {
        Egg egg = EggFactory.createEgg();
        DecoratedEgg decoratedEgg = new StickerDecorator(new PaintDecorator(egg));
        basket.addEgg(decoratedEgg);
    }

    public void addObserver(Observer observer) {
        basket.addObserver(observer);
    }
}

// Factory
abstract class Egg {
    // ...
}

class QuailEgg extends Egg {
    // ...
}

class ChickenEgg extends Egg {
    // ...
}

class DinosaurEgg extends Egg {
    // ...
}

class EggFactory {
    public static Egg createEgg() {
        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                return new QuailEgg();
            case 1:
                return new ChickenEgg();
            case 2:
                return new DinosaurEgg();
            default:
                return null;
        }
    }
}

// Decorator
abstract class DecoratedEgg extends Egg {
    protected Egg egg;

    public DecoratedEgg(Egg egg) {
        this.egg = egg;
    }
}

class StickerDecorator extends DecoratedEgg {
    public StickerDecorator(Egg egg) {
        super(egg);
    }

    // Additional sticker decoration methods
}

class PaintDecorator extends DecoratedEgg {
    public PaintDecorator(Egg egg) {
        super(egg);
    }

    // Additional paint decoration methods
}

// Observer
interface Observer {
    void update();
}

class Chick implements Observer {
    @Override
    public void update() {
        System.out.println("Яйцата са готови");
    }
}

class Basket {
    private List<DecoratedEgg> eggs;
    private List<Observer> observers;

    public Basket() {
        eggs = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addEgg(DecoratedEgg egg) {
        eggs.add(egg);
        if (eggs.size() > 5) {
            notifyObservers();
        }
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

public class EasterEggMaker {
    public static void main(String[] args) {
        EasterBunny bunny = EasterBunny.getInstance();
        Chick chick1 = new Chick();
        bunny.addObserver(chick1);
        Chick chick2 = new Chick();
        bunny.addObserver(chick2);
        Chick chick3 = new Chick();
        bunny.addObserver(chick3);

        for (int i = 0; i < 11; i++) {
            bunny.createAndDecorateEgg();
        }
    }
}
