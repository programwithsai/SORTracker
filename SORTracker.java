import java.util.TreeSet;

class SORTracker {

    class Location implements Comparable<Location> {
        private final String name;
        private final int score;
        
        Location(String name, int score) {
            this.name = name;
            this.score = score;
        }
        
        @Override
        public int compareTo(Location other) {
            return (score != other.score) ?
                other.score - score : name.compareTo(other.name);
        }
    }
    
    private final TreeSet<Location> locations = new TreeSet<>();
    private Location nextBest;
    
    public SORTracker() {
    }
    
    public void add(String name, int score) {
        Location newLocation = new Location(name, score);
        locations.add(newLocation);
        
        if (nextBest != null) {
            if (nextBest.compareTo(newLocation) > 0) {
                Location oneAbove = locations.lower(nextBest);
                if (oneAbove != null) {
                    nextBest = oneAbove;
                }
            }
        } else {
            nextBest = locations.last();
        }
    }
    
    public String get() {
        String nextBestName = nextBest.name;
        
        nextBest = locations.higher(nextBest);
        return nextBestName;
    }
}
