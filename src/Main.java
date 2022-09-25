public class Main {
  public static void main(String[] args) {
//    int[] test = {0, 1, 5, 0, 1, 5, 5, -4};
    //    int[][] test2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    //    String a = "ab";
    //    String b = "bat";
    //    System.out.println(divide(-2147483648, -1));
//    LeetCode leetCode = new LeetCode();
//    ListNode node =
//        new ListNode(
//            0,
//            new ListNode(
//                2, new ListNode(3, new ListNode(0, new ListNode(5, new ListNode(0, null))))));
    //    System.out.println(leetCode.fourSum(test, 11));

    long a = 0;
    long current = System.currentTimeMillis();
    for(int i = 0; i < 2000000000; i++) {
      a += i;
    }
    System.out.println(a);
    System.out.println(System.currentTimeMillis() - current);
    }
}
