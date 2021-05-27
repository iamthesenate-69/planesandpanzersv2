package datatypes;

public class Troops {
        int[] troops;
        public Troops(int[] _troops) {
            troops = _troops;
        }
        public int getTroopCount(int n) {
            return troops[n];
        }
}
