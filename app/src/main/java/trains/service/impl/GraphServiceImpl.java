package trains.service.impl;

import trains.service.GraphService;
import trains.service.dto.Connection;

import java.util.List;
import java.util.Optional;

public class GraphServiceImpl implements GraphService {

    @Override
    public void calculateDistance(List<Connection> connections, String[] queries) {
        for (int i = 0; i < queries.length; i++) {
            int distance = processDistance(connections, queries[i]);
            if (distance != -1) {
                System.out.println("Output #" + (i + 1) + ": " + distance);
            } else {
                System.out.println("Output #" + (i + 1) + ": NO SUCH ROUTE");
            }
        }
    }

    @Override
    public void numberStops(List<Connection> connections, char start, char end, int maxStops, int consecutive) {
        int totalRoutes = 0;
        String startTown = String.valueOf(start);
        String endTown = String.valueOf(end);

        for (Connection connection : connections) {
            if (String.valueOf(connection.start()).equals(startTown)) {
                totalRoutes += countRoutes(connections, String.valueOf(connection.end()), endTown, maxStops - 1, 1, consecutive);
            }
        }

        System.out.println("Output # " + totalRoutes);
    }

    private int countRoutes(List<Connection> connections, String currentTown, String endTown, int maxStops, int currentStops, int consecutive) {
        if (currentStops > maxStops) {
            return 0;
        }

        if (currentTown.equals(endTown) && currentStops == consecutive) {
            return 1;
        }

        int routes = 0;

        for (Connection connection : connections) {
            if (String.valueOf(connection.start()).equals(currentTown)) {
                routes += countRoutes(connections, String.valueOf(connection.end()), endTown, maxStops, currentStops + 1, consecutive);
            }
        }

        return routes;
    }



    private int processDistance(List<Connection> connections, String route) {
        int totalDistance = 0;
        String[] towns = route.split("-");
        for (int i = 0; i < towns.length - 1; i++) {
            char currentTown = towns[i].charAt(0);
            char nextTown = towns[i + 1].charAt(0);
            Optional<Connection> connection = connections.stream()
                    .filter(c -> c.start() == currentTown && c.end() == nextTown)
                    .findFirst();
            if (connection.isPresent()) {
                totalDistance += connection.get().distance();
            } else {
                return -1; // No existe la ruta
            }
        }
        return totalDistance;
    }
}
