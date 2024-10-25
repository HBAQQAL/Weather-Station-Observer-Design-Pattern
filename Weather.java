import java.util.ArrayList;
import java.util.List;

interface Observer {
  void update(float temperature);
}

interface Subject {
  void registerObserver(Observer o);

  void deleteObserver(Observer o);

  void notifyObservers();
}

class MobileAppObserver implements Observer {
  public float temperature;

  @Override
  public void update(float temperature) {
    this.temperature = temperature;
    System.out.println("Mobile App : Temperature is " + this.temperature);
  }

}

class WebAppObserver implements Observer {
  public float temperature;

  @Override
  public void update(float temperature) {
    this.temperature = temperature;
    System.out.println("Web App : Temperature is " + this.temperature);
  }

}

class WeatherStation implements Subject {
  private float temperature;
  private List<Observer> Observers = new ArrayList<Observer>();

  @Override
  public void registerObserver(Observer o) {
    Observers.add(o);
  }

  @Override
  public void deleteObserver(Observer o) {
    Observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : Observers) {
      observer.update(temperature);
    }
  }

  public void setTemperature(float temperature) {
    this.temperature = temperature;
  }

}

class Weather {
  public static void main(String[] args) {
    WeatherStation station = new WeatherStation();
    Observer mobileApp = new MobileAppObserver();
    Observer webApp = new WebAppObserver();
    station.registerObserver(mobileApp);
    station.registerObserver(webApp);

    station.setTemperature(24.23f);
    station.notifyObservers();
    station.setTemperature(0);
    station.notifyObservers();

  }
}