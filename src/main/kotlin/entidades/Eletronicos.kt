import entidades.Promocoes
import java.util.Date

class Eletronicos(
    codigoIdentificacao: Int,
    nome: String,
    dataValidade: Date?,
    valorProduto: Double,
    promocao: Promocoes,
    valorDesconto: Double? = null
) : Produto(codigoIdentificacao, nome, dataValidade, valorProduto, promocao,valorDesconto) {

    init {
        if (valorDesconto != null) {
            aplicarDesconto(valorDesconto)
        }
    }
}
