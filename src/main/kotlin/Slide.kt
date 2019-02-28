class Slide (val photo1: Photo, val photo2: Photo? = null) {


    val topics: MutableList<Topic>


    init {
        assert(!(photo1.orientation == Photo.Orientation.VERTICAL && photo2 == null))
        assert(photo1.orientation == Photo.Orientation.HORIZONTAL && photo2 != null)
        this.topics = ArrayList()

        this.topics.addAll(photo1.topics)
        if (photo2 != null) {
            this.topics.addAll(photo2.topics)
        }
    }
}