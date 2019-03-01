package algorithm
import Slide
import Data
import OutputData
import Photo


object SemiRandomLinearAlgorithm {

    fun algorithm(oData: Data, numLooops: Int, tries: Int): OutputData {
        val data = oData.clone()


        val allPossiblesHorizontalSlides: MutableList<Slide> = ArrayList()

        val allVerticalPhotos: MutableList<Photo> = ArrayList()

        for(photo in data.photos) {
            when(photo.orientation) {
                Photo.Orientation.HORIZONTAL -> allPossiblesHorizontalSlides.add(Slide(photo))
                Photo.Orientation.VERTICAL -> allVerticalPhotos.add(photo)
            }
        }
        var bestSolution: MutableList<Slide> = ArrayList()
        var bestScore = 0
        for(i in 0 until numLooops) {
            println("Loop: $i/$numLooops")
            val allPossibleSlides = ArrayList<Slide>(allPossiblesHorizontalSlides)
            val verticalPhotos = ArrayList<Photo>(allVerticalPhotos)
            // match all vertical slides

            var auxVerticalPhoto: Photo? = null
            while(verticalPhotos.isNotEmpty()) {
                if(auxVerticalPhoto == null && verticalPhotos.size == 1) {
                    verticalPhotos.clear() //Discard last vertical photo -> not possible match
                }

                val currentPhoto = verticalPhotos.random()
                verticalPhotos.remove(currentPhoto)
                if (auxVerticalPhoto != null) {
                    allPossibleSlides.add(Slide(auxVerticalPhoto, currentPhoto))
                    auxVerticalPhoto = null
                } else {
                    auxVerticalPhoto = currentPhoto
                }
            }

            // Order all possible slides in base of scores

            val currentSolution = orderList(allPossibleSlides, tries)
            val currentScore = IOUtils.getAllScore(currentSolution)

            if(currentScore > bestScore) {
                bestScore = currentScore
                bestSolution = currentSolution
            }


        }
        return OutputData(bestSolution, bestScore)
    }


    private fun orderList(slides: MutableList<Slide>, tries: Int): MutableList<Slide> {
        val orderedSlides: MutableList<Slide> = ArrayList()

        // Set first slide random
        val currentSlide = slides.random()
        orderedSlides.add(currentSlide)
        slides.remove(currentSlide)

        while(slides.isNotEmpty()) {
            var currentBestScore = 0
            var currentChoose: Slide? = null

            for(i in 0 until tries) {
                val slide = slides.random()
                val currentScore = IOUtils.getScore(orderedSlides[orderedSlides.size-1], slide)
                if(currentScore >= currentBestScore) {
                    currentBestScore = currentScore
                    currentChoose = slide
                }
            }
            if (currentChoose != null) {
                orderedSlides.add(currentChoose)
                slides.remove(currentChoose)
            }
        }
        return orderedSlides
    }
}