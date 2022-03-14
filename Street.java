/**
 * From a graph theory standpoint, streets represent edges
 * An edge can only be defined by 2 nodes - 2 intersections
 */
public class Street implements  Comparable<Street>
{
    private String streetName;
    private float streetLength;
    private Intersection intersectionA, intersectionB;

    public Street(String streetName, float streetLength, Intersection intersectionA, Intersection intersectionB) {
        this.streetName = streetName;
        this.streetLength = streetLength;
        this.intersectionA = intersectionA;
        this.intersectionB = intersectionB;
    }

    public float getStreetLength() {
        return streetLength;
    }

    @Override
    public String toString() {
        return streetName + " " + streetLength + "m";
    }


    @Override
    public int compareTo(Street o)
    {
        if (o.streetLength == this.streetLength)
            return 0;

        if (o.streetLength < this.streetLength)
            return 1;

        return  -1;
    }
}
