package com.example.tweeter.util

class SplitMessagesUtil {
    companion object {

        private const val MAX_LENGTH = 50

        @JvmStatic
        fun splitMessage(messageStr: String) : ArrayList<String>? {
            val message = StringBuilder(messageStr.trim())

            if (message.isEmpty())
                return ArrayList()
            if (message.length <= MAX_LENGTH)
                return arrayListOf(messageStr)

            val result = ArrayList<String>()
            var splitIndex = 0

            // real chunk size will greater or equal estimate chunk size
            val estimateChunkSize =  calEstimateChunkSize(message.length)
//            println("estimate chunk size: $estimateChunkSize")
            val numberOfChunkSize = countNumberOfDigits(estimateChunkSize)
            var n : Int

            // check condition current message need to split
            while ((message.length + countNumberOfDigits(result.size) + numberOfChunkSize
                    + if (message.get(0) == ' ') 1 else 2) > MAX_LENGTH) {
                // find last whitespace from max length chunk to split
                splitIndex = 0
                n = if (message.length > MAX_LENGTH) MAX_LENGTH else message.length
                for (i in n-1 downTo 1) {
                    if (message.get(i) == ' ') {
                        if ((i + countNumberOfDigits(result.size) + numberOfChunkSize
                                        + if (message.get(0) == ' ') 1 else 2)  <= MAX_LENGTH) {
                            splitIndex = i
                            break
                        }
                    }
                }

                if (splitIndex == 0) {
                    // error case
                    return ArrayList()
                }

                result.add(message.substring(0, splitIndex))
                message.delete(0, splitIndex)
            }

            result.add(message.toString())

            var s : String
            for (i in 0 until result.size) {
                s = if (result.get(i).get(0) == ' ')
                        "${i+1}/${result.size}" + result.get(i)
                    else
                        "${i+1}/${result.size} " + result.get(i)
                result.set(i, s)
            }

            return result
        }

        fun calEstimateChunkSize(messageLength: Int, indicatorLength : Int = 3) : Int {
            val d = MAX_LENGTH - indicatorLength // decrease indicator part length, 3 is min length of indicator

            val chunkSize = messageLength / d + if (messageLength % d == 0) 0 else 1
//            println("first chunk size: $chunkSize" )
            var totalIndicatorLength = calTotalIndicatorLength(chunkSize)
            val totalCharacter = messageLength + totalIndicatorLength
            val newChunkSize = totalCharacter / MAX_LENGTH + if (totalCharacter % MAX_LENGTH == 0) 0 else 1

            return newChunkSize
        }

        /**
         * calculate total length of indicator parts
         * ex: 9 chunk size -> "n/9 " -> 9*4 - 8 = 28 character
         *     19 chunk size -> 9*5 ("n/11 ") + 10*6("11/11 ") - 18 = 39 character
         *     99 chunk size -> 9 * 5("n/99 ")  + 90 * 6("nn/99 ") - 98 = 487 character
         */
        fun calTotalIndicatorLength(chunkSize : Int) : Int {
            var temp = 1
            var s = 0
            var a = 0
            val numberOfChunkSize = countNumberOfDigits(chunkSize)

            for (i in 1..numberOfChunkSize) {
                // a is count number have "i" digits. Ex 1->99 there are 90 number have 2 digits
                a = if (temp*10 <= chunkSize) {
                    temp*9
                } else {
                    chunkSize - temp + 1
                }

//                println("a: $a, i: $i, numberOfChunkSize: $numberOfChunkSize ")
                s += a * (i + numberOfChunkSize + 2) // (i + numberOfChunkSize + 2) is  length of indicator part, ex: "n/9 " -> 4
                temp*=10
            }

            return s - (chunkSize - 1) // chunkSize - 1 is number space when split
        }

        private fun countNumberOfDigits(number : Int) : Int {
            // if else is fastest solution for count digits
            if (number < 100000) {
                if (number < 100) {
                    if (number < 10) {
                        return 1
                    } else {
                        return 2
                    }
                } else {
                    if (number < 1000) {
                        return 3
                    } else {
                        if (number < 10000) {
                            return 4
                        } else {
                            return 5
                        }
                    }
                }
            } else {
                if (number < 10000000) {
                    if (number < 1000000) {
                        return 6
                    } else {
                        return 7
                    }
                } else {
                    if (number < 100000000) {
                        return 8
                    } else {
                        if (number < 1000000000) {
                            return 9
                        } else {
                            return 10
                        }
                    }
                }
            }
        }


        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello, world!")
        }
    }


}