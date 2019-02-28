object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        //val inputName = "e_shiny_selfies"
        val inputName = "a_example"

        val data = IOUtils.loadData("./src/main/resources/$inputName.txt")

        //print(data.clone())
        //print(data)


        /*var foto1 = Photo(1, Photo.Orientation.VERTICAL, 3)

        var foto2 = null
        var slide1 = Slide(foto1, foto2)*/

        var slide2 = Slide(data.photos[1], data.photos[2])
        print(slide2.getTags())
    }
}