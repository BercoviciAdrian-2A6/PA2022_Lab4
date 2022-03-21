public class Main
{
    public static void main(String args[])
    {
        InputGenerator inputGenerator= new InputGenerator();

        inputGenerator.generateRandomInput();

        //CityGrid cityGrid = new CityGrid("InputGraph.txt");
        CityGrid cityGrid = new CityGrid("GeneratedInput.txt");
        cityGrid.printCityGrid();
        cityGrid.kruskalMinimalSpanningTree();

    }
}
