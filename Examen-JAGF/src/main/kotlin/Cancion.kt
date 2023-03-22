import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*


class Cancion(
    //Atributos
    private var idCancion: Int,
    private var nombreCancion: String,
    private var autorCancion: String,
    private var calificacion: Double,
    private var fechaPublicacion: Date
) {
    init {
        idCancion
        nombreCancion
        autorCancion
        calificacion
        fechaPublicacion
    }

    fun setidCancion(idCancion: Int) {
        this.idCancion = idCancion
    }

    fun setnombreCancion(nombreCancion: String) {
        this.nombreCancion = nombreCancion
    }

    fun setautorCancion(autorCancion: String) {
        this.autorCancion = autorCancion
    }

    fun setcalificacion(calificacion: Double) {
        this.calificacion = calificacion
    }

    fun setfechaPublicacion(fechaPublicacion: Date) {
        this.fechaPublicacion = fechaPublicacion
    }

    fun getidCancion(): Int {
        return idCancion
    }

    fun getnombreCancion(): String {
        return nombreCancion
    }

    fun getautorCancion(): String {
        return autorCancion
    }

    fun getcalificacion(): Double {
        return calificacion
    }

    fun getfechaPublicacion(): Date {
        return fechaPublicacion
    }

    companion object {
        fun selectCancion() {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/cancion.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                println(
                    "Núm. canción: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Autor: " + valorCadena[2] + "\n"
                            + "Calificación: " + valorCadena[3] + "\n"
                            + "Fecha publicación: " + valorCadena[4] + "\n"
                )
            }
        }

        fun updateCancion(nombreCancion: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/cancion.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreCancion) {
                    var opcionUpdate = true
                    println(
                        "Núm. canción: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Autor: " + valorCadena[2] + "\n"
                                + "Calificación: " + valorCadena[3] + "\n"
                                + "Fecha publicación: " + valorCadena[4] + "\n"
                    )
                    //Ver que atributo desea modificar
                    var newString: String = ""
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Autor, 3) Calificación, 4) Fecha publicacion")
                        var atributoUpdate = readln().toInt()
                        when (atributoUpdate) {
                            (1) -> {
                                print("Ingrese el nuevo nombre: ")
                                var nombre = readln()
                                arrayCadena.set(0, nombre)
                            }

                            (2) -> {
                                print("Ingrese el nuevo autor: ")
                                var autor = readln()
                                arrayCadena.set(1, autor)
                            }

                            (3) -> {
                                print("Ingrese la nueva calificación: ")
                                var calificacion = readln()
                                arrayCadena.set(2, calificacion)
                            }

                            (4) -> {
                                print("Ingrese la nueva fecha de publicación (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                arrayCadena.set(3, formato.format(newFecha))
                            }
                        }
                        //Ver si desea seguir actualizando la canción elegida
                        println("¿Desea seguir actualizando la canción elegida 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false //Terminar update de cancion
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    arrayCadena[i] = valorCadena[i + 1]
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "\n"
                        }
                    } while (opcionUpdate == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("CANCIÓN NO ENCONTRADA")
            } else {
                File("src/main/resources/text/cancion.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("CANCIÓN ACTUALIZADA")
            }
        }

        fun deleteCancion(nombreCancion: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/cancion.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreCancion) {
                    println("CANCIÓN ELIMINADA")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("CANCIÓN NO ENCONTRADA")
            } else {
                File("src/main/resources/text/cancion.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumCanciones(): Int {
            var path = Paths.get("src/main/resources/text/cancion.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }
    }

    fun insertCancion() {
        //Enviar al archivo
        var path = Paths.get("src/main/resources/text/cancion.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idCancion.toString() + "," + this.nombreCancion + "," + this.autorCancion + "," + this.calificacion + "," + formato.format(
                this.fechaPublicacion
            ) + "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("CANCIÓN AGREGADA")
        } catch (e: IOException) {
            println("Fallo al ingresar cancion al archivo")
        }
    }

}