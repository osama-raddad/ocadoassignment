package com.osama.domain.categorized_products.util

import com.osama.data_model.cluster.Cluster
import com.osama.data_model.cluster.Clusters
import com.osama.data_model.cluster.Item
import com.osama.domain.entity.CategorizedProduct
import com.osama.domain.entity.Transaction
import com.osama.repository.Result

internal class ResultClustersToCategorizedProductsMapper(
    private val categorizedProductFactory: CategorizedProductFactory

) {
    fun map(products: Result<Clusters?>): Transaction<Map<String, List<CategorizedProduct>>?> =
        when (products) {
            is Result.Loaded -> buildTransactionFromNullableResult(products.result)
            is Result.Error -> {
                Transaction.Error(products.code, products.text)
            }
        }

    private fun buildTransactionFromNullableResult(result: Clusters?) = when (result != null) {
        true -> Transaction.Ready(processClustersList(result))
        false -> Transaction.Ready(null)
    }

    private fun processClustersList(result: Clusters) =
        castToMap(mapTagToCluster(groupClustersByTag(result)))

    private fun castToMap(mapTagToCluster: List<Pair<String, List<CategorizedProduct>>>) =
        mapTagToCluster.toMap()


    private fun mapTagToCluster(clusterMap: Map<String, List<Cluster>>) = clusterMap.map {
        it.key to extractClusterListFromClusterObject(it.value)
    }

    private fun groupClustersByTag(clusters: Clusters) = clusters.clusters.groupBy { it.tag }

    private fun extractClusterListFromClusterObject(value: List<Cluster>) = value.flatMap {
        extractItemsFormClusterAndConvertTheListToCategorizedProducts(it)
    }

    private fun extractItemsFormClusterAndConvertTheListToCategorizedProducts(it: Cluster) =
        it.items.map { convertItemToCategorizedProduct(it) }

    private fun convertItemToCategorizedProduct(item: Item) = categorizedProductFactory(item)


}
