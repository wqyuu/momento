package ingram.DCodeRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author qywu11
 * @Date 2024/1/16 18:01
 * @Version 1.0
 */
public class Array20240116 {
	
	// 977.有序数组的平方
	public int[] sortedSquares(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int[] ans = new int[right + 1];
		int p = right;
		while(left <= right){
			if(Math.abs(nums[left]) > Math.abs(nums[right])){
				ans[p--] = nums[left] * nums[left];
				left ++;
			}else{
				ans[p--] = nums[right] * nums[right];
				right --;
			}
		}
		return ans;
    }


    // 76.最小数组
    // [1,2,3,7,1,8] 8
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int total = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            total += nums[right];
            while (total>=s){
                min = Math.min(min,right - left + 1);
                total -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 53. 最大子数组和
     * 中等
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     *
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
        return sum;
    }

    /**
     * 189. 轮转数组
     * 中等
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    static Map<Character,Integer> posMap = new HashMap<>();
    static Map<Character,Integer> oriMap = new HashMap<>();
    /**
     * 209
     * @param s [a,v,d,c,b,s,a,r,c,t,b,o,a,f,c,b,l,a,q,q,w,b,c]
     * @param t [a,b,c]
     * @return
     */
    public static String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < t.length(); i++) {
            char cur = t.charAt(i);
            oriMap.put(cur,oriMap.getOrDefault(cur,0)+1);
        }

        List<Character> characterList = new ArrayList<>();
        List<Integer> postList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(t.indexOf(cur)!=-1){
                characterList.add(cur);
                postList.add(i);
                posMap.put(cur,posMap.getOrDefault(cur,0)+1);
            }

            while (check()){
                int j = postList.get(0);
                StringBuffer w = new StringBuffer();
                int len = i - j + 1;
                if(len < min){
                    int start = j;
                    while (start <= i){
                        w.append(s.charAt(start++));
                    }
                    min = len;
                    sb = w;
                }
                posMap.put(characterList.get(0),posMap.getOrDefault(characterList.get(0),0)-1);
                postList.remove(postList.get(0));
                characterList.remove(characterList.get(0));
            }
        }

        return sb.toString();

    }

    private static boolean check(){
        boolean flag = true;
        for (Character oKey: oriMap.keySet()){
            int oNum = oriMap.get(oKey);
            if(posMap.containsKey(oKey)){
                int pNum = posMap.get(oKey);
                if(pNum<oNum){
                    flag = false;
                }
            }else {
                flag = false;
            }
        }
        return flag;
    }


    /**
     * 二分 左闭右开区间
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return -1;
    }

    /**
     *35. 搜索插入位置
     * 简单
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     *
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     */
    public int searchInsert(int[] nums, int target) {
        int i = 0 ;
        int j = nums.length;

        while (i<j){
            int mid = (j - i)/2 +i;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target){
                j = mid;
            }else{
                i = mid + 1;
            }
        }

        return i;
    }

    /**
     * 33. 搜索旋转排序数组
     * 中等
     * 整数数组 nums 按升序排列，数组中的值 互不相同 。
     * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
     * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     *
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 中等
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     * 示例 2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int s = 0;
        int e = nums.length;
        while (s<e){
            int midv = (e - s)/2 +s;
            if(nums[midv] == target) {
                int[] res = new int[2];
                res[0] = midv;
                res[1] = midv;
                int i = midv-1;
                while (i>=0 && nums[i] == target){
                    res[0] = i;
                    i --;
                }
                int j = midv+1;
                while (j< nums.length && nums[j] == target){
                    res[1] = j;
                    j ++;
                }
                return res;
            } else if(nums[midv] > target){
                e = midv;
            }else{
                s = midv + 1;
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * 中等
     * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
     * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
     * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
     * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
     * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
     * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
     *
     * 示例 1：
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
     *
     * 示例 2：
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 3 次得到输入数组。
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else {
                low = pivot + 1;
            }
        }
        return nums[low];

    }

    /**
     * 26. 删除有序数组中的重复项
     * 简单
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
     * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。
     * nums 的其余元素与 nums 的大小不重要。
     * 返回 k 。
     *
     * 判题标准:
     * 系统会用下面的代码来测试你的题解:
     * int[] nums = [...]; // 输入数组
     * int[] expectedNums = [...]; // 长度正确的期望答案
     *
     * int k = removeDuplicates(nums); // 调用
     *
     * assert k == expectedNums.length;
     * for (int i = 0; i < k; i++) {
     *     assert nums[i] == expectedNums[i];
     * }
     * 如果所有断言都通过，那么您的题解将被 通过。
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }


    /**
     * 27. 移除元素
     * 简单
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 说明:
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * 你可以想象内部操作如下:
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     *
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     *     print(nums[i]);
     * }
     *
     * 示例 1：
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // 快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    /**
     * 283. 移动零
     * 简单
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 示例 1:
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int i = 0 ;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != 0){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i ++;
            }
        }
    }

    /**
     * 11. 盛最多水的容器  时间复杂度 O(N)
     * 已解答
     * 中等
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     *
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     *
     * 思路：设两指针 iii , jjj ，指向的水槽板高度分别为 h[i]h[i]h[i] , h[j]h[j]h[j] ，此状态下水槽面积为 S(i,j)S(i, j)S(i,j) 。
     * 由于可容纳水的高度由两板中的 短板 决定，因此可得如下 面积公式 : S(i,j)=min(h[i],h[j])×(j−i).
     * 初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }
}
