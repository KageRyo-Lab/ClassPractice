package week08.week08_3;

public class week08_3 {
    public static void main(String args[]){
        Anemonefish anemonefish = new Anemonefish("尼莫");
        Humon humon = new Humon("賈斯汀");
        Shark shark = new Shark("蘭尼");
        Submarine submarine = new Submarine("黃色一號");
        Piranha piranha = new Piranha("一隻魚");

        anemonefish.swim();
        shark.swim();
        humon.swim();
        submarine.swim();
        piranha.swim();
    }
}
