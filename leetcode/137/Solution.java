public class Solution {

  public static void main(String[] args) {
    Solution s = new Solution();
    System.out.println("Result: " + s.solve(new int[] {2, 2, 1, 2})); // should be 1
    System.out.println("Result: " + s.solve(new int[] {4, 1, 1, 2, 2, 1, 2})); // 4
    System.out.println("Result: " + s.solve(new int[] {0,1, 1, 2, 1, 2, 2})); // 0
  }

  public int solve(int[] nums) {
    int ones = 0;
    int twos = 0;
    for (var n : nums) {
      ones = (ones ^n) & (~twos);
      twos = (twos ^n) & (~ones);
    }
    return ones;
  }
}
