/**
Return Kth to Last
Loop Detection
Linked List Cycle
Reverse Linked List
*/

// 2.2: Return Kth to Last
// Implement an algorithm to find the kth to last element of a singly linked list.

// Attempt 1:
Node getKthToLastNode(Node head, int k) {
	Node currentNode = head;
	Node kthNode = head;

	while (kthNode.next != null) {
		for (int i = 0; i < k; i++) {
			kthNode = kthNode.next;
		}

		if (kthNode.next == null) {
			return currentNode;
		}

		currentNode = currentNode.next;
		kthNode = currentNode;
	}

	return null;
}

// Solution:
Node getKthToLastNode(Node head, int k) {
	Node currentNode = head;
	Node kthNode = head;

	for (int i = 0; i < k; i++) {
		if (kthNode == null) return null; // Out of bounds
		kthNode = kthNode.next;
	}

	while (kthNode.next != null) {
		currentNode = currentNode.next;
		kthNode = kthNode.next;
	}

	return currentNode;
}

// ----------------------------------------------------------------

// 2.8: Loop Detection
// Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.

// DEFN: Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list 

// EX:
// Input: A->B->C->D->E->C
// Output: C

// Attempt 1:
Node findBeginning(Node head) {
	// Create an empty array list of Nodes
	ArrayList<Node> nodes = new ArrayList<Node>();
	Node currentNode = head;

	// For every Node, if the array list contains the Node, return it; otherwise, add it to the array list and check next node
	while (currentNode.next != null) {
		if (nodes.contains(currentNode)) {
			return currentNode;
		} else {
			nodes.add(currentNode);
		}
	}

	return null;
}

// Attempt 2:
Node findBeginning(Node head) {
	Node slow = head;
	Node fast = head.next;

	while (fast.next != null) {
		if (slow == fast) {
			return slow;
		}

		fast = fast.next;

		if (slow == fast) {
			return slow;
		}

		fast = fast.next;
		slow = slow.next;
	}

	return null;
}

// ~~~ UNDERSTAND SOLUTION, IT'S WEIRD ~~~

// ----------------------------------------------------------------

// Linked List Cycle
// Given a linked list, determine if it has a cycle in it.
// Source: https://leetcode.com/problems/linked-list-cycle/

// Attempt 1:
// This works but there's a shorter way
boolean hasCycle(Node head) {
	if (head == null) {
		return false;
	}

	Node fast = head;
	Node slow = head;

	if (head.next == null) {
		return false;
	}

	if (head.next.next == null) {
		return false;
	}

	fast = head.next.next;

	while (fast.next != null && fast.next.next != null) {
		fast = fast.next.next;
		slow = slow.next;

		if (fast == slow) {
			return true;
		}
	}

	return false;
}

// Solution:
boolean hasCycle(Node head) {
	Node slow = head;
	Node fast = head;

	while (fast != null && fast.next != null) {
		slow = slow.next;
		fast = fast.next.next;

		if (fast == slow) {
			return true;
		}
	}

	return false;
}

// ----------------------------------------------------------------

// Write a program that breaks up a string of words with no spaces
// into a string with the appropriate spaces.
// Source: http://www.google.com/about/careers/lifeatgoogle/hangout-on-air-tech-interviewing.html

// Attempt 1:
String breakIntoSpaces(String source, Set<String> dictionary) {
	for (int i = 1; i < source.length; i++) {
		String left = source.subString(0, i);
		String right = source.subString(i, source.length);

		if (dictionary.contains(left) && dictionary.contains(right)) {
			return left + " " + right;
		}
	}

	return source;
}

// ~~ Solution? ~~~

// ----------------------------------------------------------------

// Reverse Linked List
// Reverse a singly linked list.
// Source: https://leetcode.com/problems/reverse-linked-list/

// Solution
ListNode reverseList(ListNode head) {
    return reverseListWithPrev(head, null);
}

// The recursive helper does two things:
// 1. reverse; 2. return new head
ListNode reverseListWithPrev(ListNode head, ListNode prev) {
    if (head == null) {
        return prev;
    }
    
    ListNode next = head.next;
    head.next = prev;
    return reverseListWithPrev(next, head);
}