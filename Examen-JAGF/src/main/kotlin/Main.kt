import java.text.SimpleDateFormat
import java.util.Date


fun main(args: Array<String>) {
    //Agregando Canciones
    //var cancion1 = Cancion(1, "Moscow Mule", "Bad Bunny", 10.0, Date(2020,10,2))
    //var cancion2 = Cancion(2, "Animals", "Maroon 5", 9.5, Date(2014,10,10))
    //var cancion3 = Cancion(3, "Natural", "Imagine Dragons", 10.0, Date(2018,10,20))
    //var cancion4 = Cancion(4, "Counting Stars", "One Republic", 10.0, Date(2013,5,1))
    //var cancion5 = Cancion(5, "RUMBATÓN", "Daddy Yankee", 10.0, Date(2022,10,2))

    //Agregando Albumes
    //var album1 = Album(1, "Un Verano Sin Ti", "Bad Bunny", Date(2022, 0,1), true)
    //var album2 = Album(2, "V", "Maroon 5", Date(2014, 0,1), true)
    //var album3 = Album(3, "Origins (Deluxe)", "Imagine Dragons", Date(2018, 0,1), false)
    //var album4 = Album(4, "Native", "One Republic", Date(2013, 0,1), true)
    //var album5 = Album(5, "LEGENDADDY", "Daddy Yankee", Date(2022, 0,1), true)

    //Menú
    do {
        var opcionUsuario = false
        println(
            "EXAMEN APLICACIONES MÓVILES - CRUD -> CANCIÓN - ÁLBUM:" +
                    "\n1) Canciones" +
                    "\n2) Álbumes" +
                    "\n3) Salir"
        )
        var opTabla = readln().toInt()
        if (opTabla != 3) {
            var opcionAux = false
            var textoConsola = ""
            if (opTabla == 1) {
                textoConsola += "Elija una opción:" +
                        "\n1) Ingresar nueva canción" +
                        "\n2) Ver canciones" +
                        "\n3) Actualizar canción" +
                        "\n4) Eliminar canción" +
                        "\n5) Volver"
            } else {
                textoConsola += "Elija una opción:" +
                        "\n1) Ingresar nuevo álbum" +
                        "\n2) Ver álbumes" +
                        "\n3) Actualizar álbum" +
                        "\n4) Eliminar álbum" +
                        "\n5) Volver"
            }
            while (!opcionAux) {
                println(textoConsola)
                var opcionCrud = readln().toInt()
                when (opcionCrud) {
                    (1) -> {
                        if (opTabla == 1) {
                            print("Nombre canción: ")
                            var nombre = readln()
                            print("Autor canción: ")
                            var autor = readln()
                            print("Calificación: ")
                            var calificacion = readln().toDouble()
                            print("Fecha publicación (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())

                            var newCancion =
                                Cancion(Cancion.getNumCanciones() + 1, nombre, autor, calificacion, fechaAux)
                            newCancion.insertCancion()

                        } else {
                            print("Nombre álbum: ")
                            var nombre = readln()
                            print("Autor álbum: ")
                            var autor = readln()
                            print("Fecha publicación (AAAA-MM-DD): ")
                            var fecha = readln()
                            var fechaSplit = fecha.split("-")
                            var fechaAux: Date =
                                Date(fechaSplit[0].toInt() - 1900, fechaSplit[1].toInt() - 1, fechaSplit[2].toInt())
                            print("Estado favorito 1)SI 2)NO: ")
                            var estadoAux = readln().toInt()
                            var estado: Boolean

                            if (estadoAux == 1) {
                                estado = true
                            } else {
                                estado = false
                            }

                            println("\n***LISTA DE CANCIONES DISPONIBLES***")
                            Cancion.selectCancion()

                            print("Seleccione las canciones a agregar al álbum (1,2,...): ")
                            var stringCanciones = readLine().toString()
                            var splitCanciones = stringCanciones.split(",")
                            var intCancionesArray = splitCanciones.map { it.toInt() }.toTypedArray()
                            var listaAlbum: ArrayList<Cancion> = Album.setArrayListCancAlbum(intCancionesArray)

                            var newAlbum = Album(Album.getNumAlbum() + 1, nombre, autor, fechaAux, estado, listaAlbum)
                            newAlbum.insertAlbum(intCancionesArray.size)
                        }
                    }

                    (2) -> {
                        if (opTabla == 1) {
                            println("LISTA DE CANCIONES:")
                            Cancion.selectCancion()
                        } else {
                            println("LISTA DE ÁLBUMES")
                            Album.selectAlbum()
                        }
                    }

                    (3) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre de la canción ha actualizar: ")
                            var searchCancion = readln()
                            Cancion.updateCancion(searchCancion)
                        } else {
                            print("Ingrese el nombre del álbum ha actualizar: ")
                            var searchAlbum = readln()
                            Album.updateAlbum(searchAlbum)
                        }
                    }

                    (4) -> {
                        if (opTabla == 1) {
                            print("Ingrese el nombre de la canción ha eliminar: ")
                            var searchCancion = readln()
                            Cancion.deleteCancion(searchCancion)
                        } else {
                            print("Ingrese el nombre del álbum ha eliminar: ")
                            var searchAlbum = readln()
                            Album.deleteAlbum(searchAlbum)
                        }
                    }

                    (5) -> {
                        opcionAux = true
                    }
                }
            }
        } else {
            opcionUsuario = true
        }
    } while (!opcionUsuario)

}