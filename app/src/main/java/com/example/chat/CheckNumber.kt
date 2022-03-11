package com.example.chat



class CheckNumber (val numstr: String){
    enum class NumberState() {
        OK, TOOSHORT, TOOLONG, WRONG
    }
    var state = NumberState.OK
    val numarr = numstr.toCharArray()
    val checklist =
        ('a'..'z').toMutableList() + ('A'..'Z').toMutableList() + ('0'..'9').toMutableList()

    fun userId() : NumberState {

        when {
            numstr.length < 4 -> state = NumberState.TOOSHORT
            numstr.length > 20 -> state = NumberState.TOOLONG
            else -> state = NumberState.OK
        }
        for (i in numarr) {
            if (!checklist.contains(i)) state = NumberState.WRONG
        }
        return state
    }

    fun passWord() : NumberState {

        when {
            numstr.length < 6 -> state = NumberState.TOOSHORT
            numstr.length > 12 -> state = NumberState.TOOLONG
            else -> state = NumberState.OK
        }
        for (i in numarr) {
            if (!checklist.contains(i)) state = NumberState.WRONG
        }
        return state
    }
}


//fun main() {
//    val test = "jgf!"
//    val result = CheckNumber(test).userId()
//    val result2 = CheckNumber(test).passWord()
//    println(result)
//    print(result2)
//}
