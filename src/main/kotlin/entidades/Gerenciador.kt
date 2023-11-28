import entidades.*
import java.time.LocalDateTime


class Gerenciador {

    val mapaProduto: MutableMap<Int, Produto> = mutableMapOf()
    val produtosEmPromocao: MutableList<Produto> = mutableListOf()

    fun nomeTodosProdutos(): List<String> {
        return mapaProduto.values.map { it.nome }
    }
    fun abrirTema(tema: String) {
        println("==================================================")
        println("=====================$tema=======================")
        println("==================================================")
    }
    fun filtrarValorMenorque(valorFiltragem: Double): List<Produto> {
        return mapaProduto.values.filter { produto ->
            produto.valorProduto <= valorFiltragem
        }
    }
    fun filtrarValorMaiorque(valorFiltragem: Double): List<Produto> {
        return mapaProduto.values.filter { produto ->
            produto.valorProduto >= valorFiltragem
        }
    }
    fun filtrarPromocionais(promocao: Promocoes): List<Produto> {
        return mapaProduto.values.filter { produto ->
            produto.obterStatusPromocional() == promocao
        }
    }
    fun obterEstoque() {
        mapaProduto.forEach { (chave, produto) ->

            println("Codigo: #$chave, Produto: ${produto.nome}\n Valor ${produto.valorProduto}       Status Promocional : ${produto.obterStatusPromocional()}\n   ")
            println("======================================")
        }
    }
    fun adicionarItem(codigo : Int, produto: Produto) {
    mapaProduto[codigo] = produto
    }
    fun removerItem(id: Int) {
        mapaProduto.remove(id)
    }
    fun consultaPorId(id: Int): Produto? {
        try {
            val produto = mapaProduto[id]
            if (produto == null) {
                throw NoSuchElementException("Produto com ID $id não encontrado.")
            }
            println("======================================")
            println("Produto: ${produto.toString()}")
            println("======================================")
            return produto
        } catch (e: NoSuchElementException) {
            println("Erro ao consultar o produto: ${e.message}")
            return null
        }
    }
    fun addListaPromocionais(produto: Produto) {
        val controle = produto.obterStatusPromocional()
        when (controle) {
            Promocoes.PROMOCAO_RELAMPAGO, Promocoes.PROMOCAO_BLACK -> {
                println("PRODUTO EM PROMOÇÃO : ${produto.obterStatusPromocional()}")
                produtosEmPromocao.add(produto)
            }

            Promocoes.NULL -> {
                println("PRODUTO NÃO ESTÁ EM PROMOÇÃO, NÃO É POSSÍVEL ADICIONÁ-LO À LISTA DE PROMOCIONAIS")
            }
        }
        fun exibirDetalhesDoPagamento(valorTotal: Double, valorComDesconto: Double?) {
            println("Confirme o pagamento:")
            println("Desconto Foi Aplicado Após O Produto Ser Adicionado Ao Carrinho".uppercase())
            println("Valor Total: $valorTotal")
            println("Valor com Desconto: $valorComDesconto")
        }

   /*     fun criarNotaFiscal(cliente: Cliente, valorTotal: Double, tipoPagamento: TiposPagamento, ): CarrinhoDeCompras): NotaFiscal {
            return NotaFiscal(LocalDateTime.now(), cliente, valorTotal, tipoPagamento, carrinho.obterItens())
        }
*/
      /*  fun processarPagamento(cliente: Cliente, tipoPagamento: TiposPagamento): NotaFiscal {
            val carrinhoCliente = cliente.carrinho
            val valorTotal = carrinhoCliente.calcularValorTotalNoCarrinho()
            val valorComDesconto = carrinhoCliente.totalSemDesconto
            val notaFiscal =  criarNotaFiscal(cliente, valorTotal, tipoPagamento, carrinhoCliente)

            exibirDetalhesDoPagamento(valorTotal, valorComDesconto)

            return notaFiscal
        }*/

        fun aplicarDesconto(valorTotal: Double, tipoPagamento: TiposPagamento): Double {
            return when (tipoPagamento) {
                TiposPagamento.CREDITO -> valorTotal * 0.95
                TiposPagamento.PIX -> valorTotal * 0.97
                TiposPagamento.DEBITO -> valorTotal * 0.98
                else -> valorTotal
            }
        }








        fun aplicarPromocaoEmProduto(produto: Produto, promocao: Promocoes) {
            val statusPromocionalProduto: Promocoes = produto.obterStatusPromocional()

            when (statusPromocionalProduto) {
                Promocoes.PROMOCAO_RELAMPAGO, Promocoes.PROMOCAO_BLACK -> {
                    println("PROMOÇÃO ATUAL DO PRODUTO : $statusPromocionalProduto")
                    println("NOVO STATUS PROMOCIONAL DO PRODUTO: $promocao")
                    println("APERTE:")
                    println("0 PARA CANCELAR TROCA")
                    println("1 PARA CONFIRMAR A TROCA")

                    val controle = readLine()?.toIntOrNull()

                    controle?.let { opcao ->
                        when (opcao) {
                            0 -> {
                                // O usuário cancelou a troca, não é necessário fazer nada
                                println("Troca cancelada.")
                            }

                            1 -> {
                                // O usuário confirmou a troca, atualize o status promocional
                                produto.definirStatusPromocional(promocao)
                                println("Troca confirmada. Novo status promocional: $promocao")
                            }

                            else -> {
                                println("Opção inválida. Nada foi alterado.")
                            }
                        }
                    }
                }

                Promocoes.NULL -> {
                    // Lógica para lidar com a promoção NULL, se necessário
                }
            }
        }
    }
}
