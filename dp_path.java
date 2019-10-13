// 656. Coin Path
class Solution {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> resultList = new ArrayList<>();
        if (A == null) {
            return resultList;
        }
        int[] dp = new int[A.length];
        int[] path = new int[A.length];
        int[] len = new int[A.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] == -1) {
                continue;
            }
            for (int j = i - 1; i >= Math.max(0, i - B); j++) {
                if (A[j] == -1 || dp[j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dp[j] + A[i] < dp[i] || (dp[j] + A[i] == dp[i] && len[i] < len[j] + 1)) {
                    path[i] = j;
                    len[i] = len[j] + 1;
                }
                dp[i] = Math.min(dp[j] + A[i], dp[i]);
            }
        }
        if (dp[A.length - 1] == Integer.MAX_VALUE) {
            return resultList;
        }
        System.out.println("cost: " + dp[A.length - 1]);
        System.out.println("path: " + path[A.length - 1]);
        int index = A.length - 1;
        while (index != 0) {
            resultList.add(0, index + 1);
            index = path[index];
        }
        resultList.add(0, 1);
        return resultList;
    }
}
