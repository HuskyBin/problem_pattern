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

// stream流中的平等样本抽取算法
// 第一个 1 
// 第二个 1 / 2
// 第三个   1/2 * 2/3
// 第四个 1/2*2/3*3/4

public int pick(int target) {
    int total = 0;
    int res = 0;
    for (int i = 0; i < nums.length; i++) {
       if (nums[i] == target) {
            total++;
            int ran = random.nextInt(total);
            if (ran == 0) {
                res = i;
            }
        }
    }
    return res;
}
