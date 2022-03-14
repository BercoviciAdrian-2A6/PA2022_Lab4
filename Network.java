import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * For algorithmic reasons the terms:
 * intersection - node
 * street - edge
 * will be used interchangeably
 */

public class Network
{
    //this data structure does not allow duplicates, instead overwrites the already present element
    HashSet<Intersection> intersections = new HashSet<>();
    LinkedList<Street> streets = new LinkedList<Street>();

    Network( String inputFileName )
    {
        File inputFile = new File("src/main/java/" + inputFileName);

        try
        {
            Scanner inputScanner = new Scanner(inputFile);

            int streetsNumber = inputScanner.nextInt();

            for (int i = 0; i < streetsNumber; i++)
            {
                String streetName = inputScanner.next();
                String intersectionA = inputScanner.next();
                String intersectionB = inputScanner.next();
                float streetLength = inputScanner.nextFloat();

                Intersection interA = new Intersection(intersectionA);
                Intersection interB = new Intersection(intersectionB);

                intersections.add(interA);
                intersections.add(interB);
                //hashsets don't tolerate duplicates
                Street newStreet = new Street(streetName, streetLength, interA, interB);
                streets.add(newStreet);
            }

            Comparator<Street> streetComparator = (o1, o2) -> o1.compareTo(o2);

            streets.sort(streetComparator.reversed());

            printNetwork();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public void printNetwork()
    {
        System.out.println("INTERSECTIONS: ");
        Iterator iterator = intersections.iterator();

        while (iterator.hasNext())
        {
            System.out.println( iterator.next() );
        }

        System.out.println("\nSTREETS: ");

        for (int i = 0; i < streets.size(); i++)
        {
            System.out.println( streets.get(i) );
        }
    }

}
