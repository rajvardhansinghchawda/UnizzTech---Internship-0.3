package firstTask;

public class convertor {
    private int from;
    private int to;
    private double amount;
    public String[] currenies = new String[4];
    public convertor(int to, int from, double amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public void setCurrenies(String[] currenies) {
        this.currenies = currenies;
    }
    public float[] arrMultiple = new float[4];



        public void Calculate() {
            switch (from){
                case 1:{
                    arrMultiple = new float[]{1, 0.011f, 0.0093f, 0.012f};
                    break;
                }
                case 2:
                {
                      arrMultiple = new float[]{89.19f, 1, 0.83f, 1.04f};
                    break;
                }
                case 3:
                {
                     arrMultiple = new float[]{107.37f, 1.20f, 1, 1.26f};
                    break;
                }
                case 4:
                {
                     arrMultiple = new float[]{85.50f, 0.96f, 0.80f, 1};
                    break;
                }

            }
            System.out.println( currenies[from-1] +" to "+ currenies[to-1]+ " is " + amount*arrMultiple[to-1] );

                }

            }





