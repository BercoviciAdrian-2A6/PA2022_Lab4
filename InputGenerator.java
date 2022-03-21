import com.github.javafaker.Faker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputGenerator
{
    class VirtualIntersection
    {
        private String intersectionName;
        private float positionX, positionY;

        public VirtualIntersection(String intersectionName, float positionX, float positionY) {
            this.intersectionName = intersectionName;
            this.positionX = positionX;
            this.positionY = positionY;
        }

        public static Double getDistance(VirtualIntersection A, VirtualIntersection B)
        {
            return Math.sqrt(
                    Math.pow(A.positionX - B.positionX, 2) + Math.pow(A.positionY - B.positionY, 2)
            );
        }

        public String getIntersectionName() {
            return intersectionName;
        }

        @Override
        public String toString() {
            return intersectionName + " - (" + positionX +  ", " + positionY + ")";
        }
    }

    public void generateRandomInput()
    {
        Random random = new Random();
        int intersectionsNumber = random.nextInt(16) + 16;
        //generate between 16 and 32 intersections

        List<VirtualIntersection> intersections = new ArrayList<>();

        Faker faker = new Faker();

        for (int i = 0; i < intersectionsNumber; i++)
        {
            String intersectionName ="IT_" +  faker.address().buildingNumber();
            //String intersectionName = faker.address().streetName();
            float randX = random.nextFloat(100);
            float randY = random.nextFloat( 100);

            intersections.add( new VirtualIntersection( intersectionName, randX , randY) );
        }

        try {
            BufferedWriter inputWriter = new BufferedWriter(new FileWriter("src/main/java/" + "GeneratedInput.txt"));

            boolean completeGraph = true;

            if (completeGraph)
            {
                for (int i = 0; i < intersections.size() - 1; i++)
                {
                    for (int j = i + 1; j < intersections.size(); j++)
                    {
                        inputWriter.write( "ST_" + faker.address().streetAddress().replace(" ", "_") + " "
                                + intersections.get(i).getIntersectionName() + " " +  intersections.get(j).getIntersectionName() + " " +
                                VirtualIntersection.getDistance( intersections.get(i), intersections.get(j) ) + "\n");
                    }
                }
            }

            inputWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
