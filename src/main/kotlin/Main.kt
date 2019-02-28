import algorithm.RandomAlgorithm

object Main {

    @JvmStatic
    fun Array<String>.main() {
        val inputs: Array<String> = arrayOf("a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies")

        for(inputName in inputs) {

            println("Processing $inputName")

            val data = IOUtils.loadData("./src/main/resources/$inputName.txt")
            val solution: OutputData= RandomAlgorithm.randomAlgorithm(data, 10)


            println("Best score ${solution.bestScore}")
            IOUtils.writeData("$inputName-output", solution.bestSolution)
        }





    }
}