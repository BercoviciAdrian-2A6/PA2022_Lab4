import java.io.File;
import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;

public class CityGrid
{
    HashMap<String,Intersection> intersections = new HashMap<>();
    //as far as I understand it, HashSets are used exclusively to check for duplicates
    //which makes them ill-suited when you have to navigate through the structure
    List<Street> streets = new LinkedList<>();
    //contains all the streets sorted ascending-ly by length
    Comparator<Street> streetComparator = (o1, o2) -> o1.compareTo(o2);

    CityGrid ( String fileName )
    {
        File inputFile = new File("src/main/java/" + fileName);

        HashSet<String> addedStreetNames = new HashSet<>();
        //incorporated hashset requirement...

        try
        {
            Scanner inputScanner = new Scanner(inputFile);

            while (inputScanner.hasNext())
            {
                String streetName = inputScanner.next();

                String intersectionNameA = inputScanner.next();

                String intersectionNameB = inputScanner.next();

                float streetLength = inputScanner.nextFloat();

                //duplicate street names are not allowed

                if ( addedStreetNames.contains(streetName) )
                    continue;

                addedStreetNames.add(streetName);

                Intersection newIntersectionA, newIntersectionB;

                if (intersections.containsKey( intersectionNameA ))
                    newIntersectionA = intersections.get(intersectionNameA);
                else {
                    newIntersectionA = new Intersection(intersectionNameA);
                    intersections.put( newIntersectionA.getIntersectionName(), newIntersectionA );
                }

                if (intersections.containsKey( intersectionNameB ))
                    newIntersectionB = intersections.get(intersectionNameB);
                else {
                    newIntersectionB = new Intersection(intersectionNameB);
                    intersections.put( newIntersectionB.getIntersectionName(), newIntersectionB );
                }

                Street newStreet = new Street(streetName, streetLength,newIntersectionA, newIntersectionB);
                streets.add(newStreet);
                newIntersectionA.addStreet(newStreet);
                newIntersectionB.addStreet(newStreet);
            }

            //streets.sort(streetComparator);

            streets = streets.stream().sorted( streetComparator ).collect(Collectors.toList());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public HashSet<Street> kruskalMinimalSpanningTree()
    {
        //HOMEWORK
        //Connecting all intersections with cables such that a minimal amount of cable is used and no intersections are left disconnected is equivalent to
        //generating the minimap spanning tree (MST)
        //this method implements Kruskal's algorithm
        //https://www.youtube.com/watch?v=Yo7sddEVONg
        //brush up on Kurskal's algorithm ^

        HashSet<Intersection> mstNodes = new HashSet<>();
        HashSet<Street> mstEdges = new HashSet<>();

        //the streets are already sorted by length
        for (int i = 0; i < streets.size(); i++)
        {
            List<Intersection> coveredIntersections = streets.get(i).getCoveredIntersections();

            boolean createsCycle = true;

            for (int j = 0; j < coveredIntersections.size(); j++)
            {
                if (!mstNodes.contains( coveredIntersections.get(j) ))
                {
                    createsCycle = false;
                    break;
                }
            }

            if (!createsCycle)
            {
                mstNodes.add(coveredIntersections.get(0));
                mstNodes.add(coveredIntersections.get(1));
                mstEdges.add(streets.get(i));
            }
        }

        System.out.println("\nMST: ");

        Iterator iterator = mstEdges.iterator();

        while (iterator.hasNext())
        {
            System.out.println( iterator.next() );
        }

        return mstEdges;

    }

    public List<Intersection> primmMinimalSpanningTree()
    {
        return null;
    }


    public void metricTravelingSalesmanProblem()
    {
        //https://www.youtube.com/watch?v=M5UggIrAOME&t=522s
        //this is clearly an instance of the traveling salesman problem
        //all the clues (solution being at least half as good as the optimum, triangle rule applies, generating the MST) indicate towards MetricTSP algorithm
        //one thing that was not confirmed is that this algorithm requires a complete graph to work...


    }

    public void printCityGrid()
    {
        for (int i = 0; i < streets.size(); i++)
        {
            System.out.println(streets.get(i));
        }

        System.out.println("\nIntersections: ");

        for (String key : intersections.keySet())
        {
            System.out.println( intersections.get(key) );
        }
    }


}
