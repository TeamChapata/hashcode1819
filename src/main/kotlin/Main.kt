import algorithm.RandomAlgorithm
import algorithm.SemiRandomLinearAlgorithm

object Main {

    @JvmStatic
    fun Array<String>.main() {
        val inputs: Array<String> = arrayOf("a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies")

        var totalScore = 0
        var totalTime: Long = 0


        for(inputName in inputs) {
            println("Processing $inputName")
            val from = System.currentTimeMillis()
            val data = IOUtils.loadData("./src/main/resources/$inputName.txt")
            val solution = SemiRandomLinearAlgorithm.algorithm(data, 25, 25)
            // val solution: OutputData= RandomAlgorithm.randomAlgorithm(data, 25)
            val to = System.currentTimeMillis()
            totalScore += solution.bestScore
            totalTime += (to-from)

            println("Best score ${solution.bestScore} in ${to-from} ms")
            IOUtils.writeData("$inputName-output", solution.bestSolution)
        }

        println("\nTotal score: $totalScore in $totalTime")
    }
}