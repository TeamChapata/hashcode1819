import algorithm.RandomAlgorithm

object Main {

    @JvmStatic
    fun Array<String>.main() {
        val inputs: Array<String> = arrayOf("a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies")

        for(inputName in inputs) {

            println("Processing $inputName")
            val from = System.currentTimeMillis()
            val data = IOUtils.loadData("./src/main/resources/$inputName.txt")
            val solution: OutputData= RandomAlgorithm.randomAlgorithm(data, 100)
            val to = System.currentTimeMillis()


            println("Best score ${solution.bestScore} in ${to-from} ms")
            IOUtils.writeData("$inputName-output", solution.bestSolution)
        }





    }
}