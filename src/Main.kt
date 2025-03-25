import javax.swing.text.StyledEditorKit.BoldAction

//Variavel Global
var listaDeConvidados = mutableListOf<Convidado>()

fun main() {
    menu()
}

private fun menu() {
    do {
        println("1- CRIAR")
        println("2- LISTAR")
        println("3- EDITAR")
        println("4- EXCLUIR")
        println("5- BUSCAR")
        println("0- SAIR")
        val op = readln()//VALIDAR!

        when (op.toInt()) {//Opções do menu
            1 -> criar()
            2 -> listar()
            3 -> editar()
            4 -> excluir()
            5 -> buscar()
            0 -> print("Saindo...")
        }
    } while (op.toInt() != 0)
}

private fun criar() {
    println("CRIAR")
    print("Nome do convidado: ")
    val nome = readln()

    print("Qual o seu presente: ")
    val presente = readln()

    print("Alguma restrição alimentar? ")
    val alimentar = readln()
    val regex = Regex("^[MF]$", RegexOption.IGNORE_CASE)

    var sexo: String
    do {
        print("Sexo: M ou F: ")
        sexo = readln().trim().uppercase()
    } while (!regex.matches(sexo)) // Valida a entrada com regex

    when (sexo) {
        "M" -> {
            val homem = Homem()
            homem.nome = nome
            homem.restricao = alimentar
            homem.vestuario = presente

            listaDeConvidados.add(homem)
        }

        "F" -> {
            val mulher = Mulher()
            mulher.nome = nome
            mulher.restricao = alimentar
            mulher.brinquedo = presente

            listaDeConvidados.add(mulher)
        }
    }
}
private fun listar(){
    var validar = validarlista()
    if(validar){
        println("Listando...")
        var i = 0
        listaDeConvidados.forEach { convidado ->
            println("${i++}; nome: ${convidado.nome}: alimentação: ${convidado.restricao}: presença: ${convidado.presenca}")
        }
    }
}
private fun editar(){
    var validar = validarlista()
    if (validar) {
        listar()
        println("Qual posição deseja alterar a presença:")
        val posicao = readln().toInt()

        println("Essa pessoa vai ou não na festa?")
        val resposta = readln()
        when (resposta.uppercase()) {
            "S" -> {
                listaDeConvidados[posicao].presenca = true
            }

            "N" -> {
                listaDeConvidados[posicao].presenca = false
            }
        }
    }
}
private fun excluir(){
    var validar = validarlista()
    if (validar) {
    listar()
    println("Qual posição deseja alterar a presença:")
    val posicao = readln().toInt()
    listaDeConvidados.removeAt(posicao)
    }
}

private fun validarlista() : Boolean{
    if (listaDeConvidados.isEmpty()){
        println("Sem convidados cadastrados")
        return false
    }
    return true
}

private fun buscar(){
    listar()
    if(validarlista()){
        println("Por quem voce deseja buscar:")
        var i = 0
        val busca = readln().toRegex(RegexOption.IGNORE_CASE)
        listaDeConvidados.forEach { convidado ->
            if(convidado.nome.contains(busca)){
                println("Nome: ${convidado.nome}, Posição: $i")
            }
        }
        i++
    }
}