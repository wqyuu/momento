package com.iflytek.biims.bhv.compute.service.impl.questionaire.environment;

public class Code02_RobotWalk {
	// N 只能在1~N范围上移动 固定参数
	// cur 当前来到的位置 可变参数
	// rest 还剩多少步要走 可变参数
	// P 最终要到的位置 固定参数
	// 函数含义：
	// 只能在1~N位置上移动，当前在cur位置上，走完rest步后，停在P位置上的方法有多少种
	public static int process1(int N,int cur,int rest,int P) {
		// 如果没有剩余步数了，并且来到了目的位置，说明之前的移动方式有效
		// 如果没有剩余步数了，并且没有来到目的位置，说明之前的移动方式无效
		if(rest==0) {
			return cur==P?1:0;
		}
		// if没中，还剩rest步要走
		
		// 如果来到了1位置，没得选，只能往右去2位置
		// 后续的过程就是来到2位置，还剩rest-1步
		if(cur==1) {
			return process1(N, 2, rest-1, P);
		}
		// 如果来到了N位置，没得选，只能往左去N-1位置
		//  后续的过程就是来到N-1位置，还剩rest-1步
		if(cur==N) {
			return process1(N, N-1, rest-1, P);
		}
		// 如果还有rest步要走，而当前的cur位置在中间，那么当前这步可以走向左，也可以走向右
		// 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
		// 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
		// 走向左、走向右是截然不同的方法，所以总方法数都要算上
		return process1(N, cur-1, rest-1, P)+process1(N, cur+1, rest-1, P);
	}
	
	public static int ways1(int N,int M,int K,int P) {
		// 参数无效直接返回0
		if(M<1 || M>N || P<1 || P>N || K<1 || N<2) {
			return 0;
		}
		// 总共N个位置，从M点出发，还剩K步，返回最终能到达P的方法数
		return process1(N, M, K, P);
	}

	// 把cur和rest的组合，返回的结果，加入到缓存中
	public static int process2(int N,int cur,int rest,int P,int[][] dp) {
		if(dp[cur][rest]!=-1) {// 不等于-1，表示已经算过，直接从缓存中拿值
			return dp[cur][rest];
		}
		if(rest==0) {
			// 返回之前先加缓存，底下都这么干
			dp[cur][rest]=cur==P?1:0;
			return dp[cur][rest];
		}
		if(cur==1) {
			dp[cur][rest]=process2(N, 2, rest-1, P,dp);
			return dp[cur][rest];
		}
		if(cur==N) {
			dp[cur][rest]=process2(N, N-1, rest-1, P,dp);
			return dp[cur][rest];
		}
		dp[cur][rest]=process2(N, cur-1, rest-1, P,dp)+process2(N, cur+1, rest-1, P,dp);
		return dp[cur][rest];
	}

	/**
	 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
	 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
	 * 返回最小距离累加和
	 *
	 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
	 * 每个值都认为是一张货币，
	 * 即便是值相同的货币也认为每一张都是不同的，
	 * 返回组成aim的方法数
	 * 例如：arr = {1,1,1}，aim = 2
	 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
	 * 一共就3种方法，所以返回3
	 *
	 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
	 * 每个值都认为是一种面值，且认为张数是无限的。
	 * 返回组成aim的方法数
	 * 例如：arr = {1,2}，aim = 4
	 * 方法如下：1+1+1+1、1+1+2、2+2
	 * 一共就3种方法，所以返回3
	 *
	 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
	 * 每个值都认为是一张货币，
	 * 认为值相同的货币没有任何不同，
	 * 返回组成aim的方法数
	 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
	 * 方法：1+1+1+1、1+1+2、2+2
	 * 一共就3种方法，所以返回3
	 *
	 * 给定5个参数，N，M，row，col，k
	 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
	 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
	 * 任何时候Bob只要离开N*M的区域，就直接死亡
	 * 返回k步之后，Bob还在N*M的区域的概率
	 * @param N
	 * @param M
	 * @param K
	 * @param P
	 * @return
	 */
	public static int ways2(int N,int M,int K,int P) {
		if(M<1 || M>N || P<1 || P>N || K<1 || N<2) {
			return 0;
		}
		// 这张二维表可以把递归所有的返回值装下
		int[][] dp=new int[N+1][K+1];
		for(int row=0; row<=N; row++) {
			for(int col=0; col<=K; col++) {
				dp[row][col]=-1;// 表示所有的参数组合都没有算过
				// 因为如果算过的话，方法数不可能小于0
			}
		}
		return process2(N, M, K, P,dp);
	}
	// N 只能在1~N范围上移动 固定参数
	// cur 当前来到的位置 可变参数
	// rest 还剩多少步要走 可变参数
	// P 最终要到的位置 固定参数
	public static int robotWalkWaysDp(int N, int M, int P, int K) {
		int dp[][] = new int[N + 1][P + 1];
		dp[K][0] = 1;
		for (int j = 1; j <= P; j++) {
			for (int i = 1; i <= N; i++) {
				if (i - 1 < 1) {
					dp[i][j] = dp[i + 1][j - 1];
				} else if (i + 1 > N) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
				}
			}
		}
		return dp[M][P];
	}
	
	public static void main(String[] args) {
		System.out.println(ways1(7, 3, 3, 2));
	}
}
