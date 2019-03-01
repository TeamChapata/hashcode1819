import java.io.File
import java.util.*
import java.util.ArrayList



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


    fun getScore(slide1: Slide, slide2: Slide): Int {

        val auxList = ArrayList(slide1.topics)
        auxList.retainAll(slide2.topics)

        val commonTags = auxList.size

        val inS1notS2 = slide1.topics.size - commonTags
        val inS2notS1 = slide2.topics.size - commonTags

        return Math.min(Math.min(commonTags, inS1notS2),inS2notS1)
    }

    fun writeData(fileName: String, result: MutableList<Slide>) {
        val destination = File("./src/main/resources/$fileName.out").bufferedWriter()
        destination.write("${result.size}\n")
        for(slide in result) {
            if(slide.photo2 == null) {
                destination.write("${slide.photo1.id}\n")
            } else {
                destination.write("${slide.photo1.id} ${slide.photo2.id}\n")
            }
        }
        destination.close()
    }

    fun getAllScore(result: MutableList<Slide>):Int {
        var score = 0
        for(i in 0 until result.size - 1) {
            score += getScore(result[i], result[i+1])
        }
        return score
    }

    fun getBestScores(fileName: String): Array<Int> {
        val scanner = Scanner(File(fileName))
        val bestScores: Array<Int> = arrayOf(0,0,0,0,0)
        bestScores[0] = scanner.nextInt()
        bestScores[1] = scanner.nextInt()
        bestScores[2] = scanner.nextInt()
        bestScores[3] = scanner.nextInt()
        bestScores[4] = scanner.nextInt()
        return bestScores
    }

    fun saveBestScores(fileName: String, bestScores: Array<Int>) {
        val destination = File("./src/main/resources/$fileName").bufferedWriter()
        destination.write("${bestScores[0]} ${bestScores[1]} ${bestScores[2]} ${bestScores[3]} ${bestScores[4]}")

        destination.close()
    }
}