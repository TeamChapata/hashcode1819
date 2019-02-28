class Data (val len: Int) {
    var photos: MutableList<Photo> = ArrayList()


    override fun toString(): String {
        var ret = "$len\n"
        for(i in 0 until len) {
            ret += "${photos[i]}"
        }
        return ret
    }

    /*fun clone(): Data {
        val ret = Data(this.len)

        for(i in 0 until photos.size) {
            val auxPhtos = photos[i]
            val auxPhotos = Photo(photos[i].id, photos[i].orientation, photos[i].numberOfTopics)
            for(j in 0 until photos[i].topics.size){
                for(h in )
            }
        }

        return ret
    }*/

    
}