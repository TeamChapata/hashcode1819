class Photo (val id: Int, val orientation: Orientation, val numberOfTopics: Int){
    enum class Orientation{
        HORIZONTAL, VERTICAL, NOTHING
    }

    val topics: MutableList<Topic> = ArrayList()


    override fun toString(): String {
        var ret = "[$id] "

        ret += when(orientation) {
            Orientation.VERTICAL -> "V "
            Orientation.HORIZONTAL -> "H "
            Orientation.NOTHING -> "FAIL "
        }

        for(j in 0 until numberOfTopics) {
            ret += "${topics[j]} "
        }

        return ret + "\n"
    }


}