import java.util.*;

public class LeetCode {

  public boolean isAlienSorted(String[] words, String order) {
    int[] dict = new int[26];
    for (int i = 0; i < dict.length; i++) {
      int idx = order.charAt(i) - 'a';
      dict[idx] = i;
    }
    for (int i = 0; i < words.length - 1; i++) {
      if (compare(words[i], words[i + 1], dict) > 0) {
        return false;
      }
    }

    return true;
  }

  private int compare(String word1, String word2, int[] dict) {
    int L1 = word1.length();
    int L2 = word2.length();
    int min = Math.min(L1, L2);
    for (int i = 0; i < min; i++) {
      int c1 = word1.charAt(i) - 'a';
      int c2 = word2.charAt(i) - 'a';
      if (c1 != c2) {
        return dict[c1] - dict[c2];
      }
    }
    return L1 == min ? -1 : 1;
  }

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 0, 4);
  }

  public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
    List<List<Integer>> res = new ArrayList<>();

    // If we have run out of numbers to add, return res.
    if (start == nums.length) {
      return res;
    }

    // There are k remaining values to add to the sum. The
    // average of these values is at least target / k.
    long average_value = target / k;

    // We cannot obtain a sum of target if the smallest value
    // in nums is greater than target / k or if the largest
    // value in nums is smaller than target / k.
    if (nums[start] > average_value || average_value > nums[nums.length - 1]) {
      return res;
    }

    if (k == 2) {
      return twoSum(nums, target, start);
    }

    for (int i = start; i < nums.length; ++i) {
      if (i == start || nums[i - 1] != nums[i]) {
        for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
          res.add(new ArrayList<>(List.of(nums[i])));
          res.get(res.size() - 1).addAll(subset);
        }
      }
    }

    return res;
  }

  public List<List<Integer>> twoSum(int[] nums, long target, int start) {
    List<List<Integer>> res = new ArrayList<>();
    int lo = start, hi = nums.length - 1;

    while (lo < hi) {
      int currSum = nums[lo] + nums[hi];
      if (currSum < target || (lo > start && nums[lo] == nums[lo - 1])) {
        ++lo;
      } else if (currSum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) {
        --hi;
      } else {
        res.add(Arrays.asList(nums[lo++], nums[hi--]));
      }
    }

    return res;
  }

  private ListNode mergeSort(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // split list
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    ListNode mid = slow.next;
    slow.next = null;

    // divide and get sorted lists
    ListNode left = mergeSort(head);
    ListNode right = mergeSort(mid);
    ListNode ans = new ListNode(-1);
    ListNode last = ans;

    // merge 2 lists
    while (left != null || right != null) {
      int n1 = left != null ? left.val : Integer.MAX_VALUE;
      int n2 = right != null ? right.val : Integer.MAX_VALUE;
      if (n1 < n2) {
        last.next = left;
        left = left.next;
      } else {
        last.next = right;
        right = right.next;
      }
      last = last.next;
    }
    return ans.next;
  }

  public ListNode sortList(ListNode head) {
    return mergeSort(head);
  }

  public ListNode mergeNodes(ListNode head) {
    if (head.next == null) {
      return head;
    }
    head = head.next;
    ListNode res = head;
    while (res != null && res.next != null) {
      ListNode cur = res;
      int sum = 0;
      while (cur.val != 0) {
        sum += cur.val;
        cur = cur.next;
      }
      res.val = sum;
      res.next = cur.next;
      res = res.next;
    }
    return head;
  }

  public ListNode swapPairs(ListNode head) {
    if (head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(0);
    ListNode pre = dummy, cur = head;
    dummy.next = head;
    while (cur != null && cur.next != null) {
      ListNode nextNode = cur.next;
      pre.next = nextNode;
      cur.next = nextNode.next;
      nextNode.next = cur;
      pre = cur;
      cur = cur.next;
    }
    return dummy.next;
  }

  public ListNode rotateRight(ListNode head, int k) {
    if (head.next == null) {
      return head;
    }

    int len = 1; // number of nodes
    ListNode newH, tail;
    tail = head;

    while (tail.next != null) // get the number of nodes in the list
    {
      tail = tail.next;
      len++;
    }
    tail.next = head; // circle the link
    k %= len;
    if (k > 0) {
      for (int i = 0; i < len - k; i++) {
        tail = tail.next; // the tail node is the (len-k)-th node (1st node is head)
      }
    }
    newH = tail.next;
    tail.next = null;
    return newH;
  }

  public int divide(int dividend, int divisor) {
    double res = (double) dividend / (double) divisor;
    return (int) res;
  }

  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int n = nums.length;

    int sum = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < n - 2; i++) {

      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int temp = nums[i] + nums[j] + nums[k];
        if (Math.abs(temp - target) < Math.abs(sum - target)) {
          sum = temp;
        }
        if (temp > target) {
          k--;
        } else if (temp < target) {
          j++;
        } else {
          return target;
        }
      }
    }
    return sum;
  }

  public char findTheDifference(String s, String t) {
    char c = 0;
    for (char cs : s.toCharArray()) {
      c ^= cs;
    }
    for (char ct : t.toCharArray()) {
      c ^= ct;
    }
    return c;
  }

  public String interpret(String command) {
    StringBuilder stringBuilder = new StringBuilder();
    int theLast = 0;
    for (int i = 0; i < command.length(); i++) {
      char c = command.charAt(i);
      if (c != '(' && c != ')') {
        stringBuilder.append(c);
      } else {
        if (c == '(') {
          theLast = i;
          continue;
        }
        if (i - 1 == theLast) {
          stringBuilder.append('o');
        }
      }
    }
    return stringBuilder.toString();
  }

  public String mergeAlternately(String word1, String word2) {
    int total = word1.length() + word2.length();
    char[] res = new char[total];
    int lastCharWord1 = 0;
    int lastCharWord2 = 0;
    for (int i = 0; i < total; i++) {
      if (lastCharWord1 == word1.length() || lastCharWord2 == word2.length()) {
        if (lastCharWord1 == word1.length()) {
          res[i] = word2.charAt(lastCharWord2);
          lastCharWord2++;
        } else {
          res[i] = word1.charAt(lastCharWord1);
          lastCharWord1++;
        }
      } else {
        if (i % 2 == 0 && lastCharWord1 < word1.length()) {
          res[i] = word1.charAt(lastCharWord1);
          lastCharWord1++;
          continue;
        }
        if (i % 2 != 0 && lastCharWord2 < word2.length()) {
          res[i] = word2.charAt(lastCharWord2);
          lastCharWord2++;
        }
      }
    }
    return String.copyValueOf(res);
  }

  public int[][] matrixReshape(int[][] nums, int r, int c) {
    int n = nums.length, m = nums[0].length;
    if (r * c != n * m) return nums;
    int[][] res = new int[r][c];
    for (int i = 0; i < r * c; i++) {
      res[i / c][i % c] = nums[i / m][i % m];
    }
    return res;
  }

  public int diagonalSum(int[][] mat) {
    int res = 0;
    if (mat.length > 0) {
      res = mat[0][mat.length - 1] + mat[mat.length - 1][0];
    }
    for (int i = 0; i < mat.length; i++) {
      for (int j = 0; j < mat[i].length; j++) {
        if (i == j) {
          res += mat[i][j];
        }
        if (j == mat.length - i - 1 && i > 0 && i < mat.length - 1 && i != j) {
          res += mat[i][j];
        }
      }
    }
    return res;
  }

  public int maximumWealth(int[][] accounts) {
    int richest = 0;
    for (int[] account : accounts) {
      int total = 0;
      for (int i : account) {
        total += i;
      }
      if (richest <= total) {
        richest = total;
      }
    }
    return richest;
  }

  public void moveZeroes(int[] nums) {
    int left = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[left] = nums[i];
        left++;
      }
    }

    while (left < nums.length) {
      nums[left] = 0;
      left++;
    }
  }

  public int sumOddLengthSubArrays(int[] arr) {
    int res = 0, n = arr.length;
    for (int i = 0; i < n; ++i) {
      res += ((i + 1) * (n - i) + 1) / 2 * arr[i];
    }
    return res;
  }

  public boolean checkStraightLine(int[][] coordinates) {
    // y = ax + b; a = (y2 -y1) / (x2 - x1)
    if ((coordinates[1][0] == coordinates[0][0])) {
      for (int[] coordinate : coordinates) {
        if (coordinate[0] != coordinates[0][0]) {
          return false;
        }
      }
    } else {
      double a =
          (double) (coordinates[1][1] - coordinates[0][1])
              / (double) (coordinates[1][0] - coordinates[0][0]);
      int b = coordinates[0][1] - (int) (a * coordinates[0][0]);
      for (int i = 2; i < coordinates.length; i++) {
        if (coordinates[i][1] != (int) (a * coordinates[i][0]) + b) {
          return false;
        }
      }
    }
    return true;
  }

  public int[] nextGreaterElement(int[] findNums, int[] nums) {
    Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
    Stack<Integer> stack = new Stack<>();
    for (int num : nums) {
      while (!stack.isEmpty()
          && stack.peek() < num) { // catch the element[i + 1] > element[i] when pop all element
        map.put(stack.pop(), num);
      }
      stack.push(num);
    }
    for (int i = 0; i < findNums.length; i++) {
      findNums[i] = map.getOrDefault(findNums[i], -1);
    }
    return findNums;
  }

  public List<Integer> preorder(Node root) {
    List<Integer> list = new ArrayList<>();
    if (root == null) {
      return list;
    }

    Stack<Node> stack = new Stack<>();
    stack.add(root);

    while (!stack.empty()) {
      root = stack.pop();
      list.add(root.val);
      for (int i = root.children.size() - 1; i >= 0; i--) {
        stack.add(root.children.get(i));
      }
    }

    return list;
  }

  public boolean areAlmostEqual(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    if (s1.equals(s2)) {
      return true;
    }
    int countDiff = 0;
    int firstPos = 0;
    int lastPos = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (countDiff == 0) {
          firstPos = i;
        }
        if (countDiff == 1) {
          lastPos = i;
        }
        countDiff++;
      }
    }
    if (countDiff != 2) {
      return false;
    }
    return s1.charAt(firstPos) == s2.charAt(lastPos) && s1.charAt(lastPos) == s2.charAt(firstPos);
  }

  public boolean isHappy(int n) {
    if (n == 1 || n == 7) {
      return true;
    } else if (n < 10) {
      return false;
    }
    int m = 0;
    while (n != 0) {
      int tail = n % 10;
      m += tail * tail;
      n = n / 10;
    }
    return isHappy(m);
  }

  public boolean canMakeArithmeticProgression(int[] arr) {
    Arrays.sort(arr);
    int progress = Math.abs(arr[0] - arr[1]);
    for (int i = 1; i < arr.length - 1; i++) {
      if (progress != Math.abs(arr[i] - arr[i + 1])) {
        return false;
      }
    }
    return true;
  }

  public int arraySign(int[] nums) {
    int pro = 1;
    for (int num : nums) {
      pro *= num;
      if (pro > 0) {
        pro = 1;
      } else if (pro < 0) {
        pro = -1;
      } else {
        return 0;
      }
    }
    return pro > 0 ? 1 : -1;
  }

  public boolean firstCharIs(String s, char ch) {
    return (s.length() > 0 && s.charAt(0) == ch);
  }

  public boolean isDigit(char ch) {
    return ch >= '0' && ch <= '9';
  }

  public int myAtoi(String s) {
    s = s.trim();
    int number = 0;
    int i = 0;
    int sign = firstCharIs(s, '-') ? -1 : 1;
    if (sign == -1 || firstCharIs(s, '+')) {
      i = 1;
    }
    for (; i < s.length() && isDigit(s.charAt(i)); i++) {
      long temp = (long) number * 10 + (s.charAt(i) - '0');
      if (temp > Integer.MAX_VALUE) {
        return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      number = (int) temp;
    }
    return sign * number;
  }

  public int reverse(int x) {
    int result = 0;

    while (x != 0) {
      int tail = x % 10;
      int newResult = result * 10 + tail;
      if ((newResult - tail) / 10 != result) {
        return 0;
      }
      result = newResult;
      x = x / 10;
    }

    return result;
  }

  public int maxArea(int[] height) {
    int max = 0;
    int length = height.length - 1;
    int currentPosition = 0;
    int area;
    while (length != currentPosition) {
      if (height[length] > height[currentPosition]) {
        area = height[currentPosition] * (length - currentPosition);
        currentPosition++;
      } else {
        area = height[length] * (length - currentPosition);
        length--;
      }
      max = Math.max(area, max);
    }
    return max;
  }
}
