package tirocinio.game.utils;

public class Connettore {

    private boolean present;
    private boolean connected;

    public Connettore() {
        present = false;
        connected = false;
    }

    public void set () {present = true;}
    public void connect () {if (present) connected = true;}
    public void disconnect () {if (present) connected = false;}

    public boolean isPresent() {return present;}

    public boolean isConnected() {return connected;}
}
