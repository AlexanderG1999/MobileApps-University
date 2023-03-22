import java.awt.event.ItemEvent
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.text.SimpleDateFormat
import java.util.*


class Album(
    //Atributos
    private var idAlbum: Int,
    private var nombreAlbum: String,
    private var autorAlbum: String,
    private var fecha: Date,
    private var estadoFavorito: Boolean,
    private var listaCanciones: ArrayList<Cancion>?
) {
    init {
        idAlbum
        nombreAlbum
        autorAlbum
        fecha
        estadoFavorito
        listaCanciones
    }

    fun setidAlbum(idAlbum: Int) {
        this.idAlbum = idAlbum
    }

    fun setnombreAlbum(nombreAlbum: String) {
        this.nombreAlbum = nombreAlbum
    }

    fun setautorAlbum(autorAlbum: String) {
        this.autorAlbum = autorAlbum
    }

    fun setfecha(fecha: Date) {
        this.fecha = fecha
    }

    fun setestadoFavorito(estadoFavorito: Boolean) {
        this.estadoFavorito = estadoFavorito
    }

    fun getidAlbum(): Int {
        return idAlbum
    }

    fun getnombreAlbum(): String {
        return nombreAlbum
    }

    fun getautorAlbum(): String {
        return autorAlbum
    }

    fun getfecha(): Date {
        return fecha
    }

    fun getestadoFavorito(): Boolean {
        return estadoFavorito
    }

    companion object {
        fun selectAlbum() {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/album.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                print(
                    "Núm. albúm: " + valorCadena[0] + "\n"
                            + "Nombre: " + valorCadena[1] + "\n"
                            + "Autor: " + valorCadena[2] + "\n"
                            + "Fecha: " + valorCadena[3] + "\n"
                            + "Favorito: " + valorCadena[4] + "\n"
                )
                println("Lista de canciones:")
                var path = Paths.get("src/main/resources/text/cancion.txt")
                Files.lines(path, Charsets.UTF_8).forEach {
                    var splitCanciones = it.split(",")
                    var idCancion = splitCanciones[0]
                    for (i in 5..valorCadena.size - 1) {
                        if (idCancion == valorCadena[i]) {
                            println("\t" + splitCanciones[0] + ") " + splitCanciones[1] + " - " + splitCanciones[2])
                        }
                    }
                }
            }
            println()
        }

        fun updateAlbum(nombreCancion: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/album.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreCancion) {
                    var opcionUpdate = true
                    print(
                        "Núm. albúm: " + valorCadena[0] + "\n"
                                + "Nombre: " + valorCadena[1] + "\n"
                                + "Autor: " + valorCadena[2] + "\n"
                                + "Fecha: " + valorCadena[3] + "\n"
                                + "Favorito: " + valorCadena[4] + "\n"
                    )
                    println("Lista de canciones:")
                    var path = Paths.get("src/main/resources/text/cancion.txt")
                    Files.lines(path, Charsets.UTF_8).forEach {
                        var splitCanciones = it.split(",")
                        var idCancion = splitCanciones[0]
                        for (i in 5..valorCadena.size - 1) {
                            if (idCancion == valorCadena[i]) {
                                println("\t" + splitCanciones[0] + ") " + splitCanciones[1] + " - " + splitCanciones[2])
                            }
                        }
                    }
                    //Ver que atributo desea modificar
                    var newString: String = ""
                    var arrayCadena = arrayOf<String>("0", "0", "0", "0", "0")
                    do {
                        println("Elija el atributo a modificar: 1) Nombre, 2) Autor, 3) Fecha, 4) Favorito, 5) Lista canciones")
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
                                print("Ingrese la nueva fecha de publicación (AAAA-MM-DD): ")
                                var fecha = readln()
                                var auxFecha = fecha.split("-")
                                val formato = SimpleDateFormat("yyyy-MM-dd")
                                var newFecha: Date =
                                    Date(auxFecha[0].toInt() - 1900, auxFecha[1].toInt() - 1, auxFecha[2].toInt())
                                arrayCadena.set(2, formato.format(newFecha))
                            }

                            (4) -> {
                                print("Ingrese el nuevo estado de favorito: ")
                                var favorito = readln()
                                arrayCadena.set(3, favorito)
                            }

                            (5) -> {
                                print("Seleccione una acción 1) Agregar canción al albúm 2) Eliminar canción del albúm: ")
                                var opcionLista = readln().toInt()
                                if (opcionLista == 1) {
                                    println("***LISTA DE CANCIONES***")
                                    Cancion.selectCancion()
                                    print("Seleccione las canciones a agregar al álbum (1,2,...): ")
                                    var newListCanciones = readln()
                                    arrayCadena.set(4, updateListaCanciones(newListCanciones, valorCadena[0].toInt()))
                                } else {
                                    print("Seleccione las canciones a eliminar del álbum (1,2,...): ")
                                    var deleteList = readln()
                                    var auxLista = deleteListaCanciones(deleteList, valorCadena[0].toInt())
                                    arrayCadena.set(4, auxLista)
                                }
                            }
                        }
                        //Ver si desea seguir actualizando la canción elegida
                        println("¿Desea seguir actualizando el albúm elegido 1) SI - 2) NO?")
                        var auxOpcion = readln().toInt()
                        if (auxOpcion == 2) {
                            opcionUpdate = false //Terminar update de albúm
                            for (i in 0..arrayCadena.size - 1) {
                                if (arrayCadena[i] == "0") {
                                    if (i == 4) { // Tomando lista de canciones original del albúm
                                        for (j in 5..valorCadena.size - 1) {
                                            if (j == valorCadena.size - 1) {
                                                arrayCadena[i] += valorCadena[j]
                                            } else {
                                                arrayCadena[i] += valorCadena[j] + ","
                                            }
                                        }
                                    } else {
                                        arrayCadena[i] = valorCadena[i + 1]
                                    }
                                }
                            }
                            archivoUpdate += valorCadena[0] + "," + arrayCadena[0] + "," + arrayCadena[1] + "," + arrayCadena[2] + "," + arrayCadena[3] + "," + arrayCadena[4] + "\n"
                        }
                    } while (opcionUpdate == true)
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("ALBÚM NO ENCONTRADO")
            } else {
                File("src/main/resources/text/album.txt").printWriter().use { out -> out.print(archivoUpdate) }
                println("ALBÚM ACTUALIZADO")
            }
        }

        fun updateListaCanciones(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/album.txt")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        if (i == valorCadena.size - 1) {
                            newLista += valorCadena[i]
                        } else {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return newLista + "," + lista
        }

        fun deleteListaCanciones(lista: String, id: Int): String {
            var newLista = ""
            var path = Paths.get("src/main/resources/text/album.txt")
            var splitListaParam = lista.split(",")
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[0].toInt() == id) {
                    for (i in 5..valorCadena.size - 1) {
                        var bandera = false
                        for (j in 0..splitListaParam.size - 1) {
                            if (valorCadena[i] != splitListaParam[j]) {
                                bandera = true
                            } else {
                                bandera = false
                                break
                            }
                        }
                        if (bandera == true) {
                            newLista += valorCadena[i] + ","
                        }
                    }
                }
            }
            return removeLastNchars(newLista, 1)
        }

        fun removeLastNchars(str: String, n: Int): String {
            return str.substring(0, str.length - n)
        }

        fun deleteAlbum(nombreAlbum: String) {
            //Leer archivo
            var path = Paths.get("src/main/resources/text/album.txt")
            var flag = false
            var archivoUpdate = ""
            Files.lines(path, Charsets.UTF_8).forEach {
                var valorCadena = it.split(",")
                if (valorCadena[1] == nombreAlbum) {
                    println("ALBÚM ELIMINADO")
                    flag = true
                } else {
                    archivoUpdate += it + "\n"
                }
            }
            if (!flag) {
                println("ALBÚM NO ENCONTRADO")
            } else {
                File("src/main/resources/text/album.txt").printWriter().use { out -> out.print(archivoUpdate) }
            }
        }

        fun getNumAlbum(): Int {
            var path = Paths.get("src/main/resources/text/album.txt")
            var numTotal = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                numTotal += 1
            }
            return numTotal
        }

        fun setArrayListCancAlbum(arrayCanciones: Array<Int>): ArrayList<Cancion> {
            var path = Paths.get("src/main/resources/text/cancion.txt")
            var listaCanciones: ArrayList<Cancion> = ArrayList()
            var i = 0
            Files.lines(path, Charsets.UTF_8).forEach {
                var stringSplit = it.split(",")
                if (i < arrayCanciones.size) {
                    if (stringSplit[0] == arrayCanciones[i].toString()) {
                        var splitFecha = stringSplit[4].split("-")
                        var cancionAux = Cancion(
                            stringSplit[0].toInt(),
                            stringSplit[1],
                            stringSplit[2],
                            stringSplit[3].toDouble(),
                            Date(splitFecha[0].toInt(), splitFecha[1].toInt(), splitFecha[2].toInt())
                        )
                        listaCanciones.add(cancionAux)
                        i++
                    }
                }
            }
            return listaCanciones
        }
    }

    fun insertAlbum(sizeArrayCanciones: Int) {
        //Enviar al archivo
        var path = Paths.get("src/main/resources/text/album.txt")
        val formato = SimpleDateFormat("yyyy-MM-dd")
        var data =
            this.idAlbum.toString() + "," + this.nombreAlbum + "," + this.autorAlbum + "," + formato.format(this.fecha) + "," + this.estadoFavorito + ","
        var i = 1
        for (item in this.listaCanciones!!) {
            if (i < sizeArrayCanciones) {
                data += item.getidCancion().toString() + ","
            } else {
                data += item.getidCancion().toString()
            }
            i++
        }
        data += "\n"
        try {
            Files.write(path, data.toByteArray(), StandardOpenOption.APPEND)
            println("ALBÚM AGREGADO\n")
        } catch (e: IOException) {
            println("Fallo al ingresar albúm al archivo")
        }
    }
}