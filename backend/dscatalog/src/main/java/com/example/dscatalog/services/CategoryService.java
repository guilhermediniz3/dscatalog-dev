package com.example.dscatalog.services;

import com.example.dscatalog.entities.Category;
import com.example.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    /*     @Transactional(readOnly = true) → garante que ocorra a comunicação acid com o banco e sua propriedade readOnly = true evita o travamento do banco de dados lock
     o processo de lock é:Exemplo prático: Você está emitindo uma Nota Fiscal no sistema que irá consumir o valor que o Cliente possui de Crédito para compras. No momento da geração da Nota Fiscal a operação que você está realizando no sistema irá criar um lock (uma trava) na linha da tabela que armazena essa informação do Crédito do Cliente até que essa linha seja atualizada (possivelmente no final da geração da NF, quando o processo foi concluído). Esse lock é necessário para que, se um outro usuário do sistema efetuar uma outra NF para o mesmo Cliente no mesmo momento, o banco de dados possa garantir que o dado do Valor de Crédito seja atualizado sequencialmente, diminuindo corretamente o valor de Crédito devido a emissão das duas NF.
O lock é comumente gerado na utilização dos comandos UPDATE, SELETE e SELECT (com indicativo de FOR UPDATE/ROWLOCK, isso porque um select por padrão não causa um lock, pois é somente uma busca de dados, mas se a busca tem por objetivo garantir que esse registro seja travado para ser atualizado em breve, então o indicativo FOR UPDATE/ROWLOCK travará o registro). https://suporte.senior.com.br/hc/pt-br/articles/4409373013012-IT-SERVICES-Performance-Lentid%C3%A3o-Travamentos-O-que-s%C3%A3o-locks-e-deadlocks-no-processo-de-comunica%C3%A7%C3%A3o-do-sistema-com-o-banco-de-dados#:~:text=O%20lock%20nada%20mais%20%C3%A9,tabela%20por%20usu%C3%A1rio%2Fsess%C3%B5es%20diferentes.
O lock é finalizado quando a transação do banco que ocasionou o lock é finalizada (commit) ou desfeita (rollback).
 o readOnly= true é usado somente em operações que são para somente leitura.*/
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();

    }


}
