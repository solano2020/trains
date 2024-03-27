package trains.service.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection {
    private char start;
    private char end;
    private int distance;

    public Connection(char start, char end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public static Connection create(char start, char end, int distance){
        return new Connection(start,end,distance);
    }

    public char start() {
        return start;
    }

    public char end() {
        return end;
    }

    public int distance() {
        return distance;
    }
}
