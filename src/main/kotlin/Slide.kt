class Slide (val photo1: Photo, val photo2: Photo? = null) {
    init {
        assert(!(photo1.orientation == Photo.Orientation.VERTICAL && photo2 == null))
        assert(photo1.orientation == Photo.Orientation.HORIZONTAL && photo2!!.orientation == Photo.Orientation.HORIZONTAL)
    }


    val topics: MutableList<Topic>
        get() {
            var topics: MutableList<Topic> = ArrayList()
            var resultado: MutableList<Topic> = ArrayList()
            var aux: MutableList<String> = ArrayList()

            topics = photo1.topics

            if (photo2 != null) {
                for (topic in photo2.topics) {
                    topics.add(topic)
                }
            }

            for (topic in topics) {
                aux.add(topic.topic)
            }

            aux = aux.distinct().toMutableList()
            /*val set: Set<Topic> = HashSet<Topic>(topics)
            topics.clear()
            topics.addAll(set)*/
            for (topicName in aux){
                resultado.add(Topic(topicName))
            }
            //return topics.distinct().toMutableList()
            return resultado
    }
}