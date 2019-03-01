import algorithm.RandomAlgorithm
import algorithm.SemiRandomLinearAlgorithm

object Main {

    @JvmStatic
    fun Array<String>.main() {
        val inputs: Array<String> = arrayOf("a_example","b_lovely_landscapes","c_memorable_moments","d_pet_pictures","e_shiny_selfies")
        val scores: Array<Int> = arrayOf(0,0,0,0,0)
        val times: Array<Long> = arrayOf(0,0,0,0,0)
        var totalScore = 0
        var totalTime: Long = 0

        for((count, inputName) in inputs.withIndex()) {
            println("Processing $inputName")
            val from = System.currentTimeMillis()
            val data = IOUtils.loadData("./src/main/resources/$inputName.txt")
            val solution = SemiRandomLinearAlgorithm.algorithm(data, 10, 10)
            // val solution: OutputData= RandomAlgorithm.randomAlgorithm(data, 25)
            val to = System.currentTimeMillis()
            totalScore += solution.bestScore
            totalTime += (to-from)


            scores[count] = solution.bestScore
            times[count] = (to-from)

            //println("Best score ${solution.bestScore} in ${to-from} ms")
            IOUtils.writeData("$inputName-output", solution.bestSolution)
        }

        for(i in 0 until 5) {
            println("${inputs[i]}:\n\tScore: ${scores[i]}\n\tTime: ${times[i]} ms\n")
        }
        println("\nTotal score: $totalScore in $totalTime ms")
    }
}