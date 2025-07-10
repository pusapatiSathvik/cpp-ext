import java.util.*;

// Node definition
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Core logic class
class Solution {

    // Step 1: Get middle of the linked list
    Node getMiddle(Node head) {
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Step 2: Reverse a linked list
    Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    // Step 3: Check if the linked list is a palindrome
    boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        Node middle = getMiddle(head);

        // Reverse the second half
        Node secondHalfStart = reverse(middle.next);
        middle.next = null; // Detach two halves

        // Compare both halves
        Node p1 = head;
        Node p2 = secondHalfStart;
        boolean isPalindrome = true;

        while (p2 != null) {
            if (p1.data != p2.data) {
                isPalindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list (optional)
        middle.next = reverse(secondHalfStart);

        return isPalindrome;
    }
}

// Main class
public class PalindromeLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] values = sc.nextLine().split(" ");

        // Build the linked list
        Node head = null;
        Node tail = null;

        for (String val : values) {
            Node newNode = new Node(Integer.parseInt(val));

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Check if it's a palindrome
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(head));
    }
}
