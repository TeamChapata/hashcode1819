object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        //val inputName = "e_shiny_selfies"
        val inputName = "a_example"

        val data = IOUtils.loadData("./src/main/resources/$inputName.txt")

        //print(data.clone())
        //print(data)
        val foto1 = Photo(1, Photo.Orientation.VERTICAL, 3)
        val foto2 = null
        val slide1 = Slide(data.photos[1], data.photos[2]).topics

        print(slide1)


    }
}