import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

enum class Promocoes {
    NORMAL, PROMOCAO
}

open class Produto(
    val codigoIdentificacao: Int,
    val nome: String,
    val dataValidade: Date? = null,
    var valorProduto: Double,
    var promocao: Promocoes = Promocoes.NORMAL
) {
    private var valorComDesconto: Double? = null

    constructor(
        codigoIdentificacao: Int,
        nome: String,
        dataValidade: Date?,
        valorProduto: Double,
        promocao: Promocoes,
        valorDesconto: Double? = null
    ) : this(
        codigoIdentificacao,
        nome,
        dataValidade,
        valorProduto,
        promocao
    ) {
        valorDesconto.let {it ->
            aplicarDesconto(it!!)
        }
    }

    fun obterStatusPromocional(): Promocoes {
        return promocao
    }

    fun obterValorPromocional(): Double? {
        return valorComDesconto
    }

    fun aplicarDesconto(valorComDesconto: Double) {
        this.valorComDesconto = valorComDesconto
    }

    fun definirStatusPromocional(novaPromocao: Promocoes) {
        this.promocao = novaPromocao
    }

    fun verificarValidade() {
        val dataAtual = LocalDate.now()
        dataValidade?.let { dataValidade ->
            val dataValidadeLocalDate = dataValidade.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

            if (dataAtual.isBefore(dataValidadeLocalDate)) {
                println("O produto '$nome' está dentro da validade.")
            } else if (dataAtual.isEqual(dataValidadeLocalDate)) {
                println("O produto '$nome' vence hoje.")
            } else {
                println("O produto '$nome' está vencido.")
            }
        } ?: run {
            println("Não foi especificada uma data de validade para o produto '$nome'.")
        }
    }

    fun obterCodigo(): Int {
        return codigoIdentificacao
    }

    override fun toString(): String {
        val dataValidadeString = dataValidade?.toString() ?: "N/A"
        val valorComDescontoString = valorComDesconto?.toString() ?: "N/A"

        return """
            ==============================
            Produto: $nome (Código: #$codigoIdentificacao)
            Data de Validade: $dataValidadeString
            Valor Promocional: $valorComDescontoString
            ==============================
        """.trimIndent()
    }
}
