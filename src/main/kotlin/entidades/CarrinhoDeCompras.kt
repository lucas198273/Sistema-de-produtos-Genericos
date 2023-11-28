package entidades

import Gerenciador
import Produto
import java.math.BigDecimal

class CarrinhoDeCompras {
    val itens: MutableList<Produto> = mutableListOf()
    var totalComDesconto: Double? = null
    var totalSemDesconto: Double? = null
    private val gerenciador: Gerenciador = Gerenciador()



    fun obterProdutoDoEstoquePorID(id: Int): Produto? {
        val produto = gerenciador.consultaPorId(id)
        if (produto != null) {
            return produto
        } else {
            println("Produto com ID $id não encontrado no estoque.")
            return null
        }
    }

    fun adicionarAoCarrinho(idProduto: Int) {
        // Substitua este bloco por sua lógica para obter o produto com base no ID.
        val produto = obterProdutoDoEstoquePorID(idProduto)

        if (produto != null) {
            val valorProduto = BigDecimal(produto.valorProduto.toString())
            val porcentagemDesconto = BigDecimal("0.10") // Exemplo: 10% de desconto
            val desconto = valorProduto.multiply(porcentagemDesconto)
            val valorComDesconto = valorProduto.subtract(desconto)

            produto.aplicarDesconto(valorComDesconto.toDouble())
            itens.add(produto)
        } else {
            println("Produto com ID $idProduto não encontrado.")
        }
    }

    fun removerProdutoPorCodigo(codigoRemocao: Int) {
        val produtoRemovido = itens.firstOrNull { it.codigoIdentificacao == codigoRemocao }

        if (produtoRemovido != null) {
            itens.remove(produtoRemovido)
            println("Produto removido com sucesso.")
        } else {
            println("Produto com código $codigoRemocao não encontrado no carrinho.")
        }
    }


    fun obterItens(): List<Produto> {
        val listaProdutos = mutableListOf<Produto>()
        itens.forEach { it ->
            println("Produto: ${it.nome}\nValor Produto: ${it.valorProduto}\nPromoção: ${it.promocao}\n")
            listaProdutos.add(it)
        }
        return listaProdutos
    }




    fun calcularValorTotalNoCarrinho(): Double {
        val somaComDesconto = itens.mapNotNull { it.obterValorPromocional() }.sum()
        val somaSemDesconto = itens.filter { it.obterValorPromocional() == null }.sumOf { it.valorProduto }

        capturarValoresParaNotaFiscal(somaSemDesconto, somaComDesconto)
        obterPagamentoDetalhado()
        return somaComDesconto + somaSemDesconto
    }

    fun capturarValoresParaNotaFiscal(totalSemDesconto: Double, totalComDesconto: Double) {
        this.totalComDesconto = totalComDesconto
        this.totalSemDesconto = totalSemDesconto
    }

    fun obterPagamentoDetalhado(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Total com desconto: $totalComDesconto")
        stringBuilder.append("Total sem desconto: $totalSemDesconto")
        return stringBuilder.toString()
    }


    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Carrinho de Compras:\n")

        itens.forEach { produto ->
            stringBuilder.append("Produto: ${produto.nome}, Preço sem desconto: ${produto.valorProduto}\n")
            stringBuilder.append("Valor Promocional ${produto.obterValorPromocional()}\n")
        }

        stringBuilder.append("Valor Total: ${calcularValorTotalNoCarrinho()}\n")

        return stringBuilder.toString()
    }
}
