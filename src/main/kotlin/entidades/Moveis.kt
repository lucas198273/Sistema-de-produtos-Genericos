import entidades.Promocoes
import java.util.Date

class Moveis(
    codigoIdentificacao: Int,
    nome: String,
    dataValidade: Date?,
    valorProduto: Double,
    promocao: Promocoes,
    valorDesconto: Double? = null
) : Produto(codigoIdentificacao, nome, dataValidade, valorProduto, promocao) {

    init {
        if (valorDesconto != null) {
            aplicarDesconto(valorDesconto)
        }
    }
}
