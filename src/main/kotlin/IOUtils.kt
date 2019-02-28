import java.io.File
import java.util.*

object IOUtils {

    fun loadData(fileName: String): Data{
        val scanner = Scanner(File(fileName))
        val numberOfPhotos = scanner.nextInt()

        val data = Data(numberOfPhotos)
        scanner.nextLine()
        for(i in 0 until numberOfPhotos) {
            val line = scanner.nextLine().split(" ")
            val orientation = when(line[0]){
                "H" -> Photo.Orientation.HORIZONTAL
                "V" -> Photo.Orientation.VERTICAL
                else -> Photo.Orientation.NOTHING
            }
            val photo = Photo(i, orientation, line[1].toInt())
            for(j in 2 until line.size) {
                photo.topics.add(Topic(line[j]))
            }
            data.photos.add(photo)
        }
        return data
    }

    fun getScore(slide1: Slide, slice2: Slide) {
        var commonTags = 0
        // Number of common tags

        for(topic in slide1.topics){
            if(slice2.topics.contains(topic)) {
                commonTags += 1
            }
        }


    }
}