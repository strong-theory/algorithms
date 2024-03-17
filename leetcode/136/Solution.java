public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println("Result: " + s.solve(new int[] {2, 2, 1})); // should be 1
    System.out.println("Result: " + s.solve(new int[] {4, 1, 2, 2, 1})); // 4
    System.out.println("Result: " + s.solve(new int[] {0, 1, 2, 2, 1})); // 0
    System.out.println("Result: " + s.solve(new int[] {5, 4, 1, 2, 4, 3, 2, 1, 5})); // 3
  }

  public int solve(int[] nums) {
    int result = 0;
    for (var n : nums) {
      result ^= n;
    }
    return result;
  }

}