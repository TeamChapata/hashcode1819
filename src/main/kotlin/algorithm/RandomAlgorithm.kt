package algorithm

import Photo
import Slide
import Data
import OutputData

object RandomAlgorithm {


    fun randomAlgorithm(oData: Data, loopTimes: Int): OutputData {
        var accumulateScore = 0
        var verticalImage: Photo? = null
        var bestScore = -1

        val bestSolution: MutableList<Slide> = ArrayList()

        for (i in 1.. loopTimes) {
            println("Loop $i/$loopTimes")
            val data = oData.clone()
            data.photos.shuffle()
            val slides: MutableList<Slide> = ArrayList()
            while (!data.photos.isEmpty()) { // Buscamos la siguiente foto a añadir
                val auxPhoto: Photo = data.photos.random() // Cogemos una imagen aleatoria

                if (auxPhoto.orientation == Photo.Orientation.VERTICAL) { // La imagen es vertical

                    if(verticalImage != null){ // Habiamos guardado una imagen vertical
                        val auxSlide = Slide(auxPhoto,verticalImage)
                        if (!slides.isEmpty()) { // No es el primer Slide
                            val aux = IOUtils.getScore(auxSlide, slides.last())
                            accumulateScore += aux
                        }
                        slides.add(auxSlide)
                        data.photos.remove(auxPhoto)
                        data.photos.remove(verticalImage)
                        verticalImage = null

                    } else { // No habiamos guardado imagen vertical
                        var finded = false
                        verticalSearch@for(j in 1..4) { // Buscamos entre 100 imagenes aleatorias una vertical
                            val partner = data.photos.random()
                            if(partner.orientation == Photo.Orientation.VERTICAL && partner != auxPhoto){ // Encontramos una imagen vertical
                                val auxSlide = Slide(auxPhoto,partner)
                                if (!slides.isEmpty()) { // No es la primera Slide
                                    val aux = IOUtils.getScore(auxSlide, slides.last())
                                    accumulateScore += aux
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
                        val aux = IOUtils.getScore(auxSlide, slides.last())
                        accumulateScore += aux
                    }
                    slides.add(auxSlide)
                    data.photos.remove(auxPhoto)
                }
            }
            if(IOUtils.getAllScore(bestSolution) > bestScore){
                //println("New best solution found")
                bestScore = accumulateScore
                bestSolution.clear()
                bestSolution.addAll(slides)
            }
            accumulateScore = 0
        }

        return OutputData(bestSolution, bestScore)
    }
}