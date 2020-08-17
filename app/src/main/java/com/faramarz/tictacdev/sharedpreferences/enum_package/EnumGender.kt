package com.faramarz.tictacdev.sharedpreferences.enum_package

enum class EnumGender private constructor(var valueStr: String?, val value: Int?) {

    UNDEFINE("UNDEFINE", -1),
    MALE("MALE", 0),
    FEMALE("FEMALE", 1);


    companion object {

        operator fun get(value: String?): EnumGender {
            if (value == null) {
                return UNDEFINE
            }

            val `arr$` = values()
            for (`val` in `arr$`) {
                if (`val`.valueStr!!.equals(value.trim { it <= ' ' }, ignoreCase = true)) {
                    return `val`
                }
            }

            return UNDEFINE
        }

        operator fun get(value: Int?): EnumGender {

            if (value == null) {
                return UNDEFINE
            }

            val `arr$` = values()
            for (`val` in `arr$`) {
                if (`val`.value === value) {
                    return `val`
                }
            }

            return UNDEFINE
        }
    }
}
