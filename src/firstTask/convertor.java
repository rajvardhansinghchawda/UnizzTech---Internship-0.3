package firstTask;

public class convertor {
    private int from;
    private int to;
    private double amount;
    public String[] currencies = new String[4];
    public convertor(int to, int from, double amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }
    public float[] CurrencyRate = new float[4];
    
        public void Calculate() {
            switch (from){
                case 1:{
                    CurrencyRate = new float[]{1, 0.011f, 0.0093f, 0.012f};
                    break;
                }
                case 2:
                {
                      CurrencyRate = new float[]{89.19f, 1, 0.83f, 1.04f};
                    break;
                }
                case 3:
                {
                     CurrencyRate = new float[]{107.37f, 1.20f, 1, 1.26f};
                    break;
                }
                case 4:
                {
                     CurrencyRate = new float[]{85.50f, 0.96f, 0.80f, 1};
                    break;
                }

            }
            System.out.println( currencies[from-1] +" to "+ currencies[to-1]+ " is " + amount* CurrencyRate[to-1] );

                }

            }





