import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
    System.out.println(Instant.parse("2023-08-30T17:00:00.000Z").atZone(ZoneId.systemDefault()).toLocalDate());
//    Map<String, String> test = new Hashtable<>();
//    Map<Integer, Integer> tst2 = new LinkedHashMap<>();
//    test.put("null", null);
//    tst2.put(null, null);
//    System.out.println(test);
//    System.out.println(tst2);
  }
}
