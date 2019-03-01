class Topic(val topic: String) {
    override fun toString(): String {
        return "$topic"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Topic

        if (topic != other.topic) return false

        return true
    }

    override fun hashCode(): Int {
        return topic.hashCode()
    }


}