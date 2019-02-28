object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        //val inputName = "e_shiny_selfies"
        val inputName = "a_example"

        val data = IOUtils.loadData("./src/main/resources/$inputName.txt")

        print(data)
    }
}