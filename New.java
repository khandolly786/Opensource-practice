import java.util.*;

class Solution {

    public static int minXOR(int n, int k, int[] arr) {
        // Calculate the initial XOR of all elements in the array
        int currentXOR = 0;
        for (int num : arr) {
            currentXOR ^= num;
        }

        // If current XOR is already 0, no operations are needed
        if (currentXOR == 0) {
            return 0;
        }

        // Try to reduce the XOR by performing up to `k` operations
        for (int i = 0; i < k; i++) {
            boolean reduced = false;

            // Check each bit from most significant to least significant
            for (int bit = 31; bit >= 0; bit--) {
                // If the bit is set in currentXOR, try to unset it by modifying an element in arr
                if ((currentXOR & (1 << bit)) != 0) {
                    for (int j = 0; j < n; j++) {
                        if ((arr[j] & (1 << bit)) != 0) {
                            // Flip the bit in arr[j] to try to reduce the overall XOR
                            int newNum = arr[j] ^ (1 << bit);
                            int newXOR = currentXOR ^ arr[j] ^ newNum;

                            // Check if this operation reduced the XOR
                            if (newXOR < currentXOR) {
                                currentXOR = newXOR;
                                arr[j] = newNum;
                                reduced = true;
                                break; // Exit the loop once a reduction is made
                            }
                        }
                    }
                }
                if (reduced) break; // Exit if we made a reduction in this iteration
            }
            
            // If no further reduction is possible, break out of the loop
            if (!reduced) {
                break;
            }
        }

        // Return the minimized XOR result
        return currentXOR;
    }

    // Main function to test the minXOR method
    public static void main(String[] args) {
        int[] arr = {3, 8, 2, 5};
        int n = arr.length;
        int k = 2;
        System.out.println(minXOR(n, k, arr)); // Expected output depends on the array and k value
    }
}
