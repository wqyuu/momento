贪心策略

最少路灯

递归

求完全二叉树节点个数

- 右树最左节点到不到最后一层
    - 到最后一层，代表左树是满的 2^左树高度 + 右树个数
    - 没到 ，右树是满的，2^右树高度 + 左树节点个数

递归上述过程↑




### 最长递增子序列

#### 方法1
arr [3,1,2,6,3,4,0]
i    0 1 2 3 4 5 6

dp  [1 1 2 3 3 4 1 ]
j    0 1 2 3 4 5 6
dp[j]  --> 子序列必须从arr[i]结尾下最长长度

O(N^2)

#### 方法2


arr   [3,2,4,5,1,7]
i      0 1 2 3 4 5


i = 0       
dp    [1 ]
j      0

ends  [3 ]    
0 1 2 3 4 5

i = 1,ends  arr[1]值为2，比ends[0]小 ，将0位置更新为2，ends里面有几个数字，就是dp里的数，如果比ends里数大，那就扩充

dp    [1 1]
j      0 1

ends  [2 ]    
0 1 2 3 4 5




i = 2 ， 4>2 扩充ends数组，[2,4]
dp    [1 1 2]
j      0 1 2

ends  [2 4 ]    
0 1 2 3 4 5

... 	   
dp    [1 1 2 3 1 4]
j      0 1 2 3 4 5

ends  [1 4 5]    
0 1 2 3 4 5


ends[i] 有效，表示所有长度为i+1的递增子序列中最小结尾



### 小Q得到一个神奇的数列： 1,12,123,...12345678910,123456789101112...

小Q得到一个神奇的数列: 1, 12, 123,…12345678910,1234567891011…。
并且小Q对于能否被3整除这个性质很感兴趣。
小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
输入描述:
输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
输出描述:
输出一个整数, 表示区间内能被3整除的数字个数。