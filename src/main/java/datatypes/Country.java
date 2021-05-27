package datatypes;

public class Country {
    int owner;
    Troops[] majorPower; //major power troops
    Country (int _owner, int[] _germany, int[] _us, int[] _russia, int[] _uk, int[] _japan) {
        int owner = _owner;
        majorPower = new Troops[5];
        majorPower[0] = new Troops(_germany);
        majorPower[1] = new Troops(_us);
        majorPower[2] = new Troops(_russia);
        majorPower[3] = new Troops(_uk);
        majorPower[4] = new Troops(_japan);
    }
    public Troops getMajorPower(int n) {
        return majorPower[n];
    }
}
