package ingram;

/**
 * 前缀树
 *
 * @Author qywu11
 * @Date 2022/12/27 17:03
 * @Version 1.0
 */
public class TrieTree {



    public static class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode(){
            pass = 0;
            end = 0;

            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        public  void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass ++;
            int index = 0;
            for (int i = 0; i < chs.length; i ++){ // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符对应走向哪条路
                if(node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass ++;
            }
            node.end ++;
        }

        // word这个单词之前加入过几次
        public int search(String word){
            if(word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i ++){ // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符对应走向哪条路
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre字符串作为前缀的
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i ++){ // 从左往右遍历字符
                index = chs[i] - 'a'; // 由字符对应走向哪条路
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

        public void delete(String word){
            if(search(word) != 0){ // 树中加入过word，才删除
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass --;
                int index = 0;
                for (int i = 0; i < chs.length; i ++){ // 从左往右遍历字符
                    index = chs[i] - 'a'; // 由字符对应走向哪条路
                    if(--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return ;
                    }
                    node = node.nexts[index];
                }
                node.end --;
            }
        }

    }


}
