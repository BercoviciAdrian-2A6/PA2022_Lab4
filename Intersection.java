import java.util.ArrayList;
import java.util.Objects;

/**
 * From a graph theory standpoint, intersections represent nodes
 */
public class Intersection
{
    private String intersectionName;
    private ArrayList<Street> incidentStreets = new ArrayList<>();

    public Intersection(String intersectionName)
    {
        this.intersectionName = intersectionName;
    }

    public void addIncidentStreet( Street street)
    {
        incidentStreets.add(street);
    }

    @Override
    public String toString() {
        return intersectionName;
    }

    //in order for the hashset to work properly
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return intersectionName.equals(that.intersectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intersectionName);
    }
}
