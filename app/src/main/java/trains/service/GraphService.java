package trains.service;

import trains.service.dto.Connection;

import java.util.List;

public interface GraphService {

    void calculateDistance(List<Connection> connections, String[] queries);

    void numberStops(List<Connection> connections, char start, char end, int maxStops, int consecutive);
}
