package com.mommoo.level3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 여행경로 {
    public static void main(String[] args) {
        String[][] tickets = new String[][]{
                new String[]{"ICN", "JFK"},
                new String[]{"HND", "IAD"},
                new String[]{"JFK", "HND"}
        };
        System.out.println(Arrays.toString(new 여행경로().solution(tickets)));

        tickets = new String[][]{
                new String[]{"ICN", "SFO"},
                new String[]{"ICN", "ATL"},
                new String[]{"SFO", "ATL"},
                new String[]{"ATL", "ICN"},
                new String[]{"ATL", "SFO"}
        };
        System.out.println(Arrays.toString(new 여행경로().solution(tickets)));

        tickets = new String[][]{
                new String[]{"ICN", "COO"},
                new String[]{"COO", "ICN"},
                new String[]{"ICN", "COO"}
        };
        System.out.println(Arrays.toString(new 여행경로().solution(tickets)));

        tickets = new String[][]{
                new String[]{"ICN", "COO"},
                new String[]{"ICN", "BOO"},
                new String[]{"COO", "ICN"},
                new String[]{"BOO", "DOO"},
        };
        System.out.println(Arrays.toString(new 여행경로().solution(tickets)));
    }

    private static Stack<AirportPath> createAirportPaths(Tickets tickets) {
        String firstDeparture = "ICN";

        Stack<AirportPath> airportPaths = new Stack<>();
        for (Ticket firstTicket : tickets.findTickets(firstDeparture)) {
            airportPaths.push(AirportPath.first(firstTicket));
        }

        return airportPaths;
    }

    public String[] solution(String[][] stringTickets) {
        int ticketCount = stringTickets.length;

        Tickets tickets = Tickets.from(stringTickets);
        Stack<AirportPath> airportPaths = createAirportPaths(tickets);
        AirportPathDecider airportPathDecider = new AirportPathDecider(ticketCount);

        while (!airportPaths.isEmpty()) {
            AirportPath airportPath = airportPaths.pop();
            if (airportPath.isEqualUseTicketCount(ticketCount)) {
                airportPathDecider.add(airportPath);
                continue;
            }

            String nextDeparture = airportPath.getNextDeparture();
            List<Ticket> nextTickets = tickets.findTickets(nextDeparture);
            for (AirportPath nextAirportPath : airportPath.nextAirportPath(nextTickets)) {
                airportPaths.push(nextAirportPath);
            }
        }

        return airportPathDecider.computeProperPath();
    }

    private static class Tickets {
        private Map<String, List<Ticket>> ticketFinder = new HashMap<>();

        public Tickets(List<Ticket> tickets) {
            tickets.forEach(ticket -> ticketFinder.computeIfAbsent(ticket.departure, key -> new LinkedList<>())
                                                  .add(ticket));
        }

        public static Tickets from(String[][] tickets) {
            return new Tickets(IntStream.range(0, tickets.length)
                                        .mapToObj(idx -> Ticket.from(idx, tickets[idx]))
                                        .collect(Collectors.toList()));
        }

        public List<Ticket> findTickets(String departure) {
            List<Ticket> tickets = ticketFinder.get(departure);
            if (tickets == null) {
                return Collections.emptyList();
            } else {
                return Collections.unmodifiableList(tickets);
            }
        }
    }

    private static class Ticket {
        private final int index;
        private final String departure;
        private final String destination;

        public Ticket(int index, String departure, String destination) {
            this.index = index;
            this.departure = departure;
            this.destination = destination;
        }

        private static Ticket from(int index, String[] ticket) {
            return new Ticket(index, ticket[0], ticket[1]);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Ticket))
                return false;
            Ticket ticket = (Ticket) o;
            return index == ticket.index &&
                   Objects.equals(departure, ticket.departure) &&
                   Objects.equals(destination, ticket.destination);
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, departure, destination);
        }
    }

    private static class AirportPath {
        private final Set<Ticket> useTickets;
        private final List<String> visitOrders;

        public AirportPath(Set<Ticket> useTickets, List<String> visitOrders) {
            this.useTickets = useTickets;
            this.visitOrders = visitOrders;
        }

        public static AirportPath first(Ticket ticket) {
            Set<Ticket> useTicket = new HashSet<>();
            useTicket.add(ticket);

            List<String> visitOrders = new LinkedList<>();
            visitOrders.add(ticket.departure);
            visitOrders.add(ticket.destination);

            return new AirportPath(useTicket, visitOrders);
        }

        public boolean isEqualUseTicketCount(int ticketCount) {
            return this.useTickets.size() == ticketCount;
        }

        public String getNextDeparture() {
            return visitOrders.get(visitOrders.size() - 1);
        }

        public List<AirportPath> nextAirportPath(List<Ticket> nextTickets) {
            return nextTickets.stream()
                              .filter(ticket -> !useTickets.contains(ticket))
                              .map(this::next)
                              .collect(Collectors.toList());
        }

        public String getVisitAirport(int order) {
            return visitOrders.get(order);
        }

        private AirportPath next(Ticket ticket) {
            Set<Ticket> useTickets = new HashSet<>(this.useTickets);
            useTickets.add(ticket);

            List<String> visitOrders = new LinkedList<>(this.visitOrders);
            visitOrders.add(ticket.destination);

            return new AirportPath(useTickets, visitOrders);
        }
    }

    private static class AirportPathDecider {
        private final int totalTicketCount;
        private AirportPath properAirportPath;

        public AirportPathDecider(int totalTicketCount) {
            this.totalTicketCount = totalTicketCount;
        }

        public void add(AirportPath airportPath) {
            if (properAirportPath == null) {
                properAirportPath = airportPath;
                return;
            }

            properAirportPath = computeProperAirportPath(airportPath);
        }

        private AirportPath computeProperAirportPath(AirportPath another) {
            for (int order = 0; order < totalTicketCount; order++) {
                String visitAirport = this.properAirportPath.getVisitAirport(order);
                String anotherVisitAirport = another.getVisitAirport(order);
                int compare = visitAirport.compareTo(anotherVisitAirport);
                if (compare < 0) {
                    return properAirportPath;
                }

                if (compare > 0) {
                    return another;
                }
            }

            return properAirportPath;
        }

        public String[] computeProperPath() {
            return properAirportPath.visitOrders.toArray(String[]::new);
        }
    }
}
