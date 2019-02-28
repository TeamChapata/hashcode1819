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

        val originData = IOUtils.loadData("./src/main/resources/$inputName.txt")

        for (i in 1.. loopTimes) {
            println("Loop: $i")
            val data = originData.clone()
            val slides: MutableList<Slide> = mutableListOf<Slide>()
            while (!data.photos.isEmpty()) { // Buscamos la siguiente foto a añadir
                val auxPhoto: Photo = data.photos.random() // Cogemos una imagen aleatoria

                if (auxPhoto.orientation == Photo.Orientation.VERTICAL) { // La imagen es vertical

                    if(verticalImage != null){ // Habiamos guardado una imagen vertical
                        val auxSlide = Slide(auxPhoto,verticalImage)
                        if (!slides.isEmpty()) { // No es el primer Slide
                            accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                        }
                        slides.add(auxSlide)
                        data.photos.remove(auxPhoto)
                        data.photos.remove(verticalImage)
                        verticalImage = null

                    } else { // No habiamos guardado imagen vertical
                        var finded = false
                        verticalSearch@for(j in 1..4) { // Buscamos entre 100 imagenes aleatorias una vertical
                            println("Buscando vertical $j")
                            val partner = data.photos.random()
                            if(partner.orientation == Photo.Orientation.VERTICAL){ // Encontramos una imagen vertical
                                val auxSlide = Slide(auxPhoto,partner)
                                if (!slides.isEmpty()) { // No es la primera Slide
                                    accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                                }
                                slides.add(auxSlide)
                                data.photos.remove(auxPhoto)
                                data.photos.remove(partner)
                                finded = true
                                break@verticalSearch
                            }
                        }

                        if(!finded){ // No encontramos ninguna imagen vertical mas. Guardamos la que ya tenemos.
                            verticalImage = auxPhoto
                            data.photos.remove(auxPhoto)
                        }
                    }

                } else if (auxPhoto.orientation == Photo.Orientation.HORIZONTAL) { // La imagen es horizontal
                    val auxSlide = Slide(auxPhoto)
                    if (!slides.isEmpty()) {
                        accumulateScore += IOUtils.getScore(auxSlide, slides.last())
                    }
                    slides.add(auxSlide)
                    data.photos.remove(auxPhoto)
                }
            }
            if(accumulateScore > bestScore){
                bestScore = accumulateScore
                bestSolution.addAll(slides)
                accumulateScore = 0
            }

        }
        println("Best score: $bestSolution")
        print(IOUtils.getScore(Slide(originData.photos[0]),Slide(originData.photos[1])))
        //IOUtils.writeData("output", bestSolution)
    }
}