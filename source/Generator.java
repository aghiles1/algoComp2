import java.util.*;

public class Generator
{
    private int binSize;
    private int numberOfItems;
    private RandomNumberGenerator generator;

    public Generator(int binSize, int numberOfItems, int generator) throws NumberException {
        this.binSize = binSize;
        this.numberOfItems = numberOfItems;
        this.generator = RandomNumberGenerator.getGeneratorFromId(generator);
        this.generator.getParam();
    }

    public List<Item> createItems()
    {
        List<Item> items = new ArrayList<>();

        int value;

        for(int i = 0; i < numberOfItems; i++)
        {
            do {
                value = generator.getRandom();
            }while (value < 0 || value > binSize);

            //System.out.println(value);
            items.add(new Item(value, i+1));
        }

        return items;
    }

    private enum RandomNumberGenerator {
        Poisson(0){
            @Override
            public void getParam() throws NumberException {
                System.out.println("lambda ? ");
                try {
                    lambda = scanner.nextDouble();
                }catch (Exception e){
                    throw new NumberException("Double");
                }

            }

            @Override
            public int getRandom(){
                double L = Math.exp(-lambda);
                int k = 0;
                double p = 1.0;
                do {
                    k++;
                    p = p * defaultR.nextDouble();
                } while (p > L);

                return (k - 1);
            }
        },
        Exponential(1){
            //OK
            @Override
            public void getParam() throws NumberException {
                System.out.println("lambda ? ");
                try {
                lambda = scanner.nextDouble();
                }catch (Exception e){
                    throw new NumberException("Double");
                }
            }
            @Override
            public int getRandom(){
                double rand = -(Math.log(defaultR.nextDouble()) / lambda);
                return (int)rand;
            }
        },
        Geometric(2){
            //OK
            @Override
            public void getParam() throws NumberException {
                System.out.println("probabilité ? ");
                try{
                proba = scanner.nextDouble();
                }catch (Exception e){
                    throw new NumberException("Double");
                }
            }
            @Override
            public int getRandom(){
                double rand = Math.log(defaultR.nextDouble()) / Math.log(1.0- proba);
                return (int)rand;
            }
        },
        Uniform(3){
            @Override
            public void getParam() throws NumberException {
                try{
                System.out.println("borne inf ? ");
                a = scanner.nextDouble();
                System.out.println("borne sup ? ");
                b = scanner.nextDouble();
                }catch (Exception e){
                    throw new NumberException("Double");
                }
            }
            @Override
            public int getRandom(){
                return (int)(a + (b - a) * defaultR.nextDouble());
            }
        },
        Constant(4){
            @Override
            public void getParam() throws NumberException {
                System.out.println("constante ?");
                try{
                constant = scanner.nextInt();
                }catch (Exception e){
                    throw new NumberException("Intger");
                }
            }
            @Override
            public int getRandom(){
                return constant;
            }
        },
        Gaussian(5){
            @Override
            public void getParam() throws NumberException {
                try{
                System.out.println("moyenne ? ");
                mean = scanner.nextDouble();
                System.out.println("variance ? ");
                deviation = scanner.nextDouble();
                }catch (Exception e){
                    throw new NumberException("Double");
                }
            }
            @Override
            public int getRandom(){
                double rand = defaultR.nextGaussian() * deviation + mean;
                return (int)rand;
            }
        };

        private int id;
        RandomNumberGenerator(int id){
            this.id = id;
        }

        public int getId(){
            return id;
        }

        public static RandomNumberGenerator getGeneratorFromId(int id){
            for(RandomNumberGenerator generator : RandomNumberGenerator.values()){
                if(generator.getId() == id)
                    return generator;
            }
            return null;
        }

        public void getParam() throws NumberException {

        }
        public int getRandom(){
            return 0;
        }
        public static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        public static final Random defaultR = new Random();
        public static double proba;
        public static double lambda;
        public static double mean;
        public static double deviation;
        public static double a;
        public static double b;
        public static int constant;
    }
}
