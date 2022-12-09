package leetcode

import log.logDoubleStringList

fun main() {
    val array = arrayOf("eate", "tea", "tan", "ate", "nat", "bat")
    logDoubleStringList(groupAnagrams(array))
}

/**
 * 49. 字母异位词分组
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val result: ArrayList<ArrayList<String>> = arrayListOf()
    val resultHashSet: HashSet<ArrayList<String>> = hashSetOf()
    for (i in strs.indices) {
        val wordsArray = arrayListOf<String>()
        for (j in strs.indices) {
            if (isMatchWords(strs[i].toCharArray(), strs[j].toCharArray())) wordsArray.add(strs[j])
        }
        if (resultHashSet.add(wordsArray)) result.add(wordsArray)
    }
    return result
}

/** 匹配两个字符数组组合元素是否相同 */
fun isMatchWords(words1: CharArray, words2: CharArray): Boolean {
    if (words1.size != words2.size) return false
    words1.sort()
    words2.sort()
    for (index in words1.indices) {
        if (words1[index] != words2[index]) return false
    }
    return true
}

fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    val resultHashMap: HashMap<String, ArrayList<String>> = hashMapOf()
    for (i in strs.indices) {
        val char = strs[i].toCharArray()
        char.sort()
        val key = String(char)
        println("key $key")
        val list: ArrayList<String> = resultHashMap[key] ?: arrayListOf()
        list.add(strs[i])
        resultHashMap[key] = list
    }
    return ArrayList(resultHashMap.values)
}

fun groupAnagrams3(strs: Array<String>): List<List<String>> {
    // HashMap + sort
    val resultHashMap: HashMap<String, ArrayList<String>> = hashMapOf()
    for (i in strs.indices) {
        val char = strs[i].toCharArray()
        // 排序：找出组合相同 words
        char.sort()
        val words = String(char)
        val list = resultHashMap[words] ?: arrayListOf()
        list.add(strs[i])
        // 不断更新相同 words 组合的单词
        resultHashMap[words] = list
    }
    val result = arrayListOf<List<String>>()
    resultHashMap.forEach { result.add(it.value) }
    return result
}

