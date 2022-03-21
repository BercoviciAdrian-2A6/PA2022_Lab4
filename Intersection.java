import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Intersection
{
    private String intersectionName;
    private List<Street> incidentStreets = new ArrayList<>();

    Intersection ( String intersectionName )
    {
        this.intersectionName = intersectionName;
    }

    public void addStreet(Street street)
    {
        incidentStreets.add(street);
    }

    public String getIntersectionName() {
        return intersectionName;
    }

    @Override
    public String toString()
    {
        String objectName = intersectionName + ": ";

        for (int i = 0; i < incidentStreets.size(); i++)
        {
            objectName += "(" + incidentStreets.get(i) + ")";

            if (i != incidentStreets.size() - 1)
                objectName += "-";
        }

        return objectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(intersectionName, that.intersectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intersectionName);
    }
}
