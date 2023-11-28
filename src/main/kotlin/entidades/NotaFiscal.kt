package entidades

import Produto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NotaFiscal(

    val dataHora : LocalDateTime ,
    val cliente: Cliente,
    val valorDaCompra : Double,
    val formaDePagamento: TiposPagamento,
    val mercadorias: List<Produto>
){
    override fun toString(): String {
        return "Nota Fiscal:\n" +
                "Data e Hora: $dataHora\n" +
                "Cliente: $cliente\n" +
                "Valor da Compra: $valorDaCompra\n" +
                "Forma de Pagamento: $formaDePagamento\n" +
                "Mercadorias:\n" +
                mercadorias.joinToString("\n")  // Aqui, unimos as informações de cada mercadoria
    }
}
