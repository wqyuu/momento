单调栈 左右两边比该数大的最近的数 栈从上到下，从小到大|

无重复值：栈从上到下的数字保持规则从小到大，当有b>a，且序列为bac, 依次进入到栈中。b进入之前无数字略过，a进入判断b>a,符合规则继续，c进入判断a<c，不符合规则，
a弹出，此时a的左边最大值为b(弹出前栈中上一个数字)，右边最大值为c(当前值)。继续判断c与b大小，若c>b，b弹出左边最大值为null,右边最大值也为c。序列中无数据，最后c弹出，左最大null，右最大null。
为什么这个流程是对的，当b进入a进入时，中间如果有数字，只可能是比a小被弹出了

	| |   
    | |
小	|a|
↑   |b|
大	---

例：1024
1,0进入，
2进入，弹出0，左大1右大2.弹出1，左大null右大2;
4进入，弹出2，左大null右大4;
无数据，弹出4，左大null右大null;
栈图：
| |   | | 	| |    | |
| |   | |   | |    | |
|0|   | |	| |    | |
|1|   |2|   |4|    | |
---   --- 	---    ---


有重复值：下标压在一起

取链表最后一个值

|       |
|{2,3}  |
|{1}    |
|{0,4,6}|
—--------

Stack<linkedlist<int>>

单调栈例题：数组中累加和与最小值的乘积，假设叫指标A。给定一个数组，
返回子数组中，指标A最大的值。

[3,1,6,4,5,2]
找某个数，左右两边第一个比该数小的值，为左右两边界，例如5的边界为4,2；
3边界为null,1;  6->1,4;   4->1,2

小->大
||
|4|
|6|
|1|
—-

3 9
1 21
6 36
4 60
5 25
2 34

sum:3,4,10,14,19,21