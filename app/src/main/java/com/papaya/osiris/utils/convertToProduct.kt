package com.papaya.osiris.utils

import com.papaya.osiris.data.Panc
import com.papaya.osiris.data.Product
import com.papaya.osiris.data.Recipe

fun convertToProduct(panc: Panc? = null, recipe: Recipe? = null, clickAction: () -> Unit): Product {
    // TODO: verify if this is the best way to handle this case
    if (panc != null && recipe != null) throw IllegalArgumentException("panc and recipe cannot be not null at the same time")

    recipe?.let {
        return Product(
            id = it.id,
            name = it.nome,
            image = it.imagem,
            description = it.descricao,
            clickAction = ClickActionImpl { clickAction() },
        )
    }

     panc?.let {
        return Product(
            id = it.id,
            name = it.nome,
            image = it.imagem,
            description = it.descricao,
            clickAction = ClickActionImpl { clickAction() }
        )
    }

    // TODO: verify if this is the best way to handle this case
    throw IllegalArgumentException("panc and recipe cannot be null at the same time")
}