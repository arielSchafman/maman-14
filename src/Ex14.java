/**
 * Class that represents Maman14
 *
 * @author ariel schafman
 * @version 2020a
 */

public class Ex14 {
    /**
     * tell how many sub strings are inside a given string that starts and ends with
     * a given char.
     *
     * the efficiency is O(2^n)
     *
     * @param s The string.
     * @param c The char to look for.
     * @return the number of sub strings.
     */
    public static int subStrC(String s, char c) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                counter++;

        if (counter < 3)
            counter = 0;
        else
            counter -= 2;

        return counter;
    }

    /**
     * Find a how much a sequence of a given char
     *
     * the efficiency is O(2^n)
     *
     * @param s The string to look at.
     * @param c The input char to look for.
     * @param k The amount of chars.
     * @return the number of sub strings.
     */
    public static int subStrMaxC(String s, char c, int k) {
        int counter = 0;

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == c)
                counter++;
        return subStrMaxCRecursively(s, c, k, counter);
    }

    /**
     * the recursion of the first question.
     *
     * the efficiency is O(2^n)
     *
     * @param s the String of the question.
     * @param c the char that the String start and end with.
     * @param k The amount of chars inside the pattern.
     * @return the number of sub strings.
     */
    private static int subStrMaxCRecursively(String s, char c, int k, int counter) {
        if (k < 0) return 0;
        if (counter < k + 2)
            counter = 0;
        else
            counter -= k + 1;

        return counter + subStrMaxCRecursively(s, c, k - 1, counter);

    }

    /**
     * tell the distance between all the 1's and the closest 0 to them.
     *
     *the efficiency is O(n)
     *
     * @param a the array of 1's and 0's.
     */
    public static void zeroDistance(int[] a) {
        int closest = -1;
        for (int i = 0; i < a.length; i++)
            if (a[i] == 0)
                closest = 0;
            else {
                if (closest == -1)
                    a[i] = Integer.MAX_VALUE;
                else {
                    closest++;
                    a[i] = closest;
                }
            }
        closest = -1;
        for (int i = a.length - 1; i >= 0; i--)
            if (a[i] == 0)
                closest = 0;
            else if (closest != -1 && a[i] > ++closest)
                a[i] = closest;
    }

    /**
     * get a String t and a String s and tell if t is a trans of s.
     *
     * @param s the original String.
     * @param t the String to see if he's trans of s.
     * @return true if trans false if not.
     */
    public static boolean isTrans(String s, String t) {
        boolean isT = false;

        if (t.length() > 1) {

            if (s.charAt(0) == t.charAt(0) && (t.charAt(0) != t.charAt(1))) {
                return isTrans(s.substring(1, s.length()), t.substring(1, t.length()));
            }
            if (s.charAt(0) == t.charAt(0) && t.charAt(0) == t.charAt(1)) {
                return isTrans(s, t.substring(1, t.length()));
            }
        } else {
            isT = s.charAt(0) == t.charAt(0);
        }

        return isT;
    }

    /**
     * count the amount of paths you can take in a 2D array according to the numbers in the array's cells.
     *
     * @param mat the array.
     * @return the number of paths.
     */
    public static int countPaths(int[][] mat) {
        int start = mat[0][0], tens = start / 10, ones = start % 10;
        boolean downByTens, downByOnes;

        if (tens <= mat.length - 1 && ones <= mat[tens].length - 1) {
            downByTens = true;
        } else {
            downByTens = false;
        }

        if (ones <= mat.length - 1 && tens <= mat[ones].length - 1) {
            downByOnes = true;
        } else {
            downByOnes = false;
        }

        if (downByTens) {
            if (downByOnes) {
                return countPaths(mat, tens, ones) + countPaths(mat, ones, tens);
            } else {
                return countPaths(mat, tens, ones);
            }
        } else {
            if (downByOnes) {
                return countPaths(mat, ones, tens);
            } else {
                return 0;
            }
        }
    }

    /**
     * use to help calculate the paths using recursion.
     *
     * @param mat the array.
     * @param row the row you in.
     * @param col the column you in.
     * @return run in recursion until return 0.
     */
    private static int countPaths(int[][] mat, int row, int col) {
        int current = mat[row][col], tens = current / 10, ones = current % 10, end = mat[mat.length - 1][mat[0].length - 1];
        boolean downByTens, downByOnes;

        if (row + tens <= mat.length - 1 && col + ones <= mat[row + tens].length - 1) {
            downByTens = true;
        } else {
            downByTens = false;
        }

        if (row + ones <= mat.length - 1 && col + tens <= mat[row + ones].length - 1) {
            downByOnes = true;
        } else {
            downByOnes = false;
        }

        if (current == end) {
            return 1;
        } else {
            if (downByTens) {
                if (downByOnes) {
                    return countPaths(mat, row + tens, col + ones) + countPaths(mat, row + ones, col + tens);
                } else {
                    return countPaths(mat, row + tens, col + ones);
                }
            } else {
                if (downByOnes) {
                    return countPaths(mat, row + ones, col + tens);
                } else {
                    return 0;
                }
            }
        }
    }
}