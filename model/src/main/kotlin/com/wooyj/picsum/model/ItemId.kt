package com.wooyj.picsum.model

@JvmInline
value class ItemId(
    private val value: Int,
) {
    fun getId() = value

    fun next(): ItemId? {
        val temp = value.plus(1)
        if (temp < 0) {
            return null
        }
        if (temp > 1084) {
            return null
        }
        return ItemId(temp)
    }

    fun prev(): ItemId? {
        val temp = value.minus(1)
        if (temp < 0) {
            return null
        }
        return ItemId(temp)
    }
}
// max : 1084, min :0

@JvmInline
value class Time(
    private val value: Long,
) {
    fun getHour() = this.value.div(3600).toInt()

    fun getMinute() =
        this.value
            .rem(3600)
            .div(60)
            .toInt()

    fun getSecond() = this.value.rem(60).toInt()

    fun getMillisecond() = this.value.rem(1000).toInt()

    companion object {
        fun fromHour(hour: Int): Time = Time(hour.toLong() * 3600)

        fun fromMinute(minute: Int): Time = Time(minute.toLong() * 60)

        fun fromSecond(second: Int): Time = Time(second.toLong())
    }
}

// @JvmInline
// value class Money(
//     private val value: Long,
// ) {
//     fun krw() = value * 환율("krw")
//
//     companion object {
//         fun fromDollar(dollar: Int): Money = Money(dollar.toLong())
//     }
// }
