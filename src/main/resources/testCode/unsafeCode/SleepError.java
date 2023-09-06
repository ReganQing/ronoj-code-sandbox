public class Main {
    /**
     * 无限睡眠，阻止程序执行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        long ONE_HOUR = 60 * 60 * 1000L;
        Thread.sleep(ONE_HOUR);
        System.out.println("睡眠结束");
    }
}
