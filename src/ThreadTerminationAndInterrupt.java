import java.math.BigInteger;

public class ThreadTerminationAndInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongComputationTask(new BigInteger("10000"), new BigInteger("200000")));

        thread.start();
        Thread.sleep(100);
        thread.interrupt(); // without this interrupt call, the main thread will keep
        // waiting for the thread to finish computation and making the program hangs
    }

    private static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println(base + "^" + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;

            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Prematurely interrupted computation.");
                    result = BigInteger.ZERO;
                    break;
                } else {
                    result = result.multiply(base);
                }
            }

            return result;
        }

    }
}
