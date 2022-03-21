import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Street implements Comparable<Street>
{
    private String streetName;
    private float streetLength;
    private Intersection intersectionA, intersectionB;

    Street (String streetName, float streetLength, Intersection intersectionA, Intersection intersectionB)
    {
        this.streetName = streetName;
        this.streetLength = streetLength;
        this.intersectionA = intersectionA;
        this.intersectionB = intersectionB;
    }

    public List<Intersection> getCoveredIntersections()
    {
        List<Intersection> coveredIntersections = new ArrayList<>();

        coveredIntersections.add(intersectionA);
        coveredIntersections.add(intersectionB);

        return coveredIntersections;
    }

    public String getStreetName() {
        return streetName;
    }

    @Override
    public String toString() {
        return streetName + " -> " + streetLength + "m";
    }

    @Override
    public int compareTo(Street o)
    {
        if (this.streetLength == o.streetLength)
            return 0;

        if (this.streetLength > o.streetLength)
            return 1;

        return -1;
    }
}
