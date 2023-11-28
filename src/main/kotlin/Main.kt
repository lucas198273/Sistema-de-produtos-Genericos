import entidades.*
import java.text.SimpleDateFormat
import java.util.*


fun main() {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val gerenciadorProdutos = Gerenciador()

    val scanner = Scanner(System.`in`)

    val celular = Eletronicos(1, "Celular Smartphone", sdf.parse("05/12/2022"), 599.99, Promocoes.PROMOCAO_RELAMPAGO)
    val mesa = Moveis(2, "Mesa de Jantar", sdf.parse("28/09/2023"), 299.99, Promocoes.PROMOCAO_BLACK)
    val livro = Eletronicos(3, "Livro de Romance", null, 29.90, Promocoes.NULL)
    val bicicleta = Eletronicos(4, "Bicicleta Urbana", sdf.parse("15/06/2023"), 349.99, Promocoes.PROMOCAO_BLACK)
    val sofa = Moveis(5, "Sofá de Couro", sdf.parse("20/11/2023"), 799.99, Promocoes.PROMOCAO_BLACK)
    val fone = Eletronicos(6, "Fone de Ouvido Bluetooth", null, 49.90, Promocoes.NULL)
    val televisao = Eletronicos(7, "Televisão 4K", sdf.parse("25/03/2023"), 499.99, Promocoes.PROMOCAO_BLACK)
    val cadeira = Moveis(8, "Cadeira de Escritório", sdf.parse("10/08/2023"), 129.99, Promocoes.PROMOCAO_RELAMPAGO)
    val camiseta = Eletronicos(9, "Camiseta Casual", null, 19.99, Promocoes.NULL)
    val aspirador = Eletronicos(10, "Aspirador de Pó", sdf.parse("30/04/2023"), 89.99, Promocoes.NULL)

    gerenciadorProdutos.adicionarItem(celular.codigoIdentificacao, celular)
    gerenciadorProdutos.adicionarItem(mesa.codigoIdentificacao, mesa)
    gerenciadorProdutos.adicionarItem(livro.codigoIdentificacao, livro)
    gerenciadorProdutos.adicionarItem(bicicleta.codigoIdentificacao, bicicleta)
    gerenciadorProdutos.adicionarItem(sofa.codigoIdentificacao, sofa)
    gerenciadorProdutos.adicionarItem(fone.codigoIdentificacao, fone)
    gerenciadorProdutos.adicionarItem(televisao.codigoIdentificacao, televisao)
    gerenciadorProdutos.adicionarItem(cadeira.codigoIdentificacao, cadeira)
    gerenciadorProdutos.adicionarItem(camiseta.codigoIdentificacao, camiseta)
    gerenciadorProdutos.adicionarItem(aspirador.codigoIdentificacao, aspirador)

    gerenciadorProdutos.abrirTema("Produtos menores que 300")
    val produtosMenoresQue300: List<Produto> = gerenciadorProdutos.filtrarValorMenorque(300.0)

    produtosMenoresQue300.forEach { produto ->
        println("Produto: ${produto.nome}, Status Promocional: ${produto.obterValorPromocional()}, Valor: ${produto.valorProduto}")
    }

    gerenciadorProdutos.abrirTema("Produtos Maiores Que 35")

    val nomesProdutos: List<String> = gerenciadorProdutos.nomeTodosProdutos()
    println(nomesProdutos)
    println("Quantidade de Produtos: ${nomesProdutos.size}")

    val cliente = Cliente("Lucas Dias")
    val carrinhoCliente = cliente.carrinho

    carrinhoCliente.adicionarAoCarrinho(fone.codigoIdentificacao)
    carrinhoCliente.adicionarAoCarrinho(livro.codigoIdentificacao)
    carrinhoCliente.adicionarAoCarrinho(camiseta.codigoIdentificacao)

    println("Valor do Fone: ${fone.valorProduto}")
    println("Valor do Livro: ${livro.valorProduto}")
    println("Valor da Camiseta: ${camiseta.valorProduto}")

    gerenciadorProdutos.abrirTema("Carrinho")
    println(carrinhoCliente.toString())


    println(camiseta.valorProduto)
    println(fone.valorProduto)

    gerenciadorProdutos.abrirTema("TESTANDO APLICAÇÃO DE VALOR PROMOCIONAL")
    println(carrinhoCliente.toString())
    println(carrinhoCliente.calcularValorTotalNoCarrinho())

    carrinhoCliente.calcularValorTotalNoCarrinho()



    fun main() {
        gerenciadorProdutos.abrirTema("SISTEMA INICIADO")
        println("SEJA BEM VINDO AO NOSSO SISTEMA DE VENDAS")
        println("DIGITE SEU NOME ")
        var nomeConfirmado: String? = null

        while (nomeConfirmado == null) {
            val nome: String? = readLine()

            nome?.let { it ->
                println("Confirme seu nome -> $it")
                println("Aperte 0 para repetir, 1 para confirmar:")
                val c = readLine()?.toInt()
                when (c) {
                    0 -> {
                        println("==========\nCancelado\n==========")
                        // Nome não confirmado, continue pedindo o nome
                    }
                    1 -> {
                        nomeConfirmado = it
                        println("Nome confirmado: $it")
                    }
                    else -> println("Opção inválida")
                }
            }
        }

        if (nomeConfirmado != null) {
            gerenciadorProdutos.abrirTema("0 para sair do sistema \n1 para exibir todos produtos \n2 para exibir apenas produtos em promoção")
            val c = readLine()?.toInt()
            when (c) {
                0 -> println("Saindo do sistema")
                1 -> {
                    gerenciadorProdutos.obterEstoque()
                    println(
                        " OPÇÕES \n-> 0 PARA RETORNAR\n" +
                                "-> 1 PARA ADICIONAR AO CARRINHO\n" +
                                "-> 2 PARA CONSULTAR PRODUTO ESPECÍFICO\n" +
                                "-> 3 PARA CONSULTAR APENAS PRODUTOS EM PROMOÇÃO"
                    )
                    val ca = readLine()?.toInt()
                    when (ca) {
                        0 -> println("Retornando ao menu anterior")
                        1 -> {
                            print("PARA INSERIR ITEM AO CARRINHO, INDIQUE O CÓDIGO DO PRODUTO -> #")
                            val cb = readLine()?.toInt()
                            cb.let { it ->
                                carrinhoCliente.adicionarAoCarrinho(cb!!)
                                println("Produto adicionado ao carrinho.")
                            }
                        }
                        2 -> {
                            println("Consultar produto específico  ->  Informe o ID do produto que deseja consultar")
                            val cc = readLine()?.toInt()
                            cc.let { it ->
                                gerenciadorProdutos.consultaPorId(cc!!)
                            }
                        }
                        3 -> println("Consultar apenas produtos em promoção")
                        else -> println("Opção inválida")
                    }
                }

                else -> println("Opção inválida")
            }
        }
    }
}


