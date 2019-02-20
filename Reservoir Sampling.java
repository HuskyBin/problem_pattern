// 完美洗牌
// 第一个   1/n
// 第二个  (1 - 1/n) * 1/(n - 1) = 1 /n
/ ...
public int[] shuffle() {
        Random random = new Random();
        for (int i = num.length; i > 1; i--) {
            int nextRandom = random.nextInt(i);
            int temp = num[i - 1];
            num[i - 1] = num[nextRandom];
            num[nextRandom] = temp;
        }
        return num;
    }
