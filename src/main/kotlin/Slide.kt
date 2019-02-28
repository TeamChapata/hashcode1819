class Slide (val photo1: Photo, val photo2: Photo? = null) {
    init {
        assert(!(photo1.orientation == Photo.Orientation.VERTICAL && photo2 == null))
        assert(photo1.orientation == Photo.Orientation.HORIZONTAL && photo2!!.orientation == Photo.Orientation.HORIZONTAL)
    }

    fun getTags(): MutableList<Topic> {
        var topics: MutableList<Topic> = ArrayList()

        topics = photo1.topics;

        if (photo2 != null){
            for (topic in photo2.topics){
                topics.add(topic)
            }
        }

        return topics
    }
}