public class Solution {

    
// duo bfs
public int ladderLength(String beginWord, String endWord, List<String> words) {
	Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();
    Set<String> wordList = new HashSet<>(words);
    if (!wordList.contains(endWord)) {
        return 0;
    }
	int len = 1;
	int strLen = beginWord.length();
	HashSet<String> visited = new HashSet<String>();
	
	beginSet.add(beginWord);
	endSet.add(endWord);
	while (!beginSet.isEmpty()) {

		Set<String> temp = new HashSet<String>();
		for (String word : beginSet) {
			char[] chs = word.toCharArray();

			for (int i = 0; i < chs.length; i++) {
				for (char c = 'a'; c <= 'z'; c++) {
					char old = chs[i];
					chs[i] = c;
					String target = String.valueOf(chs);
					if (endSet.contains(target)) {
						return len + 1;
					}

					if (!visited.contains(target) && wordList.contains(target)) {
						temp.add(target);
						visited.add(target);
					}
					chs[i] = old;
				}
			}
		}

		beginSet = endSet;
                endSet = tmp;
		len++;
	}
	
	return 0;
}
}
