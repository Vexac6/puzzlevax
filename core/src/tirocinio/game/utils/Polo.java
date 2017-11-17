package tirocinio.game.utils;

public class Polo {

    private boolean present;
    private boolean connected;

    public Polo() {
        present = false;
        connected = false;
    }

    public void set () {present = true;}
    public void connect () {connected = true;}
    public void disconnect () {connected = false;}

    public boolean isPresent() {return present;}

    public boolean isConnected() {return connected;}
}
