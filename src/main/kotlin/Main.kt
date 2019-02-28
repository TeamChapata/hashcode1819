object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        var accumulateScore = 0
        var bestScore = 0

        var bestSolution: MutableList<Slide> = mutableListOf<Slide>()

        val loopTimes = 10
        //val inputName = "e_shiny_selfies"
        val inputName = "a_example"
        var verticalImage: Photo? = null

        for (i in 1.. loopTimes) {

            var data = IOUtils.loadData("./src/main/resources/$inputName.txt")
            var slides: MutableList<Slides> = mutableListOf<Slides>()
            while (!data.photos.isEmpty()) { // Buscamos la siguiente foto a añadir
                var auxPhoto: Photo = data.photos.random() // Cogemos una imagen aleatoria

                if (auxPhoto.orientation == Photo.Orientation.VERTICAL) { // La imagen es vertical

                    if(verticalImage != null){ // Habiamos guardado una imagen vertical
                        var auxSlide = Slide(auxPhoto,verticalImage)
                        if (!slides.isEmpty()) { // No es el primer Slide
                            accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                        }
                        slides.add(auxSlide)
                        data.photos.remove(auxPhoto)
                        data.photos.remove(verticalImage)
                        verticalImage = null

                    } else { // No habiamos guardado imagen vertical
                        var finded = false
                        for(i in 1..100) verticalSearch@{ // Buscamos entre 100 imagenes aleatorias una vertical
                            var partner = data.photos.random()
                            if(partner.orientation == Photo.Orientation.VERTICAL){ // Encontramos una imagen vertical
                                var auxSlide = Slide(auxPhoto,partner)
                                if (!slides.isEmpty()) { // No es la primera Slide
                                    accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                                }
                                slides.add(auxSlide)
                                data.photos.remove(auxPhoto)
                                data.photos.remove(partner)
                                return@verticalSearch
                                finded = true
                            }
                        }

                        if(!finded){ // No encontramos ninguna imagen vertical mas. Guardamos la que ya tenemos.
                            verticalImage = auxPhoto
                            data.photos.remove(auxPhoto)
                        }
                    }

                } else if (auxPhoto.orientation == Photo.Orientation.HORIZONTAL) { // La imagen es horizontal
                    var auxSlide = Slide(auxPhoto)
                    if (!slides.isEmpty()) {
                        accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                    }
                    slides.add(auxSlide)
                }
            }
            if(accumulateScore > bestScore){
                bestScore = accumulateScore
                bestSolution = slides
                accumulateScore = 0
            }

        }
        getSolutionOf(bestSolution)
    }
}