package main.kotlin.leetcode.hashtable

import log.log
import java.util.*

fun main() {
    log(isAnagram("anagram", "nagaras"))
    log(isAnagram2("anagram", "nagaras"))
}

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val sCharArray = s.toCharArray()
    val tCharArray = t.toCharArray()
    // 通过字符顺序排序
    Arrays.sort(sCharArray)
    Arrays.sort(tCharArray)
    return sCharArray.contentEquals(tCharArray)
}

fun isAnagram2(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val map = HashMap<Char, Int>(26)
    for (i in s.indices) {
        // 记录该字符出现的次数
        map[s[i]] = map.getOrDefault(s[i], 0) + 1
    }
    for (i in t.indices) {
        val char = t[i]
        map[char] = map.getOrDefault(char, 0) - 1
        if (map[char] != null && map[char]!! < 0) {
            return false
        }
    }
    return true
}