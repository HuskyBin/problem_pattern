class Solution {
    public String rearrangeString(String s, int k) {
        if (s == null) {
            return "";
        }
        if (k == 0) {
            return s;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
            (first, second) -> (!second.getValue().equals(first.getValue()))
                        ? second.getValue() - first.getValue()
                        : first.getKey() - second.getKey()
        );
                                                             
        for (Map.Entry<Character, Integer> c : map.entrySet()) {
            queue.offer(c);
        }
        StringBuilder sb = new StringBuilder();                                                               
        while (!queue.isEmpty()) {
            List<Map.Entry<Character, Integer>> tempList = new LinkedList<>();
            int i = 0;
            for (i = 0; i < k && !queue.isEmpty(); i++) {
                Map.Entry<Character, Integer> curEntry = queue.poll();
                curEntry.setValue(curEntry.getValue() - 1);
                if (curEntry.getValue() > 0) {
                    tempList.add(curEntry);
                }
                sb.append(curEntry.getKey());
            }
            if (i < k && tempList.size() > 0) {
                return "";
            }
            for (Map.Entry<Character, Integer> c : tempList) {
                queue.offer(c);
            }
            
        }
        return sb.toString();
    }
}
